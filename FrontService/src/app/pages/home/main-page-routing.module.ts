import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./main.component";
import {MainPageGuard} from "../../guard/main-page-guard.service";
import {DesktopComponent} from "./desktop/desktop.component";
import {UsersComponent} from "./admin/users/users.component";
import {AuthoritiesComponent} from "./admin/authorities/authorities.component";
import {LoggingComponent} from "./admin/logging/logging.component";
import {UserByIdComponent} from "./admin/users/user-by-id/user-by-id.component";
import {AuthInterceptor} from "../../helpers/auth.interceptor";


const mainPageRoutes: Routes =[
  {
    path: '',
    component: MainComponent, canActivate: [MainPageGuard],
    data: { breadcrumb: 'Главная' },
    children:[
      {
      path: 'desktop',
      component: DesktopComponent,
      },
      {
        path:'users',
        data: { breadcrumb: 'Пользователи' },
        children:[
          {
            path:'',
            component:UsersComponent,
            data: { breadcrumb: '' },
          },
          {
          path:'userdetails',
          component:UserByIdComponent,
          data: { breadcrumb: 'Пользователь' }
          }
        ]
      },
      {
        path:'authorities',
        component:AuthoritiesComponent,
        data: { breadcrumb: 'Права и привелегии' }
      },
      {
        path:'logging',
        component:LoggingComponent,
        data: { breadcrumb: 'Логгирование' }
      },
      {
        path: '',
        redirectTo: 'desktop',
        pathMatch: 'full'
      }
    ]
  }
]
@NgModule({
  imports: [RouterModule.forChild(mainPageRoutes)],
  exports: [RouterModule]
})
export class MainPageRoutingModule { }
