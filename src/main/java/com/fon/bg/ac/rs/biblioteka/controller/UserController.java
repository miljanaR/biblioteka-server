package com.fon.bg.ac.rs.biblioteka.controller;

import com.fon.bg.ac.rs.biblioteka.dto.UpdateUserDto;
import com.fon.bg.ac.rs.biblioteka.dto.UserDto;
import com.fon.bg.ac.rs.biblioteka.entity.User;
import com.fon.bg.ac.rs.biblioteka.exc.CustomError;
import com.fon.bg.ac.rs.biblioteka.exc.NotFoundException;
import com.fon.bg.ac.rs.biblioteka.service.CityService;
import com.fon.bg.ac.rs.biblioteka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private CityService cityService;

  @RequestMapping(value = "/api/register", method = RequestMethod.POST)
  public ResponseEntity<?> createUser(@RequestBody UserDto dtoUser) {
    if (userService.find(dtoUser.getUsername()) != null) {
      return new ResponseEntity(
          new CustomError("user with username " + dtoUser.getUsername() + "already exist "),
          HttpStatus.CONFLICT);
    }

    User newUser = userService.createUser(dtoUser);
    newUser.setRole("USER");

    return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);
  }

  @CrossOrigin
  @RequestMapping(value = "/api/login", method = RequestMethod.GET)
  public Principal user(Principal principal) {
    return principal;
  }


  @RequestMapping(value = "/api/users", method = RequestMethod.GET)
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> list = userService.getAllUsers();
    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
  public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws Exception {
    try {
      User entity = userService.getUserById(id);
      return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    } catch (NotFoundException exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Clan Not Found", exc);
    }
  }

  @RequestMapping(value = "/api/users", method = RequestMethod.PUT)
  public void updateUser(@RequestBody UpdateUserDto userDto) {
    userService.updateUser(userDto);
  }
}