import {Component, Input, OnInit} from '@angular/core';
import { StudentService } from "./services/student.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'my-students-app';

  ngOnInit(): void {
  }


}
