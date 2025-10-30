import { Component, output } from '@angular/core';
import { DateSelect } from "../date-select/date-select";
import { Interval } from '../../../core/model/interval';

@Component({
  selector: 'app-header',
  imports: [DateSelect],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header{
  interval = output<Interval>();

  onIntervalChange(interval: Interval){
    this.interval.emit(interval)
  }
}
