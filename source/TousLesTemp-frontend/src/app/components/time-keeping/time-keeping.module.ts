import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TimeKeepingRoutingModule } from './time-keeping-routing.module';
import { CreateTimeKeepingComponent } from './create-time-keeping/create-time-keeping.component';
import { UpdateTimeKeepingComponent } from './update-time-keeping/update-time-keeping.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { AppComponent } from 'src/app/app.component';
import { CustomPaginator } from 'src/app/model/paginator-config';
import { MatDialogModule } from '@angular/material/dialog';
import { MatRadioModule } from '@angular/material/radio';
import { NgxCurrencyModule } from 'ngx-currency';
import { TagModule } from 'primeng/tag';
import { TimeKeepingComponent } from './time-keeping.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MAT_DATE_FORMATS, MatNativeDateModule } from '@angular/material/core';
import { SalaryDetailComponent } from './salary-detail/salary-detail.component';
import {MatTabsModule} from '@angular/material/tabs';



@NgModule({
  declarations: [
    UpdateTimeKeepingComponent,
    CreateTimeKeepingComponent,
    TimeKeepingComponent,
    SalaryDetailComponent
  ],
  imports: [
    CommonModule,
    TimeKeepingRoutingModule,
    FormsModule,
    MatPaginatorModule,
    MatSortModule,
    ReactiveFormsModule,
    MatTableModule,
    MatInputModule,
    Ng2SearchPipeModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule,
    MatDialogModule,
    MatDatepickerModule,
    MatRadioModule,
    MatSlideToggleModule,
    MatGridListModule,
    MatIconModule,
    TagModule,
    NgxCurrencyModule,
    MatChipsModule,
    MatTabsModule



  ],
  exports:[],
  providers:[
    { provide: MatPaginatorIntl, useValue: CustomPaginator() },
    // {provide: MAT_DATE_FORMATS, useValue: _dateFormats},

  ],
})
export class TimeKeepingModule { }
