import { ChangeDetectionStrategy, Component, effect, OnInit, output } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { BalanceRequestDto } from '../../../../core/model/balance';

const today = new Date();
const month = today.getMonth();
const year = today.getFullYear();

@Component({
  selector: 'app-date-select',
  providers: [provideNativeDateAdapter()],
  imports: [
    MatFormFieldModule,
    MatDatepickerModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './date-select.html',
  styleUrl: './date-select.css',
})
export class DateSelect implements OnInit{

  ngOnInit(): void {
    this.onRangeSelected();
  }
  readonly range = new FormGroup({
    start: new FormControl(new Date(year, month, 1)),
    end: new FormControl(new Date(today)),
  });

  readonly rangeChange = output<{ start: string | null; end: string | null }>();

  onRangeSelected(){
    const start = this.range.value.start?.toISOString() ?? null;
    const end = this.range.value.end?.toISOString() ?? null;

    this.rangeChange.emit(new BalanceRequestDto(start, end))
  }
}
