using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using ParkingManagement.Authentication;

namespace ParkingManagement.Filter
{
    public class AuthorizationFilter : Attribute, IAuthorizationFilter
    {
        public void OnAuthorization(AuthorizationFilterContext context)
        {
            Console.WriteLine("-----------------------------------------------------------authorization :)))");

            var tokenManager = (ITokenManager)context.HttpContext.RequestServices.GetService(typeof(ITokenManager));

            var result = true;

            if (!context.HttpContext.Request.Headers.ContainsKey("Authorization"))
            {
                result = false;
            }

            string token = string.Empty;
            if (result)
            {
                token = context.HttpContext.Request.Headers.First(x => x.Key == "Authorization").Value;

                try
                {
                    var claimPrinciple = tokenManager.VerifyToken(token);
                }
                catch (Exception ex)
                {
                    result = false;
                    context.ModelState.AddModelError("Authorized Error", ex.ToString());
                }
            }

            if (!result)
            {
                context.Result = new UnauthorizedObjectResult(context.ModelState);
            }
        }
    }
}
