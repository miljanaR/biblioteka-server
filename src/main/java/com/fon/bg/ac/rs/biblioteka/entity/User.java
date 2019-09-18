package com.fon.bg.ac.rs.biblioteka.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "id of user")
  private Long idKorisnika;

  @Column(unique = true, nullable = false)
  @ApiModelProperty(value = "name of user")
  private String username;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ApiModelProperty(value = "password of user")
  @Column(nullable = false)
  private String password;

  @ApiModelProperty(value = "role of user")
  @Column(nullable = false)
  private String role;

  @Column(nullable = false)
  @ApiModelProperty(value = "full name of user")
  private String imePrezime;

  @Column(nullable = false)
  @ApiModelProperty(value = "date of birth of user")
  private Date datumRodjenja;

  @ManyToOne
  @NotNull
  @ApiModelProperty(value = "place of birth of user")
  private City grad;

  @ManyToOne
  @NotNull
  @ApiModelProperty(value = "faculty of user")
  private Faculty fakultet;

  @Column(name = "brOtkazanih")
  @ApiModelProperty(value = "number of canceled reservations")
  private int brOtkazanih;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(role));
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public enum Role {USER, ADMIN}
}
