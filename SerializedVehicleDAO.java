 import java.io.*;
 import java.util.List;
 import java.util.ArrayList;
 import java.nio.file.Files;

public class SerializedVehicleDAO implements VehicleDAO {
    private String filename;
	
    //Constructor
	public SerializedVehicleDAO(String filename) {
		this.filename = filename;
	}
	
	//Serialization
	private void serializeVehicles(List<Vehicle> vehicles) {
		File file = new File(filename);
		 if(file.exists()) {file.delete();}
		
		try {
			FileOutputStream inFile = new FileOutputStream(filename);
			ObjectOutputStream writer = new ObjectOutputStream(inFile);
			
			writer.writeObject(vehicles);
			inFile.close();
			writer.close();
			
		}catch(Exception e) {
			System.err.println("Error during serialization" + e.getMessage());
			System.exit(1);
		}
	}
	
	//Deserialization
	@SuppressWarnings("unchecked")
	private List<Vehicle> deserializeVehicles(){
		File file = new File(filename);
		if(!file.exists()) return new ArrayList();

		List<Vehicle> vehicles = null;
		try {
			FileInputStream fromFile = new FileInputStream(filename);
			ObjectInputStream reader = new ObjectInputStream(fromFile);
			vehicles = (List<Vehicle>) reader.readObject(); 
			
			reader.close();
			fromFile.close();
			
		}catch(Exception e) {
			System.err.println("Error during deserialization:" + e.getMessage());
			System.exit(1);
		}
		return vehicles;
	}

	@Override
	public List<Vehicle> getVehicleList() {
		List<Vehicle> vehicles = deserializeVehicles();
		serializeVehicles(vehicles);
		
		return vehicles.isEmpty() ? new ArrayList(): vehicles; 
	}


	@Override
	public Vehicle getVehicle(int id) {
		List<Vehicle> vehicles = getVehicleList();
		
		return vehicles.stream()
				.filter(vehicle -> vehicle.getId() == id)
				.findAny()
				.orElse(null);
	}

	@Override
	public void saveVehicle(Vehicle v) {
		List<Vehicle> vehicles = deserializeVehicles();
		
		if(getVehicle(v.getId()) != null) {
			throw new IllegalArgumentException("Error: Vehicle already exists." + " (id=" + v.getId() + ")");}
		else
			vehicles.add(v);
		
		serializeVehicles(vehicles);
	}

	@Override
	public void deleteVehicle(int id) {
		List<Vehicle> vehicles = deserializeVehicles();
		
		if(!vehicles.stream().anyMatch(vehicle -> id == vehicle.getId()))
			throw new IllegalArgumentException("Error: Vehicle not found." +  " (id="+ id + ")");
		else
			vehicles.removeIf(vehicle -> id == vehicle.getId());
		
		serializeVehicles(vehicles);
	}
}