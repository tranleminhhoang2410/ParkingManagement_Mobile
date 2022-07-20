using Microsoft.IdentityModel.Tokens;
using ParkingManagement.Authentication.AuthModel;
using ParkingManagement.Data;
using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace ParkingManagement.Authentication
{
    public class TokenManager : BaseServiceContext, ITokenManager
    {
        private JwtSecurityTokenHandler tokenHandler;
        private byte[] secretKey;

        public TokenManager(AppDbContext context) : base(context)
        {
            tokenHandler = new JwtSecurityTokenHandler();
            secretKey = Encoding.ASCII.GetBytes("ThisIsTheSecretKey:917364");
        }

        public async Task AddUserValidTokenStorage(int userId)
        {
            Token token = new Token
            {
                UserId = userId
            };

            _db.ValidTokens.AddAsync(token);
            await _db.SaveChangesAsync();
        }

        public async Task DeleteToken(int userId)
        {
            Token UserToken = _db.ValidTokens.FirstOrDefault(t => t.UserId == userId);
            UserToken.Value = null;

            _db.SaveChanges();
        }

        public string GenerateNewToken(AccountDTO account)
        {
            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(new Claim[]
                {
                    new Claim(ClaimTypes.NameIdentifier, account.User.Id.ToString()),
                    new Claim(ClaimTypes.Role, account.Role)
                }),
                Expires = DateTime.UtcNow.AddHours(3),
                SigningCredentials = new SigningCredentials(
                    new SymmetricSecurityKey(secretKey), SecurityAlgorithms.HmacSha256Signature)
            };

            var token = tokenHandler.CreateToken(tokenDescriptor);

            var jwtTokenString = tokenHandler.WriteToken(token);

            Token UserToken = _db.ValidTokens.FirstOrDefault(t => t.UserId == account.User.Id);
            UserToken.Value = "bearer " + jwtTokenString;

            _db.SaveChanges();

            return jwtTokenString;
        }

        public Token GetUserValidTokenStorage(int userId)
        {
            return _db.ValidTokens.FirstOrDefault(c => c.UserId == userId);
        }

        public void SaveTokenInClient(string token)
        {
          
        }

        public ClaimsPrincipal VerifyToken(string token)
        {
            string[] tokenPart = token.Split(' ');

            Token checkToken = _db.ValidTokens.FirstOrDefault(t => t.Value == token);

            if (checkToken != null)
            {
                var claims = tokenHandler.ValidateToken(tokenPart[1],
                new TokenValidationParameters
                {
                    ValidateIssuerSigningKey = true,
                    IssuerSigningKey = new SymmetricSecurityKey(secretKey),
                    ValidateLifetime = true,
                    ValidateAudience = false,
                    ValidateIssuer = false,
                    ClockSkew = TimeSpan.Zero
                }, out SecurityToken validateJwtToken);
                return claims;
            }
            else
            {
                throw new Exception();
            }
        }


    }
}
