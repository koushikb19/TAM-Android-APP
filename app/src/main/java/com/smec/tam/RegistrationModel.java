package com.smec.tam;

public class RegistrationModel {

    String department;
    String department_section_year;
    String name;
    String phone;
    String rollno;
    String section;
    String year;

    public RegistrationModel() {
    }

    public RegistrationModel(String department, String department_section_year, String name, String phone, String rollno, String section, String year) {
        this.department = department;
        this.department_section_year = department_section_year;
        this.name = name;
        this.phone = phone;
        this.rollno = rollno;
        this.section = section;
        this.year = year;
    }

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
}
