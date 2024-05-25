package Service;

import java.util.List;

import Model.InventoryModel;
import Model.WorkOrderModel;
import Repository.InventoryRepository;

public class InventoryService {
	
	InventoryRepository iRepo=new InventoryRepository();

	public int isAddPart(InventoryModel iModel) {
		
		return iRepo.isAddPart(iModel);
	}

	public boolean isUpdatePart(InventoryModel iModel1) {
		
		return iRepo.isUpdatePart(iModel1);
	}

	public InventoryModel getSparePartDetails(String partName) {
		
		return iRepo.getSparePartDetails(partName);
	}

	public List<InventoryModel> getAllSparePartDetails() {
		
		return iRepo.getAllSparePartDetails();
	}

	public boolean isDeleteSparePart(String partName) {
		
		return iRepo.isDeleteSparePart(partName);
	}

	public int getPriceOfSparePart(String PartName) {
		
		return iRepo.getPriceOfSparePart(PartName);
	}

}
