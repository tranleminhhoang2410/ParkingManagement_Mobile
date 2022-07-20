using Microsoft.EntityFrameworkCore;
using ParkingManagement.Data;
using ParkingManagement.Model.DTO;
using ParkingManagement.Utils.Mapper;

namespace ParkingManagement.Service.Implement
{
    public class DistrictService : BaseServiceContext, IDistrictService
    {
        public DistrictService(AppDbContext context) : base(context)
        {
        }

        public async Task<IEnumerable<DistrictDTO>> GetAllDistricts()
        {
            List<DistrictDTO?> districts = await _db.Districts
                .Include(c => c.Wards)
                .Select(c => ToDTO.Map(c))
                .ToListAsync();
            return districts;
        }

        public async Task<IEnumerable<DistrictDTO>> GetDistrictByCityId(int id)
        {
            List<DistrictDTO?> districts = await _db.Districts
                .Include(c => c.Wards)
                .Where(c => c.CityId == id)
                .Select(c => ToDTO.Map(c))      
                .ToListAsync();
            return districts;
        }

        public async Task<DistrictDTO> GetDistrictById(int id)
        {
            DistrictDTO? district = ToDTO.Map(await _db.Districts
                .Include(c => c.Wards)
                .FirstOrDefaultAsync(c => c.Id == id));
            return district;
        }
    }
}
