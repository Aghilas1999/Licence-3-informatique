import { Component, OnInit, Input } from '@angular/core';
import {StudentService} from "../services/student.service";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.scss']
})
export class StudentsComponent implements OnInit {

  @Input() studentName!:string;
  @Input() studentStatus!:string;
  @Input() indexOfStudent!:number;

  @Input() id!: number;

  constructor(private studentService:StudentService) { }

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

  onSwitch(){
    if(this.studentStatus === 'present'){
      this.studentService.switchOneOff(this.indexOfStudent);
    }else if(this.studentStatus === 'absent'){
      this.studentService.switchOneOn(this.indexOfStudent);
    }
  }

}
