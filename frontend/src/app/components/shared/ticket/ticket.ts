import { Component, ViewChild, effect, inject, input, OnDestroy } from '@angular/core';
import { TicketService } from '../../../core/services/ticket.service';
import { TicketResponseDto } from '../../../core/model/ticket';
import { Interval } from '../../../core/model/interval';
import {
  ApexAxisChartSeries,
  ApexChart,
  ApexDataLabels,
  ApexXAxis,
  ApexPlotOptions,
  ChartComponent,
} from 'ng-apexcharts';
import { Subscription, tap } from 'rxjs';

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  dataLabels: ApexDataLabels;
  plotOptions: ApexPlotOptions;
  xaxis: ApexXAxis;
};

@Component({
  selector: 'app-ticket',
  imports: [ChartComponent],
  templateUrl: './ticket.html',
  styleUrl: './ticket.css',
})
export class Ticket implements OnDestroy {
  readonly storeId = input.required<number>();
  readonly channelId = input.required<number>();
  readonly interval = input.required<Interval>();

  private ticketService = inject(TicketService);
  private subscription?: Subscription;
  hiddenCard = true;

  @ViewChild('chart') chart!: ChartComponent;

  chartOptions: ChartOptions = {
    series: [{ name: 'Tickets', data: [] }],
    chart: { type: 'bar', height: 280, width: 360 },
    plotOptions: { bar: { horizontal: true } },
    dataLabels: { enabled: false },
    xaxis: { categories: [] },
  };

  constructor() {
    effect(() => {
      this.subscription?.unsubscribe();

      this.subscription = this.ticketService
        .getTickets(this.storeId(), this.channelId(), this.interval())
        .subscribe((tickets) => {
          const hasData = tickets && tickets.length > 0;
          this.hiddenCard = !hasData;

          if (hasData)
            this.chartOptions = {
              ...this.chartOptions,
              series: [{data: tickets.map(t => t.avgTicket)}],
              xaxis: {categories: tickets.map(t => t.channelName)}
            }
        })
    });
  }

  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }
}
