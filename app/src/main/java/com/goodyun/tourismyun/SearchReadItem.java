package com.goodyun.tourismyun;

public class SearchReadItem {

    String read;
    String date;

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SearchReadItem() {
    }

    public SearchReadItem(String read, String date) {
        this.read = read;
        this.date = date;
    }

    String id,type,img,mapX,mapY,title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SearchReadItem(String id, String type, String img, String mapX, String mapY, String title) {
        this.id = id;
        this.type = type;
        this.img = img;
        this.mapX = mapX;
        this.mapY = mapY;
        this.title = title;
    }
}
