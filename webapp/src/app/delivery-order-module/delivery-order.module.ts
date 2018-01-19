
import {NgModule} from "@angular/core";
import {SharedModule} from "../shared-module/shared.module";
import {DeliveryOrderRoutingModule} from "./delivery-order-routing.module";
import {DeliveryOrdersComponent} from "./delivery-orders/delivery-orders.component";

@NgModule({
  imports: [
    SharedModule,
    DeliveryOrderRoutingModule
  ],
  declarations: [
    DeliveryOrdersComponent
  ]
})

export class DeliveryOrderModule {
}
