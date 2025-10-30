import { Component, inject, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { BalanceRequestDto, BalanceResponseDto } from '../../../../core/model/balance';
import { BalanceService } from '../../../../core/services/balance.service';
import { AsyncPipe } from '@angular/common';
import { DateSelect } from '../date-select/date-select';
import { BalanceItem } from "../balance-item/balance-item";

@Component({
  selector: 'app-card-balance',
  imports: [AsyncPipe, DateSelect, BalanceItem],
  templateUrl: './card-balance.html',
  styleUrl: './card-balance.css',
})
export class CardBalance {
  balance = new Observable<BalanceResponseDto[]>();
  private balanceService = inject(BalanceService);

  getBalance(request: BalanceRequestDto) {
    this.balance = this.balanceService.getBalance(request);
  }

  onRangeSelected(range: BalanceRequestDto) {
    this.getBalance(range);
  }
}
