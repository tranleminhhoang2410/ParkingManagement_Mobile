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

        [HttpPost("m_AddVehicle")]
        public async Task<ActionResult<VehicleDTO>> AddVehicle2(int userID, string vehicleId, int typeId)
        {
            VehicleDTO vehicle = new VehicleDTO
            {
                Id = vehicleId,
                VehicleTypeId = typeId
            };

            try
            {
                await vehicleService.AddNewUserVehicle(vehicle, userID);
                return Ok(await vehicleService.GetById(vehicleId));
            }
            catch(Exception ex)
            {
                return BadRequest(ex.Message);
            }            
        }

        [HttpGet("Get/UserVehicle/{userId}")]
        public async Task<ActionResult<IEnumerable<VehicleDTO>>> GetUserVehicle(int userId)
        {
            return Ok(await vehicleService.GetAllByUserID(userId));
        }

        [HttpGet("Get/Vehicle/{Id}")]
        public async Task<ActionResult<VehicleDTO>> GetVehicle(String Id)
        {
            return Ok(await vehicleService.GetById(Id));
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

        [HttpPost("CheckIn2")]
        public async Task<ActionResult<InvoiceDTO>> CheckIn2(string vehicleId, string slotId)
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

                return invoiceDTO;
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
                                        + parkingTime[4] * parkingType.PricePerYear + parkingType.PricePerHour;

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

        [HttpPost("CheckOut2")]
        public async Task<ActionResult<InvoiceDTO>> CheckOut2(InvoiceDTO invoiceDTO)
        {
            try
            {
                string note = "";
                note += await invoiceService.UpdateInvoice(invoiceDTO);
                note += await slotService.SetParkingSlotStatus(invoiceDTO.SlotId, false);
                note += await vehicleService.SetVehicleIsParking(invoiceDTO.VehicleId, false);

                return await invoiceService.GetById(invoiceDTO.Id);
            }
            catch (Exception e)
            {
                return BadRequest("ERROR: " + e.Message);
            }
        }

        [HttpGet("GetByVehicle/{vehicleCode}")]
        public async Task<ActionResult<IEnumerable<InvoiceDTO>>> GetInvoiceByVehicle(string vehicleCode)
        {
            try
            {
                IEnumerable<InvoiceDTO> invoices = await invoiceService.GetByVehicleId(vehicleCode);

                foreach(InvoiceDTO invoice in invoices)
                {
                    if (invoice.CheckoutTime == null)
                    {
                        invoice.CheckoutTime = DateTime.Parse(DateTime.MinValue.ToString("MM/dd/yyyy HH:mm:ss"));
                    }
                }

                return Ok(invoices);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpGet("GetInvoice/{id}")]
        public async Task<ActionResult<InvoiceDTO>> GetInvoiceById(int id)
        {
            try
            {
                return Ok(await invoiceService.GetById(id));
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }

        [HttpGet("UserParkingInvoice/{userID}")]
        public async Task<ActionResult<IEnumerable<InvoiceDTO>>> UserParkingInvoice(int userID)
        {
            try
            {
                List<InvoiceDTO> invoices = new List<InvoiceDTO>();

                IEnumerable<VehicleDTO> userVehicleList = await vehicleService.GetAllByUserID(userID);
                foreach(VehicleDTO v in userVehicleList)
                {
                    foreach(InvoiceDTO invoice in await invoiceService.GetByVehicleId(v.Id))
                    {
                        if (invoice.CheckoutTime == null)
                        {
                            invoice.CheckoutTime = DateTime.Parse(DateTime.MinValue.ToString("MM/dd/yyyy HH:mm:ss"));

                            int[] parkingTime = await invoiceService.CalculateparkingTime(invoice.CheckinTime, DateTime.Parse(DateTime.Now.ToString("MM/dd/yyyy HH:mm:ss")));

                            VehicleTypeDTO parkingType = await vehicleTypeService.GetById((await slotService.GetByID(invoice.SlotId)).VehicleTypeId);

                            invoice.TotalPaid = parkingTime[0] * parkingType.PricePerHour
                                                    + parkingTime[1] * parkingType.PricePerDay
                                                    + parkingTime[2] * parkingType.PricePerWeek
                                                    + parkingTime[3] * parkingType.PricePerMonth
                                                    + parkingTime[4] * parkingType.PricePerYear + parkingType.PricePerHour;

                            invoices.Add(invoice);
                        }
                    }
                }

                return Ok(invoices);
            }
            catch (Exception e)
            {
                return BadRequest(e.Message);
            }
        }
    }
}
