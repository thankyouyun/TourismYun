package com.goodyun.tourismyun;

public class MainPage1FragMiddlesItem {

    String id;
    String img;
    String title;
    String mapX;
    String mapY;
    String addr;
    String tel;

    public MainPage1FragMiddlesItem() {
    }


    public MainPage1FragMiddlesItem(String id, String img, String title) {
        this.id = id;
        this.img = img;
        this.title = title;
    }

    public MainPage1FragMiddlesItem(String id, String img, String title, String addr) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.addr = addr;
    }



    public MainPage1FragMiddlesItem(String id, String img, String title, String addr, String tel) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.addr = addr;
        this.tel = tel;
    }


    public MainPage1FragMiddlesItem(String id, String img, String title, String mapX, String mapY, String addr, String tel) {

        this.id = id;
        this.img = img;
        this.title = title;
        this.mapX = mapX;
        this.mapY = mapY;
        this.addr = addr;
        this.tel = tel;
    }

    public MainPage1FragMiddlesItem(String id, String img, String title, String mapX, String mapY, String addr) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.mapX = mapX;
        this.mapY = mapY;
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMapX() {
        return mapX;
    }

    public void setMapX(String mapX) {
        this.mapX = mapX;
    }

    public String getMapY() {
        return mapY;
    }

    public void setMapY(String mapY) {
        this.mapY = mapY;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
