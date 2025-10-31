import { Component, output } from '@angular/core';
import { SearchStore } from '../search-store/search-store';
import { DateSelect } from '../date-select/date-select';
import { ChannelFilter } from '../channel-filter/channel-filter';
import { Interval } from '../../../../core/model/interval';
import { MatAnchor } from "@angular/material/button";

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

  storeIdOutput = output<number>();
  channelIdOutput = output<number>();
  intervalOutput = output<Interval>();

  clickOutput(){
    this.storeIdOutput.emit(this.storeId);
    this.channelIdOutput.emit(this.channelId);
    this.intervalOutput.emit(this.interval);
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
}
