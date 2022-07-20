using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingManagement.Filter;
using ParkingManagement.Model.DTO;
using ParkingManagement.Model.ViewModel;
using ParkingManagement.Service;
using System.Security.Claims;

namespace ParkingManagement.Controllers
{
    [Route("api/ParkingManagement")]
    [ApiController]
    public class UserPageController : ControllerBase
    {

        private readonly ISlotService slotService;
        private readonly IVehicleTypeService vehicleTypeService;
        private readonly IVehicleService vehicleService;
        private readonly IUserService userService;
        private readonly IInvoiceService invoiceService;

        private readonly IDistrictService districtService;
        private readonly ICityService cityService;
        private readonly IWardService wardService;

        private readonly IHttpContextAccessor httpContextAccessor;

        public UserPageController(ISlotService slotService, IVehicleTypeService vehicleTypeService, IVehicleService vehicleService, 
            IUserService userService, IInvoiceService invoiceService, IDistrictService districtService, ICityService cityService, IWardService wardService,
            IHttpContextAccessor httpContextAccessor)
        {
            this.slotService = slotService;
            this.vehicleTypeService = vehicleTypeService;
            this.vehicleService = vehicleService;
            this.userService = userService;
            this.invoiceService = invoiceService;
            this.districtService = districtService;
            this.cityService = cityService;
            this.wardService = wardService;
            this.httpContextAccessor = httpContextAccessor;
        }

        /// <summary>
        /// HIỆN SƠ ĐỒ CHO ANONYMOUS (K CÓ TRƯỜNG USER)
        /// </summary>
        /// <returns></returns>
        [HttpGet("ParkingLot"), AllowAnonymous]
        public async Task<ActionResult> GetAllParkingLot()
        {
            try
            {
                IEnumerable<SlotDTO> slots = await slotService.GetAll();

                List<SlotDTO> A = slots.Where(c => c.Area == "A").OrderBy(c => c.Position).ToList();
                List<SlotDTO> B = slots.Where(c => c.Area == "B").OrderBy(c => c.Position).ToList();
                List<SlotDTO> C = slots.Where(c => c.Area == "C").OrderBy(c => c.Position).ToList();
                List<SlotDTO> D = slots.Where(c => c.Area == "D").OrderBy(c => c.Position).ToList();
                List<SlotDTO> E = slots.Where(c => c.Area == "E").OrderBy(c => c.Position).ToList();

                List<LotRow> lotRows = new List<LotRow>();

                foreach(LotRow r in slotService.toView(A)) lotRows.Add(r);
                foreach(LotRow r in slotService.toView(B)) lotRows.Add(r);
                foreach(LotRow r in slotService.toView(C)) lotRows.Add(r);
                foreach(LotRow r in slotService.toView(D)) lotRows.Add(r);
                foreach(LotRow r in slotService.toView(E)) lotRows.Add(r);

                return Ok(new
                {
                    Slots = lotRows
                });
            }
            catch (Exception ex)
            {
                return BadRequest(new
                {
                    Error = ex
                });
            }
            
        }

