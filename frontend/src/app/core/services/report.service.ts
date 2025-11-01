import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ReportRequestDto } from '../model/report';

@Injectable({
  providedIn: 'root',
})
export class ReportService {
  private apiUrl = environment.api;
  private httpClient = inject(HttpClient);

  downloadReport(request: ReportRequestDto): Observable<Blob> {
    return this.httpClient.post(`${this.apiUrl}/report`, request, {
      responseType: 'blob',
    });
  }
}
