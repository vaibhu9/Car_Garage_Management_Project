package Service;

import java.util.List;

import Model.WorkOrderModel;
import Repository.WorkOrderRepository;

public class WorkOrderService {
	AppointmentService aService=new AppointmentService();
	WorkOrderRepository wRepo=new WorkOrderRepository();

	public boolean isCreateWorkOrder(WorkOrderModel wModel) {
		
		return (wRepo.isCreateWorkOrder(wModel))?aService.isUpdateStatus(wModel.getAId()):false;
	}

	public WorkOrderModel getBill(int aId) {
		
		return wRepo.getBill(aId);
	}

	public List<WorkOrderModel> getAllBills() {
		
		return wRepo.getAllBills();
	}

	public boolean isDeleteBill(int wId) {
		
		return wRepo.isDeleteBill(wId);
	}

	public List<String> getAllCustomerBillDateWise() {
		
		return wRepo.getAllCustomerBillDateWise();
	}

}
