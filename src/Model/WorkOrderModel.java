package Model;

public class WorkOrderModel {
	private int WId;
	private int AId;
	private String Service_Description;
	
	public WorkOrderModel() {
		
	}
	
	public WorkOrderModel(int AId, String Service_Description, String Parts_Used, int Labour_Cost, int Total_Cost) {
		this.AId=AId;
		this.Service_Description=Service_Description;
		this.Parts_Used=Parts_Used;
		this.LabourCost=Labour_Cost;
		this.TotalCost=Total_Cost;
	}

	public int getAId() {
		return AId;
	}

	public void setAId(int aId) {
		AId = aId;
	}

	public int getWId() {
		return WId;
	}

	public void setWId(int wId) {
		WId = wId;
	}

	public String getService_Description() {
		return Service_Description;
	}

	public void setService_Description(String service_Description) {
		Service_Description = service_Description;
	}

	public String getParts_Used() {
		return Parts_Used;
	}

	public void setParts_Used(String parts_Used) {
		Parts_Used = parts_Used;
	}

	public int getLabourCost() {
		return LabourCost;
	}

	public void setLabourCost(int labourCost) {
		LabourCost = labourCost;
	}

	public int getTotalCost() {
		return TotalCost;
	}

	public void setTotalCost(int totalCost) {
		TotalCost = totalCost;
	}

	private String Parts_Used;
	private int LabourCost;
	private int TotalCost;

}
