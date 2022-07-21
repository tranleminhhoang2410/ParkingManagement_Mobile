package com.example.parking.model;

public class Lot {
    private String area;
    private int position;
    private Boolean status;
    private int vehicleTypeId;
    private String vehicleTypeName;
    private Vehicle parkingVehicle;

    public Lot() {
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Vehicle getParkingVehicle() {
        return parkingVehicle;
    }

    public void setParkingVehicle(Vehicle parkingVehicle) {
        this.parkingVehicle = parkingVehicle;
    }

    public Lot(String area, int position, Boolean status, int vehicleTypeId, String vehicleTypeName, Vehicle parkingVehicle) {
        this.area = area;
        this.position = position;
        this.status = status;
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleTypeName = vehicleTypeName;
        this.parkingVehicle = parkingVehicle;
    }
}
