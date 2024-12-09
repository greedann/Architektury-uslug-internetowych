import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-employee',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './add-employee.component.html',
  styleUrl: './add-employee.component.css'
})
export class AddEmployeeComponent implements OnInit {
  constructor(private route: ActivatedRoute,
    private employeeService: EmployeeService,
    private router: Router
  ) {}
  companyId: string = '';
  employeeName: string = '';
  employeeLevel: string = '';

  ngOnInit(): void {
    this.companyId = this.route.snapshot.paramMap.get('id') || '';
  }

  addEmployee() {
    this.employeeService.addEmployee(this.companyId, {
      name: this.employeeName,
      level: this.employeeLevel,
    }).subscribe(() => {
      // Redirect to the company page
      this.router.navigate(['/companies', this.companyId]);
    });
  }

}
