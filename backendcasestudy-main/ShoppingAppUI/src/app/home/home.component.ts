import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { Router } from '@angular/router';
import { Product } from '../model/Product';
import { ProductService } from '../Services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  count:number=0;
  role:string;
  displayedColumns = ['Name', 'Features', 'Status','Price'];
  dataSource: MatTableDataSource<Product>;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private productService:ProductService, private router: Router) {
  }

  ngOnInit(): void {
    this.role=sessionStorage.getItem("role");
    console.log("role: "+this.role);
    if(this.role=='Admin'){
      this.displayedColumns = ['Name', 'Features', 'Status','Price','Actions'];
    }
    this.productService.getAllProducts().subscribe(
      (data:any) => {
        this.dataSource = new MatTableDataSource(data);
        this.count=this.dataSource.data.length
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      (error)=>{
        console.log("error: "+error);
      }
    )
  }

  /**
   * Set the paginator and sort after the view init since this component will
   * be able to query its view for the initialized paginator and sort.
   */
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  editUser(row:Product){
    this.productService.setNewProductInfo(row);
    // this.router.navigate(['editProduct'])
  }
}

