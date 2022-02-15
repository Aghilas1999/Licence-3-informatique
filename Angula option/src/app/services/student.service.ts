import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import { Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class StudentService{

  studentsSubject = new Subject<any[]>() ;


  constructor(private router:Router){}
  private students =  [
    {
      id:1,
      name: 'Henri',
      status: 'present'
    },
    {
      id:2,
      name: 'Astarté',
      status:'present'
    },
    {
      id:3,
      name: 'Cador',
      status: 'absent'
    },
    {
      id:4,
      name: 'Semire',
      status: 'absent'
    },
    {
      id:5,
      name: 'Moabdar',
      status: 'present'
    },
    {
      id:6,
      name: 'Azora',
      status:'present'
    },
    {
      id:7,
      name: 'Setoc',
      status:'absent'
    }
  ]

  switchOnAll(){
    this.emitStudentSubject();
    for(let student of this.students){
      student.status = 'present';
    }
  }

  switchOffAll(){
    this.emitStudentSubject();
    for(let student of this.students){
      student.status = 'absent';
    }
  }

  switchOneOn(i: number){
    this.emitStudentSubject();
    this.students[i].status='present';
  }

  switchOneOff(i: number){
    this.emitStudentSubject();
    this.students[i].status='absent';
  }

  getStudentById(id: number){
    const student = this.students.find(
      (student:any) => {
        return student.id === id;
      }
    );
    if(!student){
      this.router.navigate(['/not-found']);
    }
    return student;
  }
    emitStudentSubject() {
     this.studentsSubject.next(this.students.slice());
     }

     addStudent(name: string, status: string) {
      const studentObject = {
          id: 0,
          name: "",
          status: ""
      };
      studentObject.name = name;
      studentObject.status = status;
      studentObject.id = this.students[(this.students.length - 1)].id + 1;
      this.students.push(studentObject);
      this.emitStudentSubject();
  }
    }
