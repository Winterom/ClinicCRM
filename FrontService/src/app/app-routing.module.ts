import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignInComponent} from "./pages/sigin/sign-in.component";


const routes: Routes = [
  {path:'main',loadChildren:()=>import('./pages/home/main-page.module').then(mod=>mod.MainPageModule)},
  { path: 'login', component: SignInComponent },
  { path: '', redirectTo: 'main', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
