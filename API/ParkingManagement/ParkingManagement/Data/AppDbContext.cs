using Microsoft.EntityFrameworkCore;
using ParkingManagement.Authentication.AuthModel;
using ParkingManagement.Model;

namespace ParkingManagement.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options):base(options)
        {
        }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            
        }

        public DbSet<Account> Accounts { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<Vehicle> Vehicles { get; set; }
        public DbSet<Model.VehicleType> VehicleTypes { get; set; }
        public DbSet<Slot> Slots { get; set; }
        public DbSet<Invoice> Invoices { get; set; }

        public DbSet<City> Cities { get; set; }
        public DbSet<District> Districts { get; set; }
        public DbSet<Ward> Wards { get; set; }


        public DbSet<Token> ValidTokens { get; set; }
    }
}
