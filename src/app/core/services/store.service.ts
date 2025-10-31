import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { StoreResponseDto } from '../model/store';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StoreService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient);

  public getStores(): Observable<StoreResponseDto[]>{
      return this.httpClient.get<StoreResponseDto[]>(`${this.apiUrl}/stores`);
    }
}
