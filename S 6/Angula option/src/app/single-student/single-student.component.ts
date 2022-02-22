import { Component, OnInit } from '@angular/core';
import {StudentService} from "../services/student.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-single-student',
  templateUrl: './single-student.component.html',
  styleUrls: ['./single-student.component.scss']
})
export class SingleStudentComponent implements OnInit {

  name!: string;
  status!: string;

  constructor(private studentService:StudentService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    const idStudent = +this.route.snapshot.params['id'];
    this.name = this.studentService.getStudentById(idStudent).name;
    this.status = this.studentService.getStudentById(idStudent).status;
  }

}
