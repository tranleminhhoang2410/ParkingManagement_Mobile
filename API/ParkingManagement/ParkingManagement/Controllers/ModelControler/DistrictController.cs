using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingManagement.Model.DTO;
using ParkingManagement.Service;

namespace ParkingManagement.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DistrictController : ControllerBase
    {
        private readonly IDistrictService districtService;

        public DistrictController(IDistrictService districtService)
        {
            this.districtService = districtService;
        }

        [HttpGet("GetAll")]
        public async Task<ActionResult<IEnumerable<DistrictDTO>>> GetDistricts()
        {
            return Ok(await districtService.GetAllDistricts());
        }

        [HttpGet("Get/{Id}")]
        public async Task<ActionResult<DistrictDTO>> GetDistricts(int Id)
        {
            DistrictDTO district = await districtService.GetDistrictById(Id);
            if (district == null) return BadRequest("not found");
            return Ok(district);
        }

        [HttpGet("Get/CityId/{CityId}")]
        public async Task<ActionResult<IEnumerable<DistrictDTO>>> GetDistrictsByCityId(int CityId)
        {
            return Ok(await districtService.GetDistrictByCityId(CityId));
        }
    }
}
