package com.example.parking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    public int id;
    public String name;
    public String email;
    public String phone;
    public int cityId;
    public int districtId;
    public int wardId;
    public String feedback;
//    public ICollection<VehicleDTO> Vehicles;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public User(int id, String name, String email, String phone, int cityId, int districtId, int wardId, String feedback) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cityId = cityId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.feedback = feedback;
    }
}
