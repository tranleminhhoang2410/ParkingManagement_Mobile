using Microsoft.EntityFrameworkCore;
using ParkingManagement.Data;
using ParkingManagement.Model.DTO;
using ParkingManagement.Utils.Mapper;

namespace ParkingManagement.Service.Implement
{
    public class WardService : BaseServiceContext, IWardService
    {
        public WardService(AppDbContext context) : base(context)
        {
        }

        public async Task<IEnumerable<WardDTO>> GetAllWards()
        {
            List<WardDTO?> wards = await _db.Wards
                .Select(c => ToDTO.Map(c))
                .ToListAsync();
            return wards;
        }

        public async Task<IEnumerable<WardDTO>> GetWardByDistrictId(int id)
        {
            List<WardDTO?> wards = await _db.Wards
                .Where(c => c.DistrictId == id)
                .Select(c => ToDTO.Map(c))
                .ToListAsync();
            return wards;
        }

        public async Task<WardDTO> GetWardById(int id)
        {
            WardDTO? ward = ToDTO.Map(await _db.Wards
                .FirstOrDefaultAsync(c => c.Id == id));
            return ward;
        }
    }
}
