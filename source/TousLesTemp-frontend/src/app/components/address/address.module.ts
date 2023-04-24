import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddressRoutingModule } from './address-routing.module';
import { AddressComponent } from './address.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import {MatListModule} from '@angular/material/list';
import {ScrollingModule} from '@angular/cdk/scrolling';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { CreateAddressComponent } from './create-address/create-address.component';
import { MatCardModule } from '@angular/material/card';

@NgModule({
  declarations: [
    AddressComponent,
    CreateAddressComponent,
  ],
  imports: [
    CommonModule,
    AddressRoutingModule,
    GoogleMapsModule,
    FormsModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatTableModule,
    MatInputModule,
    MatListModule,
    ScrollingModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
  ]
})
export class AddressModule { }
