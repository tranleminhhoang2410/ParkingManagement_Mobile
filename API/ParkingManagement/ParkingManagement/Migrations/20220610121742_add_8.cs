using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ParkingManagement.Migrations
{
    public partial class add_8 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "IsParking",
                table: "Vehicles",
                type: "bit",
                nullable: false,
                defaultValue: false);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "IsParking",
                table: "Vehicles");
        }
    }
}
