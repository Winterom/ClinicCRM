import {Component, OnInit} from '@angular/core';
import {UserAppService} from "../../service/user-app.service";
import {Role} from "../../service/role";

@Component({
  selector: 'app-home',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  collapseMenu = false;
  showAdmin:boolean=false;
  constructor(private user:UserAppService) {

  }

  ngOnInit(): void {
    let role = this.user.getRole();
    if (role){
      this.showAdmin=(role.has(Role.ADMIN_USER_WRITE)||role.has(Role.ADMIN_USER_READ));
    }

  }

  collapseLeftMenu():void{
    this.collapseMenu = !this.collapseMenu;
  }
  logout(): void{
    this.user.signOut();
    location.reload();
  }
}
