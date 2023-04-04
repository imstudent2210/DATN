import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StoresRoutingModule } from './stores-routing.module';
import { StoresComponent } from './stores.component';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';

import { MatTableModule } from '@angular/material/table';
import { AppComponent } from 'src/app/app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CustomPaginator } from 'src/app/share/paginator-config';
import { MatInput, MatInputModule } from '@angular/material/input';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { MatSortModule } from '@angular/material/sort';
import { MatButtonModule } from '@angular/material/button';
import { StoreDetailComponent } from './store-detail/store-detail.component';
import { MatCardModule } from '@angular/material/card';
import { CreateStoreComponent } from './create-store/create-store.component';
import { MatSelectModule } from '@angular/material/select';


@NgModule({
  declarations: [
    StoresComponent,
    StoreDetailComponent,
    CreateStoreComponent,


  ],
  imports: [
    CommonModule,
    StoresRoutingModule,
    FormsModule,
    MatPaginatorModule,
    MatSortModule,
    ReactiveFormsModule,
    MatTableModule,
    MatInputModule,
    Ng2SearchPipeModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule




  ],
  exports:[],
  providers:[
    { provide: MatPaginatorIntl, useValue: CustomPaginator() }
  ],
  bootstrap:[AppComponent]
})
export class StoresModule { }
