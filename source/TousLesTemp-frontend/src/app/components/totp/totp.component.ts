import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { environment } from 'src/environment/environment.prod';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-totp',
  templateUrl: './totp.component.html',
  styleUrls: ['./totp.component.scss']
})
export class TotpComponent implements OnInit {


  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: any;
  userEmail?: string;


  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,
   private route: Router, private toast: NgToastService) { }

  otp = new FormControl('', [Validators.required]);
  errror() {
    if (this.otp.hasError('required')) {
      return 'Vui lòng nhập mã OTP';
    }
    return this.otp.hasError('otp') ? 'Không hợp lệ' : '';
  }

  value1?: number;
  login(user: any): void {
    this.tokenStorage.saveUser(user);
    this.isLoginFailed = false;
    this.isLoggedIn = true;
    this.currentUser = this.tokenStorage.getUser();

    if (this.authService.getUserRole() == 'ROLE_ADMIN') {
      this.route.navigate([environment.AUTH_REDIRECT_URL]);
      this.toast.success({ detail: "Đăng nhập thành công", summary: "Chào mừng đến với trang quản trị!", duration: 3000 })
    } else if (this.authService.getUserRole() == 'ROLE_STOREMANAGER') {
      this.route.navigate(["/user"]);
      this.toast.success({ detail: "Đăng nhập thành công", summary: "Chào mừng đến với trang người dùng!", duration: 3000 })
    } else {
      this.authService.isLogout();
    }
  }

  onSubmit(): void {
    this.authService.verify(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.login(data.user);
        this.toast.success({ detail: "Xác thực thành công", summary: "Chào mừng đến với trang quản trị!", duration: 3000 })
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
        this.toast.error({ detail: "Lỗi", summary: "Vui lòng nhập lại mã xác thực !", duration: 3000 })

      }
    );
  }

  ngOnInit(): void {
    if (this.tokenStorage.getUser()) {
      this.isLoggedIn = true;
      this.currentUser = this.tokenStorage.getUser();
    }

  }

}
