import { Component, effect, inject, input } from '@angular/core';
import { InactiveCustomersItem } from "../inactive-customers-item/inactive-customers-item";
import { Observable } from 'rxjs';
import { InactiveCustomerDto } from '../../../../core/model/customer';
import { CustomerService } from '../../../../core/services/customer.service';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-inactive-customers',
  imports: [InactiveCustomersItem, AsyncPipe],
  templateUrl: './inactive-customers.html',
  styleUrl: './inactive-customers.css',
})
export class InactiveCustomers {
  readonly storeId = input.required<number>();
  readonly channelId = input.required<number>();

  inactiveCustomers = new Observable<InactiveCustomerDto[]>();
  customerService = inject(CustomerService);


  constructor(){
    effect(() => {
      this.inactiveCustomers = this.customerService.getInactiveCustomers(this.storeId(), this.channelId());
    });
  }
}
