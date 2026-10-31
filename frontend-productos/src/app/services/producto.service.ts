import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Producto } from '../models/producto';
import { Pagina } from '../models/pagina';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private http = inject(HttpClient);
  private apiUrl = '/api/productos';

  listarPaginado(
      page: number,
      size: number): Observable<Pagina<Producto>> {

    const params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', 'id,asc');

    return this.http.get<Pagina<Producto>>(
      `${this.apiUrl}/pagina`,
      { params }
    );
  }
}