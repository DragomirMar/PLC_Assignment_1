import java.text.DecimalFormat;
import java.util.*;

public class Car extends Vehicle{
	private Integer yearOfInspection;

	public Car(String brand, String model, Integer yearBuild, Double basePrice, Integer id, Integer yearOfInspection) {
		super(brand, model, yearBuild, basePrice, id);
		
		if(yearOfInspection > calendar.get(Calendar.YEAR)) throw new IllegalArgumentException("Error: Inspection year invalid.");
		
		this.yearOfInspection = yearOfInspection;
	}
    public Integer getYearOfInspection() {return yearOfInspection;}

	@Override
	public Double getDiscount() {
		Double discount = 0.0;
		
		for(Integer i = getAge(); i > 0; --i) {
			discount += 0.5;
		}
		
		for(Integer i = 0; i < 2022-yearOfInspection; ++i) {
			discount += 0.2;
		}
		
		if(discount > 0.15)
			discount = 0.15;
		
		return discount;
	}
	
    @Override
	public String toString() {
		DecimalFormat df = Vehicle.getDecimalFormat();
		return "Type:       Car\n" +
				"Id:         " + getId() + "\n"+
				"Brand:      " + getBrand() + "\n" +
				"Model:      " + getModel() + "\n" + 
				"Year:       " +  getYearBuilt() + "\n"+
				"Inspection: " + getYearOfInspection() + "\n" +
				"Base price: " + df.format(getBasePrice()) + "\n" + 
				"Price:      " + df.format(getPrice()) + "\n" ;
	}
}