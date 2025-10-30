import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InactiveCustomerDto } from '../model/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient)
  
  public getTopProducts(): Observable<InactiveCustomerDto[]>{
    return this.httpClient.get<InactiveCustomerDto[]>(`${this.apiUrl}/inactive-customers`);
  }
}
