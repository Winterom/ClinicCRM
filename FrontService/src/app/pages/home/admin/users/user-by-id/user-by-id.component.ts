import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DataService} from "../../../../../service/data.service";

@Component({
  selector: 'app-user-by-id',
  templateUrl: './user-by-id.component.html',
  styleUrls: ['./user-by-id.component.css', '../users.component.css']
})
export class UserByIdComponent implements OnInit {
  titlePage: string = '';
  id: string | null | undefined;
  isNewUser: boolean = false;
  form: FormGroup;
  fullName: string = '';
  errorMessage = '';
  submitted = false;
  roleArray: any[] = [];
  isEmailVerified: boolean = false;
  isPhoneNumberVerified: boolean = false;

  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private dataService: DataService) {
    this.form = this.formBuilder.group({
      controlInputSurname: ['', Validators.required],
      controlInputName: ['', Validators.required],
      controlInputLastName: [''],
      controlInputEmail: ['', Validators.email],
      controlInputPhone: ['', Validators.pattern('/^[\\d]{1}\\ \\([\\d]{2,3}\\)\\ [\\d]{2,3}-[\\d]{2,3}-[\\d]{2,3}$/')],
      checkBoxControl: []
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

          this.form.controls['controlInputSurname'].setValue(data.surname);
          this.form.controls['controlInputName'].setValue(data.firstname);
          this.form.controls['controlInputLastName'].setValue(data.lastname);
          this.fioInputChange();
          this.form.controls['controlInputEmail'].setValue(data.email);
          this.form.controls['controlInputPhone'].setValue(data.phoneNumber);
          this.isEmailVerified = data.sEmailVerified;
          this.isPhoneNumberVerified = data.isPhoneNumberVerified;
        }
      );
      this.dataService.getRoleByUserId(this.id).subscribe((result:any)=>{
        console.log(result)
        this.roleArray = result;
      })
    }

  }

  ngOnInit(): void {


  }

  onSubmit() {

  }

  fioInputChange() {
    this.fullName = this.form.value.controlInputSurname + ' ' +
      this.form.value.controlInputName + ' ' +
      this.form.value.controlInputLastName;
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onReset() {

  }
}
