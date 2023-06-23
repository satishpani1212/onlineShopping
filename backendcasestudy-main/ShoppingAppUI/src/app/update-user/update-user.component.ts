import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/User';
import { AlertService } from '../Services/alert.service';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  
  pwdChangeForm: FormGroup;
  loading = false;
  submitted = false;
  options = {
      autoClose: true,
      keepAfterRouteChange: true
  };
  newUser:User= new User();

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private userService:UserService,
      private alertService: AlertService
  ) {
    
  }

  ngOnInit() {
      this.pwdChangeForm = this.formBuilder.group({
        confPassword: ['', Validators.required],
          password: ['', Validators.required]
      });

  }

  // convenience getter for easy access to form fields
  get f() { return this.pwdChangeForm.controls; }

  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.pwdChangeForm.invalid) {
          return;
      }
 
      this.loading = true;

      if(this.pwdChangeForm.value.confPassword==this.pwdChangeForm.value.password){ 

        this.newUser.password=this.pwdChangeForm.value.password;
        this.newUser.email=sessionStorage.getItem("email");
        
      this.userService.changePassword(this.newUser)
          .subscribe(
              (data:any) => {
                  if(data.statuscode == 200 || data.statuscode==undefined){
                      this.alertService.success("Password Updated Sucessfully!!",this.options)
                      this.router.navigate(['home']);
                  }
                  else{
                      console.log("data: "+data.description)
                      this.alertService.error(data.description,this.options);
                      setTimeout(() => this.resetForm(), 3000);
                  }
              });
            }else{
              this.alertService.error("Passwords Doesn't match",this.options);
                      setTimeout(() => this.resetForm(), 3000);
            }
  }
  resetForm(): void {
     this.pwdChangeForm.reset();
     this.loading=false;
     this.submitted=false
  }



}
