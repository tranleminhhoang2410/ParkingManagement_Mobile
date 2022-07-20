using Microsoft.EntityFrameworkCore.Metadata.Internal;
using System.ComponentModel.DataAnnotations;

namespace ParkingManagement.Model
{
    public class Slot
    {
        public Slot()
        {
            this.Invoices = new HashSet<Invoice>();
        }
        [Key]
        public string Id { get; set; }
        public Boolean Status { get; set; }
        public int VehicleTypeId { get; set; }
        public VehicleType VehicleType { get; set; }
        public ICollection<Invoice> Invoices { get; set; }
    }
}