        /// <summary>
        /// HIỆN SƠ ĐỒ CHO USER ĐÃ ĐĂNG NHÂP (CÓ THÊM TRƯỜNG USER ĐỂ HIỂN THỊ THÔNG TIN USER)
        /// </summary>
        /// <returns></returns>
        [AuthorizationFilter]
        [Authorize(Roles = "User")]
        [HttpGet("ParkingLot/User")]
        public async Task<ActionResult> GetAllParkingLot2()
        {
            try
            {
                IEnumerable<SlotDTO> slots = await slotService.GetAll();

                List<SlotDTO> A = slots.Where(c => c.Area == "A").OrderBy(c => c.Position).ToList();
                List<SlotDTO> B = slots.Where(c => c.Area == "B").OrderBy(c => c.Position).ToList();
                List<SlotDTO> C = slots.Where(c => c.Area == "C").OrderBy(c => c.Position).ToList();
                List<SlotDTO> D = slots.Where(c => c.Area == "D").OrderBy(c => c.Position).ToList();
                List<SlotDTO> E = slots.Where(c => c.Area == "E").OrderBy(c => c.Position).ToList();

                List<LotRow> lotRows = new List<LotRow>();

                foreach (LotRow r in slotService.toView(A)) lotRows.Add(r);
                foreach (LotRow r in slotService.toView(B)) lotRows.Add(r);
                foreach (LotRow r in slotService.toView(C)) lotRows.Add(r);
                foreach (LotRow r in slotService.toView(D)) lotRows.Add(r);
                foreach (LotRow r in slotService.toView(E)) lotRows.Add(r);

                int userid = int.Parse(httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier));
                UserDTO user = await userService.GetUserById(userid);

                return Ok(new
                {
                    Slots = lotRows,
                    LoggedUser = user
                });
            }
            catch (Exception ex)
            {
                return BadRequest(new
                {
                    Error = ex
                });
            }

        }


        /// <summary>
        /// HIỆN BẢNG GIÁ CHO ANONYMOUS
        /// </summary>
        /// <returns></returns>
        [HttpGet("PriceTable"), AllowAnonymous]
        public async Task<ActionResult> GetAllVehicleTypes()
        {
            try
            {
                IEnumerable<VehicleTypeDTO> types = await vehicleTypeService.GetAll();

                return Ok(new
                {
                    Types = types
                });
            }
            catch (Exception ex)
            {
                return BadRequest(new
                {
                    Error = ex.Message
                });
            }
        }

