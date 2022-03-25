import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./main.component";
import {MainPageGuard} from "../../guard/main-page-guard.service";
import {DesktopComponent} from "./desktop/desktop.component";
import {UsersComponent} from "./admin/users/users.component";
import {AuthoritiesComponent} from "./admin/authorities/authorities.component";
import {LoggingComponent} from "./admin/logging/logging.component";


const mainPageRoutes: Routes =[
  {
    path: 'main',
    component: MainComponent, canActivate: [MainPageGuard],
    children:[{
      path: 'desktop',
      component: DesktopComponent,
    },
      {
        path:'users',
        component:UsersComponent,
      },
      {
        path:'authorities',
        component:AuthoritiesComponent
      },
      {
        path:'logging',
        component:LoggingComponent
      },
      {
        path: '', redirectTo: 'desktop', pathMatch: 'full'
      }
    ]
  }
]
@NgModule({
  imports: [RouterModule.forChild(mainPageRoutes)],
  exports: [RouterModule]
})
export class MainPageRoutingModule { }
