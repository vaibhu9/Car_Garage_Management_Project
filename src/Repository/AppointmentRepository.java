package Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Model.AppointmentModel;

public class AppointmentRepository extends DBConfig {
	
	List list=new ArrayList();
	
	private int AId;

	private int generateAppointmentId() {
		try {
			stmt = conn.prepareStatement("select max(AId) from Appointment");
			rs = stmt.executeQuery();
			if (rs.next()) {
				AId = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
		return ++AId;
	}
	
	private int getAppointmentId(int CId, String VIN) {
		try {
			stmt = conn.prepareStatement("select AId from Vehicle where CId=? and VIN=?");
			stmt.setInt(1, CId);
			stmt.setString(2, VIN);
			rs = stmt.executeQuery();
			return (rs.next()) ? rs.getInt(1) : 0;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
	}
	
	public int isCreateAppointment(AppointmentModel aModel) {

		try {
			int AId = this.generateAppointmentId();
			if (AId != 0) {
				stmt = conn.prepareStatement("insert into Appointment (AId,VId,VIN,UId,Appointment_Date)values(?,?,?,?,?)");
				stmt.setInt(1, AId);
				stmt.setInt(2, aModel.getVId());
				stmt.setString(3, aModel.getVIN());
				stmt.setInt(4, aModel.getUId());
				stmt.setDate(5, Date.valueOf(aModel.getAppointment_Date()));
				int value = stmt.executeUpdate();
				return (value > 0) ? 1 : 0;
			}
			else {
				return 0;
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
	}

	public boolean isUpdateAppointment(int aId2, int uId, String date) {
		
		try {
			stmt=conn.prepareStatement("update appointment set UId=?,Appointment_Date=?");
			stmt.setInt(1, uId);
			stmt.setDate(2, Date.valueOf(date));
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
					
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

	public boolean isDeleteAppointmet(int aId2) {
		
		try {
			stmt=conn.prepareStatement("delete from Appointment where AId=?");
			stmt.setInt(1, aId2);
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

	public AppointmentModel getAppointmentDetails(int aId2) {
		
		try {
			stmt=conn.prepareStatement("select *from Appointment where AId=?");
			stmt.setInt(1, aId2);
			rs=stmt.executeQuery();
			if(rs.next()) {
				AppointmentModel aModel=new AppointmentModel();
				aModel.setAId(rs.getInt(1));
				aModel.setVId(rs.getInt(2));
				aModel.setVIN(rs.getString(3));
				aModel.setUId(rs.getInt(4));
				aModel.setAppointment_Date(rs.getDate(5).toString());
				aModel.setStatus(rs.getString(6));
				return aModel;
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}

	public List<AppointmentModel> getAllAppointmentDetails() {
		
		try {
			stmt=conn.prepareStatement("select *from Appointment");
			rs=stmt.executeQuery();
			while(rs.next()) {
				AppointmentModel aModel=new AppointmentModel();
				aModel.setAId(rs.getInt(1));
				aModel.setVId(rs.getInt(2));
				aModel.setVIN(rs.getString(3));
				aModel.setUId(rs.getInt(4));
				aModel.setAppointment_Date(rs.getDate(5).toString());
				aModel.setStatus(rs.getString(6));
				list.add(aModel);
			}
			return (list.size()>0)?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}

	public boolean isUpdateStatus(int aId2) {
		
		try {
			stmt=conn.prepareStatement("update Appointment set Status=? where AId=?");
			stmt.setString(1, "Completed");
			stmt.setInt(2, aId2);
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

}
