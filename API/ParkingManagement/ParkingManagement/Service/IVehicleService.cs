using ParkingManagement.Model.DTO;

namespace ParkingManagement.Service
{
    public interface IVehicleService
    {
        public Task<IEnumerable<VehicleDTO>> GetAllByUserID(int userID);
        public Task<VehicleDTO> GetById(string Id);
        public Task<string> AddNewUserVehicle(VehicleDTO vehicleDTO, int userID);
        public Task<Boolean> Remove(string Id);
        public Task<string> SetVehicleIsParking(string Id, bool isParkingStatus); 
    }
}
