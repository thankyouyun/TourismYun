package com.goodyun.tourismyun;

public class MainPage1FragMiddle6ItemVIewItems {

    String addr;
    String mapX, mapY;
    String overView;
    String tel;
    String title;

    public MainPage1FragMiddle6ItemVIewItems() {
    }

    public MainPage1FragMiddle6ItemVIewItems(String addr, String mapX, String mapY, String overView, String tel, String title) {
        this.addr = addr;
        this.mapX = mapX;
        this.mapY = mapY;
        this.overView = overView;
        this.tel = tel;
        this.title = title;
    }

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    String checkin,checkout;
    String cook;
    String food;
    String size;
    String telNum;
    String roomCount;
    String subFacility;

    public String getSubFacility() {
        return subFacility;
    }

    public void setSubFacility(String subFacility) {
        this.subFacility = subFacility;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getCook() {
        return cook;
    }

    public void setCook(String cook) {
        this.cook = cook;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(String roomCount) {
        this.roomCount = roomCount;
    }

    public MainPage1FragMiddle6ItemVIewItems(String checkin, String checkout, String cook, String food, String size, String telNum, String roomCount,String subFacility) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.cook = cook;
        this.food = food;
        this.size = size;
        this.telNum = telNum;
        this.roomCount = roomCount;
    }

    String shopTel;
    String shopTime;
    String shopSale;

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getShopTime() {
        return shopTime;
    }

    public void setShopTime(String shopTime) {
        this.shopTime = shopTime;
    }

    public String getShopSale() {
        return shopSale;
    }

    public void setShopSale(String shopSale) {
        this.shopSale = shopSale;
    }

    public MainPage1FragMiddle6ItemVIewItems(String shopTel, String shopTime, String shopSale) {
        this.shopTel = shopTel;
        this.shopTime = shopTime;
        this.shopSale = shopSale;
    }
}
