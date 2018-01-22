import {Component, ViewChild} from "@angular/core";
import {DeliveryOrderService} from "../delivery-order.service";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {DeliveryOrderDto} from "../../model/dto/delivery-order-dto";
import {Router} from "@angular/router";
import {deliveryOrderMenu} from "../delivery-order-menu";

@Component({
  selector: 'delivery-orders',
  templateUrl: './delivery-orders.html',
  providers: [DeliveryOrderService]
})

export class DeliveryOrdersComponent {
  mdIcons = MdIconsDefinitions;
  title: string = "Zamówienia dostaw";
  menuOpions: MenuElements[] = deliveryOrderMenu;
  displayedColumns = ['orderNumber', 'provider', 'predictedDeliveryDate'];
  dataSource: MatTableDataSource<DeliveryOrderDto>;
  hoveredRow = null;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private deliveryOrdersService: DeliveryOrderService, private router: Router) {
    this.initDataSource()
  }

  private async initDataSource() {
    const orders = await this.deliveryOrdersService.getAllDeliveryOrders();
    this.dataSource = new MatTableDataSource(orders);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  selectRow(row: DeliveryOrderDto) {
    this.router.navigateByUrl('/deliveryOrder/details/' + row.id)
  }

  hoverRow(id: number) {
    this.hoveredRow = id;
  }

  isHovered(row: DeliveryOrderDto) {
    return this.hoveredRow != null && this.hoveredRow == row.orderNumber
  }

}
