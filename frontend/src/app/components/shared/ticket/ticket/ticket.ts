import { Component, effect, inject, input } from '@angular/core';
import { TicketService } from '../../../../core/services/ticket.service';
import { TicketResponseDto } from '../../../../core/model/ticket';
import { Observable } from 'rxjs';
import { Interval } from '../../../../core/model/interval';
import { AsyncPipe } from '@angular/common';
import { TicketItem } from '../ticket-item/ticket-item';

@Component({
  selector: 'app-ticket',
  imports: [TicketItem, AsyncPipe],
  templateUrl: './ticket.html',
  styleUrl: './ticket.css',
})
export class Ticket {
  readonly storeId = input.required<number>();
  readonly channelId = input.required<number>();
  readonly interval = input.required<Interval>();

  tickets = new Observable<TicketResponseDto[]>();
  ticketService = inject(TicketService);

  constructor() {
    effect(() => {
      this.tickets = this.ticketService.getTickets(
        this.storeId(),
        this.channelId(),
        this.interval()
      );
    });
  }
}
