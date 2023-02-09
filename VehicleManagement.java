import java.util.List;
import java.text.DecimalFormat;
import java.util.function.Predicate;

public class VehicleManagement {
    private VehicleDAO vehicleDAO;
	
    //Constructor
	public VehicleManagement(VehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}
	
	void addVehicle(Vehicle v) {
		vehicleDAO.saveVehicle(v);
	}
	
	void deleteVehicle(int id) {
		vehicleDAO.deleteVehicle(id);
	}
	
	public Vehicle getVehicle(int id) {
		return vehicleDAO.getVehicle(id);
	}
	
	public List<Vehicle> getVehicles(){
		return vehicleDAO.getVehicleList();
	}
		
	public Integer getNumberOfVehicles() {
		return vehicleDAO.getVehicleList().size();
	}

	public Integer count(Predicate<Vehicle> p) {
		return (int) vehicleDAO.getVehicleList().stream()
				.filter(p)
				.count();
	}
	
	public List<Vehicle> getVehiclesInRange(int min, int max){
		return vehicleDAO.getVehicleList().stream()
		.filter(vehicle -> vehicle.getPrice() >= min && vehicle.getPrice() <= max)
		.toList();
	}
	
	public String meanPrice() {
		DecimalFormat df = Vehicle.getDecimalFormat();
		return df.format(vehicleDAO.getVehicleList().stream().mapToDouble(Vehicle::getPrice).average().getAsDouble());
	}
	
	public List<Integer> oldestVehicle() {
		Integer oldest = vehicleDAO.getVehicleList()
				.stream()
				.mapToInt(vehicle -> ((Vehicle) vehicle).getAge())
				.max()
				.getAsInt();
		
		return vehicleDAO.getVehicleList()
				.stream()
				.filter(vehicle -> ((Vehicle) vehicle).getAge().equals(oldest))
				.map(vehicle -> vehicle.getId())
				.toList();
		}
}
