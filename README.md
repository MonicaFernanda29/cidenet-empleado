## 📟 Cidenet - Gestión de Empleados

Aplicación full stack para registrar y listar empleados, aplicando buenas prácticas de arquitectura limpia, validaciones y estilo visual simple.

---

## 🔩 Tecnologías

* **Backend:** Java 21, Spring Boot 3, H2 DB, Gradle
* **Frontend:** Angular standalone (v17+), Tailwind CSS
* **Otros:** RxJS, Validaciones con DTO, Patrón limpio

---

## 🚀 Cómo ejecutar

### Backend (Spring Boot + H2)

#### 📦 Requisitos

* Java 21+
* Gradle
* Puerto libre `8080`

#### ▶️ Comando

```bash
./gradlew bootRun
```

#### 🔗 Endpoint principal

```http
POST   http://localhost:8080/employee       # crear empleado
GET    http://localhost:8080/employee       # listar empleados
```

#### 🔪 Datos de ejemplo (JSON)

```json
{
  "firstSurname": "GOMEZ",
  "secondLastName": "LOPEZ",
  "firstName": "CARLOS",
  "lastName": "FERNANDO",
  "country": "COLOMBIA",
  "documentType": "CC",
  "documentNumber": "123456789",
  "entryDate": "2024-06-15",
  "workArea": "OPERACION"
}
```

---

### Frontend (Angular + Tailwind)

#### 📦 Requisitos

* Node.js (v18+)
* Angular CLI

#### ▶️ Comando

```bash
npm install
ng serve
```

Accede desde: [http://localhost:4200](http://localhost:4200)

---

## ✅ Funcionalidades

* [x] Registro con validaciones (formato, mayúsculas, campos obligatorios)
* [x] Formulario moderno y responsive
* [x] Listado de empleados registrados
* [x] Validación en frontend y backend
* [x] Comunicación REST usando `HttpClient`

---

## 🛠️ Estructura del proyecto

### Backend (Spring Boot)

```
src/
├── domain/
│   ├── model
│   ├── enums (Country, DocumentType, WorkArea)
├── application/
│   └── service
├── infrastructure/
    └── entrypoint/
        ├── controller
        └── dtos
```

### Frontend (Angular)

```
src/
├── app/
│   ├── components/
│   │   ├── employee-form/
│   │   └── employee-list/
│   ├── services/
│   └── models/
```

---

## 📌 Notas importantes

* El backend solo acepta letras **mayúsculas** sin tildes ni ñ.
* Las fechas no pueden estar en el futuro.
* El segundo nombre es opcional.
* Tailwind está configurado con estilos personalizados.
