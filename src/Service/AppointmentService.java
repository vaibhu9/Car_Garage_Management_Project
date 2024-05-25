package Service;

import java.util.List;

import Model.AppointmentModel;
import Model.WorkOrderModel;
import Repository.AppointmentRepository;

public class AppointmentService {
	
	AppointmentRepository aRepo=new AppointmentRepository();
	
	public int isCreateAppointment(AppointmentModel aModel) {

		return aRepo.isCreateAppointment(aModel);
		
	}

	public boolean isUpdateAppointment(int aId, int uId, String date) {
		
		return aRepo.isUpdateAppointment(aId, uId, date);
	}

	public boolean isDeleteAppointmet(int aId) {
		
		return aRepo.isDeleteAppointmet(aId);
	}

	public AppointmentModel getAppointmentDetails(int aId) {
		
		return aRepo.getAppointmentDetails(aId);
	}

	public List<AppointmentModel> getAllAppointmentDetails() {
		
		return aRepo.getAllAppointmentDetails();
	}

	public boolean isUpdateStatus(int aId) {
		
		return aRepo.isUpdateStatus(aId);
	}

}
