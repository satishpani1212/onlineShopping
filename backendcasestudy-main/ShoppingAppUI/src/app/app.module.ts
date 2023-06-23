import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ProductComponent } from './product/product.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AlertComponent } from './alert/alert.component';
import { AlertService } from './Services/alert.service';
import { HeaderComponent } from './header/header.component';
import { LogoutComponent } from './logout/logout.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import {MatTableModule} from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {  MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule, MatPaginatorModule,  MatSortModule } from '@angular/material';
import { EditProductComponent } from './product/edit-product/edit-product.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    ProductComponent,
    AlertComponent,
    HeaderComponent,
    LogoutComponent,
    UpdateUserComponent,
    EditProductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule
  ],
  providers: [AlertService],
  bootstrap: [AppComponent]
})
export class AppModule { }
