import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaginationService {
  private _page$ = new Subject<number>();
  constructor() { }

  public changePage(count: number|undefined) {
    if(count===undefined){
      return;
    }
    this._page$.next(count);
  }

  get page$(): Subject<number> {
    return this._page$;
  }
}
