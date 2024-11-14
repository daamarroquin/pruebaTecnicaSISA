import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CarritoService{
  private carritoSubject: BehaviorSubject<any[]>;
  private carrito: Observable<any[]>;

  constructor() {
    // Asegúrate de que estemos en el navegador antes de acceder a localStorage
    if (typeof window !== 'undefined' && window.localStorage) {
      const carritoData = JSON.parse(localStorage.getItem('carrito') || '[]');
      this.carritoSubject = new BehaviorSubject<any[]>(carritoData);
      this.carrito = this.carritoSubject.asObservable();
    } else {
      // Si no estamos en el navegador (por ejemplo, en SSR), inicializamos un carrito vacío
      this.carritoSubject = new BehaviorSubject<any[]>([]);
      this.carrito = this.carritoSubject.asObservable();
    }
  }

  obtenerCarrito(): Observable<any[]> {
    return this.carrito;
  }

  agregarProducto(producto: any): void {
    const carritoActual = this.carritoSubject.value;
    carritoActual.push(producto);
    this.actualizarCarrito(carritoActual);
  }

  eliminarProducto(codigo: string): void {
    const carritoActual = this.carritoSubject.value.filter(
      (prod) => prod.codigo !== codigo
    );
    this.actualizarCarrito(carritoActual);
  }

  private actualizarCarrito(nuevoCarrito: any[]): void {
    // Asegúrate de que solo se acceda a localStorage en el navegador
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.setItem('carrito', JSON.stringify(nuevoCarrito));
    }
    this.carritoSubject.next(nuevoCarrito);
  }

  vaciarCarrito(): void {
    // Asegúrate de que solo se acceda a localStorage en el navegador
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.removeItem('carrito');
    }
    this.carritoSubject.next([]);
  }
}
