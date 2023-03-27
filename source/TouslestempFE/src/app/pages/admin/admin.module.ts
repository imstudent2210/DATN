import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AddressComponent } from 'src/app/components/address/address.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';

@NgModule({
  declarations: [
    AdminComponent,
    AddressComponent

  ],
  imports: [
    AdminRoutingModule,
    RouterModule,
    CommonModule
  ],
  exports: [],
  providers: [],
  bootstrap: [AppComponent],
})
export class AdminModule {}
