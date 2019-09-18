package com.fon.bg.ac.rs.biblioteka.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fakultet")
public class Faculty {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "id of faculty")
  private Long idFakultet;

  @Column(name = "nazivFakulteta", nullable = false)
  @ApiModelProperty(value = "name of faculty")
  private String nazivFakulteta;

  public Faculty(Long idFakultet, String nazivFakulteta) {
    this.idFakultet = idFakultet;
    this.nazivFakulteta = nazivFakulteta;
  }

  public Faculty() {
  }
}
