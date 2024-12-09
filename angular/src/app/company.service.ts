import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  constructor(private http: HttpClient) {}
  company: string = '';

  private apiUrl = 'http://localhost:8080/companies';

  getCompanies(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`);
  }

  removeCompany(id: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }

  addCompany(company: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}`, company);
  }

  getCompany(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  updateCompany(company: any): Observable<any> {
    return this.http.patch<any>(`${this.apiUrl}/${company.id}`, company);
  }

  getCompanyName(id: string): Promise<string> {
    return new Promise((resolve, reject) => {
      this.getCompany(id).subscribe(
        (data) => {
          resolve(data.name);
        },
        (error) => {
          reject(error);
        }
      );
    });
  }
}
