import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {FormControl, NgForm, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../services/authentication.service';
import {EmployeeService} from '../../services/employee.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup;  
   username: string;
   password: string;
  
  
  constructor(private AuthenticationService : AuthenticationService, private EmployeeService : EmployeeService, private route : ActivatedRoute, private myRouter : Router) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      username : new FormControl('', [Validators.required]),
      password : new FormControl('', [Validators.required])});
  }
  public hasError = (controlName: string, errorName: string) =>{
    return this.loginForm.controls[controlName].hasError(errorName);
  }
  handleLogin = (form: NgForm) => {
      this.AuthenticationService.login(this.username, this.password)
      .subscribe((response) => {
        this.EmployeeService.access_token = response.access_token;
        this.myRouter.navigate(['home'])          
      }, (error) => {
  
      })
    }
}
