import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Department} from "./department";
import {Employee} from "./employee";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  private departmentBaseURL = "http://localhost:8080/department";

  constructor(private httpClient: HttpClient) { }

  getDepartmentsList(): Observable<Department[]>{
    return this.httpClient.get<Department[]>(`${this.departmentBaseURL}`);
  }

  createDepartment(department: Department): Observable<Object>{
    return this.httpClient.post(`${this.departmentBaseURL}`,department);
  }

  updateDepartment(department: Department): Observable<Object> {
    return this.httpClient.put(`${this.departmentBaseURL}`, department);
  }

  deleteDepartment(id: number):Observable<Object>{
    return this.httpClient.delete(`${this.departmentBaseURL}/${id}`);
  }
}
