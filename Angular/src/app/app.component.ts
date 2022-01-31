import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'my-students-app';

  isAuth:boolean = false;
  lastUpdate = new Date();
  students:any = [
    {
      name: 'Henri',
      status: 'present'
    },
    {
      name: 'Astarté',
      status:'present'
    },
    {
      name: 'Cador',
      status: 'absent'
    },
    {
      name: 'Semire',
      status: 'absent'
    },
    {
      name: 'Moabdar',
      status: 'present'
    },
    {
      name: 'Azora',
      status:'present'
    },
    {
      name: 'Setoc',
      status:'absent'
    }
  ]


  constructor(){
    setTimeout(
      () => {
        this.isAuth = true;
      },4000
    );
  }

  allPresent(){
    alert('Ils sont tous present !');
  }

}
