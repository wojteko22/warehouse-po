import {Component, OnInit, ViewChild} from "@angular/core";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {DeliveryOrderService} from "../delivery-order.service";
import {ActivatedRoute} from "@angular/router";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {DeliveryOrderPositionDto} from "../../model/dto/delivery-order-position-dto";
import {DeliveryOrderDetailsDto} from "../../model/dto/delivery-order-details-dto";

@Component({
  selector: 'delivery-order-details',
  templateUrl: './delivery-order-details.html',
  styleUrls: ['./delivery-order-details.css'],
  providers: [DeliveryOrderService]
})

export class DeliveryOrderDetailsComponent implements OnInit{

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
  orderNumber: string;
  provider: string;
  orderDetails: DeliveryOrderDetailsDto;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private deliveryOrdersService: DeliveryOrderService, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.orderNumber = params['id'];
    });
    this.initDataSource()
  }

  ngOnInit(): void {
    this.title = this.title + " " + this.orderNumber;
  }

  private async initDataSource() {
    this.orderDetails = this.deliveryOrdersService.getDeliveryOrderDetail(this.orderNumber);
    this.provider = this.orderDetails.provider;
    this.dataSource = new MatTableDataSource(this.orderDetails.positions);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  makeDifferenceReport(){
    console.log("make differnecer report")
  }

}
