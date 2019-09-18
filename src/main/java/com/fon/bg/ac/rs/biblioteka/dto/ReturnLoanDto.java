package com.fon.bg.ac.rs.biblioteka.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReturnLoanDto {
  @ApiModelProperty(value = "id of loan")
  private Long idZaduzenje;
}
