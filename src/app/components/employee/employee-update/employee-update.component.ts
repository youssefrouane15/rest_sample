import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {EmployeeModel} from '../../../employee-model'
import {ClientModel} from '../../../client-model'
import {EmployeeService} from '../../../services/employee.service';
import {Employee} from '../../../employee';
import {FormControl, NgForm, FormGroup, Validators} from '@angular/forms';
import {MatDialog, MatDialogRef} from '@angular/material';
import {formatDate} from '@angular/common';
import * as moment from 'moment'


@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit {
  public employeeForm: FormGroup;  
  employeesResult: Employee;
  clientModel: ClientModel = {
    code: "",
    name: ""
  }
  employeeModel: EmployeeModel = {
    id: "",
    firstName: "",
    lastName: "",
    birthDate: "", 
    birthDateFormatted: moment(),
    currentPosition: "",
    technologies: "",
    client : this.clientModel
};
  employeeResult: Employee;
  id: string;
  showUpdateAlert: boolean;
  constructor(private EmployeeService : EmployeeService, private route : ActivatedRoute) { }

  ngOnInit() {
    this.employeeForm = new FormGroup({
      firstName : new FormControl('', [Validators.required]),
      lastName : new FormControl('', [Validators.required]),
      birthDate : new FormControl('', [Validators.required]),
      currentPosition : new FormControl('', [Validators.required]),
      technologies : new FormControl('', [Validators.required]),
      clientCode : new FormControl('', [Validators.required]),
      clientName : new FormControl('', [Validators.required])
    });
    this.id = this.route.snapshot.paramMap.get('id');
    this.EmployeeService.getEmployee(this.id).then((response) => {
      this.employeeResult = response;
      this.clientModel = {
         code: response.client.code,
         name: response.client.name
      }
      this.employeeModel = {
        id: '' + response.employeeId,
        firstName: response.firstName,
        lastName: response.lastName,
        birthDate: response.birthDate,         
        birthDateFormatted: moment(response.birthDate), 
        currentPosition: response.currentPosition,
        technologies: response.technologies,
        client : this.clientModel
      };
      console.log(this.employeeModel)
    }, (error) => {
      alert("Error: " + error.statusText)
    })

  }
  public hasError = (controlName: string, errorName: string) =>{
    return this.employeeForm.controls[controlName].hasError(errorName);
  }
  handleSubmit = (form: NgForm) => {
    
      this.employeeModel.birthDate = formatDate(this.employeeModel.birthDate, 'dd/MM/yyyy', 'fr');
      this.EmployeeService.updateEmployee(this.employeeModel)
      .subscribe((response) => {
          this.showUpdateAlert = true;
        console.log(response);
      }, (error) => {
        this.showUpdateAlert = false;    
      })
    }

}
