using ParkingManagement.Model.DTO;

namespace ParkingManagement.Service
{
    public interface IWardService
    {
        public Task<IEnumerable<WardDTO>> GetAllWards();
        public Task<WardDTO> GetWardById(int id);
        public Task<IEnumerable<WardDTO>> GetWardByDistrictId(int id);
    }
}
