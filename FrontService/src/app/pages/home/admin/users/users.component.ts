import { Component, OnInit } from '@angular/core';
import {DataService} from "../../../../service/data.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent  implements OnInit{
  usersArray: any[] = [];
  constructor(private dataService:DataService) {

  }

  ngOnInit(): void {
    this.dataService.getAllUsersPaging().subscribe((res: any) => {
      this.usersArray = res.listWrapper;
    });
    console.log(this.usersArray);
  }


}
