import { Component, OnInit } from '@angular/core';
import {EmployeeService} from '../../employee.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public EmployeeService: EmployeeService) { }

  ngOnInit() {
  }

}
