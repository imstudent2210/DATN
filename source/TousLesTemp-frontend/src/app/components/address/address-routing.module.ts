import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddressComponent } from './address.component';
import { CreateAddressComponent } from './create-address/create-address.component';

const routes: Routes = [
  {path:'map', component:AddressComponent},
  {path:'create', component:CreateAddressComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddressRoutingModule { }
