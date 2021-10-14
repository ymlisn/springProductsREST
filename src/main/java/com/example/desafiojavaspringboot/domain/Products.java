package com.example.desafiojavaspringboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "Field name can not be empty")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "Field description can not be empty")
    private String description;

    @NotNull(message = "Field price can not be empty")
    @Positive(message = "Field price must be positive")
    @Column(name = "price", precision = 20, scale = 2)
    private BigDecimal price;
}
