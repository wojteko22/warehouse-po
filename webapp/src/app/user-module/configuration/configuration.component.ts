import {Component} from "@angular/core";
import {UserService} from "../user.service";
import {ActivatedRoute} from "@angular/router";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";

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
    this.route.params.subscribe(params => this.userId = params['id']);
    this.initForm()
  }

  private initForm() {
    this.configurationForm = this.formBuilder.group({
        'pesel': [null, [Validators.required, Validators.minLength(11), Validators.maxLength(11), Validators.pattern('^[1-9]+$')]],
        'password': [null, [Validators.required, Validators.minLength(5), Validators.maxLength(15)]],
        'repeatPassword': [null],
        'address': this.formBuilder.group({
          'town': [null, Validators.required],
          'postalCode': [null, [Validators.required, Validators.pattern('^[1-9][1-9]-[1-9][1-9][1-9]$')]],
          'street': [null, Validators.required],
          'number': [null, Validators.required]
        })
      }
    );

    this.configurationForm.valueChanges.subscribe(() => this.isValid = this.configurationForm.valid);
    this.repeatPassword.setValidators([this.repeatPasswordValidator, Validators.required])
  }

  get pesel() {
    return this.configurationForm.get('pesel')
  }

  get password() {
    return this.configurationForm.get('password')
  }

  get repeatPassword() {
    return this.configurationForm.get('repeatPassword')
  }

  get repeatPasswordValidator() {
    return (control: AbstractControl) => {
      const isOk = this.password.value === control.value;
      return isOk ? null : {wrongPassword: {valid: false}};
    };
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

  get configurationDto(): UserConfigurationDto {
    const configDto = {...this.configurationForm.value, id: this.userId};
    delete configDto['repeatPassword'];
    return configDto
  }

  sendConfigurationData() {
    this.userService.postUserConfigurationData(this.configurationDto);
    this.configurationForm.reset()
  }
}
