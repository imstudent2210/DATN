import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppMaterialModule } from 'src/material.module';
import { StoresComponent } from './stores.component';
import { StoreDetailComponent } from './store-detail/store-detail.component';

const routes: Routes = [
  {
    // path: 'list', component: StoresComponent, children: [
    //   { path: 'detail/:sid', component: StoreDetailComponent }
    // ],
    path: 'list', component: StoresComponent,
  },
  { path: 'detail/:sid', component: StoreDetailComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StoresRoutingModule { }
