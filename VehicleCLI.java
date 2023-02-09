public class VehicleCLI {

	public static void main(String[] args) {
		
		//<file> = args[0] (the name of the file which creates a new VehicleManagement Object-> VehicleDAO-> SerializedVehicleDAO Object)
		try{
			VehicleManagement vm = new VehicleManagement(new SerializedVehicleDAO(args[0]));
			if(args.length <= 1) throw new IllegalArgumentException ("Error: Invalid parameter.");

			switch(args[1]){
				case "show":
					if(args.length == 3 ){
						if(vm.getVehicle(Integer.valueOf(args[2])) != null)
							System.out.println(vm.getVehicle(Integer.parseInt(args[2])));}
					else{
						for(Vehicle vehicle: vm.getVehicles()){
							System.out.println(vehicle);
						}
					}
						
				break;
				
				case "add":
					if(args[2].equals("car")) {
						if(args.length != 9) throw new IllegalArgumentException("Error: Invalid parameter.");
						vm.addVehicle( new Car(args[4],args[5],Integer.valueOf(args[6]), Double.valueOf(args[7]), Integer.valueOf(args[3]), Integer.valueOf(args[8])));
					}
					else if(args[2].equals("truck")) {
						if(args.length != 8) throw new IllegalArgumentException("Error: Invalid parameter.");
						vm.addVehicle(new Truck(args[4], args[5], Integer.valueOf(args[6]), Double.valueOf(args[7]), Integer.valueOf(args[3])));
					}
					else if(args[2].equals("bus")) {
						if(args.length != 9) throw new IllegalArgumentException("Error: Invalid parameter.");
						vm.addVehicle(new Bus(args[4],args[5],Integer.valueOf(args[6]), Double.valueOf(args[7]), Integer.valueOf(args[3]), Integer.valueOf(args[8])));
					}
				break;

				case "del":
					if(args[2].isEmpty()) 
						throw new IllegalArgumentException("Error: Invalid parameter.");
					vm.deleteVehicle(Integer.parseInt(args[2]));
				break;

				case "count":
				if(args.length == 3){
					if(args[2].equals("car")){
						System.out.println(vm.count(v -> v instanceof Car));
					}else if(args[2].equals("truck")){
						System.out.println(vm.count(v -> v instanceof Truck));
					}else if(args[2].equals("bus")) {
						System.out.println(vm.count(v -> v instanceof Bus));
					}
				}else
					System.out.println(vm.count(v -> v instanceof Vehicle));
				break;

				case "meanprice":
					System.out.println(vm.meanPrice());
				break;

				case "oldest":
					for(var oldest: vm.oldestVehicle()) {
						System.out.println("Id: " + oldest);
					}
				break;
				
				case "pricerange":
					if(args.length < 4) throw new IllegalArgumentException("Error: Invalid parameter.");
					Integer min = Integer.valueOf(args[2]);
					Integer max = Integer.valueOf(args[3]);
					
					System.out.println(vm.getVehiclesInRange(min,max));
					break;

				default: throw new IllegalArgumentException("Error: Invalid parameter.");
			}

		}catch (NumberFormatException e) {
			System.out.println("Error: Invalid parameter.");	
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());	
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
	
}