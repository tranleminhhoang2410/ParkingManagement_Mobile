using Microsoft.EntityFrameworkCore;
using ParkingManagement.Data;
using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using ParkingManagement.Utils.Mapper;

namespace ParkingManagement.Service.Implement
{
    public class UserService : BaseServiceContext, IUserService
    {
        public UserService(AppDbContext context) : base(context)
        {
        }

        public Task<bool> AddUser(UserDTO userDTO)
        {
            throw new NotImplementedException();
        }

        public async Task<IEnumerable<UserDTO>> GetAll()
        {
            List<UserDTO?> users = await _db.Users
                .Select(c => ToDTO.Map(c))
                .ToListAsync();
            return users;
        }

        public async Task<UserDTO> GetUserByEmail(string email)
        {
            UserDTO? user = ToDTO.Map(await _db.Users
                .Include(c => c.Vehicles).ThenInclude(c => c.Invoices)
                .Include(c => c.City)
                .Include(c => c.District)
                .Include(c => c.Ward)
                .FirstOrDefaultAsync(c => c.Email.Equals(email)));
            return user;
        }

        public async Task<UserDTO> GetUserById(int Id)
        {
            UserDTO? user = ToDTO.Map(await _db.Users
                .Include(c => c.Vehicles).ThenInclude(c => c.Invoices)
                .Include(c => c.City)
                .Include(c => c.District)
                .Include(c => c.Ward)
                .FirstOrDefaultAsync(c => c.Id.Equals(Id)));
            return user;
        }

        public async Task<UserDTO> GetUserByPhone(string phone)
        {
            UserDTO? user = ToDTO.Map(await _db.Users
                .Include(c => c.Vehicles).ThenInclude(c => c.Invoices)
                .Include(c => c.City)
                .Include(c => c.District)
                .Include(c => c.Ward)
                .FirstOrDefaultAsync(c => c.Phone.Equals(phone)));
            return user;
        }

        public async Task<bool> UpdateUser(UserDTO userDTO)
        {
            User? _user = await _db.Users.FirstOrDefaultAsync(c => c.Id.Equals(userDTO.Id));
            if (_user == null) return false;

            _user.Phone = userDTO.Phone;
            _user.CityId = userDTO.CityId;
            _user.DistrictId = userDTO.DistrictId;
            _user.WardId = userDTO.WardId;

            await _db.SaveChangesAsync();
            return true;
        }
    }
}
