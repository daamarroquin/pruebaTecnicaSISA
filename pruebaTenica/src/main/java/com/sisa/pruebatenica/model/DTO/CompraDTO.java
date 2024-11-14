package com.sisa.pruebatenica.model.DTO;

import com.sisa.pruebatenica.model.Cliente;
import com.sisa.pruebatenica.model.Producto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Data
@ToString
public class CompraDTO {
    private String numeroOrden;
    private String nombreCliente;
    private String correoCliente;
    private List<Producto> productos;
}
