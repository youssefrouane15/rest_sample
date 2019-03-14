import { Component, OnInit } from '@angular/core';
import { Client } from '../shared/client.model';
import { ClientService } from '../shared/client.service';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material';
import { Router } from '@angular/router';
import { Adress } from '../shared/adress.model';

@Component({
  selector: 'app-list-client',
  templateUrl: './list-client.component.html',
  styleUrls: ['./list-client.component.css']
})
export class ListClientComponent implements OnInit {
  pageSizeOptions: number[] = [5, 10, 25, 100];
  clientList = [];
  constructor(private clientService: ClientService, private router: Router) { }
client= new Client;
adress= new  Adress 
  ngOnInit() {
    this.clientService.getListClients().subscribe((response)=> this.clientList=response);
  }
  showForEdit(clientId: number) {
  this.router.navigate(['/clients/update/'+clientId]);
  }
  show(clientId: number){
    this.router.navigate(['/clients/'+clientId]);
  }
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
  }

  onDelete(id : number) {
         this.router.navigate(['/clients/delete/'+id]);
           
  }
}
