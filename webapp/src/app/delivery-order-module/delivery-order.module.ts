
import {NgModule} from "@angular/core";
import {SharedModule} from "../shared-module/shared.module";
import {DeliveryOrderRoutingModule} from "./delivery-order-routing.module";
import {DeliveryOrdersComponent} from "./delivery-orders/delivery-orders.component";
import {DeliveryOrderDetailsComponent} from "./delivery-order-detail/delivery-order-details.component";

@NgModule({
  imports: [
    SharedModule,
    DeliveryOrderRoutingModule
  ],
  declarations: [
    DeliveryOrdersComponent,
    DeliveryOrderDetailsComponent
  ]
})

export class DeliveryOrderModule {
}
