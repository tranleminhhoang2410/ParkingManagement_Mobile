using System.ComponentModel.DataAnnotations;

namespace ParkingManagement.Model
{
    public class User
    {
        public User()
        {
            this.Vehicles = new HashSet<Vehicle>();
        }

        [Key]
        public int Id { get; set; }
        public string? Name { get; set; }
        public string? Email { get; set; }
        public string? Phone { get; set; }
        public int? CityId { get; set; }
        public City City { get; set; }
        public int? DistrictId { get; set; }
        public District District { get; set; }
        public int? WardId { get; set; }
        public Ward Ward { get; set; }
        public string? Feedback { get; set; }
        public ICollection<Vehicle> Vehicles { get; set; }
    }
}
