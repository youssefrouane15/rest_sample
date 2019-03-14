import { Component, OnInit, Input } from '@angular/core';
import { Client } from '../shared/client.model';
import { ClientService } from '../shared/client.service';
import { ActivatedRoute } from '@angular/router';
import { Adress } from '../shared/adress.model';

@Component({
  selector: 'app-read-client',
  templateUrl: './read-client.component.html',
  styleUrls: ['./read-client.component.css']
})
export class ReadClientComponent implements OnInit {

  client = new Client;
  id: number;
  adress = new Adress;
  constructor(private clientService: ClientService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.client.adress=this.adress;
    this.id = this.route.snapshot.params['id'];
    this.clientService.getClientById(this.id)
      .subscribe(client => this.client = client);
    console.log(this.client);
  }

}
