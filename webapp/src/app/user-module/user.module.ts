import {NgModule} from "@angular/core";
import {UserRegisterComponent} from "./user-register-module/register.component";
import {UserRoutingModule} from "./user-routing.module";

@NgModule({
  imports: [UserRoutingModule],
  declarations: [
    UserRegisterComponent
  ],
  entryComponents: [
  ]
})

export class UserModule {
}
