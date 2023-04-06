import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppMaterialModule } from 'src/material.module';
import { StoresComponent } from './stores.component';
import { StoreDetailComponent } from './store-detail/store-detail.component';
import { CreateStoreComponent } from './create-store/create-store.component';

const routes: Routes = [
  {
    // path: 'list', component: StoresComponent, children: [
    //   { path: 'detail/:sid', component: StoreDetailComponent }
    // ],
    path: 'list', component: StoresComponent,
  },
  { path: 'detail/:sid', component: StoreDetailComponent }
  ,
  { path: 'create', component: CreateStoreComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StoresRoutingModule { }
