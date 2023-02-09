import java.text.DecimalFormat;
import java.io.*;
import java.util.*;

public abstract class Vehicle implements Serializable {
	private String brand;
	private String model;
	private Integer yearBuilt;
	private Double basePrice;
	private Integer id;
	
	Calendar calendar = new GregorianCalendar();

	public Vehicle(String brand, String model, Integer yearBuilt, Double basePrice, Integer id) {
		if(yearBuilt == null || yearBuilt > calendar.get(Calendar.YEAR)) throw new IllegalArgumentException("Error: Year built invalid.");
		if(basePrice <= 0) throw new IllegalArgumentException("Error: Base price invalid.");

		this.brand = brand;
		this.model = model;
		this.yearBuilt = yearBuilt;
		this.basePrice = basePrice;
		this.id = id;
	}
	
	public Integer getAge() {return 2022 - yearBuilt;}
	public String getBrand() {return brand;}
	public String getModel() {return model;}
	public Integer getYearBuilt() {return yearBuilt;}
	public Double getBasePrice() {return basePrice;}
	public Integer getId() {return id;}

	public Double getPrice() {                             
		return basePrice - basePrice *getDiscount();
		}
	
	public static DecimalFormat getDecimalFormat() {
		return new DecimalFormat("0.00");
	}

	public abstract Double getDiscount();
}