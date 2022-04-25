import {Component, OnInit} from '@angular/core';
import {UserDataService} from "../../../../service/user-data.service";
import {AbstractControl, FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PaginationService} from "../../../../service/pagination.service";


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent  implements OnInit{

  form: FormGroup;
  sizeValue = [
    { id: 1, value: 10 },
    { id: 2, value: 20 },
    { id: 3, value: 30 }
  ];
  fieldValue=[
    {id: 'id', value:'ID'},
    {id: 'surname', value:'Фамилия'},
    {id: 'firstname', value:'Имя'},
    {id: 'lastname', value:'Отчество'},
    {id: 'email', value:'Email'},
    {id: 'phone_number', value:'Телефон'},
  ];
  usersArray: any[] = [];
  totalPage: number | undefined;
  currentPage: number | undefined;
  directionSort: boolean=true;
  sortField: string='id';
  searchValue: string|null=null;
  searchField: string|null=null;
  sizePage:number=10;
  statusEnum = new Set<string>();

  constructor(private formBuilder: FormBuilder,private dataService:UserDataService,
              private router:Router,private route: ActivatedRoute, private pageService:PaginationService) {

    this.form=this.formBuilder.group({
      sizeValueControl:[this.sizeValue[0]],
      fieldSortControl:[this.fieldValue[0]],
      checkBoxSortedControl:[true],
      fieldSearchControl:[this.fieldValue[0]],
      valueSearchControl:[null],
      checkBoxActiveControl:[true],
      checkBoxDeletedControl:[true],
      checkBoxBannedControl:[true]
    });
    this.statusEnum.clear()
    this.statusEnum.add('ACTIVE');
    this.statusEnum.add('DELETED');
    this.statusEnum.add('BANNED');
    this.dataService.getAllUsersPaging(1,this.sizePage,this.sortField,this.directionSort,
              this.searchField,this.searchValue,this.statusEnum).subscribe((res: any) => {
      this.usersArray = res.content;
      this.currentPage = res.currentPage;
      this.totalPage = res.totalPage;

    });

  }

  ngOnInit(): void {
      this.pageService.page$.subscribe((page)=>this.changePage(page));
  }
  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit() {
    this.sizePage = this.form.value.sizeValueControl.value;
    this.directionSort =this.form.value.checkBoxSortedControl;
    this.sortField =this.form.value.fieldSortControl.id;
    this.searchValue=this.form.value.valueSearchControl;
    this.searchField =this.form.value.fieldSearchControl.id;
    if (!(this.searchValue && this.searchValue.length>3)){
      this.searchValue=null;
      this.searchField=null;
    }
    this.statusEnum.clear()
    if(this.form.value.checkBoxActiveControl){
      this.statusEnum.add('ACTIVE');
    }
    if(this.form.value.checkBoxDeletedControl){
      this.statusEnum.add('DELETED');
    }
    if(this.form.value.checkBoxBannedControl){
      this.statusEnum.add('BANNED')
    }
    this.dataService.getAllUsersPaging(null,this.sizePage,this.sortField,this.directionSort,
      this.searchField,this.searchValue,this.statusEnum).subscribe({next: data => {
      this.usersArray = data.content;
      this.currentPage = data.currentPage;
      this.totalPage = data.totalPage;
    }, error:err => {
      console.log(err);
    }
    });
  }

  onReset(){
    this.form.setValue({sizeValueControl:this.sizeValue[0],
      fieldSortControl:this.fieldValue[0],
      checkBoxSortedControl:true,
      fieldSearchControl:this.fieldValue[0],
      valueSearchControl:null,
      checkBoxActiveControl:true,
      checkBoxDeletedControl:true,
      checkBoxBannedControl:true
    })
  }
  getUserById(id:number){
    this.router.navigate(['userdetails'],{relativeTo:this.route,queryParams:{id,status:'exist'}});
  }

  changePage(newPage:number){
    this.dataService.getAllUsersPaging(newPage,this.sizePage,this.sortField,this.directionSort,
      this.searchField,this.searchValue,this.statusEnum).subscribe({next: data => {
        console.log(data);
        this.usersArray = data.content;
        this.currentPage = data.currentPage;
        this.totalPage = data.totalPage;
      }, error:err => {
        console.log(err);
      }
    });
  }

  createUser() {
    this.router.navigate(['userdetails'],{relativeTo:this.route,queryParams:{status:'new_user'}});
  }
}


