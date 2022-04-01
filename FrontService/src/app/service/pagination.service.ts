import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaginationService {
  public page$ = new Subject<number>();
  constructor() { }

  public changePage(count: number) {
    this.page$.next(count);
  }
}
