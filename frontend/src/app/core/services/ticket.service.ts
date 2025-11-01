import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Interval } from '../model/interval';
import { Observable, of } from 'rxjs';
import { TicketResponseDto } from '../model/ticket';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient);

  public getTickets(storeId: number, channelId: number, interval: Interval): Observable<TicketResponseDto[]>{
      if(storeId == undefined || channelId == undefined || interval == undefined)
        return of([]);
          
      return this.httpClient.post<TicketResponseDto[]>(`${this.apiUrl}/ticket/${storeId}/${channelId}`, interval);
    }
}
