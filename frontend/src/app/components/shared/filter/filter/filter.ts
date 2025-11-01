import { Component, inject, output } from '@angular/core';
import { SearchStore } from '../search-store/search-store';
import { DateSelect } from '../date-select/date-select';
import { ChannelFilter } from '../channel-filter/channel-filter';
import { Interval } from '../../../../core/model/interval';
import { MatAnchor } from "@angular/material/button";
import { ReportService } from '../../../../core/services/report.service';
import { ReportRequestDto } from '../../../../core/model/report';

@Component({
  selector: 'app-filter',
  imports: [SearchStore, DateSelect, ChannelFilter, MatAnchor],
  templateUrl: './filter.html',
  styleUrl: './filter.css',
})
export class Filter {
  storeId!: number;
  channelId!: number;
  interval!: Interval;
  disableDownload = false;

  storeIdOutput = output<number>();
  channelIdOutput = output<number>();
  intervalOutput = output<Interval>();

  reportService = inject(ReportService);

  clickOutput(){
    this.storeIdOutput.emit(this.storeId);
    this.channelIdOutput.emit(this.channelId);
    this.intervalOutput.emit(this.interval);
    this.disableButtonReport()
  }

  getStoreId(storeId: number) {
    this.storeId = storeId;
  }

  getChannelId(channelId: number) {
    this.channelId = channelId;
  }

  getInterval(interval: Interval) {
    this.interval = interval;
  }

  disableButtonReport(){
    if(this.interval != undefined && this.channelId != undefined && this.storeId != undefined)
      this.disableDownload = true;
  }

  downloadPdf() {
    let request = new ReportRequestDto(this.storeId, this.channelId, this.interval);

    this.reportService.downloadReport(request).subscribe({
      next: (blob: Blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'report.pdf';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
      },
      error: (err) => {
        console.error('Erro ao baixar PDF', err);
      }
    });
  }
}
