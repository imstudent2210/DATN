import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StoresRoutingModule } from './stores-routing.module';
import { StoresComponent } from './stores.component';
import { MatPaginatorModule } from '@angular/material/paginator';

import { MatTableModule } from '@angular/material/table';
import { AppComponent } from 'src/app/app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';




@NgModule({
  declarations: [
    StoresComponent,

  ],
  imports: [
    CommonModule,
    StoresRoutingModule,

    FormsModule,
    MatPaginatorModule,
    ReactiveFormsModule,
    MatTableModule,



  ],
  exports:[],
  providers:[],
  bootstrap:[AppComponent]
})
export class StoresModule { }
