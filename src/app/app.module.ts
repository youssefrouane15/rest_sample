import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import {EmployeeService} from './employee.service'
import { EmployeeModel } from './employee-model';
import { EmployeeComponent } from './components/employee/employee.component';
import { DialogModal } from './components/employee/employee.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';

// the second parameter 'fr' is optional
registerLocaleData(localeFr, 'fr');


import {
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatRippleModule
} from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { EmployeeDetailsComponent } from './components/employee/employee-details/employee-details.component';
import { EmployeeUpdateComponent } from './components/employee/employee-update/employee-update.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { DialogComponent } from './components/dialog/dialog.component';

const modules = [
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatRippleModule,
  MatTableModule,
  MatIconModule,
  MatDialogModule,
  MatDatepickerModule,
  MatNativeDateModule
  
];

@NgModule({
imports: [...modules],
exports: [...modules],

})export class MaterialModule {};

@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    EmployeeDetailsComponent,
    EmployeeUpdateComponent,
    HeaderComponent,
    FooterComponent,
    DialogComponent,
    HomePageComponent,
    NotFoundComponent,
  ],
  entryComponents: [DialogComponent],
  imports: [
    BrowserModule,
    MaterialModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule, 
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
