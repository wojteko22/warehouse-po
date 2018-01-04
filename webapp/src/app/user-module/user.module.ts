import {NgModule} from "@angular/core";
import {UserRegisterComponent} from "./register/register.component";
import {UserRoutingModule} from "./user-routing.module";
import {SharedModule} from "../shared-module/shared.module";
import {UserConfigurationComponent} from "./configuration/configuration.component";

@NgModule({
  imports: [UserRoutingModule, SharedModule],
  declarations: [
    UserRegisterComponent,
    UserConfigurationComponent
  ]
})

export class UserModule {
}
