package com.bababansiwalanew.Dashboard.dto;

/**
 * Created by Lalit on 23-01-2017.
 */

public class HomeItem {

    private String name;
    private int count;
    private int thumbnail;
    private int flag;

    public HomeItem(String name, int count, int thumbnail, int flag) {
        this.name = name;
        this.count = count;
        this.thumbnail = thumbnail;
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
