package Repository;

import java.util.ArrayList;
import java.util.List;

import Model.CustomerModel;
import Model.VehicleModel;

public class VehicleRepository extends DBConfig {
	
	List<VehicleModel> list=new ArrayList<VehicleModel>();
	List<String> list1=new ArrayList();
	
	private int VId;

	private int generateVehicleId() {
		try {
			stmt = conn.prepareStatement("select max(VId) from Vehicle");
			rs = stmt.executeQuery();
			if (rs.next()) {
				VId = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
		return ++VId;
	}
	
	public int getVehicleId(int CId, String VIN) {
		try {
			stmt = conn.prepareStatement("select VId from Vehicle where CId=? and VIN=?");
			stmt.setInt(1, CId);
			stmt.setString(2, VIN);
			rs = stmt.executeQuery();
			return (rs.next()) ? rs.getInt(1) : 0;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
	}
	
	public int isAddVehicle(VehicleModel vModel) {

		try {
			int VId = this.generateVehicleId();
			if (VId != 0) {
				stmt = conn.prepareStatement("insert into Vehicle values(?,?,?,?,?,?,?)");
				stmt.setInt(1, VId);
				stmt.setInt(2, vModel.getCId());
				stmt.setString(3, vModel.getMake());
				stmt.setString(4, vModel.getModel());
				stmt.setInt(5, vModel.getYear());
				stmt.setString(6, vModel.getVIN());
				stmt.setInt(7, vModel.getMileage());
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

	public VehicleModel getVehicleDetails(int vId2) {
		
		try {
			stmt=conn.prepareStatement("select *from Vehicle where VId=?");
			stmt.setInt(1, vId2);
			rs=stmt.executeQuery();
			
				VehicleModel vModel=new VehicleModel();
				vModel.setVId(rs.getInt(1));
				vModel.setCId(rs.getInt(2));
				vModel.setMake(rs.getString(3));
				vModel.setModel(rs.getString(4));
				vModel.setYear(rs.getInt(5));
				vModel.setVIN(rs.getString(6));
				vModel.setMileage(rs.getInt(7));
				
			return vModel;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}
	
	public List<VehicleModel> getAllVehicle(){
		
		try {
			stmt=conn.prepareStatement("select *from Vehicle");
			rs=stmt.executeQuery();
			while(rs.next()) {
				VehicleModel vModel=new VehicleModel();
				vModel.setVId(rs.getInt(1));
				vModel.setCId(rs.getInt(2));
				vModel.setMake(rs.getString(3));
				vModel.setModel(rs.getString(4));
				vModel.setYear(rs.getInt(5));
				vModel.setVIN(rs.getString(6));
				vModel.setMileage(rs.getInt(7));
				list.add(vModel);
			}
			return list.size()>0?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}
	
//	select concat(c.FirstName,' ',c.LastName) as'Customer Name',v.VId as'Vehicle Id',v.make,v.model,v.year,v.vin,v.mileage from customer c inner join vehicle v on v.cid=c.cid;

	public List<String> getCustomerWiseVehicleDetails() {
		list1.clear();
		try {
			stmt=conn.prepareStatement("select concat(c.FirstName,' ',c.LastName) as'Customer Name',v.VId,v.Make,v.Model,v.Year,v.VIN,v.Mileage from Customer c inner join Vehicle v on v.CId=c.CId order by concat(c.FirstName,' ',c.LastName)");
			rs=stmt.executeQuery();
			while(rs.next()) {
				String data=rs.getString("Customer Name")+"\t\t"+rs.getInt("VId")+"\t\t"+rs.getString("Make")+"\t\t\t"+rs.getString("Model")+"\t\t"+rs.getInt("Year")+"\t\t"+rs.getString("VIN")+"\t\t"+rs.getInt("Mileage");
				list1.add(data);
			}
			return (list1.size()>0)?list1:null;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}

	public boolean isUpdateVehicleDetails(String vIN, int cId) {
		
		try {
			stmt=conn.prepareStatement("update vehicle set Cid=? where VIN=?");
			stmt.setInt(1, cId);
			stmt.setString(2, vIN);
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

	public boolean isDeleteVehicleDetails(int vId2) {
		
		try {
			stmt=conn.prepareStatement("delete from vehicle where VId=?");
			stmt.setInt(1, vId2);
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

}
