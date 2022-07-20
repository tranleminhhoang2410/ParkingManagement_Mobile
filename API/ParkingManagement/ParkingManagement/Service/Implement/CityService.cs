using Microsoft.EntityFrameworkCore;
using ParkingManagement.Data;
using ParkingManagement.Model.DTO;
using ParkingManagement.Utils.Mapper;

namespace ParkingManagement.Service.Implement
{
    public class CityService : BaseServiceContext,ICityService
    {

        public CityService(AppDbContext db) : base(db)
        {
        }

        public async Task<IEnumerable<CityDTO>> GetAllCities()
        {
            List<CityDTO?> cities = await _db.Cities
                .Include(c => c.Districts)
                .ThenInclude(c => c.Wards)
                .Select(c => ToDTO.Map(c))
                .ToListAsync();
            return cities;
        }

        public async Task<CityDTO> GetCityById(int id)
        {
            CityDTO? city = ToDTO.Map(await _db.Cities
                .Include(c => c.Districts)
                .ThenInclude(c => c.Wards)
                .FirstOrDefaultAsync(c => c.Id == id));
            return city;
        }
    }
}
