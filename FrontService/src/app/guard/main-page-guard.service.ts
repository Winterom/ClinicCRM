import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {UserAppService} from "../service/user-app.service";

@Injectable({
  providedIn: 'root'
})
export class MainPageGuard implements CanActivate {
  constructor(private user: UserAppService,private router: Router) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.user.isLog()){
      return this.router.navigate(['login'],{queryParams:{'redirectURL':state.url}});
    }
    return true;
  }

}
