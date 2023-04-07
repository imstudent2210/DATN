import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriesComponent } from './categories.component';
import { UpdateCategoryComponent } from './update-category/update-category.component';
import { CreateCategoryComponent } from './create-category/create-category.component';

const routes: Routes = [
  {path:'list', component:CategoriesComponent},
  {path:'create', component:CreateCategoryComponent},
  {
    path:'update/:cid',component:UpdateCategoryComponent,
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoriesRoutingModule { }
