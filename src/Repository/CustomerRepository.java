package Repository;

import java.util.ArrayList;
import java.util.List;

import Model.CustomerModel;
import Model.UserModel;

public class CustomerRepository extends DBConfig {
	
	List<CustomerModel>list=new ArrayList<CustomerModel>();

	private int CId;

	private int generateCustomerId() {
		try {
			stmt = conn.prepareStatement("select max(CId) from Customer");
			rs = stmt.executeQuery();
			if (rs.next()) {
				CId = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
		return ++CId;
	}

	public int getCustomerId(String FirstName, String LastName) {
		try {
			stmt = conn.prepareStatement("select CId from Customer where FirstName=? and LastName=?");
			stmt.setString(1, FirstName);
			stmt.setString(2, LastName);
			rs = stmt.executeQuery();
			return (rs.next()) ? rs.getInt(1) : 0;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
	}

	public int isAddCustomer(CustomerModel cModel) {

		try {
			int CId = this.generateCustomerId();
			if (CId != 0) {
				stmt = conn.prepareStatement("insert into Customer values(?,?,?,?,?,?)");
				stmt.setInt(1, CId);
				stmt.setString(2, cModel.getFirstName());
				stmt.setString(3, cModel.getLastName());
				stmt.setString(4, cModel.getEmail());
				stmt.setString(5, cModel.getContact());
				stmt.setString(6, cModel.getAddress());
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

	public CustomerModel getCustomerDetails(int CId) {
		try {
			stmt=conn.prepareStatement("select CId,FirstName,LastName,Email,Contact,Address from Customer where CId=?");
			stmt.setInt(1, CId);
			rs=stmt.executeQuery();
			if(rs.next()) {
				CustomerModel cModel=new CustomerModel();
				cModel.setCId(rs.getInt(1));
				cModel.setFirstName(rs.getString(2));
				cModel.setLastName(rs.getString(3));
				cModel.setEmail(rs.getString(4));
				cModel.setContact(rs.getString(5));
				cModel.setAddress(rs.getString(6));
				
				return cModel;
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

	public CustomerModel getUserDetails(String firstName, String lastName) {
		try {
			stmt=conn.prepareStatement("select CId,FirstName,LastName,Email,Contact,Address from Customer where FirstName=? and LastName=?");
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			rs=stmt.executeQuery();
			if(rs.next()) {
				CustomerModel cModel=new CustomerModel();
				cModel.setCId(rs.getInt(1));
				cModel.setFirstName(rs.getString(2));
				cModel.setLastName(rs.getString(3));
				cModel.setEmail(rs.getString(4));
				cModel.setContact(rs.getString(5));
				cModel.setAddress(rs.getString(6));
				
				return cModel;
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

	public List<CustomerModel> getAllCustomer() {
		
		try {
			stmt=conn.prepareStatement("select *from Customer");
			rs=stmt.executeQuery();
			while(rs.next()) {
				CustomerModel cModel=new CustomerModel();
				cModel.setCId(rs.getInt(1));
				cModel.setFirstName(rs.getString(2));
				cModel.setLastName(rs.getString(3));
				cModel.setEmail(rs.getString(4));
				cModel.setContact(rs.getString(5));
				cModel.setAddress(rs.getString(6));
				list.add(cModel);
			}
			return list.size()>0?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}

	public boolean isUpdateCustomer(CustomerModel cModel1) {
		
		try {
			stmt=conn.prepareStatement("update customer set email=?,contact=?,address=? where cid=?");
			stmt.setString(1, cModel1.getEmail());
			stmt.setString(2, cModel1.getContact());
			stmt.setString(3, cModel1.getAddress());
			stmt.setInt(4, cModel1.getCId());
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

	public boolean isDeleteCustomerDetails(int cId2) {
		
		try {
			stmt=conn.prepareStatement("delete from customer whete cid=?");
			stmt.setInt(1, cId2);
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

}
