import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA} from "@angular/material";
import {DialogData} from "./dialog-data";

@Component({
  selector: 'dialog-content',
  templateUrl: './dialog.html',
  styleUrls: ['./dialog.css']
})

export class DialogComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }

}
