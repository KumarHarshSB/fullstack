import {Component, OnInit} from '@angular/core';
import {Department} from "../department";
import {ActivatedRoute, Router} from "@angular/router";
import {DepartmentService} from "../department.service";

@Component({
  selector: 'app-update-department',
  templateUrl: './update-department.component.html',
  styleUrls: ['./update-department.component.css']
})
export class UpdateDepartmentComponent implements OnInit{
  department: Department = {
    id: undefined,
    name: '',
    readOnly: undefined,
    mandatory: undefined,
    employeeList: []
  };

  constructor(private departmentService: DepartmentService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.loadDepartment();
  }

  loadDepartment() {
    const departmentId = this.route.snapshot.paramMap.get('id');
    if (departmentId) {
      this.departmentService.getDepartmentById(departmentId).subscribe(
        data => {
          this.department = data;
        },
        error => console.log(error)
      );
    }
  }

  onSubmit() {
    this.departmentService.updateDepartment(this.department).subscribe(
      () => this.router.navigate(['/department']),
      error => console.log(error)
    );
  }
}
