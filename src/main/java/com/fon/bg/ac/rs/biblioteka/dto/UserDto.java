package com.fon.bg.ac.rs.biblioteka.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
  @ApiModelProperty(value = "full name of user")
  private String imePrezime;
  @ApiModelProperty(value = "name of user")
  private String username;
  @ApiModelProperty(value = "date of birth of user")
  private Date datumRodjenja;
  @ApiModelProperty(value = "faculty of user")
  private Long idFakultet;
  @ApiModelProperty(value = "postal code of city")
  private Long postanskiBroj;
  @ApiModelProperty(value = "password of user")
  private String password;
}


