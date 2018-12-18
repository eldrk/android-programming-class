package com.example.student.sampleparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 2018-11-12.
 */

public class SimpleData implements Parcelable {

    int number;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SimpleData(int num, String msg) {
        number = num;
        this.message = msg;
    }


    public SimpleData(Parcel src) {
        number = src.readInt();
        message = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public SimpleData createFromParcel(Parcel parcel) {
            return new SimpleData(parcel);
        }

        @Override
        public SimpleData[] newArray(int i) {
            return new SimpleData[i];
        }
    };


    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeString(message);
    }
}
