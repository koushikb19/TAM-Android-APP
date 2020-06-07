package com.smec.tam.ui.crew;

public class CrewMember {

    public String designation;
    public String img;
    public boolean is_board;
    public boolean is_head;
    public String name;

    public CrewMember() {
    }

    public CrewMember(String designation, String img, boolean is_board, boolean is_head, String name) {
        this.designation = designation;
        this.img = img;
        this.is_board = is_board;
        this.is_head = is_head;
        this.name = name;
    }
}
