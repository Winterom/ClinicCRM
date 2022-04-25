import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AbstractControl, FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserDataService} from "../../../../../service/user-data.service";
import {CheckboxItem} from "./CheckBoxItem"

@Component({
  selector: 'app-user-by-id',
  templateUrl: './user-by-id.component.html',
  styleUrls: ['./user-by-id.component.css', '../users.component.css']
})


export class UserByIdComponent {
  statusValue = [
    { id: 1, value: 'Активный' ,response:'ACTIVE',style:'color: green'},
    { id: 2, value: 'Заблокирован', response: 'BANNED',style:'color: orange' },
    { id: 3, value: 'Удален', response: 'DELETED',style:'color: red'}
  ];
  data:any;
  roleResponse:any;
  titlePage: string = '';
  id: string | null | undefined;
  isNewUser: boolean = false;
  form: FormGroup;
  fullName: string = '';
  errorMessage = '';
  submitted = false;
  roleArray: CheckboxItem[] = [];
  emailVerified: string='';
  phoneVerified: string='';
  createdAt: string='';
  lastUpdate: string='';
  isEmailVerified: boolean=false;
  isPhoneNumberVerified:boolean=false;
  messageString:string='';
  errorStatus:number=0;


  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private dataService: UserDataService) {
    this.form = this.formBuilder.group({
      controlInputSurname: ['', [Validators.required,Validators.maxLength(50)]],
      controlInputName: ['', [Validators.required,Validators.maxLength(50)]],
      controlInputLastName: [''],
      controlInputEmail: ['', [Validators.email,Validators.required]],
      controlInputPhone: ['', [Validators.required,Validators.pattern('^\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$')]],
      controlSelectStatus:[],
      checkBoxControlArray: formBuilder.array([])
    });
    this.id = this.route.snapshot.queryParamMap.get('id');
    let command = this.route.snapshot.queryParamMap.get('status')
    if (command && command === 'new_user') {
      this.isNewUser = true;
    } else if (command) {
      this.isNewUser = false
    } else return;
    if (this.isNewUser) {
      this.titlePage = 'Создание нового пользователя'
    } else {
      this.titlePage = 'Информация о пользователе'
    }
    if (this.id != null) {
      this.dataService.getProfileById(this.id).subscribe((data: any) => {
        this.data = data;
         this.fillForm(data);
        }
      );
      this.dataService.getRoleByUserId(this.id).subscribe((result:any)=>{
        this.roleArray=[];
        this.roleResponse =result;
        this.roleResponse.map((x: { id: number; roleName: string; description: string; userHasRole: boolean | undefined; })=>this.roleArray.push(new CheckboxItem(x.id,x.roleName,x.description,x.userHasRole)));
        let ar:FormArray = this.getFormsControls();
        ar.clear();
        this.roleArray.forEach(x=>ar.push(x.control))
      })
    }

  }
  onSubmit() {
    this.submitted = true;
    if (this.form.invalid) {
      this.errorStatus = 100;
      this.messageString = "Не все поля формы заполнены правильно"
      return;
    }
    let check = new Set<number>();
    this.roleArray.filter(x=>x.selected).map(y=>check.add(y.getId()));
    if(check.size===0){
      this.errorStatus = 100;
      this.messageString = "Должна быть назначена хотя бы одна роль"
      return;
    }
    this.dataService.saveOrUpdateUser(this.id,this.form.value.controlInputSurname,
      this.form.value.controlInputName,this.form.value.controlInputLastName,
      this.form.value.controlInputEmail,this.form.value.controlInputPhone,
      this.getValueByID(this.form.value.controlSelectStatus),Array.from(check.values())).subscribe((data)=>{
      if(data==='CREATED'){
        this.messageString='Пользователь успешно сохранен';
        this.errorStatus=200;
      }
    }
    )
    this.submitted=false;
  }
  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }
  getFormsControls() : FormArray{
    return this.form.controls['checkBoxControlArray'] as FormArray;
  }

  fillForm(data:any){
    this.createdAt = (new Date(data.created_at)).toLocaleDateString();
    this.lastUpdate = (new Date(data.updated_at)).toLocaleDateString("ru");
    this.form.controls['controlInputSurname'].setValue(data.surname);
    this.form.controls['controlInputName'].setValue(data.firstname);
    this.form.controls['controlInputLastName'].setValue(data.lastname);
    this.fioInputChange();
    this.form.controls['controlInputEmail'].setValue(data.email);
    this.form.controls['controlInputPhone'].setValue(data.phoneNumber);
    let st=this.getValueByResponse(data.status);
    this.form.controls['controlSelectStatus'].setValue(st.id);
    this.isEmailVerified = this.data.isEmailVerified
    if (this.data.isEmailVerified){
      this.emailVerified="Почта подтверждена"
    }else {
      this.emailVerified="Почта не подтверждена"
    }
    this.isPhoneNumberVerified=this.data.isPhoneNumberVerified
    if(this.data.isPhoneNumberVerified){
      this.phoneVerified = "Телефон подтвержден"
    }else {
      this.phoneVerified = "Телефон не подтвержден"
    }
  }

  getValueByResponse(responseValue:string){
    let rez = this.statusValue.filter(x => x.response===responseValue);
    return rez[0];
  }

  getValueByID(id:number){
    let rez = this.statusValue.filter(x=>x.id===id);
    return rez[0].response;
  }

  fioInputChange() {
    this.fullName = this.form.value.controlInputSurname + ' ' +
      this.form.value.controlInputName + ' ' +
      this.form.value.controlInputLastName;
  }

  onReset() {
    this.submitted = false;
    this.fillForm(this.data);
    this.roleArray=[];
    this.roleResponse.map((x: { id: number; roleName: string; description: string; userHasRole: boolean | undefined; })=>this.roleArray.push(new CheckboxItem(x.id,x.roleName,x.description,x.userHasRole)));
    let ar:FormArray = this.getFormsControls();
    ar.clear();
    this.roleArray.forEach(x=>ar.push(x.control))
  }
}
