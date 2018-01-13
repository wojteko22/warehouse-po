import {Component} from "@angular/core";
import {UserService} from "../user.service";
import {ActivatedRoute} from "@angular/router";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component(
  {
    selector: 'user-configuration',
    templateUrl: './configuration.html',
    styleUrls: ['./configuration.css'],
    providers: [UserService]
  }
)

export class UserConfigurationComponent {
  mdIcons = MdIconsDefinitions;
  title: string = "Konfiguracja konta";
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
  userId;
  configurationForm: FormGroup;
  isValid = false;

  constructor(private userService: UserService, private route: ActivatedRoute, private formBuilder: FormBuilder) {
    this.route.params.subscribe( params => this.userId = params['id']);
    this.initForm()
  }

  private initForm() {
    this.configurationForm = this.formBuilder.group({
        'pesel': [null, Validators.required],
        'password': [null, Validators.required],
        'address': this.formBuilder.group({
          'town': [null, Validators.required],
          'postalCode': [null, Validators.required],
          'street': [null, Validators.required],
          'number': [null, Validators.required]
        })
      }
    );

    this.configurationForm.valueChanges.subscribe(() => this.isValid = this.configurationForm.valid)
  }

  get pesel() {
    return this.configurationForm.get('pesel')
  }

  get password() {
    return this.configurationForm.get('password')
  }

  get town() {
    return this.configurationForm.get('address').get('town')
  }

  get street() {
    return this.configurationForm.get('address').get('street')
  }

  get postalCode() {
    return this.configurationForm.get('address').get('postalCode')
  }

  get number() {
    return this.configurationForm.get('address').get('number')
  }
}
