# PG Management System 🏠

A Core Java + MySQL based mini project to manage tenants in a Paying Guest (PG) accommodation.

---

## ✅ Features

- ➕ **Add Tenant** – Add new tenant to the PG system
- ✏️ **Update Tenant** – Update tenant info like phone, rent, room, etc.
- ❌ **Delete Tenant** – Remove tenant details from the system
- 🔍 **Search Tenant by Aadhaar** – Search a tenant using their Aadhaar number
- 🚪 **Vacate Room** – Update tenant status to “left” and set leaving date
- 🔔 **Rent Due Alerts** – Show tenants with `payment_status = 'pending'`
- 🔄 **Room Capacity Check** – Prevent adding more than 3 tenants per room
- 📋 **Display All Tenants** – List all tenant records in a clean table

---

## 🛠 Technologies Used

- **Java (Core)** – Project logic and menu handling
- **JDBC** – For database connection
- **MySQL** – To store tenant data
- **Git & GitHub** – Version control and hosting

---

## 🗂 Project Files

- `Main.java` – Runs the main menu
- `Tenant.java` – POJO class for tenant
- `DatabaseHelper.java` – DB logic (CRUD operations)
- `pgmanagement.sql` – SQL file to create `tenants` table

---

## 🧪 How to Run

1. Import `pgmanagement.sql` into MySQL to create the database and table
2. Compile and run Java files using your IDE or terminal
3. Use the console menu to perform operations

---

## 🙋‍♀️ Created By

**Sravanthi Duddela**  
Java Developer | Fresher | Passionate about backend & full-stack development  
🌐 GitHub: [sravanthi200212](https://github.com/sravanthi200212)

---

## 💡 Passion for Full Stack Development

This project is part of my journey into full-stack development.  
I started with Core Java and MySQL to build strong backend skills, and I’m now exploring:

- 🌐 Frontend Development (HTML, CSS, JavaScript)
- ⚙️ Spring Boot & REST APIs
- 🗃️ Database Design & Optimization
- 🧠 Clean Code & Problem Solving

I believe in learning by building real projects and solving real problems.  
More projects coming soon on my GitHub 🚀

---
