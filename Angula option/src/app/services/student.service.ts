import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import { Subject } from 'rxjs-compat';


@Injectable({
  providedIn: 'root'
})
export class StudentService{

  constructor(private router:Router){}
  private students : any  =  [
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
    for(let student of this.students){
      student.status = 'present';
    }
  }

  switchOfAll(){
    for(let student of this.students){
      student.status = 'absent';
    }
  }

  switchOneOn(i: number)
  {
    this.students[i].status='present';
  }

  switchOneOff(i: number)
  {
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

  studentsSubject = new Subject<any[]>() ;


  emitStudentSubject() {
    this.studentsSubject.next(this.students.slice());
    }
}
