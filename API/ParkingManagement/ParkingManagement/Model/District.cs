using System.ComponentModel.DataAnnotations;

namespace ParkingManagement.Model
{
    public class District
    {
        public District()
        {
            this.Wards = new HashSet<Ward>();
        }

        [Key]
        public int Id { get; set; }
        public string? Name { get; set; }
        public int CityId { get; set; }
        public City City { get; set; }
        public ICollection<Ward> Wards { get; set; }
    }
}
