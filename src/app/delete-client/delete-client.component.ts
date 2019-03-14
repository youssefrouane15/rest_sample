import { Component, OnInit } from '@angular/core';
import { Client } from '../shared/client.model';
import { ClientService } from '../shared/client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Adress } from '../shared/adress.model';
@Component({
  selector: 'app-delete-client',
  templateUrl: './delete-client.component.html',
  styleUrls: ['./delete-client.component.css']
})
export class DeleteClientComponent implements OnInit {
  id: number;
  adress = new Adress();
  client = new Client();
  constructor(private clientService: ClientService, private router: Router, private route: ActivatedRoute) {
  }
  ngOnInit() {
    this.client.adress=this.adress;
    this.id = this.route.snapshot.params['id'];
    this.clientService.getClientById(this.id).subscribe(client => this.client = client);
  }
  onDelete(id: number) {
    if (confirm('Are you sure to delete this record ?') == true) {
      this.clientService.deleteClient(id)
        .subscribe(
          data => {
            this.clientService.getListClients();
            this.router.navigate(['/allClients']);
            return true;
          },
          error => {
            console.error("Error deleting Employee!");
          })
    }
  }
  cancel() {
    this.router.navigate(['/allClients']);
  }
}
