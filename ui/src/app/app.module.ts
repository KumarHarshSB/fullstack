import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import {HttpClientModule} from "@angular/common/http";
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import {FormsModule} from "@angular/forms";
import { DepartmentListComponent } from './department-list/department-list.component';
import { CreateDepartmentComponent } from './create-department/create-department.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { UpdateDepartmentComponent } from './update-department/update-department.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    CreateEmployeeComponent,
    DepartmentListComponent,
    CreateDepartmentComponent,
    UpdateEmployeeComponent,
    UpdateDepartmentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  entryComponents: [UpdateDepartmentComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
