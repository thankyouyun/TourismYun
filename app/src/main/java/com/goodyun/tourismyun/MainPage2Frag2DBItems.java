package com.goodyun.tourismyun;

public class MainPage2Frag2DBItems {
    String no;
    String id;
    String title;
    String crdate;
    String name;
    String tourdate;
    String place;
    String text;

    public MainPage2Frag2DBItems() {
    }

    public MainPage2Frag2DBItems(String no, String id, String title, String crdate, String name, String tourdate, String place, String text) {
        this.no = no;
        this.id = id;
        this.title = title;
        this.crdate = crdate;
        this.name = name;
        this.tourdate = tourdate;
        this.place = place;
        this.text = text;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }


    public MainPage2Frag2DBItems(String id, String title, String tourdate, String text, String name, String place, String crdate) {
        this.id = id;
        this.title = title;
        this.tourdate = tourdate;
        this.text = text;
        this.name = name;
        this.place = place;
        this.crdate = crdate;
    }

    public String getTourdate() {
        return tourdate;
    }

    public void setTourdate(String tourdate) {
        this.tourdate = tourdate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCrdate() {
        return crdate;
    }

    public void setCrdate(String crdate) {
        this.crdate = crdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
