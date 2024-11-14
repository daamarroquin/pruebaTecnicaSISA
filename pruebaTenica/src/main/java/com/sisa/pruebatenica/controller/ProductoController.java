package com.sisa.pruebatenica.controller;

import com.sisa.pruebatenica.model.DTO.ResponseDTO;
import com.sisa.pruebatenica.model.Producto;
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
@RequestMapping("/producto")
@Validated
@Slf4j
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/crear")
    public ResponseEntity<ResponseDTO<?>> crear(@Valid @RequestBody Producto maestro) {
        log.info("Create maestro initiated :{}", maestro.toString());
        ResponseEntity<ResponseDTO<?>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        ResponseDTO<Producto> response = new ResponseDTO<>();
        HttpStatus httpStatus;
        try {
            Producto save = productoService.crearProducto(maestro);
            httpStatus = HttpStatus.CREATED;
            response.setCode(httpStatus.value());
            response.setMessage("Cuenta creada exitosamente");
            response.setData(save);
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

    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseDTO<?>> actualizar(@PathVariable UUID id, @Valid @RequestBody Producto maestro) {
        log.info("Update maestro initiated :{}", maestro.toString());
        ResponseEntity<ResponseDTO<?>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        ResponseDTO<Producto> response = new ResponseDTO<>();
        HttpStatus httpStatus;
        try {
            Producto update = productoService.actualizarProducto(id, maestro);
            if (update == null) {
                httpStatus = HttpStatus.NOT_FOUND;
                response.setCode(httpStatus.value());
                response.setMessage("Maestro no encontrado");
            } else {
                httpStatus = HttpStatus.OK;
                response.setCode(httpStatus.value());
                response.setMessage("Maestro actualizado exitosamente");
                response.setData(update);
            }
        } catch (Exception e) {
            log.error("Error updating maestro: {}", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setCode(httpStatus.value());
            response.setMessage("Ocurrió un error al actualizar");
        }
        responseEntity = ResponseEntity.status(httpStatus).body(response);
        log.info("Response: {}", responseEntity);
        return responseEntity;
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDTO<?>> eliminar(@PathVariable UUID id) {
        log.info("Delete maestro initiated with id: {}", id);
        ResponseEntity<ResponseDTO<?>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        ResponseDTO<Producto> response = new ResponseDTO<>();
        HttpStatus httpStatus;
        try {
            boolean isDeleted = productoService.eliminarProducto(id);
            if (isDeleted) {
                httpStatus = HttpStatus.OK;
                response.setCode(httpStatus.value());
                response.setMessage("Maestro eliminado exitosamente");
            } else {
                httpStatus = HttpStatus.NOT_FOUND;
                response.setCode(httpStatus.value());
                response.setMessage("Maestro no encontrado");
            }
        } catch (Exception e) {
            log.error("Error deleting maestro: {}", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setCode(httpStatus.value());
            response.setMessage("Ocurrió un error al eliminar");
        }
        responseEntity = ResponseEntity.status(httpStatus).body(response);
        log.info("Response: {}", responseEntity);
        return responseEntity;
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/listar")
    public ResponseEntity<ResponseDTO<?>> listar(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "100") int size) {
        log.info("List maestros initiated with page: {} and size: {}", page, size);
        ResponseEntity<ResponseDTO<?>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        ResponseDTO<List<Producto>> response = new ResponseDTO<>();
        HttpStatus httpStatus;
        try {
            Pageable pageableRequest = PageRequest.of(
                    page,
                    size,
                    Sort.by(Sort.Order.desc("createdAt"))
            );
            Page<Producto> maestrosPage = productoService.obtenerProductos(pageableRequest);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Listado de maestros");
            response.setData(maestrosPage.getContent());
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Error listing maestros: {}", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setCode(httpStatus.value());
            response.setMessage("Ocurrió un error al listar");
        }
        responseEntity = ResponseEntity.status(httpStatus).body(response);
        log.info("Response: {}", responseEntity);
        return responseEntity;
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ResponseDTO<?>> buscarPorId(@PathVariable UUID id) {
        log.info("Buscar producto por ID iniciado con id: {}", id);
        ResponseEntity<ResponseDTO<?>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        ResponseDTO<Producto> response = new ResponseDTO<>();
        HttpStatus httpStatus;
        try {
            Producto producto = productoService.buscarProductoPorId(id);
            if (producto == null) {
                httpStatus = HttpStatus.NOT_FOUND;
                response.setCode(httpStatus.value());
                response.setMessage("Producto no encontrado");
            } else {
                httpStatus = HttpStatus.OK;
                response.setCode(httpStatus.value());
                response.setMessage("Producto encontrado");
                response.setData(producto);
            }
        } catch (Exception e) {
            log.error("Error buscando producto por ID: {}", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setCode(httpStatus.value());
            response.setMessage("Ocurrió un error al buscar el producto");
        }
        responseEntity = ResponseEntity.status(httpStatus).body(response);
        log.info("Response: {}", responseEntity);
        return responseEntity;
    }


}
