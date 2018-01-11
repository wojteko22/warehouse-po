import {Component, Input, ViewChild} from "@angular/core";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {MatSidenav} from "@angular/material";

@Component({
  selector: 'menu-content',
  templateUrl: 'menu-content.html',
  styleUrls: ['menu-content.css'],
})

export class MenuComponent {
  mdIcons = MdIconsDefinitions;
  @Input() title: string;
  @Input() menuOptions: MenuElements[];
  @ViewChild(MatSidenav) drawer: MatSidenav;

  toggleMenu() {
    this.drawer.toggle()
  }

}
