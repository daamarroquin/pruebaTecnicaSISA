package com.sisa.pruebatenica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "T_ORDEN_COMPRA")
public class OrdenCompra extends BaseEntity {

    @NotNull(message = "El número de la orden no puede ser nulo")
    @Column(name = "numero_orden", nullable = false, unique = true)
    private String numeroOrden;

    @NotNull(message = "La fecha de la orden no puede ser nula")
    @Column(name = "fecha_orden", nullable = false)
    private Date fechaOrden;

    @NotNull(message = "El cliente no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "ordenCompra")
    private List<DetalleOrdenCompra> detalles;
}