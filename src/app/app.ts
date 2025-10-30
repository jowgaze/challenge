import { Component } from '@angular/core';
import { Header } from "./components/shared/header/header";
import { Interval } from './core/model/interval';
import { CardBalance } from "./components/shared/balance/card-balance/card-balance";
import { Nav } from "./components/shared/nav/nav";

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports: [Header, CardBalance, Nav]
})
export class App {
  interval!: Interval;

  getInterval(interval: Interval){
    this.interval = interval;
  }
}
