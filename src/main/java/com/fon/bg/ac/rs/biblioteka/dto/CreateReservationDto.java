package com.fon.bg.ac.rs.biblioteka.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateReservationDto {
  @ApiModelProperty(value = "id of copy")
  private Long primerakId;
  @ApiModelProperty(value = "id of user")
  private Long idKorisnik;
}
