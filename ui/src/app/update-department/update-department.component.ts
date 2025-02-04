import {Component, Input, OnInit} from '@angular/core';
import {Department} from "../department";
import {ActivatedRoute, Router} from "@angular/router";
import {DepartmentService} from "../department.service";
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-update-department',
  templateUrl: './update-department.component.html',
  styleUrls: ['./update-department.component.css']
})
export class UpdateDepartmentComponent{
  @Input() department!: Department;

  constructor(public activeModal: NgbActiveModal, private departmentService: DepartmentService) {}

  onSubmit() {
    this.departmentService.updateDepartment(this.department).subscribe(() => {
      this.activeModal.close(true);
    });
  }
}
