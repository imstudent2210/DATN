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
import { SublevelMenuComponent } from './components/sidenav/sublevel-menu.component';
import { NgToastModule } from 'ng-angular-popup';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { NgxUiLoaderHttpModule, NgxUiLoaderModule } from 'ngx-ui-loader';
import { environment } from '../environment/environment.prod';
import { InputNumberModule } from 'primeng/inputnumber';
import { AngularFireModule } from '@angular/fire/compat';
import { AngularFirestoreModule } from '@angular/fire/compat/firestore';
import { TotpComponent } from './components/totp/totp.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthInterceptorProviders } from './guard/auth.interceptor';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { UserComponent } from './components/user/user.component';
import {MatBadgeModule} from '@angular/material/badge';
import {MatMenuModule} from '@angular/material/menu';
import { ProfileComponent } from './components/profile/profile.component';
import {MatDatepickerModule} from '@angular/material/datepicker';

@NgModule({
  declarations: [
    AppComponent,
    BodyComponent,
    SidenavComponent,
    HomeComponent,

    NavbarComponent,
    LoginComponent,
    SublevelMenuComponent,
    TotpComponent,
    RegisterComponent,
    UserComponent,
    ProfileComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    RouterModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    NgToastModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatBadgeModule,
    MatCheckboxModule,
    NgxUiLoaderModule,
    InputNumberModule,
    AngularFireModule.initializeApp(environment.FIREBASE_CONFIG),
    AngularFirestoreModule,
    NgxUiLoaderHttpModule.forRoot({
      showForeground:true
    })

  ],
  providers: [
    AuthInterceptorProviders,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
