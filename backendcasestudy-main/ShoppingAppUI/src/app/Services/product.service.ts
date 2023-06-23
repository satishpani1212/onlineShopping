import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Product } from '../model/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  allProductsURL='http://13.234.66.248:8090/api/v1.0/shopping/all/';
  addProductURL='http://13.234.66.248:8090/api/v1.0/shopping/add/';
  updateProductURL='http://13.234.66.248:8090/api/v1.0/shopping/';

  constructor(private http: HttpClient) { }


  private newProduct = new BehaviorSubject<Product>(new Product());

  setNewProductInfo(product: Product) {
    this.newProduct.next(product);
  }

  getNewProductInfo() {
    return this.newProduct.asObservable();
  }

  getAllProducts(){
    return this.http.get<Product[]>(this.allProductsURL)
  }

  addProduct(product:Product){
    return this.http.post<any>(this.addProductURL,product);
  }

  updateProduct(product:Product){
    return this.http.put<any>(this.updateProductURL+product.productName+"/update/",product);
  }


}
