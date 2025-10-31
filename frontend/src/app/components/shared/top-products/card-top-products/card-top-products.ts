import { Component, effect, inject, input, OnInit } from '@angular/core';
import { ProductService } from '../../../../core/services/product.service';
import { Observable } from 'rxjs';
import { TopProductResponseDto } from '../../../../core/model/product';
import { AsyncPipe } from '@angular/common';
import { ProductItem } from "../product-item/product-item";
import { Interval } from '../../../../core/model/interval';

@Component({
  selector: 'app-card-top-products',
  imports: [AsyncPipe, ProductItem],
  templateUrl: './card-top-products.html',
  styleUrl: './card-top-products.css',
})
export class CardTopProducts {
  readonly storeId = input.required<number>();
  readonly channelId = input.required<number>();
  readonly interval = input.required<Interval>();
  topProducts = new Observable<TopProductResponseDto[]>();
  private productService = inject(ProductService);

  constructor() {
    effect(() => {
      this.topProducts = this.productService.getTopProducts(this.storeId(), this.channelId(), this.interval());
    });
  }
}

