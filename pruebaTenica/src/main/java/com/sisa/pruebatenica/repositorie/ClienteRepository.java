package com.sisa.pruebatenica.repositorie;

import com.sisa.pruebatenica.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Cliente findByCorreo(String correo);
}