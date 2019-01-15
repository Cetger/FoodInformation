package com.example.count.foodinformation;

public class CommentList {

    private String yazarid;
    private String yorum;
    private Float oy;

    public CommentList(String yazarid, String yorum, Float oy) {
        this.yazarid = yazarid;
        this.yorum = yorum;
        this.oy = oy;
    }

    public String getYazarid() {
        return yazarid;
    }

    public String getYorum() {
        return yorum;
    }

    public Float getOy() {
        return oy;
    }
}
