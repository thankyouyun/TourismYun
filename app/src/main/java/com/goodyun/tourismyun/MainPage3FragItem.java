package com.goodyun.tourismyun;

public class MainPage3FragItem {

    String id;
    String img;
    String addr;
    String title;

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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MainPage3FragItem() {

    }

    public MainPage3FragItem(String id, String img, String addr, String title) {
        this.id = id;
        this.img = img;
        this.addr = addr;
        this.title = title;
    }
}
