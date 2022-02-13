import { Component, Input, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/student.service';
import { Observable } from 'rxjs-compat/Observable';
import 'rxjs-compat/add/observable/interval';
import { Subscription } from 'rxjs';



@Component({
  selector: 'app-student-view',
  templateUrl: './student-view.component.html',
  styleUrls: ['./student-view.component.scss']
})
export class StudentViewComponent implements OnInit {
  title = 'etudiants';
  students!: any[];
  isAuth: boolean = false;
  lastUptdate = new Date();
  //@Input() id: number;
  studentSubscription: Subscription | any;

  constructor(private studentService: StudentService) {
    setTimeout(() => {
      this.isAuth = true;
    }, 4000);
  }

  ngOnInit() {
    this.studentSubscription = this.studentService.studentsSubject.subscribe(
      (students: any[]) => {
        this.students = students;
      }
    );
    this.studentService.emitStudentSubject();

  }

  ngOnDestroy(): void {
    this.studentSubscription.unsubscribe();
  }

  allPresent() {
    this.studentService.switchOnAll();
    return alert('Ils sont tous là !');
  }

  allAbsent() {
    if (confirm('Etes-vous sûr qu\'ils sont tous absents ?')) {
      this.studentService.switchOffAll();
    }
    }
  }
