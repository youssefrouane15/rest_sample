import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeDetailsComponent } from './components/employee/employee-details/employee-details.component';
import { EmployeeUpdateComponent } from './components/employee/employee-update/employee-update.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { LoginComponent } from './components/login/login.component';

const appRoutes: Routes = [
  {path: 'login',
   component: LoginComponent
  },
  { path: 'home', 
    component: HomePageComponent 
  },
  { path: 'employees',      
    component: EmployeeComponent,
    data: { 
      title: 'Consult Our Employees' 
    } 
  },
  { path: 'employee/:id',      
  component: EmployeeDetailsComponent,
  data: { 
    title: 'Consult you employee\'s details' 
  } 
  },
  { path: 'employee/update/:id',      
  component: EmployeeUpdateComponent,
  data: { 
    title: 'Consult you employee\'s details' 
  } 
  },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
