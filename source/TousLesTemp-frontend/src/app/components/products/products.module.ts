import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductsRoutingModule } from './products-routing.module';
import { ProductsComponent } from './products.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { CustomPaginator } from 'src/app/share/paginator-config';
import {  MatListModule } from '@angular/material/list';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MycurrencyPipe } from 'src/app/share/custom.currencypipe';
import { CreateProductComponent } from './create-product/create-product.component';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatSelectModule } from '@angular/material/select';
import {MatAutocompleteModule} from '@angular/material/autocomplete';

@NgModule({
  declarations: [
    ProductsComponent,
    MycurrencyPipe,
    CreateProductComponent
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule,
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
    MatAutocompleteModule
  ],
  providers:[
    { provide: MatPaginatorIntl, useValue: CustomPaginator(),
       }
  ],
})
export class ProductsModule { }
