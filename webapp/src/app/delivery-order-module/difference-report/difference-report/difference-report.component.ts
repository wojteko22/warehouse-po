import {Component, ViewChild} from "@angular/core";
import {MdIconsDefinitions} from "../../../md-icons-definitions";
import {DifferenceReportService} from "../difference-report.service";
import {DifferenceReportPositionService} from "../difference-report-position.service";
import {MatDialog, MatPaginator, MatSnackBar, MatSort, MatTableDataSource} from "@angular/material";
import {ActivatedRoute, Router} from "@angular/router";
import {DeliveryOrderService} from "../../delivery-order.service";
import {SelectionModel} from "@angular/cdk/collections";
import {NewCommodityComponent} from "../../new-commodity/new-commodity.component";
import {DifferenceReportDto} from "../../../model/dto/difference-report-dto";
import {DifferenceReportPositionDto} from "../../../model/dto/difference-report-position-dto";
import {DialogData} from "../../../shared-module/dialog/dialog-data";
import {DialogComponent} from "../../../shared-module/dialog/dialog.component";

@Component({
  selector: 'difference-report',
  templateUrl: 'difference-report.html',
  styleUrls: ['difference-report.css'],
  providers: [DeliveryOrderService, DifferenceReportService, DifferenceReportPositionService]
})

export class DifferenceReportComponent {
  mdIcons = MdIconsDefinitions;
  initialTitle: string = "Protokół różnic";
  title: string = this.initialTitle;
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
              private differenceReportPositionService: DifferenceReportPositionService, private route: ActivatedRoute,
              private dialog: MatDialog, private router: Router, private snackBar: MatSnackBar) {
    this.route.params.subscribe(params => {
      this.orderId = params['id'];
      this.initDataSource()
    });
  }

  async openAddNewCommodityDialog() {
    const data = await this.differenceReportService.getAvailableCommodities(this.reportId);
    this.dialog.open(NewCommodityComponent, {data: data}).afterClosed().subscribe(commodity =>
      this.differenceReportService.addCommodity(this.reportId, commodity.id).then(
        () => this.getData()
      )
    )
  }

  everythingChecked(): boolean {
    if (!this.report) {
      return false;
    }
    return this.selection.selected.length === this.report.differenceReportPositions.length;
  }

  private async initDataSource() {
    this.reportId = await this.differenceReportService.createDefaultReport(this.orderId);
    this.getData();
  }

  getDifference(row: DifferenceReportPositionDto) {
    return row.orderedQuantity - row.deliveredQuantity;
  }

  makeDifferenceReport() {
    const data: DialogData = {
      title: "",
      message: "Czy na pewno chcesz wysłać protokół różnic do działu zaopatrzenia?"
    };
    this.dialog.open(DialogComponent, {data: data}).afterClosed().subscribe(
      response => this.sendReportIfRequested(response)
    );
  }

  private sendReportIfRequested(requested: boolean) {
    if (requested) {
      this.sendReport();
    }
  }

  private async sendReport() {
    await this.differenceReportService.send(this.reportId);
    await this.router.navigate(['deliveryOrder/all']);
    this.displayPrompt();
  }

  private displayPrompt() {
    this.openSnackBar("Protokół różnic został wysłany");
  }

  private openSnackBar(message: string) {
    this.snackBar.open(message, null, {
      duration: 3000,
    });
  }

  private async getData() {
    this.report = await this.differenceReportService.getReport(this.reportId);

    this.dataSource = new MatTableDataSource(this.report.differenceReportPositions);
    this.title = this.initialTitle + " " + this.report.deliveryOrderNumber;
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  async savePosition(position: DifferenceReportPositionDto) {
    await this.differenceReportPositionService.savePosition(position.id, position.deliveredQuantity);
  }
}
