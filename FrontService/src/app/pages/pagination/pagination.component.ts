import {ChangeDetectionStrategy, Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {PaginationService} from "../../service/pagination.service";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PaginationComponent implements OnInit, OnChanges {
  MAX_PAGE_ITEMS = 5;

  buttons: number[] | undefined;
  lastPageDisable: boolean = false;
  firstPageDisable:boolean = false;
  @Input() currentPage: number | undefined;
  @Input() totalPage: number | undefined;

  constructor(private pageService:PaginationService) {

  }

  ngOnChanges(changes: SimpleChanges): void {
      if (changes['currentPage']){
        this.paging()
      }
      if(changes['totalPage']){
        this.paging()
      }
    }

  ngOnInit(): void {
    this.paging()
  }

  paging(){
    let startPage: number;
    let endPage;
    if(this.currentPage===undefined || this.totalPage===undefined){
      return;
    }
      this.firstPageDisable = this.currentPage === 1;
      this.lastPageDisable = this.currentPage === this.totalPage;
      if(this.totalPage<this.MAX_PAGE_ITEMS){
        this.buttons = Array.from(Array(this.totalPage).keys()).map(i=>1+i)
        return;
      }
      let maxPagesBeforeCurrentPage = Math.floor(this.MAX_PAGE_ITEMS / 2);
      let maxPagesAfterCurrentPage = Math.ceil(this.MAX_PAGE_ITEMS / 2) - 1;
      if (this.currentPage <= maxPagesBeforeCurrentPage) {
        startPage = 1;
        endPage = this.MAX_PAGE_ITEMS;
      } else if (this.currentPage + maxPagesAfterCurrentPage >= this.totalPage) {
        startPage = this.totalPage - this.MAX_PAGE_ITEMS + 1;
        endPage = this.totalPage;
      } else {
        startPage = this.currentPage - maxPagesBeforeCurrentPage;
        endPage = this.currentPage + maxPagesAfterCurrentPage;
      }
      this.buttons =Array.from(Array((endPage + 1) - startPage).keys()).map(i => startPage + i);
    }
  firstPage() {
    if(this.currentPage===1){
      return;
    }
      this.pageService.changePage(1);
  }

  lastPage() {
    if(this.totalPage===this.currentPage){
      return;
    }
      this.pageService.changePage(this.totalPage);
  }

  changePage(newPage:number){
    if(newPage===this.currentPage){
      return;
    }
    this.pageService.changePage(newPage);
  }
}
