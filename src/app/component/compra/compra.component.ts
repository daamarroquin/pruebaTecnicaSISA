import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../../service/producto.service';
import { CarritoService } from '../../service/carrito.service';
import { CompraService } from '../../service/compra.service';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrl: './compra.component.css',
})
export class CompraComponent implements OnInit {
  productos: any[] = [];
  carrito: any[] = [];
  nuevoProducto = { nombre: '', codigo: '', cantidad: 0, precio: 0 };
  cliente = { nombre: '', correo: '' };
  page: number = 0; // Página actual
  size: number = 12; // Número de elementos por página
  totalPages: number = 100; // Total de páginas
  totalItems: number = 0; // Total de elementos (opcional)

  // Variables de validación
  nombreInvalido: boolean = false;
  correoInvalido: boolean = false;

  constructor(
    private productoService: ProductoService,
    private carritoService: CarritoService,
    private compraService: CompraService,
  ) {}

  ngOnInit(): void {
    this.listarProductos(this.page, this.size);
    this.carritoService.obtenerCarrito().subscribe((carrito) => {
      this.carrito = carrito;
    });
  }

  listarProductos(page: number, size: number): void {
    this.productoService.listarProductos(page, size).subscribe((response) => {
      console.log('listarProductos', response);
      this.productos = response.data || [];
      this.totalPages = response.totalPages || 100;
      this.totalItems = response.total || 100; // Total de elementos
    });
  }

  agregarProducto(producto: any): void {
    this.carritoService.agregarProducto(producto);
  }

  eliminarProducto(codigo: string): void {
    this.carritoService.eliminarProducto(codigo);
  }

  vaciarCarrito(): void {
    this.carritoService.vaciarCarrito();
  }

  // Método para manejar el evento de submit del formulario
  realizarCompra(): void {
    // Validar campos
    this.nombreInvalido =
      this.cliente.nombre.length < 2 || this.cliente.nombre.length > 100;
    this.correoInvalido =
      this.cliente.correo.length < 5 || this.cliente.correo.length > 100;

    if (this.nombreInvalido || this.correoInvalido) {
      return;
    }

    // Crear el objeto de compra que será enviado al backend
    const compra = {
      numeroOrden: this.generarNumeroOrden(),
      nombreCliente: this.cliente.nombre,
      correoCliente: this.cliente.correo,
      productos: this.carrito.map((producto) => ({
        codigo: producto.codigo,
        nombre: producto.nombre,
        precio: producto.precio,
        id: producto.id,
        cantidad: producto.cantidad,
      })),
    };

    // Llamar al servicio para finalizar la compra
    this.compraService.finalizarCompra(compra).subscribe(
      (response) => {
        console.log('Compra finalizada', response);
        // Realiza cualquier lógica adicional, como redirigir o mostrar un mensaje de éxito
        this.limpiarFormulario();
      },
      (error) => {
        console.error('Error al finalizar compra', error);
        // Lógica de manejo de errores
      }
    );
  }

  // Método para generar un número de orden (puedes modificarlo según tus necesidades)
  generarNumeroOrden(): string {
    const fecha = new Date();
    return `ORD-${fecha.getFullYear()}-${fecha.getMilliseconds()}`;
  }

  // Limpiar formulario y vaciar carrito después de la compra
  limpiarFormulario(): void {
    this.cliente = { nombre: '', correo: '' };
    this.carritoService.vaciarCarrito(); // Vaciar el carrito
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
