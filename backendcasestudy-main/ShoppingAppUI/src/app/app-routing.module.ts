import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { EditProductComponent } from './product/edit-product/edit-product.component';
import { ProductComponent } from './product/product.component';
import { RegisterComponent } from './register/register.component';
import { UpdateUserComponent } from './update-user/update-user.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home',
    children:[{ path: '', component: HomeComponent },
              { path: 'changePassword', component: UpdateUserComponent },
              {path:'product',component:ProductComponent},
              {path:'editProduct',component:EditProductComponent}]},
  { path: 'logout', component: LogoutComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
