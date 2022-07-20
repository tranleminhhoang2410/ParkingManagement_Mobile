using Microsoft.EntityFrameworkCore;
using ParkingManagement.Data;
using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using ParkingManagement.Utils.Mapper;

namespace ParkingManagement.Service.Implement
{
    public class VehicleService : BaseServiceContext, IVehicleService
    {
        public VehicleService(AppDbContext context) : base(context)
        {
        }

        public async Task<string> AddNewUserVehicle(VehicleDTO vehicleDTO, int userID)
        {
            try
            {
                Vehicle? vehicle = await _db.Vehicles.FirstOrDefaultAsync(c => c.Id.Equals(vehicleDTO.Id));
                if (vehicle != null) return "Account Exis0ted";

                Vehicle newVehicle = new Vehicle
                {
                    Id = vehicleDTO.Id,
                    VehicleBrand = vehicleDTO.VehicleBrand,
                    VehicleName = vehicleDTO.VehicleName,
                    IsParking = false,
                    UserID = userID,
                    VehicleTypeId = vehicleDTO.VehicleTypeId
                };
                _db.Vehicles.AddAsync(newVehicle);
                await _db.SaveChangesAsync();
                return "success";
            }
            catch (Exception e)
            {
                return e.Message;
            }
        }

        public async Task<IEnumerable<VehicleDTO>> GetAllByUserID(int userID)
        {
            List<VehicleDTO?> vehicles = await _db.Vehicles
                .Where(c => c.UserID == userID)
                .Include(c => c.VehicleType)
                .Include(c => c.Invoices)
                .Select(c => ToDTO.Map(c))
                .ToListAsync();
            return vehicles;
        }

        public async Task<VehicleDTO> GetById(string Id)
        {
            VehicleDTO? vehicle = ToDTO.Map(await _db.Vehicles
                .Include(c => c.VehicleType)
                .Include(c => c.Invoices)
                .Include(c => c.User)
                .FirstOrDefaultAsync(c => c.Id.Equals(Id)));
            return vehicle;
        }

        public Task<bool> Remove(string Id)
        {
            throw new NotImplementedException();
        }

        public async Task<string> SetVehicleIsParking(string Id, bool isParkingStatus)
        {
            try
            {
                Vehicle? v = await _db.Vehicles.FirstOrDefaultAsync(c => c.Id.Equals(Id));
                if (v == null) throw new Exception("no vehicle found");

                v.IsParking = isParkingStatus;

                await _db.SaveChangesAsync();
                return "vehicle isParking update success! ";
            }
            catch (Exception e)
            {
                return e.Message;
            }
        }
    }
}
