import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { StudentService } from '../services/student.service';

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html',
  styleUrls: ['./edit-student.component.scss']
})
export class EditStudentComponent implements OnInit {

  defaultStatus = 'absent';

  constructor(private router: Router, private studentService: StudentService) { }

  ngOnInit(): void { }

  onSubmit(form: NgForm) {
    console.log(form.value);
    const name = form.value['name'];
    const status = form.value['status'];
    this.studentService.addStudent(name, status);
    this.router.navigate['students'];
  }

}
