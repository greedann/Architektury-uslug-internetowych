import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-employee',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './edit-employee.component.html',
  styleUrl: './edit-employee.component.css'
})
export class EditEmployeeComponent implements OnInit {
  constructor(
    private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  employeeName: string = '';
  employeeLevel: string = '';
  employeeId: string = '';
  companyId: string = '';

  ngOnInit(): void {
    this.employeeId = this.route.snapshot.paramMap.get('employeeId') || '';
    this.companyId = this.route.snapshot.paramMap.get('id') || '';
    this.employeeService.getEmployee(this.employeeId).subscribe((data) => {
      console.log(data);
      this.employeeName = data.name;
      this.employeeLevel = data.level;
    });
  }

  updateEmployee() {
    this.employeeService
      .updateEmployee({
        id: this.employeeId,
        name: this.employeeName,
        level: this.employeeLevel
      })
      .subscribe(() => {
        this.router.navigate(['/companies', this.companyId]);
      });
  }

}
