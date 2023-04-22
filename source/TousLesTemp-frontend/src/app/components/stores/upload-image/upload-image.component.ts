import { Component, EventEmitter, Input, OnInit } from '@angular/core';
import { AngularFireDatabase } from '@angular/fire/compat/database';
import { AngularFireStorage, AngularFireStorageReference } from '@angular/fire/compat/storage';
import { Observable, finalize, map } from 'rxjs';
import { FileUploadService } from 'src/app/services/file-upload.service';
import { FileUpload } from 'src/app/model/file-upload.model';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.scss']
})
export class UploadImageComponent implements OnInit {

  constructor(private storage: AngularFireStorage) { }

  ngOnInit(): void {

  }
  private firebasePath = '/uploads';

  fileUpload?: any[];
  imageUrl?: string;
  fireBaseUrl?:string;
  downloadURL?: Observable<string>;
  percentage = 0;



  onFileSelected(event:any) {
    const file = event.target.files[0];
    const filePath = `${this.firebasePath}/${file.name}`;
    const fileRef = this.storage.ref(filePath);
    const uploadTask = this.storage.upload(filePath, file);
    uploadTask
      .snapshotChanges()
      .pipe(
        finalize(() => {
          this.downloadURL = fileRef.getDownloadURL();
          this.downloadURL.subscribe(url => {
            if (url) {
              this.fireBaseUrl = url;
            }
            console.log(this.fireBaseUrl);
          });
        })
      )
      // .subscribe(url => {
      //   if (url) {
      //     console.log(url);
      //   }
      // });


  }
}

