import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.scss']
})
export class StudentsComponent implements OnInit {

  @Input() studentName!:string;
  @Input() studentStatus!:string;

  constructor() { }

  ngOnInit(): void {
    // this.studentName = 'Aboubacar';
    // this.studentStatus = 'present';
  }

  getStatus(){
    return this.studentStatus;
  }

  getColor(){
    if(this.studentStatus === 'present'){
      return 'green'
    }else if(this.studentStatus === 'absent'){
      return 'red';
    }
    return '';
  }

}
