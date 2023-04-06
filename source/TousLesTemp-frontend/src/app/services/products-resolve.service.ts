import { Injectable } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, map, of } from 'rxjs';
import { Product } from '../share/product.module';
import { Route } from '@angular/router';
import { Router } from '@angular/router';
import { ProductsService } from './products.service';
import { ImageProcessingService } from './image-processing.service';

@Injectable({
  providedIn: 'root'
})
export class ProductsResolveService implements Resolve<Product>{

  constructor(private productService: ProductsService, private imageProcessingService:ImageProcessingService) { }
    
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product> {
    const id = route.paramMap.get("id")
    if (id) {
      return this.productService.getProductById(id)
      .pipe(
        map(p=>this.imageProcessingService.createImages(p))
      )
    } else {
      return of(this.getProductDetail());
    }
  }

  getProductDetail() {
    return {
      name: "",
      description: "",
      inventory: 0,
      price: 0,
      category: { id: 1 },
      size: { id: 1 },
      store: { id: 1, address: {} },
      images: []
    };
  }
}
