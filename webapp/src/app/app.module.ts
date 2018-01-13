import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {CoreModule} from "./core-module/core.module";
import {SharedModule} from "./shared-module/shared.module";
import {StartComponent} from "./start-page/start.component";


@NgModule({
  declarations: [
    AppComponent,
    StartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    SharedModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
