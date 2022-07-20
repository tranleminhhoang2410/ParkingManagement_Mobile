using Microsoft.EntityFrameworkCore;
using ParkingManagement.Data;
using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using ParkingManagement.Utils.Mapper;

namespace ParkingManagement.Service.Implement
{
    public class VehicleTypeService : BaseServiceContext, IVehicleTypeService
    {
        public VehicleTypeService(AppDbContext context) : base(context)
        {
        }

        public async Task<IEnumerable<VehicleTypeDTO>> GetAll()
        {
            List<VehicleTypeDTO?> types = await _db.VehicleTypes
                .Select(c => ToDTO.Map(c))
                .ToListAsync();
            return types;
        }

        public async Task<VehicleTypeDTO> GetById(int id)
        {
            VehicleTypeDTO? vehicleType = ToDTO.Map(await _db.VehicleTypes
                .FirstOrDefaultAsync(c => c.Id == id));
            return vehicleType;
        }

        public async Task<bool> Update(VehicleTypeDTO vehicleTypeDTO)
        {
            VehicleType? _type = await _db.VehicleTypes.FirstOrDefaultAsync(c => c.Id.Equals(vehicleTypeDTO.Id));
            if (_type == null) return false;

            _type.PricePerHour = vehicleTypeDTO.PricePerDay;
            _type.PricePerDay = vehicleTypeDTO.PricePerDay;
            _type.PricePerWeek = vehicleTypeDTO.PricePerWeek;
            _type.PricePerMonth = vehicleTypeDTO.PricePerMonth;
            _type.PricePerYear = vehicleTypeDTO.PricePerYear;


            await _db.SaveChangesAsync();
            return true;
        }
    }
}
