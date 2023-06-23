import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Product } from 'src/app/model/Product';
import { AlertService } from 'src/app/Services/alert.service';
import { ProductService } from 'src/app/Services/product.service';
import { ProductComponent } from '../product.component';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  editableProduct:Product;
  status:string;
  editForm: FormGroup;
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
    this.productService.getNewProductInfo().subscribe(productinfo => {
      this.editableProduct = productinfo;
    });
  }

  ngOnInit() {
     this.status="";
     
      this.editForm = this.formBuilder.group({
        productName: [this.editableProduct.productName, Validators.required],
        productDesc: [this.editableProduct.productDesc, Validators.required],
        price: [this.editableProduct.price, [Validators.required]],
        features: [this.editableProduct.features, [Validators.required]],
        status: [this.editableProduct.status, [Validators.required]]
      });
      // console.log(this.editableProduct.status)
      if(this.editableProduct.status=="Hurry up to purchase"){
          this.status='Out of Stock';
      }else{
        this.status='Hurry up to purchase'
      }
      console.log("edit: "+this.status)
  }

  // convenience getter for easy access to form fields
  get f() { return this.editForm.controls; }

  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.editForm.invalid) {
          return;
      }
      this.editForm.value.status=this.status;
      this.loading = true;
      this.newProduct.productName= this.editForm.value.productName;
      this.newProduct.productDesc=this.editForm.value.productDesc;
      this.newProduct.price=this.editForm.value.price;
      this.newProduct.features=this.editForm.value.features;
      this.newProduct.status=this.editForm.value.status;
           
      this.productService.updateProduct(this.editForm.value)
          .subscribe(
              data => {
                  if(data.statuscode == 200 || data.statuscode==undefined){
                    this.alertService.success('Product status updated successful', this.options);
                    this.router.navigate(["home"])
                }
                else{
                    console.log("data: "+data.description)
                    this.alertService.error(data.description,this.options);
                    // setTimeout(() => this.resetForm(), 3000);
                }
              });     
  }

  // reload(){
  //   // this.editForm=new FormGroup(this.newProduct)
  //   this.editForm = new FormGroup({
  //     productName: new FormControl({value: this.newProduct.productName, disabled: true}, Validators.required),
  //     productDesc: new FormControl({value: this.newProduct.productDesc, disabled: true}, Validators.required),
  //     price: new FormControl({value: this.newProduct.price, disabled: true}, Validators.required),
  //     features: new FormControl({value: this.newProduct.features, disabled: true}, Validators.required),
  //     status: new FormControl(this.newProduct.status, Validators.required)
  //   });
  //   if(this.editForm.status=='Hurry up to purchase'){
  //     this.status='Out of Stock';
  // }else{
  //   this.status='Hurry up to purchase'
  // }
  // }

//   resetForm(): void {
//     this.editForm.reset();
//     this.loading=false;
//     this.submitted=false
//  }

}
