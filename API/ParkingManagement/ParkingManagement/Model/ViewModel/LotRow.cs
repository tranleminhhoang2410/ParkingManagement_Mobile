namespace ParkingManagement.Model.ViewModel
{
    public class LotRow
    {
        public string type { get; set; }
        public char area { get; set; }
        public IEnumerable<LotCell> cells { get; set; } = new HashSet<LotCell>();
    }
}
