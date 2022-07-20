using System.ComponentModel.DataAnnotations;

namespace ParkingManagement.Model
{
    public class Ward
    {
        [Key]
        public int Id { get; set; }
        public string? Name { get; set; }
        public int DistrictId { get; set; }
        public District District { get; set; }
    }
}
