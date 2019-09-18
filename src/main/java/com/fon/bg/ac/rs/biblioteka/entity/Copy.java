package com.fon.bg.ac.rs.biblioteka.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "primerak")
public class Copy implements Serializable {

  @ManyToOne
  @ApiModelProperty(value = "publication")
  @NotNull
  public Publication publikacija;

  @Id
  @Column( nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "id of copy")
  private Long id;

  @Column( nullable = false)
  @ApiModelProperty(value = "publication")
  private Date datumStampe;

  @Column( nullable = false)
  @ApiModelProperty(value = "free")
  private boolean zaIzdavanje;
}
