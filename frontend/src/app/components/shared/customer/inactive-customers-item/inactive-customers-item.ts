import { Component, input } from '@angular/core';
import { InactiveCustomerDto } from '../../../../core/model/customer';

@Component({
  selector: 'app-inactive-customers-item',
  imports: [],
  templateUrl: './inactive-customers-item.html',
  styleUrl: './inactive-customers-item.css',
})
export class InactiveCustomersItem {
  customer = input.required<InactiveCustomerDto>();
}
