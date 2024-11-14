import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from 'express';
import { ProductoService } from '../../service/producto.service';

@Component({
  selector: 'app-actualizar',
  templateUrl: './actualizar.component.html',
  styleUrl: './actualizar.component.css',
})
export class ActualizarComponent implements OnInit {
  productoId: string = '';
  producto: any;

  constructor(
    private route: ActivatedRoute,
    private productoService: ProductoService
  ) {}

  ngOnInit(): void {
    console.log('ActualizarComponent');
    this.route.params.subscribe((params) => {
      this.productoId = params['id'];
      console.log('ID del producto', this.productoId);
      this.cargarProducto();
    });
  }

  cargarProducto() {
    this.productoService.obtenerProductoPorId(this.productoId).subscribe(
      (producto) => {
        console.log('Producto cargado', producto);
        this.producto = producto.data;
      },
      (error) => {
        console.error('Error al cargar el producto', error);
      }
    );
  }

  actualizarProducto() {
    this.productoService
      .actualizarProducto(this.productoId, this.producto)
      .subscribe(
        (respuesta) => {
          console.log('Producto actualizado', respuesta);
          window.location.href = '/productos';  // Redirige manualmente a la página de productos
        },
        (error) => {
          console.error('Error al actualizar el producto', error);
        }
      );
  }
}
