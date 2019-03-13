import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {MatDialog, MatDialogRef} from '@angular/material';
import {EmployeeService} from '../../employee.service';
import {EmployeeComponent} from '../employee/employee.component'

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<EmployeeComponent>,  private myRouter : Router, public EmployeeService: EmployeeService) {}

  onNoClick(): void {
    this.dialogRef.close();
  }


  handleDelete = () => {
    this.EmployeeService.deleteEmployee(this.EmployeeService.employeeId).subscribe((response) => {
      console.log(response);
      this.dialogRef.close();      
      this.myRouter.navigate(['/'])      
    }, (error) => {
      console.log(error);
      this.dialogRef.close();      
    })
  }

  ngOnInit() {
  }

}
