using ParkingManagement.Model;
using System.ComponentModel.DataAnnotations;

namespace ParkingManagement.Authentication.AuthModel
{
    public class Token
    {
        [Key]
        public int Id { get; set; }
        public string? Value { get; set; }
        public int UserId { get; set; }
        public User User { get; set; }
    }
}
