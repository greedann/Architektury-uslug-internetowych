import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute } from '@angular/router';
import { CompanyService } from '../company.service';

@Component({
  selector: 'app-details-employee',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './details-employee.component.html',
  styleUrl: './details-employee.component.css',
})
export class DetailsEmployeeComponent implements OnInit {
  constructor(
    private employeeService: EmployeeService,
    private companyService: CompanyService,
    private route: ActivatedRoute,
  ) {}

  employeeName: string = '';
  employeeLevel: string = '';
  employeeId: string = '';
  companyId: string = '';
  companyName: string = '';

  ngOnInit(): void {
    this.employeeId = this.route.snapshot.paramMap.get('employeeId') || '';
    this.companyId = this.route.snapshot.paramMap.get('id') || '';
    this.companyService.getCompanyName(this.companyId).then((name) => {
      this.companyName = name;
      console.log(this.companyName);
    });
    this.employeeService.getEmployee(this.employeeId).subscribe((data) => {
      this.employeeName = data.name;
      this.employeeLevel = data.level;
    });
  }
}
