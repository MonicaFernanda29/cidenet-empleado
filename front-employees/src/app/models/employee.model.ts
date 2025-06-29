export interface EmployeeRequest {
  firstSurname: string;
  secondLastName: string;
  firstName: string;
  lastName?: string;
  country: 'COLOMBIA' | 'ESTADOS_UNIDOS';
  documentType: 'CC' | 'CE' | 'PASAPORTE' | 'PERMISO_ESPECIAL';
  documentNumber: string;
  entryDate: string;
  workArea: string;
}

export interface EmployeeResponse extends EmployeeRequest {
  id: number;
  email: string;
  state: boolean;
  createDate: string;
}
