package com.fon.bg.ac.rs.biblioteka.service;

import com.fon.bg.ac.rs.biblioteka.dto.UpdateUserDto;
import com.fon.bg.ac.rs.biblioteka.dto.UserDto;
import com.fon.bg.ac.rs.biblioteka.entity.User;
import com.fon.bg.ac.rs.biblioteka.exc.NotFoundException;
import com.fon.bg.ac.rs.biblioteka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CityService cityService;

  @Autowired
  private FacultyService facultyService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User save(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.saveAndFlush(user);
  }

  public User update(User user) {
    return userRepository.save(user);
  }

  public User find(String userName) {
    return userRepository.findOneByUsername(userName);
  }

  public User createUser(UserDto dtoUser) {
    User user = new User();
    user.setUsername(dtoUser.getUsername());
    user.setImePrezime(dtoUser.getImePrezime());
    user.setDatumRodjenja(dtoUser.getDatumRodjenja());
    user.setGrad(cityService.findById(dtoUser.getPostanskiBroj()));
    user.setFakultet(facultyService.findById(dtoUser.getIdFakultet()));
    user.setPassword(dtoUser.getPassword());
    return user;
  }

  public void updateUser(UpdateUserDto userDto) {

    User userToUpdate = userRepository.getOne(userDto.getIdKorisnik());
    userToUpdate.setImePrezime(userDto.getImePrezime());
    userToUpdate.setDatumRodjenja(userDto.getDatumRodjenja());
    userToUpdate.setFakultet(facultyService.findById(userDto.getIdFakultet()));
    userToUpdate.setGrad(cityService.findById(userDto.getPostanskiBroj()));
    userToUpdate.setUsername(userDto.getUsername());
    userRepository.save(userToUpdate);
  }

  public List<User> getAllUsers() {

    List<User> userList = userRepository.findAllUsers();

    if (userList.size() > 0) {
      return userList;
    } else {
      return new ArrayList<User>();
    }
  }

  public User getUserById(Long id) throws NotFoundException {
    Optional<User> user = userRepository.findUserById(id);

    if (user.isPresent()) {
      return user.get();
    } else {
      throw new NotFoundException("No user record exist for given id");
    }
  }

  public User getUserByUsername(String username) throws Exception {
    User user = userRepository.findOneByUsername(username);

    if (user != null)
      return user;
    else
      throw new Exception("No clan record exist for given username");
  }

  public void updateNumberOfCanceled(User user) {
    user.setBrOtkazanih(user.getBrOtkazanih() + 1);
    userRepository.save(user);
  }
}
