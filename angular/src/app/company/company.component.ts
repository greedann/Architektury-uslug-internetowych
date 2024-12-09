import { Component } from '@angular/core';
import { CompanyService } from '../company.service';
import { OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-company',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './company.component.html',
  styleUrl: './company.component.css',
})
export class CompanyComponent implements OnInit {
  companies: any[] = [];

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.getCompanies();
  }

  getCompanies(): void {
    this.companyService.getCompanies().subscribe((data) => {
      this.companies = data.companies;
      console.log(this.companies);
      console.log('Companies fetched successfully');
    });
  }

  removeCompany(id: string): void {
    this.companyService.removeCompany(id).subscribe(() => {
      this.companies = this.companies.filter((company) => company.id !== id);
      console.log('Company removed successfully');
    });
  }

}
