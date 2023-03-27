import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddressComponent } from 'src/app/components/address/address.component';
import { AdminComponent } from './admin.component';

const routes: Routes = [
  {
    path:'', component:AdminComponent, children: [
      {
        path:'address', component:AddressComponent
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
