import { Component, input } from '@angular/core';
import { TopProductResponseDto } from '../../../../core/model/product';

@Component({
  selector: 'app-product-item',
  imports: [],
  templateUrl: './product-item.html',
  styleUrl: './product-item.css',
})
export class ProductItem {
  readonly product = input.required<TopProductResponseDto>();
}
