package com.fon.bg.ac.rs.biblioteka.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "zaduzenje")
public class Issue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "id of issue")
  private Long idZaduzenje;

  @Column(nullable = false)
  @ApiModelProperty(value = "date of issue")
  private Date datumZaduzenja;

  @Column(nullable = false)
  @ApiModelProperty(value = "return deadline")
  private Date rokZaPovratak;

  @Column
  @ApiModelProperty(value = "return date")
  private Date DatumPovratka;

  @Column(nullable = false)
  @ApiModelProperty(value = "returned")
  private boolean vracena;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "idRezervacija")
  @ApiModelProperty(value = "reservation")
  @NotNull
  private Reservation rezervacija;
}

