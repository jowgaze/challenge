import { Component, inject, OnInit, output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { Observable } from 'rxjs';
import { ChannelResponseDto } from '../../../../core/model/channel';
import { ChannelService } from '../../../../core/services/channel.service';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-channel-filter',
  imports: [MatRadioModule, FormsModule, AsyncPipe],
  templateUrl: './channel-filter.html',
  styleUrl: './channel-filter.css',
})
export class ChannelFilter implements OnInit {
  channelId = output<number>();
  channels = new Observable<ChannelResponseDto[]>();
  channelService = inject(ChannelService);
  id: string = '0';

  ngOnInit(): void {
    this.channels = this.channelService.getChannels();
    this.filter()
  }

  filter() {
    this.channelId.emit(parseInt(this.id));
  }
}
