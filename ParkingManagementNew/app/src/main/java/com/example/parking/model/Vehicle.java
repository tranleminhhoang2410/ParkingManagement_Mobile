package com.example.parking.model;

public class Vehicle {
    public String id;
    public String vehicleName;
    public String vehicleBrand;
    public int vehicleTypeId;
    public VehicleType vehicleType;
//    public ICollection<InvoiceDTO> Invoices { get; set; } = new HashSet<InvoiceDTO>();
    public Boolean isParking;
    public int userId;

    public Vehicle() {
    }


    public Vehicle(String id, String vehicleName, String vehicleBrand, int vehicleTypeId, VehicleType vehicleType, Boolean isParking, int userId) {
        this.id = id;
        this.vehicleName = vehicleName;
        this.vehicleBrand = vehicleBrand;
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleType = vehicleType;
        this.isParking = isParking;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Boolean getParking() {
        return isParking;
    }

    public void setParking(Boolean parking) {
        isParking = parking;
    }
}
