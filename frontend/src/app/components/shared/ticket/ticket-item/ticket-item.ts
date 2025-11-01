import { Component, input } from '@angular/core';
import { TicketResponseDto } from '../../../../core/model/ticket';

@Component({
  selector: 'app-ticket-item',
  imports: [],
  templateUrl: './ticket-item.html',
  styleUrl: './ticket-item.css',
})
export class TicketItem {
  ticket = input.required<TicketResponseDto>();
}
