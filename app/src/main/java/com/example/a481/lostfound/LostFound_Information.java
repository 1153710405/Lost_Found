package com.example.a481.lostfound;

import cn.bmob.v3.BmobObject;


public class LostFound_Information extends BmobObject {
    private Integer UserID = 1;
    private String Kind;
    private String Time ;
    private String Place ;
    private String Description ;

    public Integer getID () {
        return UserID;
    }
    public String getKind () {
        return Kind;
    }

    public void setKind (String kind) {
        this.Kind = kind;
    }

    public String getTime () {
        return Time;
    }

    public void setTime (String time) {
        this.Time = time;
    }

    public String getPlace () {
        return Place;
    }

    public void setPlace (String place) {
        this.Place = place;
    }
    public String getDescription () {
        return Description;
    }

    public void setDescription (String description) {
        this.Description = description;
    }
}
