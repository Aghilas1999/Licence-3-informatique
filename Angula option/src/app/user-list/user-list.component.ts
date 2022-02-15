import { Component, OnInit } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { User } from '../models/user.model';
import { Subscription } from 'rxjs';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
//import { HttpService } from '../services/http.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit, OnDestroy {

  users: User[];
  userSubscription: Subscription;

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {

    this.userSubscription = this.userService.userSubject.subscribe(
      (users: User[]) => {
      this.users = users;
      }
      );
      this.userService.emitUsers();


  ngOnDestroy(): void {
    this.userSubscription.unsubscribe();
  }
}
