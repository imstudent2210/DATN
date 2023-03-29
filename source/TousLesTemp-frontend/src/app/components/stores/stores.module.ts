import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StoresRoutingModule } from './stores-routing.module';
import { StoresComponent } from './stores.component';
import { MatPaginatorModule } from '@angular/material/paginator';
// import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { AppComponent } from 'src/app/app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListStoresComponent } from './list-stores/list-stores.component';
// import { AppMaterialModule } from 'src/material.module';


@NgModule({
  declarations: [
    StoresComponent,
    ListStoresComponent
  ],
  imports: [
    StoresRoutingModule,
    // MatPaginatorModule,
    // FormsModule,
    // ReactiveFormsModule,
    // MatTableModule

  ],
  exports:[],
  providers:[],
  bootstrap:[AppComponent]
})
export class StoresModule { }
