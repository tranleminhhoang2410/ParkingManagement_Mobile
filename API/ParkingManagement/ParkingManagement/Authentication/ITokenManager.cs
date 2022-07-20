using ParkingManagement.Authentication.AuthModel;
using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using System.Security.Claims;

namespace ParkingManagement.Authentication
{
    public interface ITokenManager
    {
        string GenerateNewToken(AccountDTO account);
        ClaimsPrincipal VerifyToken(string token);
        public Task AddUserValidTokenStorage(int userId);
        public Task DeleteToken(int userId);
        public Token GetUserValidTokenStorage(int userId);
        public void SaveTokenInClient(string token);
    }
}
