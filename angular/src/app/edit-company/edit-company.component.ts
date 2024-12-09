import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { CompanyService } from '../company.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-edit-company',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './edit-company.component.html',
  styleUrl: './edit-company.component.css',
})
export class EditCompanyComponent implements OnInit {
  constructor(
    private companyService: CompanyService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  companyName: string = '';
  companyCapitalization: number = 0;
  companyId: string = '';

  ngOnInit(): void {
    this.companyId = this.route.snapshot.paramMap.get('id') || '';
    this.companyService.getCompany(this.companyId).subscribe((data) => {
      console.log(data);
      this.companyName = data.name;
      this.companyCapitalization = data.capitalization;
    });
  }

  updateCompany() {
    this.companyService
      .updateCompany({
        "id": this.companyId,
        "name": this.companyName,
        "capitalization": this.companyCapitalization,
      })
      .subscribe(() => {
        this.router.navigate(['/companies']);
      });
  }
}
