import {Component, Inject, ViewChild} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {CommodityDto} from "../../model/dto/commodity-dto";
import {MdIconsDefinitions} from "../../md-icons-definitions";

@Component({
  selector: 'new-commodity',
  templateUrl: './new-commodity.html',
  styleUrls: ['./new-commodity.css']
})

export class NewCommodityComponent {
  mdIcons = MdIconsDefinitions;
  displayedColumns = ['code', 'name', 'measure', 'producer'];
  dataSource: MatTableDataSource<CommodityDto>;
  hoveredRow = null;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public dialogRef: MatDialogRef<NewCommodityComponent>, @Inject(MAT_DIALOG_DATA) public data: CommodityDto[]) {
    this.initDataSource()
  }

  private async initDataSource() {
    this.dataSource = new MatTableDataSource(this.data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  selectRow(row: CommodityDto) {
    this.dialogRef.close(row)
  }

  closeDialog() {
    this.dialogRef.close(null)
  }

  hoverRow(id: number) {
    this.hoveredRow = id;
  }

  isHovered(row: CommodityDto) {
    return this.hoveredRow != null && this.hoveredRow == row.commodityCode
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

}
