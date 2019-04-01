import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {EmployeeModel} from '../../../employee-model'
import {ClientModel} from '../../../client-model'
import {EmployeeService} from '../../../services/employee.service';
import {Employee} from '../../../employee';
@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {
  employeeResult: Employee;
  id: string;
  constructor(private EmployeeService : EmployeeService, private route : ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.EmployeeService.getEmployee(this.id).then((response) => {
      this.employeeResult = response;      
      console.log(response)
    }, (error) => {
      alert("Error: " + error.statusText)
    })
  }

}
