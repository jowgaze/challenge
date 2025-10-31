import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { BalanceResponseDto } from '../model/balance';
import { Observable, of } from 'rxjs';
import { Interval } from '../model/interval';

@Injectable({
  providedIn: 'root',
})
export class BalanceService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient);

  public getBalance(storeId: number, interval: Interval): Observable<BalanceResponseDto[]> {
    if(storeId == undefined || interval == undefined)
      return of([]);
      
    return this.httpClient.post<BalanceResponseDto[]>(`${this.apiUrl}/balance/${storeId}`, interval);
  }
}
