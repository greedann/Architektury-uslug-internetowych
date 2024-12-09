import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  constructor(private http: HttpClient) {}

  private apiUrl = 'http://localhost:8080';

  getEmployees(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`);
  }

  getEmployeesByCompany(companyId: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/companies/${companyId}/employees`);
  }

  removeEmployee(employeeId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/employees/${employeeId}`);
  }

  addEmployee(companyId: string, employee: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/companies/${companyId}/employees`, employee);
  }

  getEmployee(employeeId: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/employees/${employeeId}`);
  }

  updateEmployee(employee: any): Observable<any> {
    return this.http.patch<any>(`${this.apiUrl}/employees/${employee.id}`, employee);
  }
}
