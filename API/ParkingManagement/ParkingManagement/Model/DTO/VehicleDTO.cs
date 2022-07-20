namespace ParkingManagement.Model.DTO
{
    public class VehicleDTO
    {
        public string? Id { get; set; }
        public string? VehicleName { get; set; }
        public string? VehicleBrand { get; set; }
        public int VehicleTypeId { get; set; }
        public VehicleTypeDTO VehicleType { get; set; }
        public ICollection<InvoiceDTO> Invoices { get; set; } = new HashSet<InvoiceDTO>();
        public Boolean IsParking { get; set; }
    }
}
