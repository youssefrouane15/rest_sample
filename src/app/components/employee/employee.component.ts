import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../../services/employee.service';
import {Employee} from '../../employee';
import {EmployeeModel} from '../../employee-model'
import {ClientModel} from '../../client-model';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, NgForm, FormGroup, Validators} from '@angular/forms';
import {MatDialog, MatDialogRef} from '@angular/material';
import {DialogComponent} from '../dialog/dialog.component'
import {formatDate} from '@angular/common';
import * as moment from 'moment'

@Component({selector: 'app-employee', templateUrl: './employee.component.html', styleUrls: ['./employee.component.css']})
export class EmployeeComponent implements OnInit {
  public employeeForm : FormGroup;
  employeesResult : Employee;
  clientModel : ClientModel = {
    code: "",
    name: ""
  }
  employeeModel : EmployeeModel = {
    id: "",
    firstName: "",
    lastName: "",
    birthDate: "",
    birthDateFormatted: moment(),
    currentPosition: "",
    technologies: "",
    client: this.clientModel
  };

  title : string;
  showAddAlert : boolean;
  showDeleteModal : boolean;
  columnsToDisplay = [
    'firstName',
    'lastName',
    'birthDate',
    'currentPosition',
    'technologies',
    'client.name',
    'consult',
    'delete',
    'edit'
  ];
  constructor(private EmployeeService : EmployeeService, private route : ActivatedRoute, private myRouter : Router, private dialog : MatDialog) {
    !this.EmployeeService.showForm && this
      .EmployeeService
      .getEmployees('')
      .then((response) => {
        this.employeesResult = response;
        console.log(response)
      }, (error) => {
        alert("Error: " + error.statusText)
      })
  }

  ngOnInit() {
    this.employeeForm = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      birthDate: new FormControl('', [Validators.required]),
      currentPosition: new FormControl('', [Validators.required]),
      technologies: new FormControl('', [Validators.required]),
      clientCode: new FormControl('', [Validators.required]),
      clientName: new FormControl('', [Validators.required])
    });
    !this.EmployeeService.showForm && this
      .EmployeeService
      .getEmployees('')
      .then((response) => {
        this.employeesResult = response;
        console.log(response)
      }, (error) => {
        alert("Error: " + error.statusText)
      })
    this
      .route
      .data
      .subscribe((result) => {
        this.title = "Employees"
      });
  }
  public hasError = (controlName : string, errorName : string) => {
    return this
      .employeeForm
      .controls[controlName]
      .hasError(errorName);
  }

  handleSubmit = (form : NgForm) => {

    this.employeeModel.birthDate = formatDate(this.employeeModel.birthDate, 'dd/MM/yyyy', 'fr');
    this
      .EmployeeService
      .addEmployee(this.employeeModel)
      .subscribe((response) => {
        this.showAddAlert = true;
        console.log(response);
      }, (error) => {
        this.showAddAlert = false;
      })
  }

  handleConsult = (employeeId : string) => {
    this
      .myRouter
      .navigate(['employee/' + employeeId])
  }
  handleUpdate = (employeeId : string) => {
    this
      .myRouter
      .navigate(['employee/update/' + employeeId])
  }
  openDialog(id : string) : void {
    this.EmployeeService.employeeId = id;
    const dialogRef = this
      .dialog
      .open(DialogComponent, {width: '250px'});
    dialogRef
      .afterClosed()
      .subscribe(result => {
        console.log('The dialog was closed');
        this
          .EmployeeService
          .getEmployees('')
          .then((response) => {
            this.employeesResult = response;
            console.log(response)
          }, (error) => {
            alert("Error: " + error.statusText)
          })
      });
  }

}