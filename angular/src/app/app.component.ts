import { Component } from '@angular/core';
import { CompanyComponent } from './company/company.component';
import { HttpClientModule } from '@angular/common/http';
import { CompanyService } from './company.service';
import { AddCompanyComponent } from './add-company/add-company.component';
import { RouterOutlet } from '@angular/router';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HttpClientModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: [CompanyService, EmployeeService],
})
export class AppComponent {
  title = 'companies';
}
