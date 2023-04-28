import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StaffRoutingModule } from './staff-routing.module';
import { StaffComponent } from './staff.component';
import { CreateStaffComponent } from './create-staff/create-staff.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatDialogModule } from '@angular/material/dialog';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { CustomPaginator } from 'src/app/model/paginator-config';
import { UpdateStaffComponent } from './update-staff/update-staff.component';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import { StaffGroupComponent } from './staff-group/staff-group.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { CreateStaffgroupComponent } from './create-staffgroup/create-staffgroup.component';
import { UpdateGroupComponent } from './update-group/update-group.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';



@NgModule({
  declarations: [
    StaffComponent,
    CreateStaffComponent,
    UpdateStaffComponent,
    DeleteDialogComponent,
    StaffGroupComponent,
    CreateStaffgroupComponent,
    UpdateGroupComponent,

  ],
  imports: [
    CommonModule,
    StaffRoutingModule,
    FormsModule,
    MatPaginatorModule,
    MatSortModule,
    ReactiveFormsModule,
    MatTableModule,
    MatInputModule,
    Ng2SearchPipeModule,
    MatListModule,
    MatChipsModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatGridListModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatDialogModule,
    MatSlideToggleModule,
    MatProgressBarModule
  ],
  providers: [
    {
      provide: MatPaginatorIntl, useValue: CustomPaginator(),
    }
  ],
})
export class StaffModule { }
