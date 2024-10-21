import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';
import { CommonModule } from '@angular/common';
import { BuyProduct } from '../../models/buyProduct.model';
import { AuditService } from '../../services/audit.service';
import { OffersService } from '../../services/offers.service';

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
  offers!: any[];

  showPopoverBuy: boolean = false;
  showBestSellers: boolean = false;
  showInfo: boolean = false;
  checkSell!: string;

  searchTerm!: string;
  clientsPopular!: string;

  percentajeDiscount: number = 0;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router,
    private productService: ProductService, private auditService: AuditService, private offersService: OffersService
  ) {
    this.productForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      price: ['', [Validators.required]],
      quantity: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.getOffers();
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

  getAllProductsPopular() {
    this.productService.getAllProductsPopular().subscribe(
      {
        next: data => {
          this.products = data;
          this.filteredProducts = data;
        }
      }
    )
  }

  getOffers() {
    this.offersService.getOffers().subscribe(
      {
        next: data => {
          this.offers = data;
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

  async buyProduct(product: any) {
    
    const priceTotal: number = await this.checkDiscount(product.price);

    this.productService.buyProduct(new BuyProduct(
      product.idProduct,
      this.userService.getUser().getEmail(),
      product.email,
      product.name,
      priceTotal,
      product.publication_date,
      product.quantityToBuy)).subscribe(
        {
          next: data => {
            this.showPopoverBuy = true;

            if (data.status === 'success') {
              this.getAllProducts();
            }
            this.checkSell = data.message + " Se le aplico un descuento del " + this.percentajeDiscount + "%";
            this.percentajeDiscount = 0;
          }
        }
      )
      
  }

  async checkDiscount(originalPrice: number): Promise<number> {

    let discountedPrice: number = originalPrice;

    let discount = this.getPropertyDiscount("GENERAL_DISCOUNT");

    if (discount) {
      this.percentajeDiscount += discount.discountPercentage;
    }

    discount = this.getPropertyDiscount("FREQUENT_CUSTOMER_DISCOUNT");

    await this.getClientPopular(false);

    const isClientPopular = this.clientsPopular.includes(this.userService.getUser().getEmail());

    if (isClientPopular && discount) {
      this.percentajeDiscount += discount.discountPercentage;
    }

    discountedPrice = originalPrice - (originalPrice * (this.percentajeDiscount / 100));

    return discountedPrice
  }

  getPropertyDiscount(property: string): any {

    const currentDate = new Date().getTime();
    const generalDiscountOffer = this.offers.find(offer => offer.typeOffer === property &&
      currentDate >= offer.startDate &&
      currentDate <= offer.endDate);

    return generalDiscountOffer;
  }

  deleteProduct(product: any) {
    this.productService.deleteProduct(product.idProduct).subscribe(
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

    let term: string = '';

    if (this.searchTerm !== undefined) {
      term = this.searchTerm.toLowerCase();
    }

    this.filteredProducts = this.products.filter(product => {
      return Object.values(product).some(val =>
        val != null && val.toString().toLowerCase().includes(term)
      );
    });
  }

  filterProductsPopular() {
    if (this.showBestSellers) {
      this.getAllProductsPopular();
    } else {
      this.getAllProducts();
    }
  }

  getClientPopular(activateInfoBox: boolean): Promise<void> {
    return new Promise((resolve, reject) => {
      this.auditService.getClientPopular().subscribe({
        next: data => {
          this.showInfo = activateInfoBox;
          this.clientsPopular = data;
          resolve();
        },
        error: err => {
          reject(err);
        }
      });
    });
  }

  toggleInfo() {
    this.showInfo = false;
  }

  closePopoverBuy() {
    this.showPopoverBuy = false;
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
