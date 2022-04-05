import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {MainPageRoutingModule} from "./main-page-routing.module";
import {UsersComponent} from "./admin/users/users.component";
import {AuthoritiesComponent} from "./admin/authorities/authorities.component";
import {LoggingComponent} from "./admin/logging/logging.component";
import {UserByIdComponent} from './admin/users/user-by-id/user-by-id.component';
import {PaginationComponent} from "../pagination/pagination.component";
import {AdminComponent} from "./admin/admin.component";
import {MainComponent} from "./main.component";
import {DesktopComponent} from "./desktop/desktop.component";
import {BreadcrumbComponent} from "../breadcrumb/breadcrumb.component";


@NgModule({
  declarations: [
    UsersComponent,
    AuthoritiesComponent,
    LoggingComponent,
    UserByIdComponent,
    PaginationComponent,
    AdminComponent,
    MainComponent,
    DesktopComponent,
    BreadcrumbComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MainPageRoutingModule
  ]
})
export class MainPageModule { }
