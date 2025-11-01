import { Component, input } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { CardTopProducts } from "../top-products/card-top-products/card-top-products";
import { Interval } from '../../../core/model/interval';
import { InactiveCustomers } from "../customer/inactive-customers/inactive-customers";
import { Ticket } from "../ticket/ticket/ticket";

@Component({
  selector: 'app-nav',
  imports: [MatTabsModule, CardTopProducts, InactiveCustomers, Ticket],
  templateUrl: './nav.html',
  styleUrl: './nav.css',
})
export class Nav {
  readonly storeId = input.required<number>();
  readonly channelId = input.required<number>();
  readonly interval = input.required<Interval>();
}
