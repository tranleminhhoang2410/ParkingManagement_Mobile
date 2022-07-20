namespace ParkingManagement.Model.DTO
{
    public class UserDTO
    {
        public int Id { get; set; }
        public string? Name { get; set; }
        public string? Email { get; set; }
        public string? Phone { get; set; }
        public int? CityId { get; set; }
        public int? DistrictId { get; set; }
        public int? WardId { get; set; }
        public string? Feedback { get; set; }
        public ICollection<VehicleDTO> Vehicles { get; set; } = new HashSet<VehicleDTO>();
    }
}
