import { Component } from '@angular/core';
import {Employee} from "../employee";
import {EmployeeService} from "../employee.service";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {UpdateEmployeeComponent} from "../update-employee/update-employee.component";

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent {
  employees!: Employee[];

  constructor(private employeeService: EmployeeService,
              private router: Router,
              private modalService: NgbModal) {}

  ngOnInit(): void {
    this.getEmployees();
  }

  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data=>{
      console.log("Fetched employees:", data);
      this.employees = data;
    })
  }

  openUpdateModal(employee: Employee) {
    const modalRef = this.modalService.open(UpdateEmployeeComponent, { size: 'lg' });
    modalRef.componentInstance.employee = { ...employee };

    modalRef.result.then(
      (updated) => {
        if (updated) this.getEmployees(); // Refresh employee list after update
      },
      () => {}
    );
  }

  deleteEmployee(id: number){
    this.employeeService.deleteEmployee(id).subscribe(data=>{
      console.log(data);
      this.getEmployees();
    })
  }
}
