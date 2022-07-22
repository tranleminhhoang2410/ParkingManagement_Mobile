using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingManagement.Authentication.AuthModel;
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

        [HttpPost("Login")]
        public async Task<ActionResult<AccountDTO>> Login(AccountDTO accountDTO)
        {
            try
            {
                AccountDTO loggeduser = await accountService.GetAccountByUser(accountDTO.Username);
                if (loggeduser != null)
                {
                    if (loggeduser.Password.Equals(AuthenticationHelper.PasswordMD5Hash(accountDTO.Password)))
                    {
                        return loggeduser;
                    }
                    else
                    {
                        throw new Exception("Username or Password is not correct! Try again.");
                    }
                }
                else
                {
                    throw new Exception("Username or Password is not correct! Try again.");
                }
            }
            catch (Exception ex)
            {
                return Unauthorized(new
                {
                    Error = ex.Message
                });
            }
        }

        [HttpGet("FindAccount/{username}")]
        public async Task<ActionResult<AccountDTO>> Login2(string username)
        {
            try
            {
                return await accountService.GetAccountByUser(username);
            }
            catch (Exception ex)
            {
                return Unauthorized(new
                {
                    Error = ex.Message
                });
            }
        }

        [HttpPost("SignUp")]
        public async Task<ActionResult<AccountDTO>> SignUp(string username, string password, string ConfirmPassword)
        {
            try
            {
                if (!ConfirmPassword.Equals(password)) throw new Exception("RePassword must match with Password! Try again.");
                if ((await accountService.GetAccountByUser(username)) != null) throw new Exception("Username existed! Try again");

                AccountDTO accountDTO = new AccountDTO
                {
                    Username = username,
                    Password = AuthenticationHelper.PasswordMD5Hash(password),
                    Role = Role.User.ToString(),
                    User = new UserDTO
                    {
                        Name = "User " + username
                    }
                };
                await accountService.AddAccount(accountDTO);

                return accountDTO;
            }
            catch (Exception ex)
            {
                return BadRequest(new
                {
                    ex.Message
                });
            }
        }
    }
}
