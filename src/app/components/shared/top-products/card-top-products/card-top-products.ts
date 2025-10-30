import { Component, effect, inject, input, OnInit } from '@angular/core';
import { ProductService } from '../../../../core/services/product.service';
import { Observable } from 'rxjs';
import { TopProductResponseDto } from '../../../../core/model/product';
import { AsyncPipe } from '@angular/common';
import { ProductItem } from "../product-item/product-item";
import { ChannelFilter } from "../channel-filter/channel-filter";
import { Interval } from '../../../../core/model/interval';

@Component({
  selector: 'app-card-top-products',
  imports: [AsyncPipe, ProductItem, ChannelFilter],
  templateUrl: './card-top-products.html',
  styleUrl: './card-top-products.css',
})
export class CardTopProducts {
  readonly interval = input.required<Interval>();
  id: string = "0";
  topProducts = new Observable<TopProductResponseDto[]>();
  private productService = inject(ProductService);

  constructor() {
    effect(() => {
      this.topProducts = this.productService.getTopProducts(this.id, this.interval());
    });
  }

  onFilerSelected(id: string){
    this.id = id;
    this.topProducts = this.productService.getTopProducts(this.id, this.interval());
  }
}

