import { Component, effect, inject, input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { BalanceResponseDto } from '../../../../core/model/balance';
import { BalanceService } from '../../../../core/services/balance.service';
import { AsyncPipe } from '@angular/common';
import { BalanceItem } from '../balance-item/balance-item';
import { Interval } from '../../../../core/model/interval';

@Component({
  selector: 'app-card-balance',
  imports: [AsyncPipe, BalanceItem],
  templateUrl: './card-balance.html',
  styleUrl: './card-balance.css',
})
export class CardBalance {
  readonly storeId = input.required<number>();
  readonly interval = input.required<Interval>();
  private balanceService = inject(BalanceService);
  balance = new Observable<BalanceResponseDto[]>();

  constructor() {
    effect(() => {
      this.balance = this.balanceService.getBalance(this.storeId(), this.interval());
    });
  }
}
