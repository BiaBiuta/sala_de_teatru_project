import { Component } from '@angular/core';
import {transition, trigger, useAnimation} from "@angular/animations";

let fadeInOut;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  // animations: [
  //   trigger('fadeInOut', [ // definește animația fadeInOut
  //     transition(':enter', [
  //       useAnimation(fadeInOut)
  //     ]),
  //     transition(':leave', [
  //       useAnimation(fadeInOut)
  //     ])
  //   ])
  //   ]
})
export class AppComponent {
  title = 'iss-interface';
  showLoginForm: boolean = true;
  showViewPrinciple:boolean=false;
  showDatePersonal:boolean=false;
  handlePriniciple(): void {
    // Toggle the visibility of the tolerance form
    this.showViewPrinciple = true;
    this.showLoginForm = false;
  }
  handleDatePersonal(): void {
    console.log(("ar trebui sa afisez date personale"))
    // Toggle the visibility of the tolerance form
    this.showDatePersonal = true;
    this.showViewPrinciple = false;
    this.showLoginForm = false;
  }
  handleRegisterDatePersonal(): void {
    // Toggle the visibility of the tolerance form
    this.showDatePersonal = false;
    this.showViewPrinciple = true;
  }

  handleSignIn(): void {
    // Toggle the visibility of the tolerance form
    this.showViewPrinciple = true;
    this.showLoginForm = false;
  }
  handleLogOut(): void {
    // Toggle the visibility of the tolerance form
    this.showViewPrinciple = false;
    this.showLoginForm = true;
  }
  handleReserved(): void {
    // Toggle the visibility of the tolerance form
    this.showViewPrinciple = true;
    this.showDatePersonal = false;
    this.showLoginForm = false;
  }
}
