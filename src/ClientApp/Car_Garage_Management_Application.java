package ClientApp;

import java.util.*;

import Model.AppointmentModel;
import Model.CustomerModel;
import Model.InventoryModel;
import Model.RoleModel;
import Model.UserModel;
import Model.VehicleModel;
import Model.WorkOrderModel;
import Service.AppointmentService;
import Service.CustomerService;
import Service.InventoryService;
import Service.RoleService;
import Service.UserService;
import Service.VehicleService;
import Service.WorkOrderService;

public class Car_Garage_Management_Application {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		UserService uService = new UserService();
		RoleService rService = new RoleService();
		CustomerService cService = new CustomerService();
		VehicleService vService = new VehicleService();
		AppointmentService aService = new AppointmentService();
		WorkOrderService wService=new WorkOrderService();
		InventoryService iService=new InventoryService();

		System.out.println("Enter username");
		String username = s.nextLine();
		System.out.println("Enter password");
		String password = s.nextLine();
		boolean b = uService.verifyUser(username, password);
		if (b) {
			System.out.println("\n#############  Login Successfully  #############\n\n#############     WELCOME USER     #############\n");
			do {
				System.out.println("\n\nCase 1 : Role");
				System.out.println("Case 2 : User");
				System.out.println("Case 3 : Customer");
				System.out.println("Case 4 : Vehicle");
				System.out.println("Case 5 : Appointment");
				System.out.println("Case 6 : Bills (WorkOrder)");
				System.out.println("Case 7 : Inventory (Spare Part Stock)");

				System.out.println("Case 9 : To Exit");
				System.out.println("\nEnter your choice");
				int choice = s.nextInt();

				switch (choice) {
				case 1:
					int ch;
					do {
						System.out.println("\n\nCase 1: Add Role");
						System.out.println("Case 2: View All Role");
						System.out.println("Case 3: Delete Role");
						System.out.println("Case 9: Go to home page");
						System.out.println("Enter your choice");
						ch=s.nextInt();
						switch(ch) {
						case 1:
							System.out.println("Enter Role");
							s.nextLine();
							String Role = s.nextLine();
							RoleModel rModel = new RoleModel();
							rModel.setRole(Role);
							int result = rService.isAddRole(rModel);
							System.out.println((result == 1) ? "Role is added successfully"
									: (result == -1) ? "Role is already present" : "Role is not added");
							break;
							
						case 2:
							List<RoleModel> list = rService.getAllRole();
							System.out.println("\nList of Role");
							System.out.println("===============================");
							for (RoleModel rModel1 : list) {
								System.out.println(rModel1.getRId() + "\t" + rModel1.getRole());
							}
							list.clear();
							break;
							
						case 3:
							System.out.println("Enter role which you want to delete");
							s.nextLine();
							Role=s.nextLine();
							b=rService.isRoleDelete(Role);
							System.out.println((b)?"Role successfully deleted":"Please check correct role which you want to delete");
							break;
							
						case 9:// To exit from role panel
							break;
							
						default: System.out.println("You have entered wrong choice");
						}
					}while(ch!=9);
					
					
					break;

				case 2:
					do {
						System.out.println("Case 1: Add User");
						System.out.println("Case 2: View User Details");
						System.out.println("Case 3: Update User Details");
						System.out.println("Case 4: Delete User Details");
						System.out.println("Case 9: Go to home page");
						System.out.println("Enter your choice");
						ch=s.nextInt();
						switch(ch) {
						case 1:
							System.out.println("Enter FirstName");
							s.nextLine();
							String FirstName = s.nextLine();
							System.out.println("Enter LastName");
							String LastName = s.nextLine();
							System.out.println("Enter username");
							String Username = s.nextLine();
							System.out.println("Enter password");
							String Password = s.nextLine();
							System.out.println("Enter Email");
							String Email = s.nextLine();
							System.out.println("Enter role of user");
							String Role = s.nextLine();
							int RId = rService.getRoleId(Role);
							if (RId > 0) {
								UserModel uModel = new UserModel();
								uModel.setFirstName(FirstName);
								uModel.setLastName(LastName);
								uModel.setUsername(Username);
								uModel.setPassword(Password);
								uModel.setEmail(Email);
								int result = uService.isAddUser(uModel, RId);
								System.out.println((result == 1) ? "User is added successfully": (result == -1) ? "User is already present" : "User is not added");
							} else {
								System.out.println("This role is not present");
							}
							break;
							
						case 2:
							do {
								System.out.println("\n\nCase 1: View user by its UId");
								System.out.println("Case 2: View user by its FirstName and LastName");
								System.out.println("Case 3: View all user");
								System.out.println("Case 9: Go to user page");
								System.out.println("Enter your choice");
								ch=s.nextInt();
								switch(ch) {
								case 1:
									System.out.println("Enter UId");
									int UId=s.nextInt();
									UserModel uModel=uService.getUserDetails(UId);
									System.out.println((uModel!=null)?uModel.getUId()+"\t"+uModel.getFirstName()+"\t"+uModel.getLastName()+"\t"+uModel.getEmail():"Sorry you have entered inncorrect UId");
									
									break;
									
								case 2:
									System.out.println("Enter FirstName of user");
									s.nextLine();
									FirstName=s.nextLine();
									System.out.println("Enter LastName of user");
									LastName=s.nextLine();
									uModel=uService.getUserDetails(FirstName,LastName);
									System.out.println((uModel!=null)?uModel.getUId()+"\t"+uModel.getFirstName()+"\t"+uModel.getLastName()+"\t"+uModel.getEmail():"Sorry you have entered inncorrect UId");
					
									break;
									
								case 3:
									List<UserModel> list = uService.getAllUser();
									System.out.println("List of Users");
									System.out.println("=============================================================");
									for (UserModel uModel1 : list) {
										System.out.println(uModel1.getUId() + "\t" + uModel1.getFirstName() + "\t" + uModel1.getLastName()+"\t"+uModel1.getUsername()+"\t"+uModel1.getEmail());
									}
									list.clear();
									break;
									
								case 9://To exit from user view panel
									break;
									
								default: System.out.println("You have entered wrong choice");
								}
							}while(ch!=9);
							break;
							
						case 3:
							System.out.println("Enter username");
							s.nextLine();
							Username=s.nextLine();
							System.out.println("Enter password");
							Password=s.nextLine();
							int UId=uService.getUserId(Username, Password);
							System.out.println("Enter new username");
							Username=s.nextLine();
							System.out.println("Enter new password");
							Password=s.nextLine();
							System.out.println("Enter new email");
							Email=s.nextLine();
							UserModel uModel=new UserModel();
							uModel.setUId(UId);
							uModel.setUsername(Username);
							uModel.setPassword(Password);
							uModel.setEmail(Email);
							b=uService.isUpdateUser(uModel);
							System.out.println((b)?"User Details updated":"Sorry... username or password is incorrect");
							break;
							
						case 4:
							System.out.println("Enter user UId");
							UId=s.nextInt();
							b=uService.isUserDetailsDelete(UId);
							System.out.println((b)?"User details deleted":"Sorry... You have entered incorrect UId");
							
						case 9:// To exit from user panel
							break;
							
						default: System.out.println("You have entered wrong choice");
						}
					}while(ch!=9);
					
					break;
					
				case 3:
					do {
						System.out.println("\n\nCase 1: Add Customer");
						System.out.println("Case 2: View Customer Details");
						System.out.println("Case 3: Update Customer Details");
						System.out.println("Case 4: Delete Customer Details");
						System.out.println("Case 9: Go to home page");
						System.out.println("Enter your choice");
						ch=s.nextInt();
						switch(ch) {
						case 1:
							System.out.println("Enter First Name");
							s.nextLine();
							String FirstName = s.nextLine();
							System.out.println("Enter Last Name");
							String LastName = s.nextLine();
							System.out.println("Enter Email");
							String Email = s.nextLine();
							System.out.println("Enter Contact Number 10 digits only");
							String Contact = s.nextLine();
							System.out.println("Enter Address");
							String Address = s.nextLine();
							CustomerModel cModel = new CustomerModel(FirstName, LastName, Email, Contact, Address);
							int result = cService.isAddCustomer(cModel);
							System.out.println((result == 1) ? "Customer is added successfully"
									: (result == -1) ? "Customer is already present" : "Customer is not added");
							break;
							
						case 2:
							do {
								System.out.println("Case 1: View Customer by its CId");
								System.out.println("Case 2: View user by its FirstName and LastName");
								System.out.println("Case 3: View all customers");
								System.out.println("Case 9: Go to customer page");
								System.out.println("Enter your choice");
								ch=s.nextInt();
								switch(ch) {
								case 1:
									System.out.println("Enter CId");
									int CId=s.nextInt();
									cModel=cService.getCustomerDetails(CId);
									System.out.println((cModel!=null)?cModel.getCId()+"\t"+cModel.getFirstName()+"\t"+cModel.getLastName()+"\t"+cModel.getEmail()+"\t"+cModel.getContact()+"\t"+cModel.getAddress():"Sorry you have entered inncorrect UId");
									break;
									
								case 2:
									System.out.println("Enter FirstName of user");
									s.nextLine();
									FirstName=s.nextLine();
									System.out.println("Enter LastName of user");
									LastName=s.nextLine();
									cModel=cService.getUserDetails(FirstName,LastName);
									System.out.println((cModel!=null)?cModel.getCId()+"\t"+cModel.getFirstName()+"\t"+cModel.getLastName()+"\t"+cModel.getEmail()+"\t"+cModel.getContact()+"\t"+cModel.getAddress():"Sorry you have entered inncorrect UId");
									break;
									
								case 3:
									List<CustomerModel> list = cService.getAllCustomer();
									System.out.println("List of Users");
									System.out.println("=============================================================");
									for (CustomerModel cModel1 : list) {
										System.out.println(cModel1.getCId() + "\t" + cModel1.getFirstName() + "\t" + cModel1.getLastName()+"\t"+cModel1.getEmail()+"\t"+cModel1.getContact()+"\t"+cModel1.getAddress());
									}
									list.clear();
									break;
									
								case 9:// To exit from customer view panel
									break;
									
								default: System.out.println("You have entered wrong choice");
								}
							}while(ch!=9);
							break;
							
						case 3:
							System.out.println("Enter First Name");
							s.nextLine();
							FirstName=s.nextLine();
							System.out.println("Enter Last Name");
							LastName=s.nextLine();
							int CId=cService.getCustomerId(FirstName, LastName);
							System.out.println("Enter New Email");
							Email=s.nextLine();
							System.out.println("Enter new contact");
							Contact=s.nextLine();
							System.out.println("Enter new address");
							Address=s.nextLine();
							CustomerModel cModel1=new CustomerModel();
							cModel1.setCId(CId);
							cModel1.setEmail(Email);
							cModel1.setContact(Contact);
							cModel1.setAddress(Address);
							b=cService.isUpdateCustomer(cModel1);
							System.out.println((b)?"Customer details updated":"Sorry... Customer not in the database");
							break;
							
						case 4:
							System.out.println("Enter customer CId to delete details");
							CId=s.nextInt();
							b=cService.isDeleteCustomerDetails(CId);
							System.out.println((b)?"Customer details deleted":"Sorry... You have entered incorrect CId");
							break;
							
						case 9:// To exit from customer panel
							break;
							
						default: System.out.println("You have entered wrong choice");
						}
					}while(ch!=9);
					break;

				case 4:
					do {
						System.out.println("\n\nCase 1: Add Vehicle Details");
						System.out.println("Case 2: View Vehicle Details");
						System.out.println("Case 3: Update Vehicle Details");
						System.out.println("Case 4: Delete Vehicle Details");
						System.out.println("Case 9: Go to home page");
						System.out.println("Enter your choice");
						ch=s.nextInt();
						switch(ch) {
						case 1:
							System.out.println("Enter Customer FirstName");
							s.nextLine();
							String FirstName = s.nextLine();
							System.out.println("Enter Customer LastName");
							String LastName = s.nextLine();
							int CId = cService.getCustomerId(FirstName, LastName);
							System.out.println("Enter Vehicle Make");
							String Make = s.nextLine();
							System.out.println("Enter Vehicle Model");
							String Model = s.nextLine();
							System.out.println("Enter Vehicle Year");
							int Year = s.nextInt();
							System.out.println("Enter Vehicle VIN");
							s.nextLine();
							String VIN = s.nextLine();
							System.out.println("Enter Vehicle Mileage");
							int Mileage = s.nextInt();
							VehicleModel vModel = new VehicleModel(CId, Make, Model, Year, VIN, Mileage);
							int result = vService.isAddVehicle(vModel);
							System.out.println((result == 1) ? "Vehicle is added successfully"
									: (result == -1) ? "Vehicle is already present" : "Vehicle is not added");
							break;
							
						case 2:
							do {
								System.out.println("Case 1: View Vehicle by its VId");
								//System.out.println("Case 2: View Vehicle by its Owner FirstName and LastName");
								System.out.println("Case 2: View all vehicles");
								System.out.println("Case 9: Go to vehicle page");
								System.out.println("Enter your choice");
								ch=s.nextInt();
								switch(ch) {
								case 1:
									System.out.println("Enter VId");
									int VId=s.nextInt();
									vModel=vService.getVehicleDetails(VId);
									System.out.println((vModel!=null)?vModel.getVId()+"\t"+vModel.getCId()+"\t"+vModel.getMake()+"\t"+vModel.getModel()+"\t"+vModel.getYear()+"\t"+vModel.getVIN()+"\t"+vModel.getMileage():"Sorry you have entered inncorrect UId");
									
									break;
									
								//case 2:
									
									//break;
									
								case 2:
									List<VehicleModel> list = vService.getAllVehicle();
									System.out.println("List of Vehicle");
									System.out.println("=============================================================");
									for (VehicleModel vModel1 : list) {
										System.out.println(vModel1.getVId()+"\t"+vModel1.getCId()+"\t"+vModel1.getMake()+"\t"+vModel1.getModel()+"\t"+vModel1.getYear()+"\t"+vModel1.getVIN()+"\t"+vModel1.getMileage());
									}
									list.clear();
									break;
									
								case 9:// To exit from vehicle view panel
									break;
									
								default: System.out.println("You have entered wrong choice");
								}
							}while(ch!=9);
							break;
							
						case 3:
							System.out.println("Enter VIN number of car");
							s.nextLine();
							VIN=s.nextLine();
							System.out.println("Enter Customer First name");
							FirstName=s.nextLine();
							System.out.println("Enter Customer Last name");
							LastName=s.nextLine();
							CId=cService.getCustomerId(FirstName, LastName);
							b=vService.isUpdateVehicleDetails(VIN,CId);
							System.out.println((b)?"Vehicle details updated":"Sorry...Incorrect VIN");
							break;
							
						case 4:
							System.out.println("Enter vehicle VId which you want to delete");
							int VId=s.nextInt();
							b=vService.isDeleteVehicleDetails(VId);
							break;
							
						case 9:// To exit from vehicle panel
							break;
							
						default: System.out.println("You have entered wrong choice");
						}
					}while(ch!=9);
					break;
					
				case 5:
					do {
						System.out.println("Case 1: Create Appointment");
						System.out.println("Case 2: View Appointment");
						System.out.println("Case 3: Update Appointment");
						System.out.println("Case 4: Delete Appointment");
						System.out.println("Case 5: View Customer wise Vehicle Details");
						System.out.println("Case 9: Go to home page");
						System.out.println("Enter your choice");
						ch=s.nextInt();
						switch(ch) {
						case 1:
							System.out.println("Enter Customer FirstName");
							s.nextLine();
							String FirstName = s.nextLine();
							System.out.println("Enter Customer LastName");
							String LastName = s.nextLine();
							int CId = cService.getCustomerId(FirstName, LastName);
							System.out.println("Enter Vehicle VIN");
							String VIN = s.nextLine();
							int VId = vService.getVehicleId(CId, VIN);
							List<UserModel> list=uService.getAllUser();
							System.out.println("List of Available Mechanics (Users)");
							System.out.println("=============================================================");
							if(list!=null) {
								for (UserModel uModel : list) {
									System.out.println(uModel.getUId() + "\t" + uModel.getFirstName() + "\t" + uModel.getLastName());
								}
								System.out.println("\nSelect User Id to set appointment");
								int UId = s.nextInt();
								System.out.println("Enter Date in format ( YYYY-MM-DD )");
								s.nextLine();
								String Date = s.nextLine();
								AppointmentModel aModel = new AppointmentModel();
								aModel.setVId(VId);
								aModel.setVIN(VIN);
								aModel.setUId(UId);
								aModel.setAppointment_Date(Date);
								int result = aService.isCreateAppointment(aModel);
								System.out.println(
										(result == 1) ? "Appointment is created successfully" : "Appointment is not created");
							}
							else {
								System.out.println("Sorry...Mechanics not available. Please wait for few hours.");
							}
							list.clear();
							break;
							
						case 2:
							do {
								System.out.println("Case 1: View Appointment by AId");
								System.out.println("Case 2: View All Appointment");
								System.out.println("Case 9: Go to home page");
								System.out.println("Enter your choice");
								ch=s.nextInt();
								switch(ch) {
								case 1:
									System.out.println("Enter Appointmet AId");
									int AId=s.nextInt();
									AppointmentModel aModel=aService.getAppointmentDetails(AId);
									System.out.println("AId\tVId\tVIN\t\tUId\tAppointment Date\tStatus\\n=================================================================================");
									System.out.println((aModel!=null)?aModel.getAId()+"\t"+aModel.getVId()+"\t"+aModel.getVIN()+"\t"+aModel.getUId()+"\t"+aModel.getAppointment_Date()+"\t\t"+aModel.getStatus():"Sorry...You have entered incorrect appointment AId");
									break;
									
								case 2:
									List<AppointmentModel> list1=aService.getAllAppointmentDetails();
									System.out.println("AId\tVId\tVIN\t\tUId\tAppointment Date\tStatus\n=================================================================================");
									for(AppointmentModel aModel1:list1) {
										System.out.println(aModel1.getAId()+"\t"+aModel1.getVId()+"\t"+aModel1.getVIN()+"\t"+aModel1.getUId()+"\t"+aModel1.getAppointment_Date()+"\t\t"+aModel1.getStatus());
									}
									list1.clear();
									break;
									
								case 9:// To exit from appointment panel
									break;
									
								default: System.out.println("You have entered wrong choice");
								}
							}while(ch!=9);
							break;
							
						case 3:
							System.out.println("Enter Appointment AId");
							int AId=s.nextInt();
							List<UserModel> list1 = uService.getAllUser();
							System.out.println("List of Available Mechanics (Users)");
							System.out.println("=============================================================");
							if(list1!=null) {
								for (UserModel uModel : list1) {
									System.out.println(uModel.getUId() + "\t" + uModel.getFirstName() + "\t" + uModel.getLastName());
								}
								System.out.println("\nSelect new User Id");
								int UId = s.nextInt();
								System.out.println("Enter new Date in format (YYYY-MM-DD)");
								s.nextLine();
								String Date=s.nextLine();
								b=aService.isUpdateAppointment(AId,UId,Date);
								System.out.println((b)?"Appointment updated":"Sorry... you have entered incorrect AId");
							}
							else {
								System.out.println("Sorry...Mechanics not available. Please wait for few hours.");
							}
							list1.clear();
							break;
							
						case 4:
							System.out.println("Enter Appointment AId");
							AId=s.nextInt();
							b=aService.isDeleteAppointmet(AId);
							System.out.println((b)?"Appointment deleted":"Sorry... You have entered incorrect AId");
							break;
							
						case 5:
							List<String> list2=vService.getCustomerWiseVehicleDetails();
							if(list2!=null) {
								System.out.println("Customer Name\t\tVehicle Id\tCompany Name(Make)\tModel\t\tYear\t\tVIN\t\t\tMileage\n======================================================================================================================================");
								for(String data:list2) {
									System.out.println(data);
								}
							}
							else {
								System.out.println("Sorry... There is no record present");
							}
							break;
							
						case 9:// To exit from appointment panel
							break;
							
						default: System.out.println("You have entered wrong choice");
						}
					}while(ch!=9);
					break;
					
				case 6:
					do {
						System.out.println("Case 1: Create Bill (WorkOrder)");
						System.out.println("Case 2: View Bills (WorkOrder)");
						System.out.println("Case 3: Delete Bill (WorkOrder)");
						System.out.println("Case 9: Go to home page");
						System.out.println("Enter your choice");
						ch=s.nextInt();
						switch(ch) {
						case 1:
							System.out.println("Enter Appointment AId");
							int AId=s.nextInt();
							System.out.println("Enter Service_Description");
							s.nextLine();
							String Service_Description=s.nextLine();
							System.out.println("Enter All Used Parts Seprate by (,) Comma");
							String Parts_Used=s.nextLine();
							String Parts[]=Parts_Used.split(",");
							int Total_Cost=0;
//							for(int i=0;i<Parts.length;i++) {
//								int Price=iService.getPriceOfSparePart(Parts[i]);
//								Total_Cost=Total_Cost+Price;
//							}
							for(String part:Parts) {
								int Price=iService.getPriceOfSparePart(part);
								Total_Cost=Total_Cost+Price;
							}
							System.out.println("Enter Labour_Cost");
							int Labour_Cost=s.nextInt();
							Total_Cost=Total_Cost+Labour_Cost;
							WorkOrderModel wModel=new WorkOrderModel(AId,Service_Description,Parts_Used,Labour_Cost,Total_Cost);
							b=wService.isCreateWorkOrder(wModel);
							System.out.println((b)?"Bill or WordOrder created successfully":"Sorry... You have entered incorrect Appointment AId");
							break;
							
						case 2:
							do {
								System.out.println("Case 1: View Bill (WorkOrder) By Appointment AId");
								System.out.println("Case 2: View All Bills (WorkOrder)");
								System.out.println("Case 3: View All Customer Bill Date Wise");
								System.out.println("Case 9: Go to home page");
								System.out.println("Enter your choice");
								ch=s.nextInt();
								switch(ch) {
								case 1:
									System.out.println("Enter Appointment AId");
									AId=s.nextInt();
									WorkOrderModel wModel1=wService.getBill(AId);
									System.out.println("WId\tAId\tService Description\t\t\tUsed Parts\t\t\tLabour Cost\tTotal Cost");
									System.out.println("=======================================================================================================================");
									System.out.println((wModel1!=null)?wModel1.getWId()+"\t"+wModel1.getAId()+"\t"+wModel1.getService_Description()+"\t"+wModel1.getParts_Used()+"\t\t"+wModel1.getLabourCost()+"\t\t"+wModel1.getTotalCost():"Sorry...You have entered incorrect Appointment AId");
									break;
									
								case 2:
									List<WorkOrderModel> list = wService.getAllBills();
									System.out.println("List of All Bills");
									System.out.println("WId\tAId\tService Description\t\t\tUsed Parts\t\t\tLabour Cost\tTotal Cost");
									System.out.println("=========================================================================================================================");
									for (WorkOrderModel wModel2 : list) {
										System.out.println(wModel2.getWId()+"\t"+wModel2.getAId()+"\t"+wModel2.getService_Description()+"\t"+wModel2.getParts_Used()+"\t\t"+wModel2.getLabourCost()+"\t\t"+wModel2.getTotalCost());
									}
									break;
									
								case 3:
									List<String> list2=wService.getAllCustomerBillDateWise();
									if(list2!=null) {
										System.out.println("Appointment Date\tAppointment Id\tVehicle Id\tCustomer Id\tFirstName\tLastName\tLabour Cost\tTotal Cost\n======================================================================================================================================");
										for(String data:list2) {
											System.out.println(data);
										}
									}
									else {
										System.out.println("Sorry... There is no record present");
									}
									break;
									
								case 9:// To exit from View Bills (WorkOrder) panel
									break;
									
								default: System.out.println("You have entered wrong choice");
								}
							}while(ch!=9);
							break;
							
						case 3:
							System.out.println("Enter Bill number or WorkOrder WId");
							int WId=s.nextInt();
							b=wService.isDeleteBill(WId);
							System.out.println((b)?"Bill deleted successfully":"Sorry...You have entered incorrect bill number or WId");
							break;
							
						case 9:// To exit from Bills (WorkOrder) panel
							break;
							
						default: System.out.println("You have entered wrong choice");
						}
					}while(ch!=9);
					break;
					
				case 7:
					do {
						System.out.println("Case 1: Add Spare Parts In Stocks");
						System.out.println("Case 2: View Spare Parts Details");
						System.out.println("Case 3: Update Spare Parts Details");
						System.out.println("Case 4: Delete Spare Parts From Inventory");
						System.out.println("Case 9: Go to home page");
						System.out.println("Enter your choice");
						ch=s.nextInt();
						switch(ch) {
						case 1:
							System.out.println("Enter Part Name");
							s.nextLine();
							String PartName=s.nextLine();
							System.out.println("Enter Price of single unit");
							int Price=s.nextInt();
							System.out.println("Enter Quantity of part");
							int Quantity=s.nextInt();
							InventoryModel iModel=new InventoryModel(PartName,Price,Quantity);
							int result=iService.isAddPart(iModel);
							System.out.println((result==1)?"Part Added successfully":(result==-1)?"Part is already present please choose update option":"Sorry... Part is not added");
							break;
							
						case 2:
							do {
								System.out.println("Case 1: View Spare Part Details By Name");
								System.out.println("Case 2: View All Spare Parts Details");
								System.out.println("Case 9: Go to inventory panel");
								System.out.println("Enter your choice");
								ch=s.nextInt();
								switch(ch) {
								case 1:
									System.out.println("Enter Spare Part name");
									s.nextLine();
									PartName=s.nextLine();
									InventoryModel iModel2 =iService.getSparePartDetails(PartName);
									System.out.println((iModel2!=null)?iModel2.getPId()+"\t"+iModel2.getPartName()+"\t"+iModel2.getPrice()+"\t"+iModel2.getQuantity():"Sorry... this spare part is not available");
									break;
									
								case 2:
									List<InventoryModel> list = iService.getAllSparePartDetails();
									System.out.println("Details of All Spare Parts");
									System.out.println("Part Id\t\tPart Name\t\tPrice\tQuantity\n=============================================================");
									for (InventoryModel iModel3 : list) {
										System.out.println(iModel3.getPId()+"\t\t"+iModel3.getPartName()+"\t\t\t"+iModel3.getPrice()+"\t"+iModel3.getQuantity());
									}
									break;
									
								case 9:// To exit from inventory view panel
									break;
									
								default: System.out.println("You have entered wrong choice");
								}
							}while(ch!=9);
							break;
							
						case 3:
							System.out.println("Enter Part Name");
							s.nextLine();
							PartName=s.nextLine();
							System.out.println("Enter Price of single unit");
							Price=s.nextInt();
							System.out.println("Enter Quantity of part");
							Quantity=s.nextInt();
							InventoryModel iModel1=new InventoryModel(PartName,Price,Quantity);
							b=iService.isUpdatePart(iModel1);
							System.out.println((b)?"Part Details Updated":"Sorry... Part Details not updated");
							break;
							
						case 4:
							System.out.println("Enter Part Name");
							s.nextLine();
							PartName=s.nextLine();
							b=iService.isDeleteSparePart(PartName);
							System.out.println((b)?"Spare part details deleted":"Sorry...You have entered incorrect spare part name");
							break;
							
						case 9:// To exit from appointment panel
							break;
							
						default: System.out.println("You have entered wrong choice");
						}
					}while(ch!=9);
					break;

				case 9:
					System.exit(0);

				default:
					System.out.println("Sorry... You have entered wrong choice...");
				}
			} while (true);
		} else {
			System.out.println("\n##########   Login Failed   ##########\n\nUsername or Password is incorrect\n");
		}

	}

}
