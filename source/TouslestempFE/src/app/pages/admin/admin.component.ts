import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  constructor(private login: LoginService) {}
  isLogin = false;
  account = null;
  ngOnInit(): void {
    this.isLogin = this.login.isLogin();
    this.account = this.login.getUser();
  }

  isSideNavCollapsed = false;
  screenWidth = 0;

  onToggleSideNav(data: SideNavToggle): void {
    this.screenWidth = data.screenWidth;
    this.isSideNavCollapsed = data.collapsed;
  }
}
