package com.fon.bg.ac.rs.biblioteka.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "obavestenje")
public class Notification implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "id of notification")
  private Long id;
  @ApiModelProperty(value = "rtpe of notification")
  @Column(nullable = false)
  private String tip;
  @ApiModelProperty(value = "content of notification")
  @Column(nullable = false)
  private String tekst;
  @ApiModelProperty(value = "date of notification")
  private Date datum;
  @ManyToOne
  @ApiModelProperty(value = "user")
  @NotNull
  private User user;
}
