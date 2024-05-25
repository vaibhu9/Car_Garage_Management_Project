package Model;

public class AppointmentModel {

	private int AId;
	private int VId;
	private String VIN;

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	private int UId;
	private String Appointment_Date;
	private String Status;

	public int getAId() {
		return AId;
	}

	public void setAId(int aId) {
		AId = aId;
	}

	public int getVId() {
		return VId;
	}

	public void setVId(int vId) {
		VId = vId;
	}

	public int getUId() {
		return UId;
	}

	public void setUId(int uId) {
		UId = uId;
	}

	public String getAppointment_Date() {
		return Appointment_Date;
	}

	public void setAppointment_Date(String appointment_Date) {
		Appointment_Date = appointment_Date;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
