import { Component, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  constructor(private login: LoginService, private route: Router) {}
  ngOnInit(): void {}
  username = new FormControl('', [Validators.required]);
  hide = true;
  loginData = { username: '', password: '' };
  // @Output() profile?:string;
  formSubmit() {
    this.login.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log(data);
        Swal.fire({
          icon: 'success',
          title: 'Sign in successfully !',
          width:'250px',
          showConfirmButton: false,
          timer: 1500,
        });
        this.login.loginToken(data.token);
        // console.log(this.loginData.username);
        // this.profile =  this.loginData.username;
        this.login.getCurrentUser().subscribe((user: any) => {
          this.login.setUser(user);
          console.log(user);
          if (this.login.getUserRole() == 'Admin') {
            this.route.navigate(['admin']);
          }  else {
            this.login.isLogout();
          }
        });
      },
      (error) => {
        console.log(error);
        Swal.fire({
          icon: 'error',
          text: 'Username or password is incorrect !!',
          width:'250px',
          confirmButtonColor:'var(--yellow)',
        });
      }
    );
  }
}
