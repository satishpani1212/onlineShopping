import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/User';


@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  
  loginUrl="http://13.234.66.248:8090/api/v1.0/shopping/login/";
  registerUrl="http://13.234.66.248:8090/api/v1.0/shopping/register/";
  changePwdUrl="http://13.234.66.248:8090/api/v1.0/shopping/";

  headers= new HttpHeaders().set('content-type','application/json').set('Access-Control-Allow-Origin','*');

  
  register(user: User) {
      return this.http.post<any>(this.registerUrl,user,{'headers':this.headers});
  }


  login(uname: any, pwd: any,role:any) {
      const user={
        "loginId":uname,
        "password":pwd,
        "role":role
      }
      console.log(user);
      return this.http.post<any>(this.loginUrl,user);
  }

  changePassword(newUser: User) {
    return this.http.put<any>(this.changePwdUrl+sessionStorage.getItem("username")+"/forgot",newUser)
  }

  constructor(private http: HttpClient) { }
}
