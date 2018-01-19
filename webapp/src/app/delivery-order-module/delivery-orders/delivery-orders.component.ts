
import {Component} from "@angular/core";
import {DeliveryOrderService} from "../delivery-order.service";
import {MdIconsDefinitions} from "../../md-icons-definitions";

@Component({
  selector: 'delivery-orders',
  templateUrl: './delivery-orders.html',
  providers: [DeliveryOrderService]
})

export class DeliveryOrdersComponent {
  mdIcons = MdIconsDefinitions;
  title: string = "Zamówienia dostaw";
  menuOpions: MenuElements[] = [
    {
      icon: this.mdIcons.start_page,
      optionName: "Strona główna",
      routerLink: ""
    }
  ];

  constructor(private deliveryOrdersService: DeliveryOrderService) {
  }

}
