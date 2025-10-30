import { Component, input } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { CardTopProducts } from "../top-products/card-top-products/card-top-products";
import { Interval } from '../../../core/model/interval';
import { InactiveCustomers } from "../customer/inactive-customers/inactive-customers";

@Component({
  selector: 'app-nav',
  imports: [MatTabsModule, CardTopProducts, InactiveCustomers],
  templateUrl: './nav.html',
  styleUrl: './nav.css',
})
export class Nav {
  interval = input.required<Interval>();
}
