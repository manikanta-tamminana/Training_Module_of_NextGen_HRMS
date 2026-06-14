package com.MyNicProject.hrms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}