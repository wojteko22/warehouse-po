import {Component, OnInit, ViewChild} from "@angular/core";
import {UserService} from "../user.service";
import {MdIconsDefinitions} from "../../md-icons-definitions";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSelectionList} from "@angular/material";

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

  constructor(private userService: UserService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.initForm(this.formBuilder.array([]));
    this.allUserPermissions().then(
      result => this.initForm(result)
    );
    this.userDataForm.updateValueAndValidity();
  }

  private initForm(permissions: FormArray) {
    this.userDataForm = this.formBuilder.group({
        'name': [null, Validators.required],
        'surname': [null, Validators.required],
        'email': [null, Validators.email],
        'permissions': permissions
      }
    );

    this.userDataForm.valueChanges.subscribe(() => this.isValid = this.userDataForm.valid)
  }

  private async allUserPermissions(): Promise<FormArray> {
    let result = await this.userService.getUserPermissions();
    const permissions = result.map(permission => this.formBuilder.control(permission));
    return this.formBuilder.array(permissions);
  }

  private get permissions(): FormArray {
    return this.userDataForm.get('permissions') as FormArray
  }

  async sendUserData() {
    const userDto = this.userData;
    const isUnique = await this.userService.getIsUniqueEmail(userDto.email);
    if (isUnique) {
      this.userService.postUserData(userDto)
    }
  //  todo: jak nie uniqie to jakieś komunikat
  }

  private get userData(): UserRegisterDto {
    return {...this.userDataForm.value, permissions: this.userPermissions.selectedOptions.selected.map(x => x.value)}
  }
}
