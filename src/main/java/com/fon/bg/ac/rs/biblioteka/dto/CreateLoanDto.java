package com.fon.bg.ac.rs.biblioteka.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateLoanDto {
  @ApiModelProperty(value = "id of reservation")
  private Long idRezervacije;
}
