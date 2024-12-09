import { Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CompanyComponent } from './company/company.component';
import { EditCompanyComponent } from './edit-company/edit-company.component';
import { AddCompanyComponent } from './add-company/add-company.component';
import { DetailsCompanyComponent } from './details-company/details-company.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { EditEmployeeComponent } from './edit-employee/edit-employee.component';
import { DetailsEmployeeComponent } from './details-employee/details-employee.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/companies',
    pathMatch: 'full',
  },
  {
    component: CompanyComponent,
    path: 'companies',
  },
  {
    component: EditCompanyComponent,
    path: "companies/:id/edit",
  },
  {
    component: DetailsCompanyComponent,
    path: "companies/:id",
  },
  {
    component: AddCompanyComponent,
    path: 'companies/new',
  },
  {
    component: AddEmployeeComponent,
    path: 'companies/:id/employees/new',
  },
  {
    component: EditEmployeeComponent,
    path: 'companies/:id/employees/:employeeId/edit',
  },
  {
    component: DetailsEmployeeComponent,
    path: 'companies/:id/employees/:employeeId',
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
