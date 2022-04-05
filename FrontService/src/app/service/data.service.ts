import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiService} from "./api.service";

@Injectable({
  providedIn: 'root'
})
export class DataService {


  constructor(private http: HttpClient, private api: ApiService) { }

  public getAllUsersPaging(page:number,size:number,sort_field:string|null,directSort:boolean,
                           search_field:string|null,search_value:string|null, status:Set<string>):Observable<any> {

    const getAllUserWithFilters = {
      page: page,
      itemInPage:size,
      sortField:sort_field,
      directSort:directSort,
      searchField:search_value,
      status:Array.from(status.keys())
    }
    console.log(JSON.stringify(getAllUserWithFilters));
    return this.http.post<any>(this.api.getAllUser,getAllUserWithFilters)
  };
}
