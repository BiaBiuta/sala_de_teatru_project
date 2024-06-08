import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {AppComponent} from "./app.component";
import {HttpClientModule} from "@angular/common/http";
import {BaseComponent} from "./base/base.component";
import {InterfaceComponent} from "./interface/interface.component";
import {ViewTheatreComponent} from "./view-theatre/view-theatre.component";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {ReactiveFormsModule} from "@angular/forms";
import {DatePersonaleComponent} from "./date-personale/date-personale.component";

@NgModule({
  declarations: [
    AppComponent,
    BaseComponent,
      DatePersonaleComponent,
    InterfaceComponent,
    ViewTheatreComponent,

    // Declare components, directives, and pipes here
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent], // Add the root component here
})
export class AppModule { }
