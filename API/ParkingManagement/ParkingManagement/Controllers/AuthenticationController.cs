using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingManagement.Authentication;
using ParkingManagement.Authentication.AuthModel;
using ParkingManagement.Filter;
using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using ParkingManagement.Service;
using System.Security.Claims;

namespace ParkingManagement.Controllers
{
    [Route("api/ParkingManagement")]
    [ApiController]
    public class AuthenticationController : ControllerBase
    {
        private readonly IAccountService accountService;
        private readonly ITokenManager tokenManager;
        private readonly IHttpContextAccessor httpContextAccessor;

        public AuthenticationController(IAccountService accountService, ITokenManager tokenManager, IHttpContextAccessor httpContextAccessor)
        {
            this.accountService = accountService;
            this.tokenManager = tokenManager;
            this.httpContextAccessor = httpContextAccessor;
        }


        /// <summary>
        /// LOGIN FUNCTION - FOR ALL
        /// </summary>
        /// <param name="username"></param>
        /// <param name="password"></param>
        /// <returns></returns>
        [HttpPost("Login")]
        public async Task<IActionResult> Login(string username, string password)
        {
            try
            {
                AccountDTO loggeduser = await accountService.GetAccountByUser(username);
                if (loggeduser != null)
                {
                    if (loggeduser.Password.Equals(AuthenticationHelper.PasswordMD5Hash(password)))
                    {
                        if (tokenManager.GetUserValidTokenStorage(loggeduser.User.Id) == null)
                        {
                            await tokenManager.AddUserValidTokenStorage(loggeduser.User.Id);
                        }

                        string token = tokenManager.GenerateNewToken(loggeduser);
                        SetTokenCookie(token, DateTime.Now.AddHours(2));

                        return Ok(new
                        {
                            Token = token
                        });
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
            catch(Exception ex)
            {
                return Unauthorized(new
                {
                    Error = ex.Message
                });
            }
        }


        /// <summary>
        /// ADD NEW USER
        /// </summary>
        /// <param name="username"></param>
        /// <param name="password"></param>
        /// <param name="ConfirmPassword"></param>
        /// <returns></returns>
        [HttpPost("SignUp")]
        public async Task<IActionResult> SignUp(string username, string password, string ConfirmPassword)
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
                        Name = "User" + username
                    }
                };
                await accountService.AddAccount(accountDTO);

                return Ok(new
                {
                    Success = "User register sucessful"
                });
            }
            catch (Exception ex)
            {
                return BadRequest(new
                {
                    Fail = ex.Message
                });
            }
        }

        /// <summary>
        /// CHANGE PASSWORD - MUST AUTHENTICATE
        /// </summary>
        /// <param name="user"></param>
        /// <param name="password"></param>
        /// <param name="newPassword"></param>
        /// <returns></returns>
        [AuthorizationFilter]
        [Authorize]
        [HttpPost("ChangePassword")]
        public IActionResult ChangePassword(string user, string password, string newPassword)
        {
            return Ok();
        }

        /// <summary>
        /// lOGOUT - MUST AUTHENTICATE
        /// </summary>
        /// <returns></returns>
        [AuthorizationFilter]
        [Authorize]
        [HttpDelete("Logout")]
        public async Task<IActionResult> Logout()
        {
            try
            {
                int userid = int.Parse(httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier));
                await tokenManager.DeleteToken(userid);
                SetTokenCookie(string.Empty, DateTime.Now.AddDays(-1));

                return Ok(new
                {
                    Success = "logout success"
                });
            }
            catch (Exception ex)
            {
                return BadRequest(new
                {
                    Fail = ex.Message
                });
            }
        }

        private void SetTokenCookie(string Token, DateTime Expires)
        {
            var CookieOption = new CookieOptions
            {
                HttpOnly = true,
                Expires = Expires
            };
            Response.Cookies.Append("accessToken", Token, CookieOption);
        }
    }
}
