package com.example.a481.lostfound;

import android.util.EventLogTags;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Renton on 2016/3/18.
 */
public class Item extends BmobObject {

    private Integer UserID = 1;
    private String ItemID;
    private String Kind;
    private String Time ;
    private String Place ;
    private String Description ;
    private String ImageName;
    private String ImageUri;
    private BmobFile ImageFile;

    public Item(Integer UserID, String ItemID, String Kind, String Time, String Place, String Description, String ImageName, String ImageUri, BmobFile ImageFile){
        this.UserID = UserID;
        this.ItemID = ItemID;
        this.Kind = Kind;
        this.Time = Time;
        this.Place = Place;
        this.Description = Description;
        this.ImageName = ImageName;
        this.ImageUri = ImageUri;
        this.ImageFile = ImageFile;
    }

    public Item(Integer UserID, String Kind, String Time, String Place, String Description){
        this.UserID = UserID;
        this.Kind = Kind;
        this.Time = Time;
        this.Place = Place;
        this.Description = Description;
    }


    public Integer getUserID () {
        return UserID;
    }

    public String getItemID () {
        return ItemID;
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

    public String getImageName () {
        return ImageName;
    }

    public String getImageUri () {
        return ImageUri;
    }

    public BmobFile getImageFile () {
        return ImageFile;
    }

    public void setImageName (String ImageName) {
        this.ImageName = ImageName;
    }

    public void setImageUri (String ImageUri) {
         this.ImageUri = ImageUri;
    }

    public void setImageFile (BmobFile ImageFile) {
        this.ImageFile = ImageFile;
    }
}
