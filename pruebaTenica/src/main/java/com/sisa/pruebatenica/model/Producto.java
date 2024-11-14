package com.sisa.pruebatenica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "T_PRODUCTO")
public class Producto extends BaseEntity {

    @NotNull(message = "El código del producto no puede ser nulo")
    @Size(min = 2, max = 50, message = "El código del producto debe tener entre 2 y 50 caracteres")
    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    @NotNull(message = "El nombre del producto no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre del producto debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull(message = "El precio no puede ser nulo")
    @Column(name = "precio", nullable = false)
    private double precio;

    @NotNull(message = "La cantidad no puede ser nula")
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
}