import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {DataService} from "../../../../../service/data.service";

@Component({
  selector: 'app-user-by-id',
  templateUrl: './user-by-id.component.html',
  styleUrls: ['./user-by-id.component.css']
})
export class UserByIdComponent implements OnInit {
  titlePage:string='';
  id: string | null | undefined;
  isNewUser: boolean=false;
  form: FormGroup;
  constructor(private route:ActivatedRoute,private formBuilder: FormBuilder,private dataService:DataService) {
    this.form = this.formBuilder.group({});
    this.id=this.route.snapshot.queryParamMap.get('id');
    let command = this.route.snapshot.queryParamMap.get('status')
    if (command && command==='new'){
      this.isNewUser = true;
    }
    if(this.isNewUser){
      this.titlePage = 'Создание нового пользователя'
    }else {
      this.titlePage = 'Информация о пользователе'
    }
  }

  ngOnInit(): void {


  }

  onSubmit() {

  }
}
