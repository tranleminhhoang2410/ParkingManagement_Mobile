using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingManagement.Model.DTO;
using ParkingManagement.Service;

namespace ParkingManagement.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class WardController : ControllerBase
    {
        private readonly IWardService wardService;

        public WardController(IWardService wardService)
        {
            this.wardService = wardService;
        }

        [HttpGet("GetAll")]
        public async Task<ActionResult<IEnumerable<WardDTO>>> GetWards()
        {
            return Ok(await wardService.GetAllWards());
        }

        [HttpGet("Get/{Id}")]
        public async Task<ActionResult<WardDTO>> GetWard(int Id)
        {
            WardDTO ward = await wardService.GetWardById(Id);
            if (ward == null) return BadRequest("not found");
            return Ok(ward);
        }

        [HttpGet("Get/DistrictId/{DistrictId}")]
        public async Task<ActionResult<IEnumerable<WardDTO>>> GetWardByDistrictId(int DistrictId)
        {
            return Ok(await wardService.GetWardByDistrictId(DistrictId));
        }
    }
}
