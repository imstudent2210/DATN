import { Injectable } from '@angular/core';
import { Product } from '../model/product.model';
import { FileHandle } from '../model/file-handle.model';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor(private sanitizer: DomSanitizer) { }

  public createImages(product: any) {
    const productImages: any[] = product.images;
    const productImagesToFileHandle: FileHandle[] = [];

    for (let index = 0; index < productImages.length; index++) {
      const imageFileData = productImages[index];
      const imageBlog = this.dataURItoBlog(imageFileData.picByte, imageFileData.type);
      const imageFile = new File([imageBlog], imageFileData.name, { type: imageFileData.type });

      const finalFileHandle: FileHandle = {
        file: imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      };

      productImagesToFileHandle.push(finalFileHandle);
    }

    product.images = productImagesToFileHandle;
    return product;
  }

  public dataURItoBlog(picBytes: any, imageType: any) {
    const byteString = window.atob(picBytes);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);

    for (let index = 0; index < byteString.length; index++) {
      int8Array[index] = byteString.charCodeAt(index);
    }

    const blob = new Blob([int8Array], { type: imageType });
    return blob;
  }
}
