import { Component } from '@angular/core';
import { CardBalance } from "../../shared/balance/card-balance/card-balance";
import { CardTopProducts } from "../../shared/top-products/card-top-products/card-top-products";

@Component({
  selector: 'app-home',
  imports: [CardBalance, CardTopProducts],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

}
