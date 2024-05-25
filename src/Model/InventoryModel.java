package Model;

public class InventoryModel {
	private int PId;
	private String PartName;
	private int Quantity;
	private int Price;
	
	public InventoryModel(){
		
	}
	
	public InventoryModel(String PartName, int Price, int Quantity) {
		this.PartName=PartName;
		this.Price=Price;
		this.Quantity=Quantity;
	}

	public int getPId() {
		return PId;
	}

	public void setPId(int pId) {
		PId = pId;
	}

	public String getPartName() {
		return PartName;
	}

	public void setPartName(String partName) {
		PartName = partName;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

}
