  import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StoresComponent } from './stores.component';
import { StoreDetailComponent } from './store-detail/store-detail.component';
import { CreateStoreComponent } from './create-store/create-store.component';
import { UpdateStoreComponent } from './update-store/update-store.component';

const routes: Routes = [
  { path: 'list', component: StoresComponent },
  { path: 'detail/:sid', component: StoreDetailComponent },
  { path: 'create', component: CreateStoreComponent },
  { path: 'update/:sid', component: UpdateStoreComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StoresRoutingModule { }
