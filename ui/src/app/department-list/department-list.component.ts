import { Component } from '@angular/core';
import {Department} from "../department";
import {DepartmentService} from "../department.service";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {UpdateDepartmentComponent} from "../update-department/update-department.component";

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent {
  departments!: Department[];

  constructor(private departmentService: DepartmentService,
              private router: Router,
              private modalService: NgbModal) {}

  ngOnInit(): void {
    this.getDepartments();
  }

  private getDepartments(){
    this.departmentService.getDepartmentsList().subscribe(data=>{
      this.departments = data;
    })
  }

  // updateDepartment(department: Department) {
  //   this.router.navigate(['update-department', department.id]);
  // }

  openUpdateModal(department: Department) {
    const modalRef = this.modalService.open(UpdateDepartmentComponent, { backdrop: true });
    modalRef.componentInstance.department = { ...department };

    modalRef.result.then((updated) => {
      if (updated) this.getDepartments();
    }).catch(() => {});
  }

  deleteDepartment(id: number){
    this.departmentService.deleteDepartment(id).subscribe(data=>{
      console.log(data);
      this.getDepartments();
    })
  }
}
