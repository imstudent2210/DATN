import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TimeKeepingComponent } from './time-keeping.component';
import { CreateTimeKeepingComponent } from './create-time-keeping/create-time-keeping.component';
import { UpdateTimeKeepingComponent } from './update-time-keeping/update-time-keeping.component';

const routes: Routes = [
  { path: 'list', component: TimeKeepingComponent },
  { path: 'create', component: CreateTimeKeepingComponent },
  { path: 'update/:tid', component: UpdateTimeKeepingComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TimeKeepingRoutingModule { }
