import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BodyComponent } from './components/body/body.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthInterceptorProvider } from './guard/auth.interceptor';
import { SublevelMenuComponent } from './components/sidenav/sublevel-menu.component';
import { NgToastModule } from 'ng-angular-popup';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { StoresModule } from './components/stores/stores.module';
import { StoresComponent } from './components/stores/stores.component';
import { AddressComponent } from './components/address/address.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { StaffComponent } from './components/staff/staff.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    AppComponent,
    BodyComponent,
    SidenavComponent,
    HomeComponent,
    NavbarComponent,
    LoginComponent,
    SublevelMenuComponent,
    // AddressComponent,
    // CategoriesComponent,
    // StaffComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    RouterModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    NgToastModule,
    MatIconModule,
    MatButtonModule
  ],
  providers: [AuthInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
