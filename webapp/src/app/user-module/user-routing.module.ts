import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {UserRegisterComponent} from './register/register.component';
import {UserConfigurationComponent} from "./configuration/configuration.component";

const routes: Routes = [
  {path: '', component: UserRegisterComponent},
  {path: 'configuration/:id', component: UserConfigurationComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule {
}
