package com.smec.tam.ui.crew;

import java.util.List;

public class Department {

    String department;
    List<CrewMember> members;

    public Department(String department, List<CrewMember> members) {
        this.department = department;
        this.members = members;
    }

    public Department() {
    }
}
