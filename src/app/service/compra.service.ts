import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CompraService {
  private apiUrl = 'http://localhost:8081/compra/finalizar'; // URL del backend

  constructor(private http: HttpClient) {}

  // Método para finalizar la compra
  finalizarCompra(compra: any): Observable<any> {
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/json; charset=utf-8'
    );
    return this.http.post(this.apiUrl, compra, { headers });
  }
}
