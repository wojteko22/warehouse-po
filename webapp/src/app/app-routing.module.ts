import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StartComponent} from "./start-page/start.component";

const routes: Routes = [
  {path: '', component: StartComponent},
  {path: 'user', loadChildren: './user-module/user.module#UserModule'},
  {path: 'deliveryOrder', loadChildren: './delivery-order-module/delivery-order.module#DeliveryOrderModule'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
