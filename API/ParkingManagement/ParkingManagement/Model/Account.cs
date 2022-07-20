using System.ComponentModel.DataAnnotations;

namespace ParkingManagement.Model
{
    public class Account
    {
        [Key]
        public int Id { get; set; }
        public string? Username { get; set; }
        public string? Password { get; set; }
        public string? Role { get; set; }

        public int UserId { get; set; }
        public User User { get; set; }
    }
}
