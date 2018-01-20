import {Component, ViewChild} from "@angular/core";
import {MdIconsDefinitions} from "../../../md-icons-definitions";
import {DifferenceReportService} from "../difference-report.service";
import {DifferenceReportPositionService} from "../difference-report-position.service";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {DeliveryOrderPositionDto} from "../../../model/dto/delivery-order-position-dto";
import {ActivatedRoute} from "@angular/router";
import {DeliveryOrderDetailsDto} from "../../../model/dto/delivery-order-details-dto";
import {DeliveryOrderService} from "../../delivery-order.service";
import {SelectionModel} from "@angular/cdk/collections";

@Component({
  selector: 'difference-report',
  templateUrl: 'difference-report.html',
  styleUrls: ['difference-report.css'],
  providers: [DeliveryOrderService, DifferenceReportService, DifferenceReportPositionService]
})

export class DifferenceReportComponent {
  mdIcons = MdIconsDefinitions;
  title: string = "Protokół różnic";
  menuOpions: MenuElements[] = [
    {
      icon: this.mdIcons.start_page,
      optionName: "Strona główna",
      routerLink: ""
    }
  ];
  displayedColumns = ['code', 'name', 'quantity', 'measure', 'deliveredQuantity', 'difference', 'select'];
  dataSource: MatTableDataSource<DifferenceReportRow>;
  orderNumber: string;
  orderDetails: DeliveryOrderDetailsDto;
  selection = new SelectionModel<DifferenceReportRow>(true, []);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private deliveryOrdersService: DeliveryOrderService, private differenceReportService: DifferenceReportService,
              private differenceReportPositionService: DifferenceReportPositionService, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.orderNumber = params['id'];
    });
    this.initDataSource()
  }

  ngOnInit(): void {
    this.title = this.title + " " + this.orderNumber;
  }

  private async initDataSource() {
    this.orderDetails = await this.deliveryOrdersService.getDeliveryOrderDetail(this.orderNumber);
    this.dataSource = new MatTableDataSource(this.orderDetails.positions.map((position: DeliveryOrderPositionDto) => {
      return {orderPosition: position, difference: null, deliveredQuantity: null}
    }));
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  getDifference(row: DifferenceReportRow) {
    const diff = Math.round((parseFloat(row.orderPosition.quantity) - row.deliveredQuantity) * 100) / 100;
    row.difference = diff;
    return diff;
  }

  makeDifferenceReport() {
    console.log("createDifferenceReport")
  }

  addNewCommodity() {

  }

}

export interface DifferenceReportRow {
  orderPosition: DeliveryOrderPositionDto,
  difference: number,
  deliveredQuantity: number
}
