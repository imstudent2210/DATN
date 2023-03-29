import { Component, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  constructor(private login: LoginService, private route: Router, private toast:NgToastService) {}
  ngOnInit(): void {}
  @Output() profile?:string;

  username = new FormControl('', [Validators.required]);
  hide = true;
  loginData = { username: '', password: '' };


  formSubmit() {
    this.login.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log(data);
        // Swal.fire({
        //   icon: 'success',
        //   title: 'Sign in successfully !',
        //   width:'270px',
        //   showConfirmButton: false,
        //   timer: 1500,
        // });
        this.toast.success({detail:"Success Message", summary:"Sign in successfully !", duration:3000})
        this.login.loginToken(data.token);
        this.profile =  this.loginData.username;
        this.login.getCurrentUser().subscribe((user: any) => {
          this.login.setUser(user);
          console.log(user);
          if (this.login.getUserRole() == 'Admin') {
            this.route.navigate(['home']);
          }  else {
            this.login.isLogout();
          }
        });
      },
      (error) => {
        console.log(error);
        // Swal.fire({
        //   icon: 'error',
        //   text: 'Username or password is incorrect',
        //   width:'270px',
        //   confirmButtonColor:'var(--yellow)',
        // });
        this.toast.error({detail:"Error Message", summary:"Username or password is incorrect !", duration:3000})
      }
    );
  }
}
