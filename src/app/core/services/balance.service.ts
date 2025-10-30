import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { BalanceRequestDto, BalanceResponseDto } from '../model/balance';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BalanceService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient)

  public getBalance(request: BalanceRequestDto): Observable<BalanceResponseDto[]>{
    return this.httpClient.post<BalanceResponseDto[]>(`${this.apiUrl}/balance`, request);
  }
}
