using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using ParkingManagement.Service;

namespace ParkingManagement.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AccountController : ControllerBase
    {
        private readonly IAccountService accountService;

        public AccountController(IAccountService accountService)
        {
            this.accountService = accountService;
        }

        [HttpPost("Register")]
        public async Task<ActionResult<string>> Register(string username, string password, string name, string email, string phone)
        {
            AccountDTO accountDTO = new AccountDTO
            {
                Username = username,
                Password = password,
                Role = Role.User.ToString(),
                User = new UserDTO
                {
                    Email = email,
                    Phone = phone,
                    Name = name
                }
            };
            return await accountService.AddAccount(accountDTO);
        }
    }
}
