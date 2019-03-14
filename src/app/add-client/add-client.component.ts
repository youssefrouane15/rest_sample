import { Component, OnInit } from '@angular/core';
import { NgForm, FormBuilder, Validators, FormControl, FormGroupDirective, FormGroup } from '@angular/forms';
import { ClientService } from '../shared/client.service';
import { ErrorStateMatcher } from '@angular/material/core';

import { Client } from '../shared/client.model';
import { Adress } from '../shared/adress.model';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {
  formBuilder: FormBuilder;
  adress=new  Adress();
  client= new  Client();

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

  constructor(private clientService: ClientService) { }
  ngOnInit() {
    this.client.adress=this.adress;
    this.clientForm.reset();
  }
  onSubmit(client: Client) {
     this.clientService.postClient(client).subscribe((response) =>{
     this.clientForm.reset();
      return true;
    },
    error => {
      console.error("Cannot save Client!");
    })
}      
}