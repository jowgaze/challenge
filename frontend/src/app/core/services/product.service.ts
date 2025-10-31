import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { TopProductResponseDto } from '../model/product';
import { Observable, of } from 'rxjs';
import { Interval } from '../model/interval';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient)
  
  public getTopProducts(storeId: number, channelId: number, interval: Interval): Observable<TopProductResponseDto[]>{
    if(storeId == undefined || channelId == undefined || interval == undefined)
      return of([]);
        
    return this.httpClient.post<TopProductResponseDto[]>(`${this.apiUrl}/top-products/${storeId}/${channelId}`, interval);
  }
}
