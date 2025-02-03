import { Component, OnInit } from '@angular/core';
import {Employee} from "../employee";
import {Department} from "../department";
import {EmployeeService} from "../employee.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {
  employee: Employee = {
    id: undefined,
    name: '',
    email: '',
    departmentList: []
  };
  departments: Department[] = [];

  constructor(private employeeService: EmployeeService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.loadEmployee();
    this.loadDepartments();
  }

  loadEmployee() {
    const employeeId = this.route.snapshot.paramMap.get('id');
    if (employeeId) {
      this.employeeService.getEmployeeById(employeeId).subscribe(
        data => {
          this.employee = data;
        },
        error => console.log(error)
      );
    }
  }

  loadDepartments() {
    this.employeeService.getDepartmentList().subscribe(
      data => {
        this.departments = data;

        const mandatoryDepartments = data.filter(dept => dept.mandatory);
        mandatoryDepartments.forEach(mandatoryDept => {
          if (!this.employee.departmentList.some(empDept => empDept.name === mandatoryDept.name)) {
            this.employee.departmentList.push(mandatoryDept);
          }
        });
      },
      error => console.log(error)
    );
  }

  onSubmit() {
    this.employeeService.updateEmployee(this.employee).subscribe(
      () => this.router.navigate(['/employee']),
      error => console.log(error)
    );
  }
}

