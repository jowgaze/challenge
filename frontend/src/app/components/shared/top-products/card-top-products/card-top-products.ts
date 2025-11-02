import { Component, ViewChild, effect, inject, input, OnDestroy } from '@angular/core';
import { ProductService } from '../../../../core/services/product.service';
import { Subscription } from 'rxjs';
import { Interval } from '../../../../core/model/interval';
import { ChartComponent } from 'ng-apexcharts';
import { ApexNonAxisChartSeries, ApexResponsive, ApexChart } from 'ng-apexcharts';

export type ChartOptions = {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  responsive: ApexResponsive[];
  labels: any;
};

@Component({
  selector: 'app-card-top-products',
  imports: [ChartComponent],
  templateUrl: './card-top-products.html',
  styleUrl: './card-top-products.css',
})
export class CardTopProducts implements OnDestroy {
  readonly storeId = input.required<number>();
  readonly channelId = input.required<number>();
  readonly interval = input.required<Interval>();

  private productService = inject(ProductService);
  private subscription?: Subscription;
  hiddenCard = true;

  @ViewChild('chart') chart!: ChartComponent;

  chartOptions: ChartOptions = {
    series: [],
    chart: { width: 500, type: 'donut' },
    labels: [],
    responsive: [
      {
        breakpoint: 480,
        options: {
          chart: { width: 200 },
          legend: { position: 'bottom' },
        },
      },
    ],
  };

  constructor() {
    effect(() => {
      this.subscription?.unsubscribe();

      this.subscription = this.productService
        .getTopProducts(this.storeId(), this.channelId(), this.interval())
        .subscribe((products) => {
          const hasData = products && products.length > 0;
          this.hiddenCard = !hasData;

          if (hasData)
            this.chartOptions.series = products.map((p) => Number(p.totalSold));
            this.chartOptions.labels = products.map((p) => p.name);
        });
    });
  }

  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }
}
