import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddressComponent } from 'src/app/components/address/address.component';
import { BodyComponent } from 'src/app/components/body/body.component';
import { CategoryComponent } from 'src/app/components/category/category.component';
import { ProductComponent } from 'src/app/components/product/product.component';
import { ProfileComponent } from 'src/app/components/profile/profile.component';
import { StaffComponent } from 'src/app/components/staff/staff.component';
import { StoreComponent } from 'src/app/components/store/store.component';
import { AdminGuard } from 'src/app/guard/admin.guard';
import { LoginComponent } from '../login/login.component';
import { AdminComponent } from './admin.component';

const routes: Routes = [
  {
    path:'', component:AdminComponent, children: [
      {
        path:'address', component:AddressComponent
      },
      {
        path:'login', component:LoginComponent
      },
      {
        path:'category', component:CategoryComponent
      },
      {
        path:'store', component:StoreComponent
      },
      {
        path:'product', component:ProductComponent
      },
      {
        path:'staff', component:StaffComponent
      },
      {
        path:'profile', component:ProfileComponent
      },
      // {
      //   path:'body', component:BodyComponent
      // },


    ],canActivate:[AdminGuard]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
