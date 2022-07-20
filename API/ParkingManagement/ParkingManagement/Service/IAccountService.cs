using ParkingManagement.Model.DTO;

namespace ParkingManagement.Service
{
    public interface IAccountService
    {
        public Task<AccountDTO> GetAccount(string username, string password);
        public Task<AccountDTO> GetAccountByUser(string username);
        public Task<string> AddAccount(AccountDTO accountDTO);
        public Task<string> UpdateAccount(AccountDTO accountDTO);
    }
}
