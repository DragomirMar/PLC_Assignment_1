import java.text.DecimalFormat;
import java.util.*;

public class Bus extends Vehicle {
	private Integer yearOfInspection;
	
	public Bus(String brand, String model, Integer yearBuild, Double basePrice, Integer id, Integer yearOfInspection) {
		super(brand, model,yearBuild, basePrice, id);
		
		if(yearOfInspection > calendar.get(Calendar.YEAR)) throw new IllegalArgumentException("ERROR: Invalid year of inspection.");
		
		this.yearOfInspection = yearOfInspection;
	}
	public Integer getYearOfInspection() {return yearOfInspection;}
	
	@Override
	public Double getDiscount() {
		Double discount = 0.0;
		
		for(Integer i = getAge(); i > 0; --i) {
			discount += 0.3;
		}
		
		for(Integer i = 0; i < 2022-yearOfInspection; ++i) {
			discount += 0.3;
		}
		
		if(discount > 0.25)
			discount = 0.25;
		
		return discount;
	}
	
	
	@Override
	public String toString() {
		DecimalFormat df = Vehicle.getDecimalFormat();
		return "Type:       Bus\n" +
				"Id:         " + getId() + "\n"+
				"Brand:      " + getBrand() + "\n" +
				"Model:      " + getModel() + "\n" + 
				"Year:       " +  getYearBuilt() + "\n"+
				"Inspection: " + getYearOfInspection() + "\n" +
				"Base price: " + df.format(getBasePrice()) + "\n" + 
				"Price:      " + df.format(getPrice()) + "\n" ;
	}
}

