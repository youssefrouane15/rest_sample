import { Injectable } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeModel } from '../employee-model';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {catchError} from 'rxjs/operators'

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
cachedValues : Array < {
  [query : string]: Employee
}> = [];
  showForm: boolean;
  baseUrl: string = "http://localhost:8080/agh/employees";
  access_token: string;
  employeeId: string;
  constructor(private http: HttpClient) { 
    this.http = http;
    this.showForm = false;
    console.log(this.showForm);
  }
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
  /** POST: add a new Emloyee to the database */
addEmployee(employee: EmployeeModel): Observable<EmployeeModel> {
  return this.http.post<EmployeeModel>(this.baseUrl + '?access_token=' + this.access_token, employee, httpOptions)
    .pipe(
      catchError(this.handleError)
    );
}

/** UPDATE: update the given employee*/
updateEmployee(employee: EmployeeModel): Observable<EmployeeModel> {
  return this.http.put<EmployeeModel>(this.baseUrl +'/' + employee.id + '?access_token=' + this.access_token, employee, httpOptions)
    .pipe(
      catchError(this.handleError)
    );
}
/** DELETE: delete the given employee from the database */
deleteEmployee (id: string): Observable<{}> {
  const url = `${this.baseUrl}/${id}`; // DELETE api/heroes/42
  return this.http.delete(url +  '?access_token=' + this.access_token, httpOptions)
    .pipe(
      catchError(this.handleError)
    );
}

/** GET: fetch for all the employees from the database */
getEmployees = (query: string): Promise<Employee> => {
    let promise = new Promise<Employee>((resolve, reject) => {
      if (this.cachedValues[query]) {
        resolve(this.cachedValues[query])
      }else {
        this.http.get(this.baseUrl +  '?access_token=' + this.access_token)
        .toPromise()
        .then( (response) => {
          resolve(response as Employee)
      }, (error) => {
          reject(error);
      })        
      }
      })
      return promise;
      }

/** GET: fetch for the given employee by Id from the database */
getEmployee = (id: string): Promise<Employee> => {
    let promise = new Promise<Employee>((resolve, reject) => {
        this.http.get(this.baseUrl + '/' +id + '?access_token=' + this.access_token)
        .toPromise()
        .then( (response) => {
          resolve(response as Employee)
      }, (error) => {
          reject(error);
      })        
      })
      return promise;
      }
  enableForm = () => {
    this.showForm = true;
    console.log(this.showForm);
  }
  disableForm = () => {
    this.showForm = false;
    console.log(this.showForm);    
  }
}
