using ParkingManagement.Model;
using ParkingManagement.Model.DTO;
using System.Collections;

namespace ParkingManagement.Utils.Mapper
{
    public static class ToDTO
    {
        public static AccountDTO? Map(Account account)
        {
            AccountDTO? accountDTO = null;

            if (account != null)
            {
                accountDTO = new AccountDTO
                {
                    Id = account.Id,
                    Password = account.Password,
                    Username = account.Username,
                    Role = account.Role,
                    User = Map(account.User)
                };
            }   
            
            return accountDTO;
        }

        public static UserDTO? Map(User user)
        {
            UserDTO? userDTO = null;

            if(user != null)
            {
                userDTO = new UserDTO
                {
                    Id = user.Id,
                    Email = user.Email,
                    Name = user.Name,
                    Feedback = user.Feedback,
                    Phone = user.Phone,
                    CityId = user.CityId,
                    DistrictId = user.DistrictId,
                    WardId = user.WardId
                };

                foreach (Vehicle vehicle in user.Vehicles)
                {
                    userDTO.Vehicles.Add(Map(vehicle));
                }
            }

            return userDTO;
        }

        public static VehicleDTO? Map(Vehicle vehicle)
        {
            VehicleDTO? vehicleDTO = null;

            if(vehicle != null)
            {
                vehicleDTO = new VehicleDTO
                {
                    Id = vehicle.Id,
                    VehicleBrand = vehicle.VehicleBrand,
                    VehicleName = vehicle.VehicleName,
                    VehicleTypeId = vehicle.VehicleTypeId,
                    VehicleType = Map(vehicle.VehicleType),
                    IsParking = vehicle.IsParking
                };

                foreach (Invoice invoice in vehicle.Invoices)
                {
                    vehicleDTO.Invoices.Add(Map(invoice));
                }
            }
            
            return vehicleDTO;
        }

        public static VehicleTypeDTO? Map(VehicleType vehicleType)
        {
            VehicleTypeDTO? vehicleTypeDTO = null;

            if(vehicleType != null)
            {
                vehicleTypeDTO = new VehicleTypeDTO
                {
                    Id = vehicleType.Id,
                    PricePerHour = vehicleType.PricePerHour,
                    PricePerDay = vehicleType.PricePerDay,
                    PricePerMonth = vehicleType.PricePerMonth,
                    PricePerWeek = vehicleType.PricePerWeek,
                    PricePerYear = vehicleType.PricePerYear,
                    TypeName = vehicleType.TypeName
                };

            }

            return vehicleTypeDTO;
        }

        public static InvoiceDTO? Map(Invoice invoice)
        {
            InvoiceDTO? invoiceDTO = null;

            if(invoice != null)
            {
                invoiceDTO = new InvoiceDTO
                {
                    Id = invoice.Id,
                    CheckinTime = invoice.CheckinTime,
                    CheckoutTime = invoice.CheckoutTime,
                    SlotId = invoice.SlotId,
                    VehicleId = invoice.VehicleId,
                    //Slot = Map(invoice.Slot),
                    TotalPaid = invoice.TotalPaid
                };
            }

            return invoiceDTO;
        }

        public static SlotDTO? Map(Slot slot)
        {
            SlotDTO? slotDTO = null;

            if(slot != null)
            {
                string area = slot.Id.Substring(0, 1);
                string position = slot.Id.Substring(1);

                slotDTO = new SlotDTO
                {
                    Area = area,
                    Position = int.Parse(position),
                    Status = slot.Status,
                    VehicleTypeId = slot.VehicleTypeId,
                    VehicleTypeName = slot.VehicleType.TypeName
                };
            }
            
            return slotDTO;
        }

        public static WardDTO? Map(Ward ward)
        {
            WardDTO? wardDTO = null;

            if(ward != null)
            {
                wardDTO = new WardDTO
                {
                    Id = ward.Id,
                    Name = ward.Name
                };
            }
            
            return wardDTO;
        }

        public static DistrictDTO? Map(District district)
        {
            DistrictDTO? districtDTO = null;
            
            if(district != null)
            {
                districtDTO = new DistrictDTO
                {
                    Id = district.Id,
                    Name = district.Name
                };

                foreach (Ward ward in district.Wards)
                {
                    districtDTO.Wards.Add(Map(ward));
                }
            }
            
            return districtDTO;
        }

        public static CityDTO? Map(City city)
        {
            CityDTO? cityDTO = null;

            if(city != null)
            {
                cityDTO = new CityDTO
                {
                    Id = city.Id,
                    Name = city.Name
                };

                foreach (District district in city.Districts)
                {
                    cityDTO.Districts.Add(Map(district));
                }
            }
            
            return cityDTO;
        }
    }
}
