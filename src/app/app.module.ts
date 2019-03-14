import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';
import { ToastrModule } from 'ngx-toastr';
import { appRoutes } from './routerConfig';
import { MatButtonModule } from '@angular/material/button';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material'
import { DeleteClientComponent } from './delete-client/delete-client.component';
import { ListClientComponent } from './list-client/list-client.component';
import { AddClientComponent } from './add-client/add-client.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FormControl } from "@angular/forms";

import {
  MatCheckboxModule,
  MatRadioModule,
  MatSelectModule,
  MatInputModule,
  MatIconModule,
  MatMenuModule
} from '@angular/material';
import { ReadClientComponent } from './read-client/read-client.component';
import { LoggerComponent } from './logger/logger.component';
import { ChangeLoggerComponent } from './logger/change-logger/change-logger.component';




@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    LoginComponent,
    DeleteClientComponent,
    ListClientComponent,
    AddClientComponent,
    EditClientComponent,
    PageNotFoundComponent,
    ReadClientComponent,
    LoggerComponent,
    ChangeLoggerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, RouterModule.forRoot(appRoutes),
    HttpModule, BrowserAnimationsModule,
    ToastrModule.forRoot(),
    ReactiveFormsModule, FormsModule,
    MatMenuModule, MatButtonModule, MatIconModule,
    MatTableModule, MatPaginatorModule, FormsModule,
    HttpClientModule,
    MatCheckboxModule,
    MatRadioModule,
    MatSelectModule,
    MatInputModule


  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule {



}
