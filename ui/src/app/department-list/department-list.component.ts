import { Component } from '@angular/core';
import {Department} from "../department";
import {DepartmentService} from "../department.service";

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent {
  departments!: Department[];

  constructor(private departmentService: DepartmentService) {}

  ngOnInit(): void {
    this.getDepartments();
  }

  private getDepartments(){
    this.departmentService.getDepartmentsList().subscribe(data=>{
      this.departments = data;
    })
  }

  deleteDepartment(id: number){
    this.departmentService.deleteDepartment(id).subscribe(data=>{
      console.log(data);
      this.getDepartments();
    })
  }
}
