import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChannelResponseDto } from '../model/channel';

@Injectable({
  providedIn: 'root',
})
export class ChannelService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient);

  public getChannels(): Observable<ChannelResponseDto[]> {
    return this.httpClient.get<ChannelResponseDto[]>(`${this.apiUrl}/channels`);
  }
}