        /// <summary>
        /// HIỆN BẢNG GIÁ CHO USER ĐĂNG NHẬP
        /// </summary>
        /// <returns></returns>
        [AuthorizationFilter]
        [Authorize(Roles = "User")]
        [HttpGet("PriceTable/User")]
        public async Task<ActionResult> GetAllVehicleTypes2()
        {
            try
            {
                IEnumerable<VehicleTypeDTO> types = await vehicleTypeService.GetAll();

                int userid = int.Parse(httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier));
                UserDTO user = await userService.GetUserById(userid);

                return Ok(new
                {
                    Types = types,
                    LoggedUser = user
                });
            }
            catch (Exception ex)
            {
                return BadRequest(new
                {
                    Error = ex.Message
                });
            }
        }

        /// <summary>
        /// HIỆN FEEDBACK CHO ANONYMOUS
        /// </summary>
        /// <returns></returns>
        [HttpGet("UserFeedback"), AllowAnonymous]
        public async Task<ActionResult> UserFeedback()
        {
            try
            {
                IEnumerable<UserDTO> users = await userService.GetAll();
                Dictionary<string, string> feedbacks = new Dictionary<string, string>();

                foreach(UserDTO u in users)
                {
                    if(u.Feedback != null && u.Feedback != string.Empty)
                    {
                        feedbacks[u.Name] = u.Feedback;
                    }        
                }

                return Ok(new
                {
                    Feedbacks = feedbacks
                });
            }
            catch (Exception ex)
            {
                return BadRequest(new
                {
                    Error = ex.Message
                });
            }
        }

        /// <summary>
        /// HIỆN FEEDBACK CHO USER ĐĂNG NHẬP
        /// </summary>
        /// <returns></returns>
        [AuthorizationFilter]
        [Authorize(Roles = "User")]
        [HttpGet("UserFeedback/User")]
        public async Task<ActionResult> UserFeedback2()
        {
            try
            {
                IEnumerable<UserDTO> users = await userService.GetAll();
                Dictionary<string, string> feedbacks = new Dictionary<string, string>();

                foreach (UserDTO u in users)
                {
                    if (u.Feedback != null && u.Feedback != string.Empty)
                    {
                        feedbacks[u.Name] = u.Feedback;
                    }
                }


                int userid = int.Parse(httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier));
                UserDTO user = await userService.GetUserById(userid);

                return Ok(new
                {
                    Types = users,
                    LoggedUser = user
                });
            }
            catch (Exception ex)
            {
                return BadRequest(new
                {
                    Error = ex.Message
                });
            }
        }

        /// <summary>
        /// cÁI NÀY SẼ GỌI KHI CLICK VÀO 1 LOT TRỐNG ĐỂ GỬI 1 CÁI API LIST XE CHƯA ĐỖ CỦA USER THEO LOẠI XE CỦA CÁI LOT ĐÓ
        /// </summary>
        /// <param name="SlotId">LOT ĐƯỢC CHỌN (AREA+NUMBER)</param>
        /// <returns></returns>

        [AuthorizationFilter]
        [Authorize(Roles = "User")]
        [HttpGet("CheckIn/{SlotId}")]
        public async Task<ActionResult<IEnumerable<VehicleDTO>>> CheckIn(string SlotId)
        {
            int UserId = int.Parse(httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier));
            IEnumerable<VehicleDTO> vehicleDTOs = await vehicleService.GetAllByUserID(UserId);
            SlotDTO slotDTO = await slotService.GetByID(SlotId);

            List<VehicleDTO> result = new List<VehicleDTO>();

            foreach (VehicleDTO vehicle in vehicleDTOs)
            {
                if (vehicle.IsParking == false && vehicle.VehicleTypeId == slotDTO.VehicleTypeId)
                {
                    result.Add(vehicle);
                }
            }

            return Ok(result);
        }

        /// <summary>
        /// GỌI KHI NGƯỜI DÙNG CLICK VÀO NÚT CHECK IN, NÓ SẼ LẤY TẠO 1 HÓA ĐƠN ĐỂ TRỐNG PHẦN CHECKOUT TIME VÀ TỔNG TIỀN (2 CÁI NÀY THÊM VÀO LÚC CHECK OUT)
        /// </summary>
        /// <param name="vehicleId">CHỌN TỪ DANH SÁCH</param>
        /// <param name="slotId">LOT HIỆN TẠI (AREA+NUMBER)</param>
        /// <returns></returns>
        [AuthorizationFilter]
        [Authorize(Roles = "User")]
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
        /// CÁI NÀY ĐƯỢC GỌI KHI NGƯỜI DÙNG CLICK VÀO LOT ĐÃ ĐẶT, NÓ SẼ TÍNH TỔNG TIỀN THEO GIỜ CHECKIN VÀ GIỜ CHECKOUT
        /// </summary>
        /// <param name="SlotId"> LOT ĐƯỢC CHỌN (AREA+NUMBER)</param>
        /// <returns></returns>

        [AuthorizationFilter]
        [Authorize(Roles = "User")]
        [HttpGet("CheckOut/{SlotId}")]
        public async Task<ActionResult<InvoiceDTO>> CheckOut(string SlotId)
        {
            try
            {
                InvoiceDTO invoiceDTO = await invoiceService.GetIsParkingInvoiceBySlot(SlotId);

                int UserId = int.Parse(httpContextAccessor.HttpContext.User.FindFirstValue(ClaimTypes.NameIdentifier));
                UserDTO user = await userService.GetUserById(UserId);

                if (!user.Vehicles.Contains(await vehicleService.GetById(invoiceDTO.VehicleId))) throw new Exception("It's not yours");

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

        /// <summary>
        /// GỌI CÁI NÀY KHI USER NHẤN VÀO NÚT CHECK OUT, NÓ SẼ CẬP NHẬT LẠI HÓA ĐƠN THEO THÔNG TIN ĐÃ HIỆN
        /// </summary>
        /// <param name="invoiceDTO"> ĐẨY LẠI CÁI DTO Ở HÀM TRÊN VÀO ĐÂY </param>
        /// <returns></returns>
        [AuthorizationFilter]
        [Authorize(Roles = "User")]
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
