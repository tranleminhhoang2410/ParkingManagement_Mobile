package com.example.parking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VehicleType implements Serializable, Parcelable {
    @SerializedName("id")
    public int Id;
    @SerializedName("typeName")
    public String TypeName;
    @SerializedName("pricePerHour")
    public double PricePerHour;
    @SerializedName("pricePerDay")
    public double PricePerDay;
    @SerializedName("pricePerWeek")
    public double PricePerWeek;
    @SerializedName("pricePerMonth")
    public double PricePerMonth;
    @SerializedName("pricePerYear")
    public double PricePerYear;

    public VehicleType(int id, String typeName, double pricePerHour, double pricePerDay, double pricePerWeek, double pricePerMonth, double pricePerYear) {
        Id = id;
        TypeName = typeName;
        PricePerHour = pricePerHour;
        PricePerDay = pricePerDay;
        PricePerWeek = pricePerWeek;
        PricePerMonth = pricePerMonth;
        PricePerYear = pricePerYear;
    }

    protected VehicleType(Parcel in) {
        Id = in.readInt();
        TypeName = in.readString();
        PricePerHour = in.readDouble();
        PricePerDay = in.readDouble();
        PricePerWeek = in.readDouble();
        PricePerMonth = in.readDouble();
        PricePerYear = in.readDouble();
    }

    public static final Creator<VehicleType> CREATOR = new Creator<VehicleType>() {
        @Override
        public VehicleType createFromParcel(Parcel in) {
            return new VehicleType(in);
        }

        @Override
        public VehicleType[] newArray(int size) {
            return new VehicleType[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public double getPricePerHour() {
        return PricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        PricePerHour = pricePerHour;
    }

    public double getPricePerDay() {
        return PricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        PricePerDay = pricePerDay;
    }

    public double getPricePerWeek() {
        return PricePerWeek;
    }

    public void setPricePerWeek(double pricePerWeek) {
        PricePerWeek = pricePerWeek;
    }

    public double getPricePerMonth() {
        return PricePerMonth;
    }

    public void setPricePerMonth(double pricePerMonth) {
        PricePerMonth = pricePerMonth;
    }

    public double getPricePerYear() {
        return PricePerYear;
    }

    public void setPricePerYear(double pricePerYear) {
        PricePerYear = pricePerYear;
    }

    @Override
    public String toString() {
        return "VehicleType{" +
                "Id=" + Id +
                ", TypeName='" + TypeName + '\'' +
                ", PricePerHour=" + PricePerHour +
                ", PricePerDay=" + PricePerDay +
                ", PricePerWeek=" + PricePerWeek +
                ", PricePerMonth=" + PricePerMonth +
                ", PricePerYear=" + PricePerYear +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(TypeName);
        dest.writeDouble(PricePerHour);
        dest.writeDouble(PricePerDay);
        dest.writeDouble(PricePerWeek);
        dest.writeDouble(PricePerMonth);
        dest.writeDouble(PricePerYear);
    }
}
