import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {DeliveryOrdersComponent} from "./delivery-orders/delivery-orders.component";
import {DeliveryOrderDetailsComponent} from "./delivery-order-detail/delivery-order-details.component";
import {DifferenceReportComponent} from "./difference-report/difference-report/difference-report.component";

const routes: Routes = [
  {path: 'all', component: DeliveryOrdersComponent},
  {path: 'details/:id', component: DeliveryOrderDetailsComponent},
  {path: 'differenceReport/:id', component: DifferenceReportComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class DeliveryOrderRoutingModule{
}
