import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MaterialDesignModule} from "./material-design-module/material-design.module";
import {MenuComponent} from "./menu/menu-content.component";
import {RouterModule} from "@angular/router";
import {DialogComponent} from "./dialog/dialog.component";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialDesignModule,
    RouterModule
  ],
  declarations: [
    MenuComponent,
    DialogComponent
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialDesignModule,
    MenuComponent,
    RouterModule,
    DialogComponent
  ],
  entryComponents: [
    DialogComponent
  ],
  providers: [
  ]
})

export class SharedModule {
}
