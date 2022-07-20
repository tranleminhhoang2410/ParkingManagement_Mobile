using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingManagement.Model.DTO;
using ParkingManagement.Service;

namespace ParkingManagement.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class VehicleController : ControllerBase
    {
        private readonly IVehicleService vehicleService;
        private readonly IInvoiceService invoiceService;
        private readonly ISlotService slotService;
        private readonly IVehicleTypeService vehicleTypeService;

        public VehicleController(IVehicleService vehicleService, IInvoiceService invoiceService, ISlotService slotService, IVehicleTypeService vehicleTypeService)
        {
            this.vehicleService = vehicleService;
            this.invoiceService = invoiceService;
            this.slotService = slotService;
            this.vehicleTypeService = vehicleTypeService;
        }

        [HttpPost("AddVehicle")]
        public async Task<ActionResult<string>> AddVehicle(int userID, string vehicleId, string name, string brand, int typeId)
        {
            VehicleDTO vehicle = new VehicleDTO
            {
                Id = vehicleId,
                VehicleName = name,
                VehicleBrand = brand,
                VehicleTypeId = typeId
            };
            return await vehicleService.AddNewUserVehicle(vehicle, userID);
        }

        [HttpGet("Get/UserVehicle/{userId}")]
        public async Task<ActionResult<IEnumerable<VehicleDTO>>> GetUserVehicle(int userId)
        {
            return Ok(await vehicleService.GetAllByUserID(userId));
        }

        /// <summary>
        /// ---This action call when user clicks a empty slot to check in---
        /// </summary>
        /// <param name="SlotId"></param>
        /// <param name="UserId"></param>
        /// <returns></returns>

        [HttpGet("CheckIn/{SlotId}/{UserId}")]
        public async Task<ActionResult<IEnumerable<VehicleDTO>>> CheckIn(string SlotId, int UserId)
        {
            IEnumerable<VehicleDTO> vehicleDTOs = await vehicleService.GetAllByUserID(UserId);
            SlotDTO slotDTO = await slotService.GetByID(SlotId);

            List<VehicleDTO> result = new List<VehicleDTO>();

            foreach(VehicleDTO vehicle in vehicleDTOs)
            {
                if(vehicle.IsParking == false && vehicle.VehicleTypeId == slotDTO.VehicleTypeId)
                {
                    result.Add(vehicle);
                }
            }

            return Ok(result);
        }

        [HttpPost("CheckIn")]
        public async Task<ActionResult<string>> CheckIn(string vehicleId, string slotId)
        {
            InvoiceDTO invoiceDTO = new InvoiceDTO
            {
                VehicleId = vehicleId,
                SlotId = slotId
            };

            try
            {
                string note = "";
                note += await invoiceService.AddNewInvoice(invoiceDTO);
                note += await slotService.SetParkingSlotStatus(slotId, true);
                note += await vehicleService.SetVehicleIsParking(vehicleId, true);

                return note;
            }
            catch (Exception e)
            {
                return BadRequest("ERROR: " + e.Message);
            }
        }

        /// <summary>
        /// <---This action call when user clicks a parked slot to check out--->
        /// </summary>
        /// <param name="SlotId"></param>
        /// <returns></returns>

        [HttpGet("CheckOut/{SlotId}")]
        public async Task<ActionResult<InvoiceDTO>> CheckOut(string SlotId)
        {
            try
            {
                InvoiceDTO invoiceDTO = await invoiceService.GetIsParkingInvoiceBySlot(SlotId);

                invoiceDTO.CheckoutTime = DateTime.Parse(DateTime.Now.ToString("MM/dd/yyyy HH:mm:ss"));

                int[] parkingTime = await invoiceService.CalculateparkingTime(invoiceDTO.CheckinTime, invoiceDTO.CheckoutTime);

                VehicleTypeDTO parkingType = await vehicleTypeService.GetById((await slotService.GetByID(SlotId)).VehicleTypeId);

                invoiceDTO.TotalPaid = parkingTime[0] * parkingType.PricePerHour
                                        + parkingTime[1] * parkingType.PricePerDay
                                        + parkingTime[2] * parkingType.PricePerWeek
                                        + parkingTime[3] * parkingType.PricePerMonth
                                        + parkingTime[4] * parkingType.PricePerYear;

                return Ok(invoiceDTO);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpPost("CheckOut")]
        public async Task<ActionResult<string>> CheckOut(InvoiceDTO invoiceDTO)
        {
            try
            {
                string note = "";
                note += await invoiceService.UpdateInvoice(invoiceDTO);
                note += await slotService.SetParkingSlotStatus(invoiceDTO.SlotId, false);
                note += await vehicleService.SetVehicleIsParking(invoiceDTO.VehicleId, false);

                return note;
            }
            catch (Exception e)
            {
                return BadRequest("ERROR: " + e.Message);
            }
        }
    }
}
