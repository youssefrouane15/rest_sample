import { Component, OnInit } from '@angular/core';
import {EmployeeService} from '../../services/employee.service';


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  addEmployee: boolean;  
  constructor(private EmployeeService: EmployeeService) { }

  ngOnInit() {
  }
}
