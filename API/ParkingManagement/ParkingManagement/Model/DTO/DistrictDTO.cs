namespace ParkingManagement.Model.DTO
{
    public class DistrictDTO
    {
        public int Id { get; set; }
        public string? Name { get; set; }
        public ICollection<WardDTO> Wards { get; set; } = new HashSet<WardDTO>();
    }
}
