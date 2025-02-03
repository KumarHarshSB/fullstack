import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EmployeeListComponent} from "./employee-list/employee-list.component";
import {CreateEmployeeComponent} from "./create-employee/create-employee.component";
import {DepartmentListComponent} from "./department-list/department-list.component";
import {CreateDepartmentComponent} from "./create-department/create-department.component";
import {UpdateEmployeeComponent} from "./update-employee/update-employee.component";
import {UpdateDepartmentComponent} from "./update-department/update-department.component";

const routes: Routes = [
  {path:'employee',component: EmployeeListComponent},
  {path:'create-employee',component: CreateEmployeeComponent},
  {path:'department',component: DepartmentListComponent},
  {path:'create-department',component: CreateDepartmentComponent},
  {path:'update-employee',component: UpdateEmployeeComponent},
  {path:'update-employee/:id',component: UpdateEmployeeComponent},
  {path:'update-department',component: UpdateDepartmentComponent},
  {path:'update-department/:id',component: UpdateDepartmentComponent},
  {path:'',redirectTo:'employee',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
