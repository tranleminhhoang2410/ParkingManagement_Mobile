namespace ParkingManagement.Model.DTO
{
    public class SlotDTO
    {
        public string Area { get; set; }
        public int Position { get; set; }
        public Boolean Status { get; set; }
        public int VehicleTypeId { get; set; }
        public string VehicleTypeName { get; set; }
    }
}
