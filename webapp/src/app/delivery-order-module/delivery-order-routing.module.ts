import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {DeliveryOrdersComponent} from "./delivery-orders/delivery-orders.component";

const routes: Routes = [
  {path: 'all', component: DeliveryOrdersComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class DeliveryOrderRoutingModule{
}
