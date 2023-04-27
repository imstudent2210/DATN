import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { NgToastService } from 'ng-angular-popup';
import { Mail } from 'src/app/model/mail.model';
import { AuthService } from 'src/app/services/auth.service';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  constructor(private authService: AuthService,  private toast: NgToastService) { }

  ngOnInit(): void {
  }

  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  isUsing2FA = false;
  errorMessage = '';
  qrCodeImage = '';
  hide = true;

  namef = new FormControl('', [Validators.required]);
  addressDetailf = new FormControl('', [Validators.required]);
  matcher = new MyErrorStateMatcher();
  passwordf = new FormControl('', [Validators.required]);
  email = new FormControl('', [Validators.required,Validators.email, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]);
  sendmail =  this.form.email;

  clientMail: Mail = {
    displayName:"",
    email:"",
    username:"",
    password:""

  }
  errror() {
    if (this.passwordf.hasError('required')) {
      return 'Mật khẩu ít nhất 6 ký tự';
    }
    return this.passwordf.hasError('password') ? 'Không hợp lệ' : '';
  }
  errrorPassword() {
    if (this.passwordf.hasError('required')) {
      return 'Mật khẩu ít nhất 6 ký tự';
    }
    return this.passwordf.hasError('password') ? 'Không hợp lệ' : '';
  }
  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'Vui lòng nhập email';
    }

    return this.email.hasError('email') ? 'Email không hợp lệ' : '';
  }


  onSubmit(): void {
    this.authService.register(this.form).subscribe(
      data => {
        console.log(data);
        if(data.using2FA){
        	this.isUsing2FA = true;
        	this.qrCodeImage = data.qrCodeImage;
          this.toast.success({detail:"Thành công", summary:"Đăng ký thành công!", duration:3000})

        }
	      this.isSuccessful = true;
        this.isSignUpFailed = false;
        console.log(this.form.email);

        this.clientMail.email = this.form.email;
        this.clientMail.password =this.form.password;
        this.clientMail.displayName = this.form.displayName
        this.clientMail.username = this.form.email;

        this.authService.sendmail(this.clientMail).subscribe(
          (data)=>{
            console.log("Gửi mail thành công");
          }
        )
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
        this.toast.error({detail:"Lỗi", summary:"Vui lòng kiểm tra thông tin!", duration:3000})

      }
    );
  }

}
