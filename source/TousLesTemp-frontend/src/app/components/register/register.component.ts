import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { NgToastService } from 'ng-angular-popup';
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
  password = new FormControl('', [Validators.required]);
  email = new FormControl('', [Validators.required,Validators.email, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]);
  sendmail =  this.form.email;
  errror() {
    if (this.password.hasError('required')) {
      return 'Mật khẩu không được để trống';
    }
    return this.password.hasError('password') ? 'Không hợp lệ' : '';
  }
  errrorPassword() {
    if (this.password.hasError('required')) {
      return 'Mật khẩu không được để trống';
    }
    return this.password.hasError('password') ? 'Không hợp lệ' : '';
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
        this.authService.sendmail(this.sendmail).subscribe(
          (data)=>{
            this.toast.success({ detail: "Thông báo thành công", summary: " Đã gửi thư xác nhận!", duration: 3000 })
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
