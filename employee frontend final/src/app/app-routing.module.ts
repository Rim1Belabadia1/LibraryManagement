import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { ShowDetailsComponent } from './show-details/show-details.component';
import { HomeComponent } from './home/home.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { SignupComponent } from './signup/signup.component';
import { AddBookComponent } from './add-book/add-book.component';
import { EditBookComponent } from './edit-book/edit-book.component';
import { BookListComponent } from './book-list/book-list.component';
import { BookUserListComponent } from './bookuser-list/bookuser-list.component';
import { EmpruntFormComponent } from './components/emprunt-form/emprunt-form.component';
import { ListeEmpruntsComponent } from './liste-emprunts/liste-emprunts.component';
const routes: Routes = [

  {path:"show-all-employees",component: EmployeeListComponent},
  {path:"add-employee", component: AddEmployeeComponent},
  {path:'', redirectTo: "home", pathMatch:"full"},
  {path:'updating-by-id/:id',component:UpdateEmployeeComponent},
  {path:'details-of-employee/:id',component:ShowDetailsComponent},
  {path:'home',component:HomeComponent},
  { path: 'book-list', component: BookListComponent },
  {path:'login',component:AdminLoginComponent},
  { path: 'signup', component: SignupComponent },
  { path: 'bookuser-list', component: BookUserListComponent },
  { path: 'books/edit/:id', component: EditBookComponent },
  { path: 'add-book', component: AddBookComponent },
  { path: 'emprunt-form', component: EmpruntFormComponent },
  { path: 'liste-emprunts', component: ListeEmpruntsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
