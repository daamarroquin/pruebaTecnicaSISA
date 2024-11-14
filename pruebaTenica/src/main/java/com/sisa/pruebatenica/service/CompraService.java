package com.sisa.pruebatenica.service;

import com.sisa.pruebatenica.model.Cliente;
import com.sisa.pruebatenica.model.DTO.CompraDTO;
import com.sisa.pruebatenica.model.DetalleOrdenCompra;
import com.sisa.pruebatenica.model.OrdenCompra;
import com.sisa.pruebatenica.model.Producto;
import com.sisa.pruebatenica.repositorie.ClienteRepository;
import com.sisa.pruebatenica.repositorie.DetalleOrdenCompraRepository;
import com.sisa.pruebatenica.repositorie.OrdenCompraRepository;
import com.sisa.pruebatenica.repositorie.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CompraService {

    private OrdenCompraRepository ordenCompraRepository;
    private ClienteRepository clienteRepository;
    private ProductoRepository productoRepository;
    private DetalleOrdenCompraRepository detalleOrdenCompraRepository;

    @Autowired
    public CompraService(OrdenCompraRepository ordenCompraRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository, DetalleOrdenCompraRepository detalleOrdenCompraRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.detalleOrdenCompraRepository = detalleOrdenCompraRepository;
    }

    @Transactional
    public OrdenCompra crearCompra(CompraDTO compraDTO) {
        // Buscar al cliente por correo
        Cliente cliente = clienteRepository.findByCorreo(compraDTO.getCorreoCliente());
        if (cliente == null) {
            // Crear un nuevo cliente
            cliente = new Cliente();
            cliente.setNombre(compraDTO.getNombreCliente());
            cliente.setCorreo(compraDTO.getCorreoCliente());
            cliente = clienteRepository.save(cliente);
        }

        // Crear la nueva orden de compra
        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setNumeroOrden(compraDTO.getNumeroOrden());
        ordenCompra.setFechaOrden(new Date()); // Fecha actual
        ordenCompra.setCliente(cliente);

        // Guardar la orden de compra
        ordenCompra = ordenCompraRepository.save(ordenCompra);

        // Crear los detalles de la orden
        for (Producto productoDTO : compraDTO.getProductos()) {
            Producto producto = productoRepository.findByCodigo(productoDTO.getCodigo());
            if (producto == null) {
                throw new RuntimeException("Producto no encontrado: " + productoDTO.getCodigo());
            }

            DetalleOrdenCompra detalle = new DetalleOrdenCompra();
            detalle.setCantidad(productoDTO.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio()); // Asignar el precio del producto
            detalle.setOrdenCompra(ordenCompra); // Asociar con la orden
            detalle.setProducto(producto); // Asociar con el producto

            detalleOrdenCompraRepository.save(detalle); // Guardar cada detalle
        }

        return ordenCompra; // Retornar la orden de compra creada
    }
}