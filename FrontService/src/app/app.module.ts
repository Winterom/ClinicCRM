import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminComponent } from './pages/home/admin/admin.component';

import { MainComponent } from './pages/home/main.component';
import { ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

import { DesktopComponent } from './pages/home/desktop/desktop.component';
import {LoginComponent} from "./pages/login/login.component";
import {MainPageModule} from "./pages/home/main-page.module";
import { BreadcrumbComponent } from './pages/breadcrumb/breadcrumb.component';
import {AuthInterceptor} from "./helpers/auth.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    MainComponent,
    LoginComponent,
    DesktopComponent,
    BreadcrumbComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    MainPageModule
  ],
  providers: [AuthInterceptor],
  bootstrap: [AppComponent]
})
export class AppModule { }
