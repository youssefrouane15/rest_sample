import { Component, OnInit } from '@angular/core';
import { FormsModule }   from '@angular/forms';
@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {
  lastUpdate = new Date();
  info = {
    name: "Agh Digital",
    adresse: "7 rue la boétie 75008 Paris",
    sujet: "Formation Angular"
  }
  comments = [{ message: "hello api" },
  { message: "you can start " }

  ];
  commentaire = { message: "" }
  isAuth = false;
  constructor() { }

  ngOnInit() {
  }
  onAddCommentaire(comm) {
    this.comments.push(comm);
    this.commentaire = { message: "" };
  }
  getColor() {
    return 'green';
  }
}
