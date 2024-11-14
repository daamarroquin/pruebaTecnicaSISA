import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../../service/producto.service';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrl: './producto.component.css'
})
export class ProductoComponent implements OnInit {
  productos: any[] = [];
  nuevoProducto = { nombre: '', codigo: '', cantidad: 0, precio: 0 };
  page: number = 0; // Página actual
  size: number = 10; // Número de elementos por página
  totalPages: number = 100; // Total de páginas
  totalItems: number = 0; // Total de elementos (puedes usarlo si lo necesitas)

  constructor(private productoService: ProductoService) {}

  ngOnInit(): void {
    this.listarProductos(this.page, this.size);
  }

  listarProductos(page: number, size: number): void {
    this.productoService.listarProductos(page, size).subscribe((response) => {
      console.log('listarProductos', response);
      this.productos = response.data || [];
      this.totalPages = response.totalPages || 100;
      this.totalItems = response.total || 100; // Total de elementos (opcional)
    });
  }

  crearProducto(): void {
    this.productoService.crearProducto(this.nuevoProducto).subscribe(() => {
      this.listarProductos(0, this.size);
      this.nuevoProducto = { nombre: '', codigo: '', cantidad: 0, precio: 0 };
    });
  }

  eliminarProducto(codigo: string): void {
    this.productoService.eliminarProducto(codigo).subscribe(() => {
      this.listarProductos(this.page, this.size);
    });
  }

  actualizarProducto(codigo: string): void {
    const productoActualizado = {
      ...this.nuevoProducto,
      nombre: 'Nombre Actualizado',
    };
    this.productoService
      .actualizarProducto(codigo, productoActualizado)
      .subscribe(() => {
        this.listarProductos(this.page, this.size);
      });
  }

  nextPage(): void {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.listarProductos(this.page, this.size);
    }
  }

  previousPage(): void {
    if (this.page > 0) {
      this.page--;
      this.listarProductos(this.page, this.size);
    }
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.page = page;
      this.listarProductos(this.page, this.size);
    }
  }
}
