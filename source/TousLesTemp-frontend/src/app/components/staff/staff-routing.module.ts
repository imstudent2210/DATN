import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StaffComponent } from './staff.component';
import { CreateStaffComponent } from './create-staff/create-staff.component';
import { UpdateStaffComponent } from './update-staff/update-staff.component';
import { StaffGroupComponent } from './staff-group/staff-group.component';

import { CreateStaffgroupComponent } from './create-staffgroup/create-staffgroup.component';
import { UpdateGroupComponent } from './update-group/update-group.component';

const routes: Routes = [
  { path: 'list', component: StaffComponent },
  { path: 'create', component: CreateStaffComponent },
  { path: 'update/:stid', component: UpdateStaffComponent },
  { path: 'group', component: StaffGroupComponent},
  { path: 'group/update/:gid',component:UpdateGroupComponent},
  { path: 'group/create', component: CreateStaffgroupComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],

  exports: [RouterModule]
})
export class StaffRoutingModule { }
