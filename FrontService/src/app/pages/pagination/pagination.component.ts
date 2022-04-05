import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Page} from "../../helpers/message";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit, OnChanges {
  MAX_PAGE_ITEMS = 5;
  @Input() page: Page | undefined;
  buttons: number[] | undefined;

  constructor() {

  }

  ngOnChanges(changes: SimpleChanges): void {
      if (changes['page']){
        this.paging(this.page)
      }
    }

  ngOnInit(): void {

  }

  paging(page:Page|undefined){
    let startPage: number;
    let endPage;
    if(!page){
      return;
    }

      if (page.currentPage < 1) {
        page.currentPage = 1;
      } else if (page.currentPage > page.totalPage) {
        page.currentPage = page.totalPage;
      }
      let maxPagesBeforeCurrentPage = Math.floor(this.MAX_PAGE_ITEMS / 2);
      let maxPagesAfterCurrentPage = Math.ceil(this.MAX_PAGE_ITEMS / 2) - 1;
      if (page.currentPage <= maxPagesBeforeCurrentPage) {
        startPage = 1;
        endPage = this.MAX_PAGE_ITEMS;
      } else if (page.currentPage + maxPagesAfterCurrentPage >= page.totalPage) {
        startPage = page.totalPage - this.MAX_PAGE_ITEMS + 1;
        endPage = page.totalPage;
      } else {
        startPage = page.currentPage - maxPagesBeforeCurrentPage;
        endPage = page.currentPage + maxPagesAfterCurrentPage;
      }
      this.buttons =Array.from(Array((endPage + 1) - startPage).keys()).map(i => startPage + i);
    }





}
