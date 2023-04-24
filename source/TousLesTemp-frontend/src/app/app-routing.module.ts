import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { HomeGuard } from './guard/home.guard';
import { TotpComponent } from './components/totp/totp.component';
import { RegisterComponent } from './components/register/register.component';
import { UserComponent } from './components/user/user.component';
import { ProfileComponent } from './components/profile/profile.component';


const routes: Routes = [
  {
    path: 'home', component: HomeComponent, children: [
      { path: 'products', loadChildren: () => import('./components/products/products.module').then(m => m.ProductsModule) },
      { path: 'stores', loadChildren: () => import('./components/stores/stores.module').then(m => m.StoresModule)},
      { path: 'address', loadChildren: () => import('./components/address/address.module').then(m => m.AddressModule)},
      { path: 'categories', loadChildren: () => import('./components/categories/categories.module').then(m => m.CategoriesModule)},
      { path: 'staff', loadChildren: () => import('./components/staff/staff.module').then(m => m.StaffModule)},


    ], canActivate: [HomeGuard]
  },
  { path: 'login', component: LoginComponent, pathMatch: "full" },
  { path: '', component: LoginComponent, pathMatch: "full" },
  { path: 'otp', component: TotpComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user', component: UserComponent },
  { path: 'profile', component: ProfileComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
