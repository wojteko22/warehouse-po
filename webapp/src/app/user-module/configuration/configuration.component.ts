import {Component} from "@angular/core";
import {UserService} from "../user.service";
import {ActivatedRoute} from "@angular/router";

@Component(
  {
    selector: 'user-configuration',
    templateUrl: './configuration.html',
    providers: [UserService]
  }
)

export class UserConfigurationComponent {
  userId;

  constructor(private userService: UserService, private route: ActivatedRoute) {
    this.route.params.subscribe( params => this.userId = params['id'])
  }
}
