import { Component, output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { MatAnchor } from "@angular/material/button";

@Component({
  selector: 'app-channel-filter',
  imports: [MatRadioModule, FormsModule, MatAnchor],
  templateUrl: './channel-filter.html',
  styleUrl: './channel-filter.css',
})
export class ChannelFilter {
  id: string = "0";
  channelId = output<string>();

  filter(){
    this.channelId.emit(this.id)
  }
}
