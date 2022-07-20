using Microsoft.EntityFrameworkCore;
using ParkingManagement.Data;
using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using ParkingManagement.Utils.Mapper;

namespace ParkingManagement.Service.Implement
{
    public class InvoiceService : BaseServiceContext, IInvoiceService
    {
        public InvoiceService(AppDbContext context) : base(context)
        {
        }

        public async Task<string> AddNewInvoice(InvoiceDTO invoiceDTO)
        {
            try
            {
                Vehicle? vehicle = await _db.Vehicles.FirstOrDefaultAsync(c => c.Id.Equals(invoiceDTO.VehicleId));
                if (vehicle == null) return "Vehicle not existed";

                Slot? slot = await _db.Slots.FirstOrDefaultAsync(c => c.Id.Equals(invoiceDTO.SlotId));
                if (slot.Status == true) return slot.Id + "Slot is full!";

                Invoice invoice = new Invoice
                {
                    SlotId = invoiceDTO.SlotId,
                    VehicleId = invoiceDTO.VehicleId,
                    CheckinTime = DateTime.Parse(DateTime.Now.ToString("MM/dd/yyyy HH:mm:ss"))
                };

                await _db.Invoices.AddAsync(invoice);
                await _db.SaveChangesAsync();
                return "add invoice success!";
            }
            catch (Exception e)
            {
                return e.Message;
            }
        }

        public async Task<int[]> CalculateparkingTime(DateTime? checkin, DateTime? checkout)
        {
            TimeSpan timeSpan = ((DateTime)checkout).Subtract((DateTime)checkin);

            int[] parkingTime = new int[]
            {
                timeSpan.Hours,
                timeSpan.Days % 365 % 30 % 7,
                timeSpan.Days % 365 % 30 / 7,
                timeSpan.Days % 365 / 30,
                timeSpan.Days / 365           
            };

            return parkingTime;
        }

        public Task<IEnumerable<InvoiceDTO>> GetAll()
        {
            throw new NotImplementedException();
        }

        public Task<InvoiceDTO> GetById(int Id)
        {
            throw new NotImplementedException();
        }

        public Task<IEnumerable<InvoiceDTO>> GetByUserId(int userId)
        {
            throw new NotImplementedException();
        }

        public Task<IEnumerable<InvoiceDTO>> GetByVehicleId(string vehicleId)
        {
            throw new NotImplementedException();
        }

        public async Task<InvoiceDTO> GetIsParkingInvoiceBySlot(string slotId)
        {
            InvoiceDTO? invoice = ToDTO.Map(await _db.Invoices
                .FirstOrDefaultAsync(c => c.SlotId.Equals(slotId) && c.CheckoutTime == null));
            return invoice;
        }

        public async Task<string> UpdateInvoice(InvoiceDTO invoiceDTO)
        {
            try
            {
                Invoice? invoice = await _db.Invoices.FirstOrDefaultAsync(c => c.Id.Equals(invoiceDTO.Id));
                if (invoice == null) return "no invoice";

                invoice.CheckoutTime = invoiceDTO.CheckoutTime;
                invoice.TotalPaid = invoiceDTO.TotalPaid;

                await _db.SaveChangesAsync();
                return "update invoice success";
            }
            catch (Exception e)
            {
                return e.Message;
            }
        }
    }
}
