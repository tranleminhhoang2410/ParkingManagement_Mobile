using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ParkingManagement.Migrations
{
    public partial class add_11 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Vehicles_Users_UserId",
                table: "Vehicles");

            migrationBuilder.RenameColumn(
                name: "UserId",
                table: "Vehicles",
                newName: "UserID");

            migrationBuilder.RenameIndex(
                name: "IX_Vehicles_UserId",
                table: "Vehicles",
                newName: "IX_Vehicles_UserID");

            migrationBuilder.AddForeignKey(
                name: "FK_Vehicles_Users_UserID",
                table: "Vehicles",
                column: "UserID",
                principalTable: "Users",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Vehicles_Users_UserID",
                table: "Vehicles");

            migrationBuilder.RenameColumn(
                name: "UserID",
                table: "Vehicles",
                newName: "UserId");

            migrationBuilder.RenameIndex(
                name: "IX_Vehicles_UserID",
                table: "Vehicles",
                newName: "IX_Vehicles_UserId");

            migrationBuilder.AddForeignKey(
                name: "FK_Vehicles_Users_UserId",
                table: "Vehicles",
                column: "UserId",
                principalTable: "Users",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
