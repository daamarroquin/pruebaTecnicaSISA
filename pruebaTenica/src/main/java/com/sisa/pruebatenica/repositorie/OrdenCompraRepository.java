package com.sisa.pruebatenica.repositorie;

import com.sisa.pruebatenica.model.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.rmi.server.UID;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, UID> {
}