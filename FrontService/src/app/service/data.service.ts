import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiService} from "./api.service";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient, private api: ApiService) { }

  public getAllUsersPaging():Observable<any[]> {
    return this.http.get<any[]>(this.api.getAllUser)
  };
}
