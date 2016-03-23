package com.example.a481.lostfound;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.EventLogTags;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Renton on 2016/3/18.
 */
public class Item extends BmobObject implements Parcelable {

    private String Username;
    private String ItemID;
    private String Kind;
    private String Time;
    private String Place;
    private String Description;
    private String ImageName;
    private String ImageUri;

    public Item(String Username, String ItemID, String Kind, String Time, String Place, String Description, String ImageName, String ImageUri) {
        this.Username = Username;
        this.ItemID = ItemID;
        this.Kind = Kind;
        this.Time = Time;
        this.Place = Place;
        this.Description = Description;
        this.ImageName = ImageName;
        this.ImageUri = ImageUri;
    }

    public Item(String Username, String Kind, String Time, String Place, String Description, String ImageName, String ImageUri) {
        this.Username = Username;
        this.Kind = Kind;
        this.Time = Time;
        this.Place = Place;
        this.Description = Description;
        this.ImageName = ImageName;
        this.ImageUri = ImageUri;
    }

    public Item(Parcel source) {
        this.Username = source.readString();
        this.ItemID = source.readString();
        this.Kind = source.readString();
        this.Time = source.readString();
        this.Place = source.readString();
        this.Description = source.readString();
        this.ImageName = source.readString();
        this.ImageUri = source.readString();
    }

    public String getUsername() {
        return Username;
    }

    public String getItemID() {
        return ItemID;
    }

    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        this.Kind = kind;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        this.Place = place;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getImageName() {
        return ImageName;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageName(String ImageName) {
        this.ImageName = ImageName;
    }

    public void setImageUri(String ImageUri) {
        this.ImageUri = ImageUri;
    }

    public static final Parcelable.Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Username);
        dest.writeString(ItemID);
        dest.writeString(Kind);
        dest.writeString(Time);
        dest.writeString(Place);
        dest.writeString(Description);
        dest.writeString(ImageName);
        dest.writeString(ImageUri);
    }
}

