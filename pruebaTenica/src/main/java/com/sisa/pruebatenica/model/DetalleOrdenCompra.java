package com.sisa.pruebatenica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "T_DET_ORDEN_COMPRA")
public class DetalleOrdenCompra extends BaseEntity {

    @NotNull(message = "La cantidad no puede ser nula")
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @NotNull(message = "El precio unitario no puede ser nulo")
    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "orden_compra_id", nullable = false)
    private OrdenCompra ordenCompra;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
}