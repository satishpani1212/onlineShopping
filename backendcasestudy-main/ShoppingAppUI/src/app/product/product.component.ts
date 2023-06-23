import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Product } from '../model/Product';
import { AlertService } from '../Services/alert.service';
import { ProductService } from '../Services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  productForm: FormGroup;
  loading = false;
  submitted = false;
  newProduct:Product= new Product();
  options = {
    autoClose: true,
    keepAfterRouteChange: true
   };

  constructor(
      private formBuilder: FormBuilder,
      private router: Router,
      private productService: ProductService,
      private alertService: AlertService
  ) { 
    
  }

  ngOnInit() {
      this.productForm = this.formBuilder.group({
        productName: ['', Validators.required],
        productDesc: ['', Validators.required],
        price: ['', [Validators.required]],
        features: ['', [Validators.required]],
        status: ['', [Validators.required]]
      });
  }

  // convenience getter for easy access to form fields
  get f() { return this.productForm.controls; }

  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.productForm.invalid) {
          return;
      }

      this.loading = true;
      this.newProduct.productName= this.productForm.value.productName;
      this.newProduct.productDesc=this.productForm.value.productDesc;
      this.newProduct.price=this.productForm.value.price;
      this.newProduct.features=this.productForm.value.features;
      this.newProduct.status=this.productForm.value.status;
           
      this.productService.addProduct(this.productForm.value)
          .subscribe(
              data => {
                  if(data.statuscode == 200 || data.statuscode==undefined){
                    this.alertService.success('Product added successful', this.options);
                    this.router.navigate(['/home']);
                }
                else{
                    console.log("data: "+data.description)
                    this.alertService.error(data.description,this.options);
                    setTimeout(() => this.resetForm(), 3000);
                }
              });     
  }

  resetForm(): void {
    this.productForm.reset();
    this.loading=false;
    this.submitted=false
 }

}
