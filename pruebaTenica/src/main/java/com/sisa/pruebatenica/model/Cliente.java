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
@Table(name = "T_CLIENTE")
public class Cliente extends BaseEntity {

    @NotNull(message = "El nombre del cliente no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre del cliente debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull(message = "El correo del cliente no puede ser nulo")
    @Size(min = 5, max = 100, message = "El correo del cliente debe tener entre 5 y 100 caracteres")
    @Column(name = "correo", nullable = false)
    private String correo;
}