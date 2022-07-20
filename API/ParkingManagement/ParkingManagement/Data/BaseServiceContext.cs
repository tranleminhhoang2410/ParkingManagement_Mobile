namespace ParkingManagement.Data
{
    public abstract class BaseServiceContext
    {
        protected readonly AppDbContext _db;

        public BaseServiceContext(AppDbContext context)
        {
            _db = context;
        }
    }
}
