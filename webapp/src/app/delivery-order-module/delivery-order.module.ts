import {NgModule} from "@angular/core";
import {SharedModule} from "../shared-module/shared.module";
import {DeliveryOrderRoutingModule} from "./delivery-order-routing.module";
import {DeliveryOrdersComponent} from "./delivery-orders/delivery-orders.component";
import {DeliveryOrderDetailsComponent} from "./delivery-order-detail/delivery-order-details.component";
import {DifferenceReportComponent} from "./difference-report/difference-report/difference-report.component";
import {NewCommodityComponent} from "./new-commodity/new-commodity.component";
import {AcceptanceOrdersComponent} from "./acceptance-orders/acceptance-orders.component";

@NgModule({
  imports: [
    SharedModule,
    DeliveryOrderRoutingModule
  ],
  declarations: [
    DeliveryOrdersComponent,
    DeliveryOrderDetailsComponent,
    DifferenceReportComponent,
    NewCommodityComponent,
    AcceptanceOrdersComponent,
  ],
  entryComponents: [
    NewCommodityComponent
  ]
})

export class DeliveryOrderModule {
}
