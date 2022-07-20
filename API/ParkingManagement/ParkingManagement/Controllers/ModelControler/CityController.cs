using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ParkingManagement.Data;
using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using ParkingManagement.Service;
using ParkingManagement.Service.Implement;
using ParkingManagement.Utils.Mapper;

namespace ParkingManagement.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CityController : ControllerBase
    {
        private readonly ICityService cityService;

        public CityController(ICityService cityService)
        {
            this.cityService = cityService;
        }


        [HttpGet("GetAll")]
        public async Task<ActionResult<IEnumerable<CityDTO>>> GetCities()
        {
            return Ok(new
            {
                Cities = await cityService.GetAllCities()
            });
        }

        [HttpGet("Get/{Id}")]
        public async Task<ActionResult<CityDTO>> GetCities(int Id)
        {
            CityDTO city = await cityService.GetCityById(Id);
            if (city == null) return BadRequest("not found");
            return Ok(city);
        }
    }
}
