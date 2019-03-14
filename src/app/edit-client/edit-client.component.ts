import { Component, OnInit } from '@angular/core';
import { ClientService } from '../shared/client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Client } from '../shared/client.model';
import { NgForm, FormBuilder, FormControl, Validators, FormGroup } from '@angular/forms';
import { Adress } from '../shared/adress.model';

@Component({
  selector: 'app-edit-client',
  templateUrl: '../add-client/add-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit {
  id: number;
  adress = new Adress();
  client = new Client();
  clientForm = new FormGroup({
    'code': new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(13)
    ]),
    'numberPhone': new FormControl('', [
      Validators.required,
      Validators.minLength(6),
      Validators.maxLength(13)
    ]),
    'email': new FormControl('', [
      Validators.required,
      Validators.email,
    ]),
    'firstName': new FormControl('', Validators.required),
    'lastName': new FormControl('', Validators.required),
    'dateCreation': new FormControl('', Validators.required),
    'libelleCourt': new FormControl('', Validators.required),
    'libelleLong': new FormControl('', Validators.required),
    'codePostal': new FormControl('', Validators.required),
    'pay': new FormControl('', Validators.required),
    'ville': new FormControl('', Validators.required)

  });
  constructor(private clientService: ClientService, private router: Router, private route: ActivatedRoute) { }
  ngOnInit() {
    this.client.adress=this.adress;
    this.id = this.route.snapshot.params['id'];
    this.clientService.getClientById(this.id)
      .subscribe(client => this.client = client);
    console.log(this.client);
  }

  onSubmit(client: Client) {
    this.clientService.putClient(client.clientId, client)
      .subscribe(data => {
        this.clientForm.reset();
        this.clientService.getListClients();
        this.router.navigate(['/allClients']);
      })
  }
}
