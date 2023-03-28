import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProductsComponent } from './components/products/products.component';
import { StoresComponent } from './components/stores/stores.component';
import { HomeGuard } from './guard/home.guard';

const routes: Routes = [
  {
    path: 'home', component: HomeComponent, children: [
      // { path: 'stores', component: StoresComponent },
      { path: 'products', component: ProductsComponent },
      { path: 'stores', loadChildren:()=>import('./components/stores/stores.module').then(m=>m.StoresModule)},
    ], canActivate: [HomeGuard]
  },
  { path: 'login', component: LoginComponent, pathMatch: "full" },
  { path: '', component: LoginComponent,pathMatch:"full"},
  // { path: 'stores', loadChildren:()=>import('./components/stores/stores.module').then(m=>m.StoresModule)},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
