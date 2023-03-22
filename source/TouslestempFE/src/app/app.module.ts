import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
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
import { RegisterComponent } from './components/register/register.component';
import { AuthInterceptor, AuthInterceptorProvider } from './guard/auth.interceptor';
import { AddressComponent } from './components/address/address.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AddressComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule,
    MatInputModule,
    MatIconModule,
    MatToolbarModule,
    MatCardModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule




  ],
  providers: [AuthInterceptorProvider],// get current user ....
  bootstrap: [AppComponent]
})
export class AppModule { }
