package com.fon.bg.ac.rs.biblioteka.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "jezik")
public class Language implements Serializable {

  @Id
  @Column
  @ApiModelProperty(value = "id of language")
  private Long idJezik;

  @Column( nullable = false)
  @ApiModelProperty(value = "language")
  private String nazivJezika;
}
