namespace ParkingManagement.Model.DTO
{
    public class CityDTO
    {
        public int Id { get; set; }
        public string? Name { get; set; }
        public ICollection<DistrictDTO> Districts { get; set; } = new HashSet<DistrictDTO>();
    }
}
