using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingManagement.Model.DTO;
using ParkingManagement.Service;
using System.Security.Claims;

namespace ParkingManagement.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IUserService userService;
        private readonly IHttpContextAccessor httpContextAccessor;

        public UserController(IUserService userService, IHttpContextAccessor httpContextAccessor)
        {
            this.userService = userService;
            this.httpContextAccessor = httpContextAccessor;
        }

        [HttpGet("Get/Id/{Id}")]
        public async Task<ActionResult<UserDTO>> GetById(int Id)
        {
            UserDTO user = await userService.GetUserById(Id);
            if (user == null) return BadRequest("not found");
            return Ok(user);
        }

        [HttpGet("GetLoggedUser")]
        public async Task<ActionResult<UserDTO>> GetLoggedUser()
        {
            int userid = int.Parse(httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier));
            UserDTO user = await userService.GetUserById(userid);
            return Ok(user);
        }

        [HttpGet("Get/Email/{Email}")]
        public async Task<ActionResult<UserDTO>> GetByEmail(string Email)
        {
            UserDTO user = await userService.GetUserByEmail(Email);
            if (user == null) return BadRequest("not found");
            return Ok(user);
        }

        [HttpGet("Get/Phone/{Phone}")]
        public async Task<ActionResult<UserDTO>> GetByPhone(string Phone)
        {
            UserDTO user = await userService.GetUserByPhone(Phone);
            if (user == null) return BadRequest("not found");
            return Ok(user);
        }

        [HttpPut("Update")]
        public async Task<ActionResult<UserDTO>> Update(UserDTO userDTO)
        {
            Boolean updated = await userService.UpdateUser(userDTO);
            if (updated)
            {
                UserDTO user = await userService.GetUserById(userDTO.Id);
                return Ok(user);
            }
            else
            {
                return BadRequest("update fail");
            }

        }
    }
}
