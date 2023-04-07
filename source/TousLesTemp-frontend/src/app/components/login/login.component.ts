import { Component, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService, SocialUser } from '@abacritt/angularx-social-login';
import { NgToastService } from 'ng-angular-popup';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  constructor( private login: LoginService, private route: Router,
               private toast:NgToastService, private socialService: SocialAuthService) {}

  @Output() profile?:string;

  username = new FormControl('', [Validators.required]);
  password = new FormControl('', [Validators.required]);
  hide = true;
  loginData = { username: '', password: '' };
  // GoogleLoginProvider = GoogleLoginProvider;

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


  formSubmit() {
    this.login.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log(data);
        this.toast.success({detail:"Thành công", summary:"Đăng nhập quản trị thành công!", duration:3000})
        this.login.loginToken(data.token);
        this.profile =  this.loginData.username;
        this.login.getCurrentUser().subscribe((user: any) => {
          this.login.setUser(user);
          console.log(user);
          if (this.login.getUserRole() == 'Admin') {
            this.route.navigate(['home/stores/list']);
          }  else {
            this.login.isLogout();
          }
        });
      },
      (error) => {
        console.log(error);
        this.toast.error({detail:"Lỗi", summary:"Tài khoản không tồn tại !", duration:3000})
      }
    );
  }


  //================== Social Login=================
  user?: SocialUser;
  loggedIn?: boolean;
  // GoogleLoginProvider?:any;
  signInWithGoogle(): void {
    this.socialService.signIn(GoogleLoginProvider.PROVIDER_ID).then(
      (user)=>{
        this.login.googleSignIn(user.idToken).subscribe(
          (res)=>{
            console.log(res);
            this.route.navigate(['home']);
          },
          (error)=>{
            console.log(error);
          }
        )
      }
    );
  }

  // signInWithFB(): void {
  //   this.socialService.signIn(FacebookLoginProvider.PROVIDER_ID);
  // }

  signOut(): void {
    this.socialService.signOut();
  }
  refreshToken(): void {
    this.socialService.refreshAuthToken(GoogleLoginProvider.PROVIDER_ID);
  }




//=========================
  ngOnInit(): void {
    this.socialService.authState.subscribe((user) => {
      this.user = user;
      this.loggedIn = (user != null);
    });
  }
}
