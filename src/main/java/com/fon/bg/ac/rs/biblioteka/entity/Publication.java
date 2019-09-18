package com.fon.bg.ac.rs.biblioteka.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "publikacija")
public class Publication implements Serializable {

  @Id
  @Column
  @ApiModelProperty(value = "isbn number of publication ")
  private String isbn;

  @Column(nullable = false)
  @ApiModelProperty(value = "title of publication")
  private String nazivPublikacije;

  @ManyToOne
  @ApiModelProperty(value = "language of publication")
  @NotNull
  private Language jezik;

  @Column(nullable = false)
  @ApiModelProperty(value = "author of publication")
  private String autor;

  @Column(nullable = false)
  @ApiModelProperty(value = "year of publication")
  private int godinaIzdanja;


}
