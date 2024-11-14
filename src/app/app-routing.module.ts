import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductoComponent } from './component/producto/producto.component';
import { CompraComponent } from './component/compra/compra.component';
import { ActualizarComponent } from './component/actualizar/actualizar.component';

const routes: Routes = [
  { path: '', component: CompraComponent },
  { path: 'producto/actualizar/:id', component: ActualizarComponent },
  { path: 'productos', component: ProductoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
