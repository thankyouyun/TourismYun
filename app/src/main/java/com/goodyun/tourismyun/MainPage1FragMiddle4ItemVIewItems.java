package com.goodyun.tourismyun;

public class MainPage1FragMiddle4ItemVIewItems {

    String img;
    String mapX, mapY;
    String overView;
    String title;
    String tel;


    String card;
    String mainMenu, subMenu;
    String time;
    String parking;
    String rest;




    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(String mainMenu) {
        this.mainMenu = mainMenu;
    }

    public String getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    String smoke;

    public MainPage1FragMiddle4ItemVIewItems(String card, String mainMenu, String subMenu, String time, String parking, String rest, String smoke) {
        this.card = card;
        this.mainMenu = mainMenu;
        this.subMenu = subMenu;
        this.time = time;
        this.parking = parking;
        this.rest = rest;
        this.smoke = smoke;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public MainPage1FragMiddle4ItemVIewItems(String img, String mapX, String mapY, String overView, String title, String tel) {

        this.img = img;
        this.mapX = mapX;
        this.mapY = mapY;
        this.overView = overView;
        this.title = title;
        this.tel = tel;
    }

    public MainPage1FragMiddle4ItemVIewItems() {
    }

    public MainPage1FragMiddle4ItemVIewItems(String img, String mapX, String mapY, String overView, String title) {
        this.img = img;
        this.mapX = mapX;
        this.mapY = mapY;
        this.overView = overView;
        this.title = title;
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
}
