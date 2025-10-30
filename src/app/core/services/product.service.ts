import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { TopProductResponseDto } from '../model/product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient)
  
  public getTopProducts(channelId: string): Observable<TopProductResponseDto[]>{
    return this.httpClient.get<TopProductResponseDto[]>(`${this.apiUrl}/top-products/${channelId}`);
  }
}
