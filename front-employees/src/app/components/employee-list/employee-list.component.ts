import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeService } from '../../services/employee.service';
import { EmployeeResponse } from '../../models/employee.model';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-list.component.html'
})
export class EmployeeListComponent implements OnChanges {
  @Input() reload = 0;
  employees: EmployeeResponse[] = [];

  constructor(private readonly employeeService: EmployeeService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['reload']) {
      this.fetchEmployees();
    }
  }

  fetchEmployees() {
    this.employeeService.getAll().subscribe({
      next: (data) => (this.employees = data),
      error: (err) => {
        console.error('Error al obtener empleados:', err);
        this.employees = [];
      }
    });
  }
}
