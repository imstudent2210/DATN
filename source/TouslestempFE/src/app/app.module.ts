import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './pages/login/login.component';
//===============================
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatCardModule, MatCardTitleGroup } from '@angular/material/card';
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { MatInputModule } from '@angular/material/input';
import { RouterModule, Routes } from "@angular/router";
import { HttpClientModule } from '@angular/common/http';
// import { AuthInterceptorProvider } from './services/auth.interceptor';
import {MatToolbarModule} from '@angular/material/toolbar';
import { RegisterComponent } from './pages/register/register.component';
import { AuthInterceptor, AuthInterceptorProvider } from './guard/auth.interceptor';
import { CommonModule } from '@angular/common';
import { AdminModule } from './pages/admin/admin.module';
import { ProductComponent } from './components/product/product.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { BodyComponent } from './components/body/body.component';
// import { StoreComponent } from './components/store/store.component';
// import { StaffComponent } from './components/staff/staff.component';
// import { CategoryComponent } from './components/category/category.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    // BodyComponent,
    // SidenavComponent,
    // ProductComponent,
    // StoreComponent,
    // StaffComponent,
    // CategoryComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    AdminModule,

    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule,
    MatInputModule,
    MatIconModule,
    MatToolbarModule,
    MatCardModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule,
  ],
  providers: [AuthInterceptorProvider],// get current user ....
  bootstrap: [AppComponent]
})
export class AppModule { }
