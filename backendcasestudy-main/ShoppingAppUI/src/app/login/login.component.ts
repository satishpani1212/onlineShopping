import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '../Services/alert.service';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    isAdmin: boolean=false;
    isRegister: boolean=false;
    options = {
        autoClose: true,
        keepAfterRouteChange: true
    };

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private userService:UserService,
        private alertService: AlertService
    ) {
      
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }
        let role;
        this.loading = true;
        if(this.isAdmin==true){
            role='Admin'
        }else{
            role='CUST'
        }
        this.userService.login(this.f.username.value, this.f.password.value,role)
            .subscribe(
                (data:any) => {
                    if(data.statuscode == 200 || data.statuscode==undefined){
                        this.alertService.success("User Logged in Sucessfully!!",this.options)
                        sessionStorage.setItem("username",data.loginId)
                        sessionStorage.setItem("role",data.role)
                        sessionStorage.setItem("email",data.emailId)
                        this.router.navigate(['home']);
                    }
                    else{
                        console.log("data: "+data.description)
                        this.alertService.error(data.description,this.options);
                        setTimeout(() => this.resetForm(), 3000);
                    }
                });
    }
    resetForm(): void {
       this.loginForm.reset();
       this.loading=false;
       this.submitted=false
    }

    loginType(type){
                
        if(type=="Admin"){
            this.isAdmin=true;
        }else{
            this.isAdmin=false;
        }
        this.resetForm();
            
    }
}
