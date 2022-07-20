using Microsoft.EntityFrameworkCore;
using ParkingManagement.Data;
using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using ParkingManagement.Model.ViewModel;
using ParkingManagement.Utils.Mapper;

namespace ParkingManagement.Service.Implement
{
    public class SlotService : BaseServiceContext, ISlotService
    {
        public SlotService(AppDbContext context) : base(context)
        {
        }

        public async Task<IEnumerable<SlotDTO>> GetAll()
        {
            List<SlotDTO?> slots = await _db.Slots
                .Include(c => c.VehicleType)
                .Select(c => ToDTO.Map(c))
                .ToListAsync();
            return slots;
        }

        public async Task<IEnumerable<SlotDTO>> GetAllByTypeId(int typeId)
        {
            List<SlotDTO?> slots = await _db.Slots
                .Include(c => c.VehicleType)
                .Where(c => c.VehicleTypeId == typeId)
                .Select(c => ToDTO.Map(c))   
                .ToListAsync();
            return slots;
        }

        public async Task<SlotDTO> GetByID(string id)
        {
            SlotDTO? slot = ToDTO.Map(await _db.Slots
                .Include(c => c.VehicleType)
                .FirstOrDefaultAsync(c => c.Id.Equals(id)));
            return slot;
        }

        public async Task<string> SetParkingSlotStatus(string id, bool status)
        {
            try
            {
                Slot? s = await _db.Slots.FirstOrDefaultAsync(c => c.Id.Equals(id));
                if (s == null) throw new Exception("no slot found");

                s.Status = status;

                await _db.SaveChangesAsync();
                return "slot status update susscess! ";
            }
            catch (Exception e)
            {
                return e.Message;
            }
        }

        public IEnumerable<LotRow> toView(List<SlotDTO> slotDTOs)
        {

            SlotDTO temp = new SlotDTO();
            List<LotRow> ListRows = new List<LotRow>(); 

            foreach (SlotDTO slot in slotDTOs)
            {
                if (slot.Position % 2 == 1)
                {
                    temp = slot;
                }
                else
                {
                    LotRow row = new LotRow
                    {
                        area = slot.Area[0],
                        type = slot.VehicleTypeName,
                        cells = new List<LotCell>
                        {
                            new LotCell
                            {
                                isParked = temp.Status,
                                number = temp.Position
                            },
                            new LotCell
                            {
                                isParked = slot.Status,
                                number = slot.Position
                            }
                        }
                    };

                    ListRows.Add(row);
                }
            }

            return ListRows;
        }

        public async Task<Boolean> UpdateSlot(SlotDTO slot)
        {
            string slotId = (slot.Area + slot.Position).Trim();

            Slot? _slot = await _db.Slots.FirstOrDefaultAsync(c => c.Id.Equals(slotId));
            if (_slot == null) return false;

            _slot.Status = slot.Status;

            await _db.SaveChangesAsync();
            return true;
        }
    }
}
