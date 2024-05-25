package Repository;

import java.util.ArrayList;
import java.util.List;

import Model.UserModel;

public class UserRepository extends DBConfig {
	
	List<UserModel> list = new ArrayList<UserModel>();

	private int UId;

	private int generateUserId() {
		try {
			stmt = conn.prepareStatement("select max(UId) from User");
			rs = stmt.executeQuery();
			if (rs.next()) {
				UId = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
		return ++UId;
	}
	
	private int getUserId(String username) {
		try {
			stmt = conn.prepareStatement("select UId from User where username=?");
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			return (rs.next())?rs.getInt(1):0;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
	}
	
	public int getUserId(String Username, String Password) {
		try {
			stmt = conn.prepareStatement("select UId from User where Username=? and Password=?");
			stmt.setString(1, Username);
			stmt.setString(2, Password);
			rs = stmt.executeQuery();
			return (rs.next())?rs.getInt(1):0;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
	}

	public boolean isUserPresent(String username) {
		try {
			stmt = conn.prepareStatement("select *from User where Username=?");
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			return (rs.next()) ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

	public boolean verifyUser(String username, String password) {
		try {
			stmt=conn.prepareStatement("select *from User where Username=? and Password=?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs=stmt.executeQuery();
			return (rs.next())?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}

	}

	public int isAddUser(UserModel uModel,int RId) {

		try {
			boolean b = this.isUserPresent(uModel.getUsername());
			if(!b) {
				int UId = this.generateUserId();
				if (UId != 0) {
					stmt=conn.prepareStatement("insert into User values(?,?,?,?,?,?)");
					stmt.setInt(1, UId);
					stmt.setString(2, uModel.getFirstName());
					stmt.setString(3, uModel.getLastName());
					stmt.setString(4, uModel.getUsername());
					stmt.setString(5, uModel.getPassword());
					stmt.setString(6, uModel.getEmail());
					int value=stmt.executeUpdate();
					if(value>0) {
						stmt=conn.prepareStatement("insert into UserRoleJoin values(?,?)");
						stmt.setInt(1, UId);
						stmt.setInt(2, RId);
						value=stmt.executeUpdate();
						return (value>0)?1:0;
					}
					else {
						return 0;
					}
					
				}
				else {
					return 0;
				}
			}
			else {
				return -1;
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
	}
	
	public List<UserModel> getAllUser(){
		try {
			stmt=conn.prepareStatement("select *from User where UId not IN(select UId from Appointment where status=?)");
			stmt.setString(1, "Pending");
			rs=stmt.executeQuery();
			while(rs.next()) {
				UserModel uModel=new UserModel();
				uModel.setUId(rs.getInt(1));
				uModel.setFirstName(rs.getString(2));
				uModel.setLastName(rs.getString(3));
				uModel.setUsername(rs.getString(4));
				uModel.setPassword(rs.getString(5));
				uModel.setEmail(rs.getString(6));
				list.add(uModel);
			}
			return list.size()>0?list:null;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}

	public UserModel getUserDetails(int UId) {
		try {
			stmt=conn.prepareStatement("select UId,FirstName,LastName,Email from User where UId=?");
			stmt.setInt(1, UId);
			rs=stmt.executeQuery();
			if(rs.next()) {
				UserModel uModel=new UserModel();
				uModel.setUId(rs.getInt(1));
				uModel.setFirstName(rs.getString(2));
				uModel.setLastName(rs.getString(3));
				uModel.setEmail(rs.getString(4));
				return uModel;
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

	public UserModel getUserDetails(String firstName, String lastName) {
		try {
			stmt=conn.prepareStatement("select UId,FirstName,LastName,Email from User where FirstName=? and LastName=?");
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			rs=stmt.executeQuery();
			if(rs.next()) {
				UserModel uModel=new UserModel();
				uModel.setUId(rs.getInt(1));
				uModel.setFirstName(rs.getString(2));
				uModel.setLastName(rs.getString(3));
				uModel.setEmail(rs.getString(4));
				return uModel;
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

	public boolean isUpdateUser(UserModel uModel) {
		
		try {
			stmt=conn.prepareStatement("update user set username=?,password=?,email=? where uid=?");
			stmt.setString(1, uModel.getUsername());
			stmt.setString(2, uModel.getPassword());
			stmt.setString(3, uModel.getEmail());
			stmt.setInt(4, uModel.getUId());
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
			
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

	public boolean isUserDetailsDelete(int uId2) {
		
		try {
			stmt=conn.prepareStatement("delete from user where uid=?");
			stmt.setInt(1, uId2);
			int value=stmt.executeUpdate();
			return (value>0)?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	}

}
