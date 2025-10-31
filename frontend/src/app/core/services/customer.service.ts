import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { InactiveCustomerDto } from '../model/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient)
  
  public getInactiveCustomers(storeId: number, channelId: number): Observable<InactiveCustomerDto[]>{
    if(storeId == undefined || channelId == undefined)
          return of([]);

    return this.httpClient.get<InactiveCustomerDto[]>(`${this.apiUrl}/inactive-customers/${storeId}/${channelId}`);
  }
}
