import {Injectable} from '@angular/core';

const TOKEN_KEY = 'auth-token';

@Injectable({
  providedIn: 'root'
})
export class UserAppService {
  private isLogin: boolean = false;
  private _expireDate: Date | undefined;
  private _email: string='';
  private _roles = new Set;

  constructor() {
    if (!this.getToken()) {
      return;
    }
    this.parseJwt(this.getToken())
    if(this.isNonExpire()) {
      this.isLogin = true;
    }else {
      this.isLogin = false;
    }
  }

  signOut(): void {
    this._roles.clear();
    this.isLogin =false;
    this._email='';
    this._expireDate = undefined;
    window.localStorage.clear();
  }

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
    this.parseJwt(token);
  }

  public getToken(): string {
    return <string>window.localStorage.getItem(TOKEN_KEY);
  }

  parseJwt(token: string) {
    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    let jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    console.log(JSON.parse(jsonPayload));
    let tok = JSON.parse(jsonPayload);
    this._expireDate = new Date(tok.exp * 1000);
    this._email = tok.sub;
    this._roles = new Set(tok.roles);
  }

  isNonExpire():boolean{
    if (this.expireDate){
      if (this.expireDate.getDate()<Date.now()){
        return true;
      }
      return false;
    }
    return false;
  }
  isLog(): boolean {
    return this.isLogin;
  }

  setLog(log: boolean) {
    this.isLogin = log;
  }


  get expireDate(): Date | undefined {
    return this._expireDate;
  }

  get email(): string {
    return this._email;
  }

  get roles(): Set<any> {
    return this._roles;
  }
}


