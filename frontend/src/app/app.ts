import { Component } from '@angular/core';
import { Header } from "./components/shared/header/header";
import { Interval } from './core/model/interval';
import { CardBalance } from "./components/shared/balance/card-balance/card-balance";
import { Nav } from "./components/shared/nav/nav";
import { Footer } from "./components/shared/footer/footer";
import { Filter } from "./components/shared/filter/filter/filter";

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports: [Header, CardBalance, Nav, Footer, Filter]
})
export class App {
  storeId!: number;
  channelId!: number;
  interval!: Interval;

  getStoreId(storeId: number) {
    this.storeId = storeId;
  }

  getChannelId(channelId: number) {
    this.channelId = channelId;
  }

  getInterval(interval: Interval){
    this.interval = interval;
  }
}
