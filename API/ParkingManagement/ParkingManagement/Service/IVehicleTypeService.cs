using ParkingManagement.Model.DTO;

namespace ParkingManagement.Service
{
    public interface IVehicleTypeService
    {
        public Task<IEnumerable<VehicleTypeDTO>> GetAll();
        public Task<VehicleTypeDTO> GetById(int id);
        public Task<Boolean> Update(VehicleTypeDTO vehicleTypeDTO);
    }
}
