import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { AlertService } from '../Services/alert.service';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  title="ShoppingAPP"
  loading = false;
  submitted = false;
  newUser:User= new User();
  options = {
    autoClose: true,
    keepAfterRouteChange: true
   };

  constructor(
      private formBuilder: FormBuilder,
      private router: Router,
      private userService: UserService,
      private alertService: AlertService
  ) { 
    
  }

  ngOnInit() {
      this.registerForm = this.formBuilder.group({
          firstName: ['', Validators.required],
          lastName: ['', Validators.required],
          emailId: ['', [Validators.required,Validators.email]],
          loginId: ['', [Validators.required,Validators.minLength(4)]],
          password: ['', [Validators.required, Validators.minLength(8)]],
          confirmPassword: ['', [Validators.required, Validators.minLength(8)]],
        //   contactNo: ['', [Validators.required, Validators.minLength(10),Validators.maxLength(10)]],
          contactNo: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.registerForm.invalid) {
          return;
      }

      this.loading = true;
      if(this.registerForm.value.confirmPassword==this.registerForm.value.password){ 

            this.newUser.contactNumber= this.registerForm.value.contactNo;
            this.newUser.firstName=this.registerForm.value.firstName;
            this.newUser.lastName=this.registerForm.value.lastName;
            this.newUser.loginId=this.registerForm.value.loginId;
            this.newUser.password=this.registerForm.value.password;
            this.newUser.email=this.registerForm.value.email;

            this.userService.register(this.registerForm.value)
          .subscribe(
              data => {
                  if(data.statuscode == 200 || data.statuscode==undefined){
                    this.alertService.success('Registration successful', this.options);
                    this.router.navigate(['/login']);
                }
                else{
                    console.log("data: "+data.description)
                    this.alertService.error(data.description,this.options);
                    setTimeout(() => this.resetForm(), 3000);
                }
              });

      }else{
          this.alertService.error("Passwords doesn't match",this.options)
      }
     
  }

  resetForm(): void {
    this.registerForm.reset();
    this.loading=false;
    this.submitted=false
 }

}
