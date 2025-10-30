import { Component } from '@angular/core';
import { Home } from "./components/pages/home/home";

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports: [Home]
})
export class App {

}
