import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddressComponent } from './components/address/address.component';
import { LoginComponent } from './components/login/login.component';
import { AdminGuard } from './guard/admin.guard';
import { AdminComponent } from './pages/admin/admin.component';

const routes: Routes = [
  {
    path:'login', component:LoginComponent, pathMatch:"full"
  },
  {
    path:'', component:LoginComponent, pathMatch:"full"
  },
  {
    path:'admin', component:AdminComponent, pathMatch:"full",canActivate:[AdminGuard]
  },
  {
    path:'address', component:AddressComponent, pathMatch:"full"
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
