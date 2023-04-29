import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SalaryComponent } from './salary.component';
import { CreateSalaryComponent } from './create-salary/create-salary.component';
import { UpdateSalaryComponent } from './update-salary/update-salary.component';
const routes: Routes = [
  { path: 'list', component: SalaryComponent },
  { path: 'create', component: CreateSalaryComponent },
  { path: 'update/:said', component: UpdateSalaryComponent },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalaryRoutingModule { }
