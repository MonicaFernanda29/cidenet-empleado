import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  ReactiveFormsModule,
  FormGroup,
  Validators,
} from '@angular/forms';
import { EmployeeService } from '../../services/employee.service';
import { EmployeeRequest } from '../../models/employee.model';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './employee-form.component.html',
})
export class EmployeeFormComponent {
  @Output() onRegister = new EventEmitter<void>();
  form: FormGroup;
  loading = false;

  countries = [
    { label: 'Colombia', value: 'COLOMBIA' },
    { label: 'Estados Unidos', value: 'ESTADOS_UNIDOS' },
  ];

  documentTypes = [
    { label: 'Cédula de Ciudadanía', value: 'CC' },
    { label: 'Cédula de Extranjería', value: 'CE' },
    { label: 'Pasaporte', value: 'PASSPORT' },
    { label: 'Permiso Especial', value: 'SPECIAL_PERMIT' },
  ];

  areas = [
    { label: 'Administración', value: 'ADMINISTRACION' },
    { label: 'Financiera', value: 'FINANCIERA' },
    { label: 'Compras', value: 'COMPRAS' },
    { label: 'Infraestructura', value: 'INFRAESTRUCTURA' },
    { label: 'Operación', value: 'OPERACION' },
    { label: 'Talento Humano', value: 'TALENTO_HUMANO' },
    { label: 'Servicios Varios', value: 'SERVICIOS_VARIOS' },
  ];

  constructor(
    private readonly fb: FormBuilder,
    private readonly employeeService: EmployeeService
  ) {
    this.form = this.fb.group({
      firstSurname: [
        '',
        [Validators.required, Validators.pattern(/^[A-Z]{1,20}$/)],
      ],
      secondLastName: [
        '',
        [Validators.required, Validators.pattern(/^[A-Z]{1,20}$/)],
      ],
      firstName: [
        '',
        [Validators.required, Validators.pattern(/^[A-Z]{1,20}$/)],
      ],
      lastName: ['', [Validators.pattern(/^[A-Z ]{0,50}$/)]],
      country: ['', Validators.required],
      documentType: ['', Validators.required],
      documentNumber: [
        '',
        [Validators.required, Validators.pattern(/^[a-zA-Z0-9\-]{1,20}$/)],
      ],
      entryDate: ['', Validators.required],
      workArea: ['', Validators.required],
    });
  }

  submit() {
    // transforma valores antes de validar
    const upper = (v: string) => (v || '').toUpperCase().trim();

    this.form.patchValue({
      firstSurname: upper(this.form.value.firstSurname),
      secondLastName: upper(this.form.value.secondLastName),
      firstName: upper(this.form.value.firstName),
      lastName: upper(this.form.value.lastName),
    });

    if (this.form.invalid) {
      console.warn('Formulario inválido:', this.form.value);
      return;
    }

    this.loading = true;

    const payload: EmployeeRequest = this.form.value;

    this.employeeService.create(payload).subscribe({
      next: () => {
        this.form.reset();
        this.loading = false;
        this.onRegister.emit();
      },
      error: (err: any) => {
        alert('Error: ' + (err?.error || 'No se pudo registrar.'));
        this.loading = false;
      },
    });
  }
}
