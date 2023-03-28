import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductComponent } from './components/product/product.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {
    path:'login', component:LoginComponent, pathMatch:"full"
  },
  {
    path:'', component:LoginComponent, pathMatch:"full"
  },
  {
    path:'admin',
    loadChildren:()=> import('./pages/admin/admin.module').then(m=>m.AdminModule)
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
