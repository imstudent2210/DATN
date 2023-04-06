import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './products.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { ProductsResolveService } from 'src/app/services/products-resolve.service';
import { UpdateProductComponent } from './update-product/update-product.component';

const routes: Routes = [
  {
    path:'list',component:ProductsComponent
  },
  {
    path:'create', component:CreateProductComponent, resolve:{
      product: ProductsResolveService
    }
  },
  {
    path:'update/:pid',component:UpdateProductComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule { }
