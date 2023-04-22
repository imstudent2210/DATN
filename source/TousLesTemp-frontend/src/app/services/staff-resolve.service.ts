import { Injectable } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, map, of } from 'rxjs';
import { Product } from '../model/product.model';
import { Route } from '@angular/router';
import { Router } from '@angular/router';
import { ProductsService } from './products.service';
import { ImageProcessingService } from './image-processing.service';
import { StaffService } from './staff.service';
import { Staff } from '../model/staff.model';

@Injectable({
  providedIn: 'root'
})
export class StaffResolveService implements Resolve<Staff>{

  constructor(private staffService: StaffService, private imageProcessingService:ImageProcessingService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Staff> {
    const id = route.paramMap.get("id")
    if (id) {
      return this.staffService.getStaffById(id)
      .pipe(
        map(p=>this.imageProcessingService.createImages(p))
      )
    } else {
      return of(this.getStaffDetail());
    }
  }

  getStaffDetail() {
    return {
      name: "",
      email: "",
      phone: "",
      staffGroup: { id: 1 },
      store: { id: 1, address: {} },
      images: []
    };
  }
}

