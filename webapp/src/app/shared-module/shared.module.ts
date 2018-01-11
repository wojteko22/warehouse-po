import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MaterialDesignModule} from "./material-design-module/material-design.module";
import {MenuComponent} from "./menu/menu-content.component";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialDesignModule
  ],
  declarations: [
    MenuComponent
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialDesignModule,
    MenuComponent
  ],
  entryComponents: [
  ],
  providers: [
  ]
})

export class SharedModule {
}
