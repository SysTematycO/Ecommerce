import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';
import { CommonModule } from '@angular/common';
import { BuyProduct } from '../../models/buyProduct.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  productForm: FormGroup;
  errorAddProduct!: string;

  products!: any[];
  productsCopy!: any[];
  filteredProducts: any[] = [];

  showPopoverBuy: boolean = false;
  checkSell!: string;

  searchTerm!: string;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router,
    private productService: ProductService
  ) {
    this.productForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      price: ['', [Validators.required]],
      quantity: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.getAllProducts();
  }

  getAllProducts() {
    this.productService.getAllProduct().subscribe(
      {
        next: data => {
          this.products = data;
          this.filteredProducts = data;
        }
      }
    )
  }

  addProduct() {
    if (this.productForm.valid) {
      const formData = this.productForm.value;

      const currentDate = new Date();

      this.productService.addProduct(
        new Product(
          '',
          this.userService.getUser().getEmail(),
          formData.name, formData.description,
          formData.price,
          currentDate,
          formData.quantity)).subscribe(
            {
              next: data => {
                if (data.status === 'success') {
                  this.getAllProducts();
                  this.errorAddProduct = '';
                  this.showPopoverBuy = true;
                  this.checkSell = data.message
                } else {
                  this.errorAddProduct = data.message;
                }
              }
            }
          )
    }
  }

  buyProduct(product: any) {
    console.log(product)
    this.productService.buyProduct(new BuyProduct(
      product.idProduct,
      this.userService.getUser().getEmail(),
      product.email,
      product.name,
      product.price,
      product.publication_date,
      product.quantityToBuy)).subscribe(
        {
          next: data => {
            this.showPopoverBuy = true;

            if (data.status === 'success') {
              this.getAllProducts();
            }
            this.checkSell = data.message;
          }
        }
      )
  }

  deleteProduct(product: any) {
    this.productService.deleteProduc(product.idProduct).subscribe(
      {
        next: data => {
          this.showPopoverBuy = true;

          if (data.status === 'success') {
            this.getAllProducts();
          }
          this.checkSell = data.message;
        }
      }
    )
  }

  filterProducts() {
    const term = this.searchTerm.toLowerCase();
    
    this.filteredProducts = this.products.filter(product => {
      return Object.values(product).some(val => 
        val != null && val.toString().toLowerCase().includes(term)
      );
    });
  }
  

  closePopoverBuy() {
    this.showPopoverBuy = false;
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
