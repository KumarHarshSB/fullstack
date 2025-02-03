import {Component, OnInit} from '@angular/core';
import {Employee} from "../employee";
import {EmployeeService} from "../employee.service";
import {Router} from "@angular/router";
import {Department} from "../department";

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit{

  employee: Employee = {
    name: '',
    email: '',
    departmentList: []
  };
  departments: Department[] = [];

  constructor(private employeeService: EmployeeService,
              private router: Router) {}

  ngOnInit(): void {
    this.employeeService.getDepartmentList().subscribe(
      data => {
        this.departments = data;
      },
      (error: any) => console.log(error)
    );
  }

  saveEmployee() {
    const employeeData = {
      name: this.employee.name,
      email: this.employee.email,
      departmentList: (this.employee.departmentList ?? []).map(dept => ({
        name: dept.name,
        readOnly: dept.readOnly,
        mandatory: dept.mandatory,
        empList: []
      }))
    };

    this.employeeService.createEmployee(employeeData).subscribe(
        (data: any) => {
        this.goToEmployeeList();
      },
      (error: any) => console.log(error)
    );
  }

  goToEmployeeList(){
    this.router.navigate(['/employee']);
  }

  onSubmit(){
    console.log('submit', this.employee.departmentList);
    this.saveEmployee();
  }
}
