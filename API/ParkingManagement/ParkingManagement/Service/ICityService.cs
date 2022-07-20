using ParkingManagement.Model.DTO;

namespace ParkingManagement.Service
{
    public interface ICityService
    {
        public Task<IEnumerable<CityDTO>> GetAllCities();
        public Task<CityDTO> GetCityById(int id);
    }
}
