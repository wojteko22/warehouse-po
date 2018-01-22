import {Component, ViewChild} from "@angular/core";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {deliveryOrderMenu} from "../delivery-order-menu";
import {AcceptanceOrderPositionDto} from "../../model/dto/acceptance-order-position-dto";
import {AcceptanceOrderService} from "../acceptance-orders/acceptance-order.service";
import {AcceptanceOrderDetailsDto} from "../../model/dto/acceptance-order-details-dto";
import {DialogData} from "../../shared-module/dialog/dialog-data";
import {DialogComponent} from "../../shared-module/dialog/dialog.component";
import {RegistrationDocumentService} from "./registration-document.service";

@Component({
  selector: 'acceptance-order-details',
  templateUrl: './acceptance-order-details.html',
  providers: [AcceptanceOrderService, RegistrationDocumentService]
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

  constructor(private acceptanceOrderSerivce: AcceptanceOrderService,
              private registrationDocumentSerivce: RegistrationDocumentService,
              private route: ActivatedRoute,
              private dialog: MatDialog, private router: Router) {
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

  askIfGenerate() {
    const data: DialogData = {
      title: "",
      message: "Czy na pewno chcesz wygenerować dokument Pz?"
    };
    this.dialog.open(DialogComponent, {data: data}).afterClosed().subscribe(
      response => this.generateDocumentIfRequested(response)
    );
  }

  private generateDocumentIfRequested(requested: boolean) {
    if (requested) {
      this.generateDocument();
    }
  }

  private async generateDocument() {
    await this.registrationDocumentSerivce.generate(this.id);
    await this.router.navigate(['deliveryOrder/acceptanceOrders']);
  }
}
