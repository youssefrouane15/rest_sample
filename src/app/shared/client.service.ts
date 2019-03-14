import { Injectable } from '@angular/core';
import { Response, Headers, RequestOptions, RequestMethod } from '@angular/http';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent, throwError } from 'rxjs';
import 'rxjs/operator/toPromise';
import { catchError } from 'rxjs/operators';
import { Client } from './client.model';

import 'rxjs/add/operator/map';
import { HttpHeaders, HttpErrorResponse, HttpClient } from '@angular/common/http';

  const apiUrl = "http://localhost:9966/agh/v1/clients";
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };
@Injectable({
  providedIn: 'root'
})
export class ClientService {
  selectedClient: Client;
  client: Client;
  clientList: Client[];
  acess_token: string = "?access_token=396d71f4-48c5-4250-98c9-880d11b1ec0f";
  constructor(private http: HttpClient) {
    

  }
  postClient (clt: Client):Observable<Client> {
    return this.http.post<Client>(apiUrl+this.acess_token, clt,httpOptions);
}
  
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }


  getListClients() :Observable<Client[]>{
    return this.http.get<Client[]>(apiUrl+this.acess_token);

  }
  getClientById(clientId: number): Observable<Client> {
    return this.http.get<Client>('http://localhost:9966/agh/v1/clients/id/'+ clientId+this.acess_token);
  }
  putClient(id, clt):Observable<Client>{  
    return this.http.put <Client>(`${apiUrl}/${id}`+this.acess_token,
   clt,httpOptions).
    pipe(catchError(this.handleError)
    );
  }
  deleteClient(id: number) {
    return this.http.delete(`${apiUrl}/${id}` );
  }

 
  postLevelLogger (level: string) {
    return this.http.post("http://localhost:9966/agh/level/"+level+this.acess_token,httpOptions);
}
}
