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
    const selectedDepartments = new Set(this.employee.departmentList);
    this.departments.forEach(dept => {
      if (dept.mandatory) {
        selectedDepartments.add(dept);
      }
    });

    const employeeData = {
      name: this.employee.name,
      email: this.employee.email,
      departmentList: Array.from(selectedDepartments).map(dept => ({
        name: dept.name,
        readOnly: dept.readOnly,
        mandatory: dept.mandatory,
        empList: dept.employeeList
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
    this.saveEmployee();
  }
}
