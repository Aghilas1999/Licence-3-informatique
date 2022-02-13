import { User } from "../models/user.model";
import { Subject } from "rxjs";
import { Injectable } from "@angular/core";


@Injectable()
export class UserService {


  private users: User[] = [
    new User('Charles', 'Quint', 'charles.quint@lesrois.fr', 'License 3 informatique', ['webapplication', 'baby - foot'])
];

  userSubject = new Subject<User[]>();


  constructor() { }

  emitUsers() {
    this.userSubject.next(this.users.slice());
}

addUser(user: User) {
    this.users.push(user);
    this.emitUsers();
}


}
