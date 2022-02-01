import { Component, OnInit } from '@angular/core';
import {StudentService} from "../services/student.service";

@Component({
  selector: 'app-student-view',
  templateUrl: './student-view.component.html',
  styleUrls: ['./student-view.component.scss']
})
export class StudentViewComponent implements OnInit {

  isAuth:boolean = false;
  lastUpdate = new Date();
  students!:any;

  constructor(private studentService:StudentService){
    setTimeout(
      () => {
        this.isAuth = true;
      },4000
    );
  }
  ngOnInit() {
    this.students = this.studentService.students;
  }

  allPresent(){
    this.studentService.switchOnAll();
  }

  allAbsent(){
    if(confirm('Etes-vous sure qu\'ils sont tous absents ?')){
      this.studentService.switchOfAll();
    }
  }



}
