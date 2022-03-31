import { Injectable } from '@angular/core';
import {HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
   private _httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
   private API_ROOT="http://localhost:5555/"
   private _loginApi = 'auth/api/v1/auth';
   private _register = 'auth/api/v1/registration';
    private _getAllUser ='auth/api/v1/get_all'

  get getAllUser(): string {
    return this.API_ROOT+this._getAllUser;
  }

  get httpOptions(): { headers: HttpHeaders } {
    return this._httpOptions;
  }

  get loginApi(): string {
    return this.API_ROOT+this._loginApi;
  }

  get register(): string {
    return this.API_ROOT+this._register;
  }


}
