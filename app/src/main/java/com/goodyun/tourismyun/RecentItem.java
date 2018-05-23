package com.goodyun.tourismyun;

public class RecentItem {
    String id;
    String img;
    String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RecentItem(String id, String img, String type) {
        this.id = id;
        this.img = img;
        this.type = type;
    }
}
