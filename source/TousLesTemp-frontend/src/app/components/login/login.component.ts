import { Component, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  constructor(private route1: Router, private authService: AuthService,
    private tokenStorage: TokenStorageService, private toast: NgToastService,
    private route: ActivatedRoute,
    private userService: UserService) { }

  @Output() profile?: string;

  username = new FormControl('', [Validators.required]);
  password = new FormControl('', [Validators.required]);
  hide = true;
  loginData = { username: '', password: '' };
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: any;
  errror() {
    if (this.username.hasError('required')) {
      return 'Tên đăng nhập không được để trống';
    }
    return this.username.hasError('username') ? 'Không hợp lệ' : '';
  }
  errrorPassword() {
    if (this.password.hasError('required')) {
      return 'Mật khẩu không được để trống';
    }
    return this.password.hasError('password') ? 'Không hợp lệ' : '';
  }



  login(user: any): void {
    this.tokenStorage.saveUser(user);
    this.isLoginFailed = false;
    this.isLoggedIn = true;
    this.currentUser = this.tokenStorage.getUser();
    window.location.reload();
  }
  formSubmit(): void {
    this.authService.login(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        if(data.authenticated){
	        this.login(data.user);
        } else {
        	this.route1.navigate(['/otp']);
        }
        this.toast.info({detail:"Đăng nhập", summary:"Vui lòng nhập mã xác thực!", duration:3000})
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
        this.toast.error({detail:"Lỗi", summary:"Tài khoản không tồn tại !", duration:3000})
      }
    );
  }


  //=========================
  ngOnInit(): void {
    const token: string = this.route.snapshot.queryParamMap.get('token')!;
    const error: string = this.route.snapshot.queryParamMap.get('error')!;
    if (this.tokenStorage.getUser()) {
      this.isLoggedIn = true;
      this.currentUser = this.tokenStorage.getUser();
    }
    else if (token) {
      this.tokenStorage.saveToken(token);
      this.userService.getCurrentUser().subscribe(
        data => {
          this.login(data);
        },
        err => {
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        }
      );
    }
    else if (error) {
      this.errorMessage = error;
      this.isLoginFailed = true;
    }
  }
}
