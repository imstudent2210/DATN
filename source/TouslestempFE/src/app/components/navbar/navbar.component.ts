import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
[x: string]: any;
  constructor(private login:LoginService, private route:Router){

  }

  isLogin = false;
  user = null;
  username = null;
  email = null;
  loginData = { username: '', password: '' };

  ngOnInit():void{
    this.isLogin = this.login.isLogin();
    this.user = this.login.getUser();
    this.username = this.login.getUserName();
    this.email = this.login.getEmail();
  }
  logout(){
    this.login.isLogout();
    this.isLogin = false;
    this.user = null;
    this.route.navigate(['login']);
  }
}
