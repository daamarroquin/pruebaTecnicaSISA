package com.sisa.pruebatenica.controller;

import com.sisa.pruebatenica.model.Cliente;
import com.sisa.pruebatenica.model.DTO.CompraDTO;
import com.sisa.pruebatenica.model.DTO.ResponseDTO;
import com.sisa.pruebatenica.model.OrdenCompra;
import com.sisa.pruebatenica.model.Producto;
import com.sisa.pruebatenica.service.ClienteService;
import com.sisa.pruebatenica.service.CompraService;
import com.sisa.pruebatenica.service.ProductoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/compra")
@Validated
@Slf4j
public class CompraController {

    private CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/finalizar")
    public ResponseEntity<ResponseDTO<?>> crear(@Valid @RequestBody CompraDTO request) {
        request.setNumeroOrden(UUID.randomUUID().toString());
        log.info("Create initiated :{}", request.toString());
        ResponseEntity<ResponseDTO<?>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        ResponseDTO<OrdenCompra> response = new ResponseDTO<>();
        HttpStatus httpStatus;
        try {
//            Cliente save = clienteService.crearCliente(r());

            OrdenCompra compra =  compraService.crearCompra(request);
            httpStatus = HttpStatus.CREATED;
            response.setCode(httpStatus.value());
            response.setMessage("Cuenta creada exitosamente");
            response.setData(compra);
        } catch (Exception e) {
            log.error("Error creating balance: {}", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setCode(httpStatus.value());
            response.setMessage("Ocurrio un error al crear");
        }
        responseEntity = ResponseEntity.status(httpStatus).body(response);
        log.info("Response: {}", responseEntity);
        return responseEntity;
    }


}
