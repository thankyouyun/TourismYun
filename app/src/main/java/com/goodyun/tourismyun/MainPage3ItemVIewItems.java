package com.goodyun.tourismyun;

public class MainPage3ItemVIewItems {

    public MainPage3ItemVIewItems() {
    }

    String addr;
    String mapX, mapY;
    String overView;
    String title;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MainPage3ItemVIewItems(String addr, String mapX, String mapY, String overView, String title) {
        this.addr = addr;
        this.mapX = mapX;
        this.mapY = mapY;
        this.overView = overView;
        this.title = title;
    }


    String info;
    String time;
    String rest;
    String parking;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public MainPage3ItemVIewItems(String info, String time, String rest, String parking) {
        this.info = info;
        this.time = time;
        this.rest = rest;
        this.parking = parking;
    }
}
