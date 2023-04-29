import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  constructor(private token: TokenStorageService) { }
  currentUser: any;
  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }

}

