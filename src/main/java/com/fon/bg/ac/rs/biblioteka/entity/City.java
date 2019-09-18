package com.fon.bg.ac.rs.biblioteka.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "grad")
public class City {

  @Column(name = "postanskiBroj")
  @Id
  @ApiModelProperty(value = "city code")
  private Long postanskiBroj;

  @ApiModelProperty(value = "city name")
  @Column(name = "nazivGrada", nullable = false)
  private String nazivGrada;


}
