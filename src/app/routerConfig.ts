
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { NgModule } from '@angular/core';
import { ListClientComponent } from './list-client/list-client.component';
import { AddClientComponent } from './add-client/add-client.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { DeleteClientComponent } from './delete-client/delete-client.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ReadClientComponent } from './read-client/read-client.component';
import { LoggerComponent } from './logger/logger.component';
import { ChangeLoggerComponent } from './logger/change-logger/change-logger.component';

export const appRoutes: Routes = [
  {path: 'clients',component: AddClientComponent,data: { title: 'Add Client' } },
  {path: 'allClients',component: ListClientComponent,data: { title: 'List of Clients' }},
  {path: 'about', component: AboutComponent },
  {path: 'setting',component: ChangeLoggerComponent},
  {path: 'changeLog',component: ChangeLoggerComponent},
  {path: 'clients/:id',component: ReadClientComponent,data: { title: 'Read Client' } },
  {path: 'clients/update/:id',component: EditClientComponent,data: { title: 'Update Client' }},
  {path: 'clients/delete/:id',component: DeleteClientComponent,data: { title: 'Delete Client' }},{ path: '', redirectTo: 'clients', pathMatch: 'full' },
  {path: '**', component: PageNotFoundComponent }
];
@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class RouterConfig { }