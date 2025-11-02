import { Component, input } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';

import { Interval } from '../../../core/model/interval';
import { InactiveCustomers } from "../customer/inactive-customers/inactive-customers";
import { Ticket } from "../ticket/ticket";
import { CardTopProducts } from "../card-top-products/card-top-products";

@Component({
  selector: 'app-nav',
  imports: [MatTabsModule, InactiveCustomers, Ticket, CardTopProducts],
  templateUrl: './nav.html',
  styleUrl: './nav.css',
})
export class Nav {
  readonly storeId = input.required<number>();
  readonly channelId = input.required<number>();
  readonly interval = input.required<Interval>();
}
