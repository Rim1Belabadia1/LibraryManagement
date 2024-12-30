import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { FormsModule } from '@angular/forms';

import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule} from '@angular/material/core';


import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { ShowDetailsComponent } from './show-details/show-details.component';
import { HomeComponent } from './home/home.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SignupComponent } from './signup/signup.component';
import { BookManagerComponent } from './book-manager/book-manager.component';
import { AddBookComponent } from './add-book/add-book.component';
import { BookListComponent } from './book-list/book-list.component';
import { EditBookComponent } from './edit-book/edit-book.component';
import { BookUserListComponent } from './bookuser-list/bookuser-list.component';
import { EmpruntFormComponent } from './components/emprunt-form/emprunt-form.component';
import { ListeEmpruntsComponent } from './liste-emprunts/liste-emprunts.component';



@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
BookUserListComponent,
    AddEmployeeComponent,
         UpdateEmployeeComponent,
         ShowDetailsComponent,
         AdminLoginComponent,
         SignupComponent,
         BookManagerComponent,
         AddBookComponent,
         BookListComponent,
         EditBookComponent,
         EmpruntFormComponent,
         ListeEmpruntsComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule,FormsModule,HomeComponent, BrowserAnimationsModule ,
    MatFormFieldModule, MatInputModule,MatFormFieldModule, MatInputModule, MatDatepickerModule,
     MatNativeDateModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
