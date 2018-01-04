import {Component} from "@angular/core";
import {UserService} from "../user.service";

@Component({
  selector: 'user-register',
  templateUrl: './register.component.html',
  providers: [UserService]
})

export class UserRegisterComponent {

  constructor(private userService: UserService) {
  }
}
