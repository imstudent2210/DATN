import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListStoresComponent } from './list-stores/list-stores.component';
import { StoresComponent } from './stores.component';

const routes: Routes = [
  {
    // path:'stores', component:StoresComponent,children:[
    // {
    //   path:'list',component:ListStoresComponent
    // }
    // ]
    path: 'stores',
    component: StoresComponent,
  },
  {
    path: 'list',
    component: ListStoresComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StoresRoutingModule {}
