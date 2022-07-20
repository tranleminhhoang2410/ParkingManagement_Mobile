using System.ComponentModel.DataAnnotations;

namespace ParkingManagement.Model
{
    public class Invoice
    {
        [Key]
        public int Id { get; set; }
        public DateTime? CheckinTime { get; set; }
        public DateTime? CheckoutTime { get; set; }
        public string? VehicleId { get; set; }
        public Vehicle Vehicle { get; set; }
        public string? SlotId { get; set; }
        public Slot Slot { get; set; }
        public double TotalPaid { get; set; }
    }
}
