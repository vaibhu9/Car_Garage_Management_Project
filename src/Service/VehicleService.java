package Service;

import java.util.List;

import Model.VehicleModel;
import Repository.VehicleRepository;

public class VehicleService {
	
	VehicleRepository vRepo=new VehicleRepository();

	public int isAddVehicle(VehicleModel vModel) {

		return vRepo.isAddVehicle(vModel);
		
	}
	
	public int getVehicleId(int CId, String VIN) {
		return vRepo.getVehicleId(CId, VIN);
	}

	public VehicleModel getVehicleDetails(int vId) {
		
		return vRepo.getVehicleDetails(vId);
	}

	public List<VehicleModel> getAllVehicle() {
		
		return vRepo.getAllVehicle();
	}

	public boolean isUpdateVehicleDetails(String vIN, int cId) {
		
		return vRepo.isUpdateVehicleDetails(vIN,cId);
	}

	public boolean isDeleteVehicleDetails(int vId) {
		
		return vRepo.isDeleteVehicleDetails(vId);
	}

	public List<String> getCustomerWiseVehicleDetails() {
		
		return vRepo.getCustomerWiseVehicleDetails();
	}

}
