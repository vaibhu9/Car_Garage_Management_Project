package Helper;
import java.sql.*;
import java.util.*;
public class Table_Creation_Application {

	public static void main(String[] args) throws SQLException, ClassNotFoundException{
//		com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
//		DriverManager.registerDriver(d);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cargaragemanagement","root","Vaibhav");
		Scanner s=new Scanner(System.in);
		int choice;
		if(conn!=null) {
			do {
				System.out.println("\n\n\nCase 1: User table.");
				System.out.println("Case 2: Role table.");
				System.out.println("Case 3: UserRoleJoin table.");
				System.out.println("Case 4: Customer table.");
				System.out.println("Case 5: Vehicle table.");
				System.out.println("Case 6: ServiceAppointment table");
				System.out.println("Case 7: WorkOrder table.");
				System.out.println("Case 8: Inventory table.");
				System.out.println("Case 9: To Exit.");
				System.out.println("");
				System.out.println("Enter your choice.");
				choice=s.nextInt();
				
				switch(choice) {
				case 1:
					PreparedStatement pstmt=conn.prepareStatement("create table User(UId int(5)primary key, FirstName varchar(90)not null, LastName varchar(90)not null, Username varchar(90)not null unique, Password varchar(90)not null, Email varchar(90)not null unique)");
					int value=pstmt.executeUpdate();
					if(value==0) {
						System.out.println("User table created successfully.");
					}
					else {
						System.out.println("Table is not created.");
					}
					break;
				case 2:
					pstmt=conn.prepareStatement("create table Role(RId int(5)primary key, Role varchar(90)not null unique)");
					value=pstmt.executeUpdate();
					if(value==0) {
						System.out.println("Role table created successfully.");
					}
					else {
						System.out.println("Table is not created.");
					}
					break;
				case 3:
					pstmt=conn.prepareStatement("create table UserRoleJoin(UId int(5),foreign key(UId)references User(UId) on delete cascade on update cascade, RId int(5),foreign key(RId)references Role(RId) on delete cascade on update cascade)");
					value=pstmt.executeUpdate();
					if(value==0) {
						System.out.println("UserRoleJoin table created successfully.");
					}
					else {
						System.out.println("Table is not created.");
					}
					break;
				case 4:
					pstmt=conn.prepareStatement("create table Customer(CId int(5)primary key, FirstName varchar(90)not null, LastName varchar(90)not null, Email varchar(90)not null unique, Contact varchar(90)not null unique, Address varchar(90)not null)");
					value=pstmt.executeUpdate();
					if(value==0) {
						System.out.println("Customer table created successfully.");
					}
					else {
						System.out.println("Table is not created.");
					}
					break;
				case 5:
					pstmt=conn.prepareStatement("create table Vehicle(VId int(5)primary key, CId int(5),foreign key(CId)references Customer(CId) on delete cascade on update cascade, Make varchar(90)not null, Model varchar(90)not null, Year int(5)not null, VIN varchar(90)not null unique, Mileage int(5)not null)");
					value=pstmt.executeUpdate();
					if(value==0) {
						System.out.println("Vehicle table created successfully.");
					}
					else {
						System.out.println("Table is not created.");
					}
					break;
				case 6:
					pstmt=conn.prepareStatement("create table Appointment(AId int(5)primary key, VId int(5),foreign key(VId)references Vehicle(VId) on delete cascade on update cascade,VIN varchar(90)not null unique, UId int(5),foreign key(UId)references User(UId) on delete cascade on update cascade, Appointment_Date DATE not null, Status varchar(90)default 'Pending')");
					value=pstmt.executeUpdate();
					if(value==0) {
						System.out.println("Appointment table created successfully.");
					}
					else {
						System.out.println("Table is not created.");
					}
					break;
				case 7:
					pstmt=conn.prepareStatement("create table WorkOrder(WId int(5)primary key, AId int(5),foreign key(AId)references Appointment(AId) on delete cascade on update cascade, Service_Description varchar(250)not null, Parts_Used varchar(250), Labour_Cost int(5) default 200, Total_Cost int(5)default 200)");
					value=pstmt.executeUpdate();
					if(value==0) {
						System.out.println("WorkOrder table created successfully.");
					}
					else {
						System.out.println("Table is not created.");
					}
					break;
				case 8:
					pstmt=conn.prepareStatement("create table Inventory(PId int(5)primary key, Part_Name varchar(90)not null, Price int(5)not null, Quantity int(5)not null)");
					value=pstmt.executeUpdate();
					if(value==0) {
						System.out.println("Inventory table created successfully.");
					}
					else {
						System.out.println("Table is not created.");
					}
					break;
				case 9:System.exit(0);
					break;
				default: System.out.println("You have entered wrong choice...");
				}
			}while(true);
		}
		else {
			System.out.println("Database is not connected...");
		}

	}

}
