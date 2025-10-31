import { Component, input } from '@angular/core';
import { BalanceResponseDto } from '../../../../core/model/balance';

@Component({
  selector: 'app-balance-item',
  imports: [],
  templateUrl: './balance-item.html',
  styleUrl: './balance-item.css',
})
export class BalanceItem {
  readonly balance = input.required<BalanceResponseDto>();
}
