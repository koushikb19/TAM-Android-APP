package com.smec.tam.ui.events;

public class EventsModel {

    public String Details;
    public String content;
    public String date;
    public String image;
    public  boolean isfirstYear;
    public String location;
    public  int man;
    public  String name;
    public  int np;
    public   String time;
    public Boolean interClg;

    public EventsModel() {
    }

    public EventsModel(String details, String content, String date, String image, boolean isfirstYear, String location, int man, String name, int np, String time, Boolean interClg) {
        Details = details;
        this.content = content;
        this.date = date;
        this.image = image;
        this.isfirstYear = isfirstYear;
        this.location = location;
        this.man = man;
        this.name = name;
        this.np = np;
        this.time = time;
        this.interClg = interClg;
    }


}
