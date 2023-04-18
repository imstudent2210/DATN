import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppMaterialModule } from 'src/material.module';
import { StoresComponent } from './stores.component';
import { StoreDetailComponent } from './store-detail/store-detail.component';
import { CreateStoreComponent } from './create-store/create-store.component';
import { UpdateCategoryComponent } from '../categories/update-category/update-category.component';
import { UpdateStoreComponent } from './update-store/update-store.component';
import { UpdateProductComponent } from '../products/update-product/update-product.component';
import { UploadImageComponent } from './upload-image/upload-image.component';

const routes: Routes = [
  {
    // path: 'list', component: StoresComponent, children: [
    //   { path: 'detail/:sid', component: StoreDetailComponent }
    // ],
    path: 'list', component: StoresComponent,
  },
  { path: 'detail/:sid', component: StoreDetailComponent }
  ,
  { path: 'create', component: CreateStoreComponent },
  { path: 'update/:sid', component: UpdateStoreComponent },
  { path: 'upload', component: UploadImageComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StoresRoutingModule { }
