package com.example.count.foodinformation;

public class CommentList {

    private String yazarid;
    private String yorum;

    public CommentList(String yazarid, String yorum, Float oy) {
        this.yazarid = yazarid;
        this.yorum = yorum;
    }

    public String getYazarid() {
        return yazarid;
    }

    public String getYorum() {
        return yorum;
    }


    public void setYazarid(String yazarid) {
        this.yazarid = yazarid;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

}
