import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "./employee";
import {Department} from "./department";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL = "http://localhost:8080/employee";
  private departmentBaseURL = "http://localhost:8080/department";

  constructor(private httpClient: HttpClient) { }

  getEmployeesList(): Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseURL}`);
  }

  createEmployee(employee: any): any{
    return this.httpClient.post(`${this.baseURL}`,employee);
  }

  getDepartmentList(): Observable<Department[]>{
    return this.httpClient.get<Department[]>(`${this.departmentBaseURL}`)
  }

  updateEmployee(employee: Employee): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}`, employee); // No ID in URL
  }

  deleteEmployee(id: number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
