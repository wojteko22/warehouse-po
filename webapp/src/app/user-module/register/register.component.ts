import {Component} from "@angular/core";
import {UserService} from "../user.service";
import {MdIconsDefinitions} from "../../md-icons-definitions";

@Component({
  selector: 'user-register',
  templateUrl: './register.component.html',
  providers: [UserService]
})

export class UserRegisterComponent {
  mdIcons = MdIconsDefinitions;
  title: string = "Rejestracja";
  menuOpions: MenuElements[] = [
    {
      icon: this.mdIcons.start_page,
      optionName: "Strona główna",
      routerLink: ""
    },
    {
      icon: this.mdIcons.add_user,
      optionName: "Dodaj użytkownika",
      routerLink: "/user"
    }
  ];

  constructor(private userService: UserService) {
  }
}
