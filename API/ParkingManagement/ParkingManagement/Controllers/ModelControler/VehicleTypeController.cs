using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingManagement.Model.DTO;
using ParkingManagement.Service;

namespace ParkingManagement.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class VehicleTypeController : ControllerBase
    {
        private readonly IVehicleTypeService vehicleTypeService;

        public VehicleTypeController(IVehicleTypeService vehicleTypeService)
        {
            this.vehicleTypeService = vehicleTypeService;
        }

        [HttpGet("GetAll")]
        public async Task<ActionResult<IEnumerable<VehicleTypeDTO>>> GetAllVehicleTypes()
        {
            return Ok(await vehicleTypeService.GetAll());
        }

        [HttpGet("Get/{Id}")]
        public async Task<ActionResult<VehicleTypeDTO>> GetType(int Id)
        {
            VehicleTypeDTO type = await vehicleTypeService.GetById(Id);
            if (type == null) return BadRequest("not found");
            return Ok(type);
        }

        [HttpPut("Update")]
        public async Task<ActionResult<IEnumerable<VehicleTypeDTO>>> Update(VehicleTypeDTO vehicleTypeDTO)
        {
            Boolean updated = await vehicleTypeService.Update(vehicleTypeDTO);
            if (updated)
            {
                return Ok(await vehicleTypeService.GetAll());
            }
            else
            {
                return BadRequest("update fail");
            }

        }
    }
}
