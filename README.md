# 🏥 Hospital Management System

A Full Stack Hospital Management System built using:

* Java
* JavaFX
* Spring Boot
* REST API
* SQLite
* Maven
* Render Deployment

This project provides a complete hospital workflow including patient admission, room allocation, billing, discharge system, database management, and backend REST APIs.

---

# 🚀 Live Backend Deployment

🔗 Backend API:

https://hospital-management-system-wtdw.onrender.com/patients

---

# 📌 Features

## ✅ Frontend (JavaFX)

* Secure Login System
* Patient Admission
* ICU & Regular Patients
* Room Allocation
* Search Patient
* Discharge Patient
* Bill Calculation
* Revenue Generation
* Database Viewer
* Modern UI Dashboard

---

## ✅ Backend (Spring Boot)

* REST APIs
* SQLite Database Integration
* Add Patient API
* Fetch Patients API
* JSON Request Handling
* Maven Build System

---

# 🛠️ Tech Stack

| Technology  | Usage                 |
| ----------- | --------------------- |
| Java        | Core Programming      |
| JavaFX      | Frontend GUI          |
| Spring Boot | Backend APIs          |
| SQLite      | Database              |
| Maven       | Dependency Management |
| Render      | Deployment            |
| GitHub      | Version Control       |

---

# 📂 Project Structure

```text
Hospital-Management-System/
│
├── frontend/
├── src/
├── hospital.db
├── pom.xml
├── README.md
├── Dockerfile
└── render.yaml
```

---

# 🖥️ Frontend Screenshots

## 🔐 Login Page

![Login](images/login.png)

---

## 📊 Dashboard

![Dashboard](images/dashboard.png)

---

## 🗄️ Database View

![Database](images/database.png)

---

# ⚙️ Backend APIs

## Home API

```http
GET /
```

Response:

```text
Hospital Backend Running
```

---

## Get Patients API

```http
GET /patients
```

Fetches all patients from SQLite database.

---

## Add Patient API

```http
POST /add
```

### Request Body

```json
{
  "name":"Tanvi",
  "age":21,
  "blood":"O+",
  "type":"ICU",
  "room":1,
  "status":"Admitted",
  "bill":0
}
```

---

# 🧪 API Testing

APIs tested using:

* Postman
* Browser
* JavaFX Frontend

---

# 🗄️ Database

SQLite database stores:

* Patient Name
* Age
* Blood Group
* Room Number
* Patient Type
* Status
* Bill Amount

Database file:

```text
hospital.db
```

---

# ▶️ Run Backend Locally

## Clone Repository

```bash
git clone https://github.com/yerawartanvi79-source/Hospital-Management-System.git
```

---

## Open Project

```bash
cd hospital/hospital
```

---

## Run Spring Boot

```bash
mvn spring-boot:run
```

---

# ▶️ Run Frontend

Compile:

```bash
javac --module-path ~/Tanvi/String/openjfx-17.0.19_linux-x64_bin-sdk/javafx-sdk-17.0.19/lib --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.base HospitalGUI.java
```

Run:

```bash
java --module-path ~/Tanvi/String/openjfx-17.0.19_linux-x64_bin-sdk/javafx-sdk-17.0.19/lib --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.base -cp ".:sqlite-jdbc-3.53.1.0.jar" HospitalGUI
```

---

# 📈 Future Improvements

* Doctor Management
* Appointment Booking
* JWT Authentication
* Admin Dashboard
* Cloud Database
* Microservices Architecture
* CI/CD Pipeline
* Docker Deployment

---

# 👩‍💻 Author

## Tanvi Yerawar

GitHub:

https://github.com/yerawartanvi79-source

---

# ⭐ Conclusion

This project demonstrates full stack development using Java ecosystem technologies with frontend, backend, database, and deployment integration.
