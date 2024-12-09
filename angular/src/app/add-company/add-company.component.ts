import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CompanyService } from '../company.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-add-company',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './add-company.component.html',
  styleUrl: './add-company.component.css',
})
export class AddCompanyComponent implements OnInit {
  CompanyName: string = '';
  CompanyCapitalization: number = 0;

  constructor(private companyService: CompanyService, private router: Router) {}

  ngOnInit(): void {}

  addCompany(): void {
    if (this.CompanyName.trim()) {
      const company = {
        name: this.CompanyName,
        capitalization: this.CompanyCapitalization,
      };

      this.companyService.addCompany(company).subscribe(() => {
        console.log('Company added successfully');
        this.router.navigate(['/companies']);
      });
    } else {
      console.log('Company name is required');
    }
  }
}
