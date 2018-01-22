import {Component, ViewChild} from "@angular/core";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {ActivatedRoute, Router} from "@angular/router";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {deliveryOrderMenu} from "../delivery-order-menu";
import {AcceptanceOrderPositionDto} from "../../model/dto/acceptance-order-position-dto";
import {AcceptanceOrderService} from "../acceptance-orders/acceptance-order.service";
import {AcceptanceOrderDetailsDto} from "../../model/dto/acceptance-order-details-dto";

@Component({
  selector: 'delivery-order-details',
  templateUrl: './delivery-order-details.html',
  providers: [AcceptanceOrderService]
})

export class AcceptanceOrderDetailsComponent {

  mdIcons = MdIconsDefinitions;
  title: string = "Zlecenie przyjęcia";
  menuOpions: MenuElements[] = deliveryOrderMenu;

  displayedColumns = ['code', 'name', 'measure', 'quantityToAccept', 'fraction'];

  id: number;
  dataSource: MatTableDataSource<AcceptanceOrderPositionDto>;
  orderDetails: AcceptanceOrderDetailsDto;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private acceptanceOrderSerivce: AcceptanceOrderService, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });
    this.initDataSource()
  }

  private async initDataSource() {
    this.orderDetails = await this.acceptanceOrderSerivce.get(this.id);
    this.dataSource = new MatTableDataSource(this.orderDetails.positions);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

}
