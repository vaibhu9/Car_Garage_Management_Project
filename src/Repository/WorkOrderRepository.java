package Repository;

import java.util.ArrayList;
import java.util.List;

import Model.WorkOrderModel;

public class WorkOrderRepository extends DBConfig {
	
	List<WorkOrderModel> list=new ArrayList();
	List<String> list1=new ArrayList();
	
	private int WId;

	private int generateWorkOrderId() {
		try {
			stmt = conn.prepareStatement("select max(WId) from WorkOrder");
			rs = stmt.executeQuery();
			if (rs.next()) {
				WId = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
		return ++WId;
	}

	public boolean isCreateWorkOrder(WorkOrderModel wModel) {
		
		try {
			stmt=conn.prepareStatement("select *from WorkOrder where AId=?");
			stmt.setInt(1, wModel.getAId());
			rs=stmt.executeQuery();
			if(!(rs.next())) {
				int WId=this.generateWorkOrderId();
				stmt=conn.prepareStatement("insert into WorkOrder values(?,?,?,?,?,?)");
				stmt.setInt(1, WId);
				stmt.setInt(2, wModel.getAId());
				stmt.setString(3, wModel.getService_Description());
				stmt.setString(4, wModel.getParts_Used());
				stmt.setInt(5, wModel.getLabourCost());
				stmt.setInt(6, wModel.getTotalCost());
				int value=stmt.executeUpdate();
				return (value>0)?true:false;
			}
			else {
				return false;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

	public WorkOrderModel getBill(int aId) {
		
		try {
			stmt=conn.prepareStatement("select *from WorkOrder where AId=?");
			stmt.setInt(1, aId);
			rs=stmt.executeQuery();
			if(rs.next()) {
				WorkOrderModel wModel=new WorkOrderModel();
				wModel.setWId(rs.getInt(1));
				wModel.setAId(rs.getInt(2));
				wModel.setService_Description(rs.getString(3));
				wModel.setParts_Used(rs.getString(4));
				wModel.setLabourCost(rs.getInt(5));
				wModel.setTotalCost(rs.getInt(6));
				return wModel;
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

	public List<WorkOrderModel> getAllBills() {
		
		try {
			stmt=conn.prepareStatement("select *from WorkOrder");
			rs=stmt.executeQuery();
			while(rs.next()) {
				WorkOrderModel wModel=new WorkOrderModel();
				wModel.setWId(rs.getInt(1));
				wModel.setAId(rs.getInt(2));
				wModel.setService_Description(rs.getString(3));
				wModel.setParts_Used(rs.getString(4));
				wModel.setLabourCost(rs.getInt(5));
				wModel.setTotalCost(rs.getInt(6));
				list.add(wModel);
			}
			return (list.size()>0)?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}
	
//	select a.Appointment_Date,a.AId as'Appointment Id',a.VId as'Vehicle Id',c.CId as'Customer Id',c.FirstName,c.LastName,w.Labour_Cost,w.Total_Cost from Appointment a inner join Workorder w on a.AId=w.AId inner join Vehicle v on v.VId=a.VId inner join Customer c on c.CId=v.CId;

	public List<String> getAllCustomerBillDateWise() {
		list1.clear();
		try {
			stmt=conn.prepareStatement("select a.Appointment_Date,a.AId,a.VId,c.CId,c.FirstName,c.LastName,w.Labour_Cost,w.Total_Cost from Appointment a inner join Workorder w on a.AId=w.AId inner join Vehicle v on v.VId=a.VId inner join Customer c on c.CId=v.CId order by a.Appointment_Date");
			rs=stmt.executeQuery();
			while(rs.next()) {
				String data=rs.getDate("a.Appointment_Date").toString()+"\t\t"+rs.getInt("a.AId")+"\t\t"+rs.getInt("a.VId")+"\t\t"+rs.getInt("c.CId")+"\t\t"+rs.getString("c.FirstName")+"\t\t"+rs.getString("c.LastName")+"\t\t"+rs.getInt("w.Labour_Cost")+"\t\t"+rs.getInt("w.Total_Cost");
				list1.add(data);
			}
			return (list1.size()>0)?list1:null;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}
		
	public boolean isDeleteBill(int wId2) {
		
		try {
			stmt=conn.prepareStatement("delete from WorkOrder where WId=?");
			stmt.setInt(1, wId2);
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

}
