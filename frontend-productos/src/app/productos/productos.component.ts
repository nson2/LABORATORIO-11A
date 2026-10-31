import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductoService } from '../services/producto.service';
import { Producto } from '../models/producto';

@Component({
  selector: 'app-productos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './productos.component.html'
})
export class ProductosComponent implements OnInit {

  private productoService = inject(ProductoService);

  productos: Producto[] = [];
  pagina = 0;
  tamanio = 5;
  totalPaginas = 0;
  totalElementos = 0;
  primera = true;
  ultima = false;

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.productoService
      .listarPaginado(this.pagina, this.tamanio)
      .subscribe(respuesta => {
        this.productos = respuesta.content;
        this.totalPaginas = respuesta.totalPages;
        this.totalElementos = respuesta.totalElements;
        this.primera = respuesta.first;
        this.ultima = respuesta.last;
      });
  }

  anterior(): void {
    if (!this.primera) {
      this.pagina--;
      this.cargarProductos();
    }
  }

  siguiente(): void {
    if (!this.ultima) {
      this.pagina++;
      this.cargarProductos();
    }
  }
}