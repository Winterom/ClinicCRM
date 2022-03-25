import { Component, OnInit } from '@angular/core';
import {UserAppService} from "../../service/user-app.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  statusCode:number|undefined;
  submitted = false;
  redirectURL:string|undefined;

  constructor( private formBuilder: FormBuilder, private user: UserAppService,private auth: AuthService,private router: Router, private route: ActivatedRoute) {
    this.form = this.formBuilder.group({
      email: ['', Validators.required],
      password:['',Validators.required]
    } );
  }

  ngOnInit(): void {
    let params = this.route.snapshot.queryParams;
    if (params['redirectURL']) {
      this.redirectURL = params['redirectURL'];
    }
    }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.form.invalid) {
      return;
    }
    this.auth.login(this.form.value.email, this.form.value.password).subscribe({
      next: data => {
        console.log(data);
        this.user.saveToken(data.accessToken);
        this.user.setLog(true);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        if (this.redirectURL) {
          this.router.navigateByUrl(this.redirectURL,)
            .catch(() => this.router.navigate(['main']))
        } else {
          this.router.navigate(['main'])
        }
      },
      error: err => {
        this.statusCode= err.status;
        this.isLoginFailed = true;
      }
    });
  }
}
