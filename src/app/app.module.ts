import { NgModule } from '@angular/core';
import {
  BrowserModule,
  provideClientHydration,
} from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ProductoComponent } from './component/producto/producto.component';
import { CompraComponent } from './component/compra/compra.component';
import { ActualizarComponent } from './component/actualizar/actualizar.component';
import { CarritoService } from './service/carrito.service';

@NgModule({
  declarations: [
    AppComponent,
    ProductoComponent,
    CompraComponent,
    ActualizarComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [CarritoService],
  bootstrap: [AppComponent],
})
export class AppModule {}
