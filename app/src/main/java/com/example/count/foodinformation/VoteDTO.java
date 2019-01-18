package com.example.count.foodinformation;

import Model.BaseDTO;

public class VoteDTO extends BaseDTO
{
    private int  UserVote ;
    private String  BarcodeID ;

    public VoteDTO(int userVote, String barcodeID,int ID) {
        UserVote = userVote;
        BarcodeID = barcodeID;
        super.CreatedUserId = ID;
    }

    public int getUserVote() {
        return UserVote;
    }

    public void setUserVote(int userVote) {
        UserVote = userVote;
    }

    public String getBarcodeID() {
        return BarcodeID;
    }

    public void setBarcodeID(String barcodeID) {
        BarcodeID = barcodeID;
    }
}