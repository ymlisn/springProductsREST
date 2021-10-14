package com.example.desafiojavaspringboot.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude="id")
@JsonPropertyOrder({"id", "name", "description", "price"})
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Integer id;
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private Double price;


}
