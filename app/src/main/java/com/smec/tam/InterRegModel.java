package com.smec.tam;

public class InterRegModel {

    String department;
    String department_section_year;
    String name;
    String phone;
    String rollno;
    String section;
    String year;
    String clg_send;

    public String getDepartment() {
        return department;
    }

    public String getDepartment_section_year() {
        return department_section_year;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRollno() {
        return rollno;
    }

    public String getSection() {
        return section;
    }

    public String getYear() {
        return year;
    }

    public String getClg_send() {
        return clg_send;
    }

    public String getEmail() {
        return email;
    }

    public InterRegModel() {
    }

    public InterRegModel(String department, String department_section_year, String name, String phone, String rollno, String section, String year, String clg_send, String email) {
        this.department = department;
        this.department_section_year = department_section_year;
        this.name = name;
        this.phone = phone;
        this.rollno = rollno;
        this.section = section;
        this.year = year;
        this.clg_send = clg_send;
        this.email = email;
    }

    String email;

}
