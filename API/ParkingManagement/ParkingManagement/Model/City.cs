using System.ComponentModel.DataAnnotations;

namespace ParkingManagement.Model
{
    public class City
    {
        public City()
        {
            this.Districts = new HashSet<District>();
        }

        [Key]
        public int Id { get; set; }
        public string? Name { get; set; }
        public ICollection<District> Districts { get; set; }
    }
}
