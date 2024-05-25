package Repository;

import java.util.ArrayList;
import java.util.List;

import Model.RoleModel;

public class RoleRepository extends DBConfig {
	
	List<RoleModel> list = new ArrayList<RoleModel>();
	
	private int RId;
	
	public int generateRoleId() {
		
		try {
			stmt=conn.prepareStatement("select max(RId) from Role");
			rs=stmt.executeQuery();
			if(rs.next()) {
				RId=rs.getInt(1);
			}
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return 0;
		}
		return ++RId;
		
	}
	
	public int getRoleId(String Role) {
		
		try {
			stmt=conn.prepareStatement("select RId from Role where Role=?");
			stmt.setString(1, Role);
			rs=stmt.executeQuery();
			return (rs.next())?rs.getInt(1):0;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return 0;
		}
		
	}
	
	public boolean isRolePresent(RoleModel rModel) {
		
		try {
			stmt=conn.prepareStatement("select *from Role where Role=?");
			stmt.setString(1, rModel.getRole());
			rs=stmt.executeQuery();
			return (rs.next())?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
		
	}

	public boolean isAddRole(RoleModel rModel) {

		try {
			int RId=this.generateRoleId();
			stmt=conn.prepareStatement("insert into Role values(?,?)");
			stmt.setInt(1, RId);
			stmt.setString(2, rModel.getRole());
			int value=stmt.executeUpdate();
			if(value>0) {
				return true;
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

	public List<RoleModel> getAllRole() {
		
		try {
			stmt=conn.prepareStatement("select RId,Role from Role");
			rs=stmt.executeQuery();
			while(rs.next()) {
				RoleModel rModel=new RoleModel();
				rModel.setRId(rs.getInt(1));
				rModel.setRole(rs.getString(2));
				list.add(rModel);
			}
			return list.size()>0?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}

	public boolean isRoleDelete(String role) {
		
		try {
			stmt=conn.prepareStatement("Delete from Role where role=?");
			stmt.setString(1, role);
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

}
