import {Component, ViewChild} from "@angular/core";
import {MdIconsDefinitions} from "../../../md-icons-definitions";
import {DifferenceReportService} from "../difference-report.service";
import {DifferenceReportPositionService} from "../difference-report-position.service";
import {MatDialog, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {ActivatedRoute} from "@angular/router";
import {DeliveryOrderService} from "../../delivery-order.service";
import {SelectionModel} from "@angular/cdk/collections";
import {NewCommodityComponent} from "../../new-commodity/new-commodity.component";
import {DifferenceReportDto} from "../../../model/dto/difference-report-dto";
import {DifferenceReportPositionDto} from "../../../model/dto/difference-report-position-dto";

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
  dataSource: MatTableDataSource<DifferenceReportPositionDto>;
  orderId: string;
  reportId: string;
  report: DifferenceReportDto;
  selection = new SelectionModel<DifferenceReportPositionDto>(true, []);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private deliveryOrdersService: DeliveryOrderService, private differenceReportService: DifferenceReportService,
              private differenceReportPositionService: DifferenceReportPositionService, private route: ActivatedRoute, private dialog: MatDialog) {
    this.route.params.subscribe(params => {
      this.orderId = params['id'];
      this.initDataSource()
    });
  }

  private async initDataSource() {
    this.reportId = await this.differenceReportService.createDefaultReport(this.orderId);
    this.report = await this.differenceReportService.getReport(this.reportId);

    this.dataSource = new MatTableDataSource(this.report.differenceReportPositions);
    this.title = this.title + " " + this.report.deliveryOrderNumber;
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  getDifference(row: DifferenceReportPositionDto) {
    return Math.round((parseFloat(row.orderedQuantity) - parseFloat(row.deliveredQuantity)) * 100) / 100;
  }

  makeDifferenceReport() {
    console.log("createDifferenceReport")
  }

  async openAddNewCommodityDialog() {
    const data = await this.differenceReportService.getAvailableCommodities(this.reportId);
    this.dialog.open(NewCommodityComponent, {data: data})
      .afterClosed()
      .subscribe(
        result => console.log(result)
      )
  }

}
