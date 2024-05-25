package Repository;

import java.util.ArrayList;
import java.util.List;

import Model.InventoryModel;
import Model.UserModel;
import Model.WorkOrderModel;

public class InventoryRepository extends DBConfig {
	
	List list=new ArrayList();
	
	private int PId;
	
	private int generatePartId() {
		try {
			stmt = conn.prepareStatement("select max(PId) from Inventory");
			rs = stmt.executeQuery();
			if (rs.next()) {
				PId = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
		return ++PId;
	}

	public int isAddPart(InventoryModel iModel) {
		
		try {
			stmt=conn.prepareStatement("select *from Inventory where Part_Name=?");
			stmt.setString(1, iModel.getPartName());
			rs=stmt.executeQuery();
			if(!(rs.next())) {
				int PId=this.generatePartId();
				stmt=conn.prepareStatement("insert into inventory values(?,?,?,?);");
				stmt.setInt(1, PId);
				stmt.setString(2, iModel.getPartName());
				stmt.setInt(3, iModel.getPrice());
				stmt.setInt(4, iModel.getQuantity());
				int value=stmt.executeUpdate();
				return (value>0)?1:0;
			}
			else {
				return -1;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return 0;
		}
	}

	public boolean isUpdatePart(InventoryModel iModel1) {
		
		try {
			stmt=conn.prepareStatement("update Inventory set Price=?, Quantity=? where Part_Name=?");
			stmt.setInt(1, iModel1.getPrice());
			stmt.setInt(2, iModel1.getQuantity());
			stmt.setString(3, iModel1.getPartName());
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

	public InventoryModel getSparePartDetails(String partName) {
		
		try {
			stmt=conn.prepareStatement("select *from Inventory where Part_Name=?");
			stmt.setString(1, partName);
			rs=stmt.executeQuery();
			if(rs.next()) {
				InventoryModel iModel=new InventoryModel();
				iModel.setPId(rs.getInt(1));
				iModel.setPartName(rs.getString(2));
				iModel.setPrice(rs.getInt(3));
				iModel.setQuantity(rs.getInt(4));
				return iModel;
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

	public List<InventoryModel> getAllSparePartDetails() {
		
		try {
			stmt=conn.prepareStatement("select *from Inventory");
			rs=stmt.executeQuery();
			while(rs.next()) {
				InventoryModel iModel=new InventoryModel();
				iModel.setPId(rs.getInt(1));
				iModel.setPartName(rs.getString(2));
				iModel.setPrice(rs.getInt(3));
				iModel.setQuantity(rs.getInt(4));
				list.add(iModel);
			}
			return list.size()>0?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}

	public boolean isDeleteSparePart(String partName) {
		
		try {
			stmt=conn.prepareStatement("delete from inventory where Part_Name=?");
			stmt.setString(1, partName);
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

	public int getPriceOfSparePart(String partName) {
		
		try {
			stmt=conn.prepareStatement("select Price from inventory where Part_Name=?");
			stmt.setString(1, partName);
			rs=stmt.executeQuery();
			return (rs.next())?rs.getInt(1):0;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return 0;
		}
	}

}
