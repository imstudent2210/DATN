import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './products.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { ProductsResolveService } from 'src/app/services/products-resolve.service';

const routes: Routes = [
  {
    path:'list',component:ProductsComponent
  },
  {
    path:'create', component:CreateProductComponent, resolve:{
      product: ProductsResolveService
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }
