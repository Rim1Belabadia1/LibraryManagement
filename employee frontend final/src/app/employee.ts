import { DatePipe } from '@angular/common';

export class Employee {
  id!: number;
  fname!: string;
  lname!: string;
  email!: string;
  salary!: string;
  department: string;
  designation: string;
  joiningDate!: string;

  constructor() {
    // this.id = 0;
    // // this.fname="";
    // this.lname="";
    this.email = '@gmail.com';
    this.salary = '';
    this.department = '';
    this.designation = '';
    // this.joiningDate = new Date();
    //  // Set the default date value
    //  const today = new Date();
    //  const year = today.getFullYear();
    //  const month = ('0' + (today.getMonth() + 1)).slice(-2); // Adding 1 because months are zero-based
    //  const day = ('0' + today.getDate()).slice(-2);

    //  this.joiningDate = `${year}-${month}-${day}`;
  }
}
