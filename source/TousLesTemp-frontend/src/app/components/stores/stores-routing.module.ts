import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StoresComponent } from './stores.component';
import { StoreDetailComponent } from './store-detail/store-detail.component';
import { CreateStoreComponent } from './create-store/create-store.component';
import { UpdateStoreComponent } from './update-store/update-store.component';
import { UploadImageComponent } from './upload-image/upload-image.component';

const routes: Routes = [
  {
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
