import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppMaterialModule } from 'src/material.module';
import { StoresComponent } from './stores.component';

const routes: Routes = [
  {
    path: 'list',
    component: StoresComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StoresRoutingModule {}
