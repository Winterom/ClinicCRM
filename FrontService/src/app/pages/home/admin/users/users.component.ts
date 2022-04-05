import {Component, OnInit} from '@angular/core';
import {DataService} from "../../../../service/data.service";
import {AbstractControl, FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Page} from "../../../../helpers/message";


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
  page: Page;

  constructor(private formBuilder: FormBuilder,private dataService:DataService, private router:Router,private route: ActivatedRoute) {
    this.page = {totalPage:1,currentPage:1};
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
    let statusEnum = new Set<string>();
      statusEnum.add('ACTIVE');
      statusEnum.add('DELETED');
      statusEnum.add('BANNED');
    this.dataService.getAllUsersPaging(this.page.currentPage,10,'id',true,null,null,statusEnum).subscribe((res: any) => {
      this.usersArray = res.content;
      this.page.currentPage = res.currentPage;
      this.page.totalPage = res.totalPage;
    });
  }

  ngOnInit(): void {

  }
  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit() {
    console.log(this.form.value);

    let sizePage:number|null = this.form.value.sizeValueControl.value;
    if(!sizePage){
      sizePage=10;
    }
    let directionSort: boolean =this.form.value.checkBoxSortedControl;
    let sortField: string=this.form.value.fieldSortControl.id;
    let searchValue: string|null=this.form.value.valueSearchControl;
    let searchField: string|null=this.form.value.fieldSearchControl.id;
    if (!(searchValue && searchValue.length>3)){
      searchValue=null;
      searchField=null;
    }
    let statusEnum = new Set<string>();
    if(this.form.value.checkBoxActiveControl){
      statusEnum.add('ACTIVE');
    }
    if(this.form.value.checkBoxDeletedControl){
      statusEnum.add('DELETED');
    }
    if(this.form.value.checkBoxBannedControl){
      statusEnum.add('BANNED')
    }
    this.dataService.getAllUsersPaging(this.page.currentPage,sizePage,sortField,directionSort,searchField,searchValue,statusEnum).subscribe({next: data => {
      console.log(data);
      this.usersArray = data.content;
      this.page.currentPage = data.currentPage;
      this.page.totalPage = data.totalPage;
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

}


