package com.goodyun.tourismyun;

public class MainPage1FragBestViewItem {

    public MainPage1FragBestViewItem() {

    }


    String Subid;
    String title;
    String img;
    String overView;

    public MainPage1FragBestViewItem(String subid, String title, String img, String overView) {
        Subid = subid;
        this.title = title;
        this.img = img;
        this.overView = overView;
    }

    public String getSubid() {
        return Subid;
    }

    public void setSubid(String subid) {
        Subid = subid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }
}
