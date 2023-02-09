import java.text.DecimalFormat;

public class Truck extends Vehicle{
    public Truck(String brand, String model, Integer yearBuild, Double basePrice, Integer id) {
		super(brand, model, yearBuild, basePrice, id);
	}

	@Override
	public Double getDiscount() {
		Double discount = 0.0;
		
		for(Integer i = getAge(); i > 0; --i) {
			discount += 0.5;
		}
		
		if(discount > 0.20)
			discount = 0.20;
		
		return discount;
	}

	@Override
		public String toString() {
			DecimalFormat df = Vehicle.getDecimalFormat();
			return "Type:       Truck\n" +
					"Id:         " + getId() + "\n"+
					"Brand:      " + getBrand() + "\n" +
					"Model:      " + getModel() + "\n" + 
					"Year:       " +  getYearBuilt() + "\n"+
					"Base price: " + df.format(getBasePrice()) + "\n" + 
					"Price:      " + df.format(getPrice()) + "\n" ;
		}
}
