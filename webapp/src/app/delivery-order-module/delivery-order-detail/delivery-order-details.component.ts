import {Component, ViewChild} from "@angular/core";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {DeliveryOrderService} from "../delivery-order.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {DeliveryOrderPositionDto} from "../../model/dto/delivery-order-position-dto";
import {DeliveryOrderDetailsDto} from "../../model/dto/delivery-order-details-dto";

@Component({
  selector: 'delivery-order-details',
  templateUrl: './delivery-order-details.html',
  styleUrls: ['./delivery-order-details.css'],
  providers: [DeliveryOrderService]
})

export class DeliveryOrderDetailsComponent {

  mdIcons = MdIconsDefinitions;
  title: string = "Zamówienie";
  menuOpions: MenuElements[] = [
    {
      icon: this.mdIcons.start_page,
      optionName: "Strona główna",
      routerLink: ""
    }
  ];
  displayedColumns = ['code', 'name', 'quantity', 'measure'];
  dataSource: MatTableDataSource<DeliveryOrderPositionDto>;
  orderId: string;
  provider: string;
  orderDetails: DeliveryOrderDetailsDto;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private deliveryOrdersService: DeliveryOrderService, private route: ActivatedRoute, private router: Router) {
    this.route.params.subscribe(params => {
      this.orderId = params['id'];
    });
    this.initDataSource()
  }

  private async initDataSource() {
    this.orderDetails = await this.deliveryOrdersService.getDeliveryOrderDetail(this.orderId);
    this.provider = this.orderDetails.provider;
    this.title = this.title + " " + this.orderDetails.orderNumber;
    this.dataSource = new MatTableDataSource(this.orderDetails.positions);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  goToDifferenceReportCreator() {
    this.router.navigateByUrl('/deliveryOrder/differenceReport/' + this.orderId)
  }

}
