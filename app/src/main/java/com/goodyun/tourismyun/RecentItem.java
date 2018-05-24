package com.goodyun.tourismyun;

import java.io.Serializable;

public class RecentItem implements Serializable {
    String id;
    String img;
    int type;
    int no;
    boolean sel=false;

    public RecentItem(String id, String img, int type, int no, boolean sel) {
        this.id = id;
        this.img = img;
        this.type = type;
        this.no = no;
        this.sel = sel;
    }

    public boolean isSel() {
        return sel;
    }

    public void setSel(boolean sel) {
        this.sel = sel;
    }

    public RecentItem() {
    }

    

    public RecentItem(String id, String img, int type, int no) {
        this.id = id;
        this.img = img;
        this.type = type;
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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


    public RecentItem(String id, String img, int type) {
        this.id = id;
        this.img = img;
        this.type = type;
    }
}
