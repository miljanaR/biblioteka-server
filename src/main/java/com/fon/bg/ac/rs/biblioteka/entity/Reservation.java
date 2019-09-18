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
@Table(name = "rezervacija")
public class Reservation {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "id of reservation")
  private Long idRezervacija;

  @Column(nullable = false)
  @ApiModelProperty(value = "date of reservation")
  private Date datumRezervisanja;

  @Column(nullable = false)
  @ApiModelProperty(value = "expiry date of reservation")
  private Date datumIstekaRezervacije;

  @Column
  @ApiModelProperty(value = "accomplished")
  private boolean ostvarena;

  @ManyToOne
  @NotNull
  @ApiModelProperty(value = "copy")
  private Copy primerak;

  @ManyToOne
  @NotNull
  @ApiModelProperty(value = "user")
  private User user;
}

