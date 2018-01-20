import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {DeliveryOrdersComponent} from "./delivery-orders/delivery-orders.component";
import {DeliveryOrderDetailsComponent} from "./delivery-order-detail/delivery-order-details.component";

const routes: Routes = [
  {path: 'all', component: DeliveryOrdersComponent},
  {path: 'details/:id', component: DeliveryOrderDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class DeliveryOrderRoutingModule{
}
