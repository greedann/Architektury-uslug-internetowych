import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CompanyService } from '../company.service';
import { EmployeeService } from '../employee.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-details-company',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './details-company.component.html',
  styleUrl: './details-company.component.css',
})
export class DetailsCompanyComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private companyService: CompanyService,
    private employeeService: EmployeeService,
  ) {}
  company: any;
  employees: any[] = [];

  ngOnInit(): void {
    this.loadCompany();
    this.loadEmployees();
  }

  loadCompany() {
    const id = this.route.snapshot.paramMap.get('id') || '';
    this.companyService.getCompany(id).subscribe((data) => {
      this.company = data;
    });
  }

  loadEmployees() {
    const id = this.route.snapshot.paramMap.get('id') || '';
    this.employeeService.getEmployeesByCompany(id).subscribe((data) => {
      this.employees = data.employees;
      console.log(this.employees);
    });
  }

  removeEmployee(employeeId: string) {
    this.employeeService.removeEmployee(employeeId).subscribe(() => {
      this.loadEmployees();
    });
  }
}
