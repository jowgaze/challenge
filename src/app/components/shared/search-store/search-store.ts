import {Component, inject, OnInit, output} from '@angular/core';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {AsyncPipe} from '@angular/common';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { StoreResponseDto } from '../../../core/model/store';
import { StoreService } from '../../../core/services/store.service';

@Component({
  selector: 'app-search-store',
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    AsyncPipe,
  ],
  templateUrl: './search-store.html',
  styleUrl: './search-store.css',
})
export class SearchStore implements OnInit{
  readonly store = output<StoreResponseDto>();

  stores = new Observable<StoreResponseDto[]>();
  storeService = inject(StoreService);
  myControl = new FormControl<StoreResponseDto | string>('1');

  ngOnInit() {
    this.stores = this.storeService.getStores();
  }

  outputStore(){
    this.store.emit(this.myControl.value as StoreResponseDto);
  }

  displayFn(store: StoreResponseDto): string {
    return store && store.name ? store.name : '';
  }
}
