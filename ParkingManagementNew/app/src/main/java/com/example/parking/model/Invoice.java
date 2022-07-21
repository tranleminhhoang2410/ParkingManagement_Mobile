package com.example.parking.model;

public class Invoice {
    public int id;
    public String checkinTime;
    public String checkoutTime;
    public String vehicleId;
    public String slotId;
    //public SlotDTO Slot;
    public double totalPaid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Invoice(int id, String checkinTime, String checkoutTime, String vehicleId, String slotId, double totalPaid) {
        this.id = id;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.vehicleId = vehicleId;
        this.slotId = slotId;
        this.totalPaid = totalPaid;
    }
}
