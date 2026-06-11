# NextGen HRMS – Training & Learning Management Module

## 📌 Project Overview

This project is being developed as part of the Internship Program under the Human Resource Management System (HRMS).

The current module focuses on Training & Learning Management where employees can submit training details and upload certificates, while reporting officers can review employee records and verify training information.

The project follows a full-stack architecture:

- Frontend: React.js + Vite
- Backend: Spring Boot
- Database: PostgreSQL
- Version Control: Git & GitHub

---

## 🎯 Current Objectives

### Employee Features

- Submit training details
- Upload training certificates
- View submitted records

### Reporting Officer Features

- View employee profiles
- Verify training records
- Review uploaded certificates

### System Features

- REST API Integration
- PostgreSQL Database Storage
- Training Record Management
- Certificate Management

---

## 🛠 Tech Stack

### Frontend

- React.js
- Vite
- JavaScript
- HTML5
- CSS3

### Backend

- Spring Boot
- Java
- REST APIs

### Database

- PostgreSQL

### Tools

- Git
- GitHub
- VS Code
- Postman

---

## 📂 Frontend Structure

```text
src/
│
├── assets/
│   ├── hero.png
│   ├── meity_logo.svg
│   ├── nic_logo.svg
│   ├── tripura_logo.png
│   └── tripura_logo.svg
│
├── components/
│   ├── FaqAccordion.jsx
│   ├── Footer.jsx
│   ├── Header.jsx
│   ├── Hero.jsx
│   ├── JsonPreview.jsx
│   ├── TrainingCards.jsx
│   └── TrainingForm.jsx
│
├── data/
│   └── trainingData.js
│
├── pages/
│
├── App.jsx
├── App.css
├── index.css
└── main.jsx
```

---

## 📋 Internship Tasks Completed / In Progress

### Phase 1

- [ ] Design Database Schema
- [ ] Create ER Diagram
- [ ] Build REST API
- [ ] Prepare Project Report
- [ ] Develop React Form

### Phase 2

- [ ] Integrate APIs with React
- [ ] Configure PostgreSQL Database
- [ ] Validate Backend Database Integration
- [ ] Upload Frontend to GitHub
- [ ] Upload Backend to GitHub

### React Revision

- [ ] JSX
- [ ] Components & Props
- [ ] useState Hook
- [ ] Virtual DOM

---

## 🚀 Setup Instructions

### Clone Repository

```bash
git clone <repository-url>
```

### Frontend Setup

```bash
npm install
npm run dev
```

### Backend Setup

```bash
mvn spring-boot:run
```

---

## 🌿 Git Workflow

### Create Branch

```bash
git checkout -b feature/your-feature-name
```

Example:

```bash
git checkout -b feature/react-form
git checkout -b feature/api-integration
git checkout -b feature/database-schema
```

---

## ✅ Commit Guidelines

Use meaningful commit messages.

Examples:

```bash
git commit -m "Added training submission form"
```

```bash
git commit -m "Integrated employee API"
```

```bash
git commit -m "Created PostgreSQL schema"
```

---

## 🔀 Pull Request Guidelines

Before creating a Pull Request:

1. Pull latest changes

```bash
git pull origin main
```

2. Resolve conflicts if any.

3. Push your branch.

```bash
git push origin feature/your-feature-name
```

4. Create Pull Request on GitHub.

---

## 🐛 Raising Issues

If you find a bug or want to request a feature:

### Bug Report

Include:

- Description
- Steps to Reproduce
- Expected Behaviour
- Actual Behaviour
- Screenshots (if available)

### Feature Request

Include:

- Problem Statement
- Proposed Solution
- Benefits

---

## 📝 Pull Request Template

### Description

Brief summary of changes made.

### Changes

- Added:
- Updated:
- Fixed:

### Screenshots

Attach screenshots if UI changes were made.

### Testing

- [ ] Tested locally
- [ ] No console errors
- [ ] API working correctly

---

## 📌 Branch Naming Convention

| Type | Example |
|--------|----------|
| Feature | feature/training-form |
| Bug Fix | fix/form-validation |
| Database | db/schema-update |
| Documentation | docs/readme-update |

---

## ⚠ Important Rules

- Never push directly to `main`
- Always create a feature branch
- Create Pull Requests for all changes
- Keep commits small and meaningful
- Review code before merging
- Update documentation whenever new functionality is added

---

## 🔮 Future Scope

The following modules may be added in future mentorship sessions:

- Employee Management
- Leave Management
- Attendance Management
- Performance Management
- Recruitment Management
- Notifications
- Role-Based Access Control
- Dashboard Analytics

---

## 📈 Project Status

Current Phase:
Training & Learning Management Module Development

This repository will continue evolving as new tasks and requirements are assigned during the internship.

# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Oxc](https://oxc.rs)
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/)

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.
