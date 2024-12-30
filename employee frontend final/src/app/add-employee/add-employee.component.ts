import { Component } from '@angular/core';
import { Employee } from '../employee';
import { Observable } from 'rxjs';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';





@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html'
  ,
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent //implements OnInit//
{

  constructor(
    private employeeService: EmployeeService,
    private router: Router,

  ) {

  }







  submitform!: NgForm;
  private baseURL = "http://localhost:8080/api/v1/employees";
  employee: Employee = new Employee();


  showPassword = false; // Variable pour contrôler la visibilité du mot de passe

  // Fonction pour basculer la visibilité
  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }


  saveEmployee() {
    this.employeeService.addEmployee(this.employee).subscribe(data => {
      console.log(data);
      this.goToEmployeeList();
    },
      error => console.log(error));
  }

  goToEmployeeList() {
    this.router.navigate(['/employee-list']);
  }
  minDate(): string {
    const currentDate = new Date();
    currentDate.setFullYear(2024, 0, 1); // Fixe l'année à 2024
    return currentDate.toISOString().split('T')[0]; // Retourne la date au format "YYYY-MM-DD"
  }
  ngOnInit(): void { }
  onSubmit() {
    console.log(this.employee);


    this.saveEmployee();
  }


}









