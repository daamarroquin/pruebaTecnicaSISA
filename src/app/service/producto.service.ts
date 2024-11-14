import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  private apiUrl = 'http://localhost:8081/producto';

  constructor(private http: HttpClient) {}

  crearProducto(maestro: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/crear`, maestro);
  }

  eliminarProducto(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/eliminar/${id}`);
  }

  actualizarProducto(id: string, maestro: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/actualizar/${id}`, maestro);
  }

  listarProductos(page: number, size: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/listar?page=${page}&size=${size}`);
  }

  obtenerProductoPorId(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/buscar/${id}`);
  }
}
