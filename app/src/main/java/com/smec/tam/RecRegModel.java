package com.smec.tam;

public class RecRegModel {

    String department;
    String department_section_year;
    String name;
    String phone;
    String rollno;
    String section;
    String year;
    String asset;
    String unique;
    String deptQuest;

    public RecRegModel() {
    }

    public RecRegModel(String department, String department_section_year, String name, String phone, String rollno, String section, String year, String asset, String unique, String deptQuest) {
        this.department = department;
        this.department_section_year = department_section_year;
        this.name = name;
        this.phone = phone;
        this.rollno = rollno;
        this.section = section;
        this.year = year;
        this.asset = asset;
        this.unique = unique;
        this.deptQuest = deptQuest;
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

    public String getAsset() {
        return asset;
    }

    public String getUnique() {
        return unique;
    }

    public String getDeptQuest() {
        return deptQuest;
    }
}
