import {Component, OnInit, ViewChild} from "@angular/core";
import {UserService} from "../user.service";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ErrorStateMatcher, MatSelectionList} from "@angular/material";

@Component({
  selector: 'user-register',
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
  providers: [UserService]
})

export class UserRegisterComponent implements OnInit {
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
  userDataForm: FormGroup;
  isValid = false;
  @ViewChild(MatSelectionList) userPermissions: MatSelectionList;
  isExistEmail = false;
  errorMatcher: ErrorStateMatcher = {
    isErrorState: (control: FormControl | null): boolean  => {
      return !!(control && (control.invalid || this.isExistEmail) && control.touched);
    }
  };
  permissions = [];

  constructor(private userService: UserService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.initForm();
    this.allUserPermissions()
  }

  private initForm() {
    this.userDataForm = this.formBuilder.group({
        'name': [null, Validators.required],
        'surname': [null, Validators.required],
        'email': [null, [Validators.email, Validators.required]],
      }
    );

    this.userDataForm.valueChanges.subscribe(() => this.isValid = this.userDataForm.valid)
  }

  private async allUserPermissions() {
    this.permissions = await this.userService.getUserPermissions();
  }

  async sendUserData() {
    const userDto = this.userData;
    this.isExistEmail = await this.userService.getIsExistEmail(userDto.email);
    if (!this.isExistEmail) {
      this.userService.postUserData(userDto);
      this.userDataForm.reset();
      this.userPermissions.deselectAll()
    }
  }

  private get userData(): UserRegisterDto {
    return {...this.userDataForm.value, permissions: this.userPermissions.selectedOptions.selected.map(x => x.value)}
  }

  get nameInput() {
    return this.userDataForm.get('name')
  }

  get surnameInput() {
    return this.userDataForm.get('surname')
  }

  get emailInput() {
    return this.userDataForm.get('email')
  }
}
