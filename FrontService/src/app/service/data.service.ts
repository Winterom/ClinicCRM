import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiService} from "./api.service";

@Injectable({
  providedIn: 'root'
})
export class DataService {


  constructor(private http: HttpClient, private api: ApiService) { }

  public getAllUsersPaging(page:number,size:number,sort_field:string|null,directSort:boolean,
                           search_field:string|null,search_value:string|null):Observable<any> {
    console.log(page)
    console.log(size)
    console.log(sort_field)
    console.log(directSort)
    console.log(search_field)
    console.log(search_value)
    let params = new HttpParams();
    params = params.set("page",page.toString())
    params = params.set("size",size.toString());
    if(sort_field){
      params = params.set("sort_field",sort_field.toString());
    }
    params = params.set("directSort",directSort);
    if (search_field){
      params = params.set("search_field",search_field.toString());
    }
    if (search_value){
      params = params.set("search_value",search_value.toString());
    }
    console.log(params);
    return this.http.get<any>(this.api.getAllUser, {'params': params })
  };
}
