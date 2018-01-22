import {Component, ViewChild} from "@angular/core";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {Router} from "@angular/router";
import {deliveryOrderMenu} from "../delivery-order-menu";
import {AcceptanceOrderDto} from "../../model/dto/acceptance-order-dto";
import {AcceptanceOrderService} from "./acceptance-order.service";

@Component({
  selector: 'acceptance-orders',
  templateUrl: './acceptance-orders.html',
  providers: [AcceptanceOrderService]
})

export class AcceptanceOrdersComponent {
  mdIcons = MdIconsDefinitions;
  title: string = "Zlecenia przyjęcia";
  menuOpions: MenuElements[] = deliveryOrderMenu;
  displayedColumns = ['orderNumber', 'provider'];
  dataSource: MatTableDataSource<AcceptanceOrderDto>;
  hoveredRow = null;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private acceptanceOrdersService: AcceptanceOrderService, private router: Router) {
    this.initDataSource()
  }

  private async initDataSource() {
    const orders = await this.acceptanceOrdersService.getUnhandled();
    this.dataSource = new MatTableDataSource(orders);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  async selectRow(row: AcceptanceOrderDto) {
    const data = await this.acceptanceOrdersService.get(row.id);
    console.log(data); // todo: Usunąć
    this.router.navigateByUrl('/deliveryOrder/acceptanceOrders/' + row.id)
  }

  hoverRow(id: number) {
    this.hoveredRow = id;
  }

  isHovered(row: AcceptanceOrderDto) {
    return this.hoveredRow != null && this.hoveredRow == row.orderNumber
  }
}
