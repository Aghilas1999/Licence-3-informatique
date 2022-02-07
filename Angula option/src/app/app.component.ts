import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import { Observable, Subscription ,interval} from 'rxjs';
import 'rxjs/Rx';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'my-students-app';
  secondes : number | any;
  counterSubscription: Subscription | any;

  ngOnInit(){
    const counter = interval(1000);
    this.counterSubscription = counter.subscribe(
      (value) => {
        this.secondes = value;
       // this.emitstudentSubject();
        },
        (error) => {
        console.log('An error occured ! : ' + error);
        },
        () => {
        console.log('Observable complete!');
        }
        );
  }
  ngOnDestroy() {
    this.counterSubscription.unsubscribe();
    }

}
