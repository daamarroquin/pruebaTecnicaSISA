package com.sisa.pruebatenica.repositorie;

import com.sisa.pruebatenica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {
    Producto findByCodigo(String codigo);
}