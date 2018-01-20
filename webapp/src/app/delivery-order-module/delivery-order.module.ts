
import {NgModule} from "@angular/core";
import {SharedModule} from "../shared-module/shared.module";
import {DeliveryOrderRoutingModule} from "./delivery-order-routing.module";
import {DeliveryOrdersComponent} from "./delivery-orders/delivery-orders.component";
import {DeliveryOrderDetailsComponent} from "./delivery-order-detail/delivery-order-details.component";
import {DifferenceReportComponent} from "./difference-report/difference-report/difference-report.component";

@NgModule({
  imports: [
    SharedModule,
    DeliveryOrderRoutingModule
  ],
  declarations: [
    DeliveryOrdersComponent,
    DeliveryOrderDetailsComponent,
    DifferenceReportComponent
  ]
})

export class DeliveryOrderModule {
}
