import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { TopProductResponseDto } from '../model/product';
import { Observable } from 'rxjs';
import { Interval } from '../model/interval';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient)
  
  public getTopProducts(channelId: string, interval: Interval): Observable<TopProductResponseDto[]>{
    return this.httpClient.post<TopProductResponseDto[]>(`${this.apiUrl}/top-products/${channelId}`, interval);
  }
}
