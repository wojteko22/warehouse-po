
import {Component} from "@angular/core";
import {UserService} from "../user.service";

@Component({
  selector: 'user-register',
  templateUrl: './register.component.html'
})

export class UserRegisterComponent {

  constructor(private userService: UserService) {}
}
