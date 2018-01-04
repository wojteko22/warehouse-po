import {NgModule} from "@angular/core";
import {UserRegisterComponent} from "./user-register/register.component";
import {UserRoutingModule} from "./user-routing.module";
import {SharedModule} from "../shared-module/shared.module";

@NgModule({
  imports: [UserRoutingModule, SharedModule],
  declarations: [
    UserRegisterComponent
  ],
  entryComponents: [
  ]
})

export class UserModule {
}
