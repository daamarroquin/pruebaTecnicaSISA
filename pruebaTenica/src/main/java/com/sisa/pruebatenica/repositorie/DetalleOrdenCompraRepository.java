package com.sisa.pruebatenica.repositorie;

import com.sisa.pruebatenica.model.DetalleOrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompra, UUID> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}