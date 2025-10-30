import { Component, input } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { CardTopProducts } from "../top-products/card-top-products/card-top-products";
import { interval } from 'rxjs';
import { Interval } from '../../../core/model/interval';

@Component({
  selector: 'app-nav',
  imports: [MatTabsModule, CardTopProducts],
  templateUrl: './nav.html',
  styleUrl: './nav.css',
})
export class Nav {
  interval = input.required<Interval>();
}
