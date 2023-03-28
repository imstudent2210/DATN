import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AddressComponent } from 'src/app/components/address/address.component';
import { BodyComponent } from 'src/app/components/body/body.component';
import { NavbarComponent } from 'src/app/components/navbar/navbar.component';
import { ProductComponent } from 'src/app/components/product/product.component';
import { SidebarComponent } from 'src/app/components/sidebar/sidebar.component';
import { SidenavComponent } from 'src/app/components/sidenav/sidenav.component';
import { StaffComponent } from 'src/app/components/staff/staff.component';
import { StoreComponent } from 'src/app/components/store/store.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';

@NgModule({
  declarations: [
    AdminComponent,
    AddressComponent,
    NavbarComponent,
    SidebarComponent,
    ProductComponent,
    StaffComponent,
    StoreComponent,
    SidenavComponent,
    BodyComponent
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
