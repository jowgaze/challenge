import { Component, inject, OnInit } from '@angular/core';
import { ProductService } from '../../../../core/services/product.service';
import { Observable } from 'rxjs';
import { TopProductResponseDto } from '../../../../core/model/product';
import { AsyncPipe } from '@angular/common';
import { ProductItem } from "../product-item/product-item";
import { ChannelFilter } from "../channel-filter/channel-filter";

@Component({
  selector: 'app-card-top-products',
  imports: [AsyncPipe, ProductItem, ChannelFilter],
  templateUrl: './card-top-products.html',
  styleUrl: './card-top-products.css',
})
export class CardTopProducts {
  topProducts = new Observable<TopProductResponseDto[]>();
  private productService = inject(ProductService);

  ngOnInit(): void {
    this.topProducts = this.productService.getTopProducts("1");
  }

  onFilerSelected(id: string){
    this.topProducts = this.productService.getTopProducts(id);
  }
}

