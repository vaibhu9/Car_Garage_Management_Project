package Model;

public class VehicleModel {
	private int VId;
	private int CId;
	private String Make;
	private String Model;
	private int Year;
	private String VIN;
	private int Mileage;
	
	public VehicleModel() {
		
	}
	
	public VehicleModel(int CId, String Make, String Model, int Year, String VIN, int Mileage) {
		this.CId=CId;
		this.Make=Make;
		this.Model=Model;
		this.Year=Year;
		this.VIN=VIN;
		this.Mileage=Mileage;
	}

	public int getVId() {
		return VId;
	}

	public void setVId(int vId) {
		VId = vId;
	}

	public int getCId() {
		return CId;
	}

	public void setCId(int cId) {
		CId = cId;
	}

	public String getMake() {
		return Make;
	}

	public void setMake(String make) {
		Make = make;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public int getMileage() {
		return Mileage;
	}

	public void setMileage(int mileage) {
		Mileage = mileage;
	}

}
