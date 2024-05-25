package Service;

import java.util.List;

import Model.CustomerModel;
import Repository.CustomerRepository;

public class CustomerService {
	
	CustomerRepository cRepo=new CustomerRepository();

	public int isAddCustomer(CustomerModel cModel) {

		return cRepo.isAddCustomer(cModel);
		
	}
	
	public int getCustomerId(String FirstName, String LastName) {
		
		return cRepo.getCustomerId(FirstName, LastName);
		
	}

	public CustomerModel getCustomerDetails(int cId) {
		return cRepo.getCustomerDetails(cId);
	}

	public CustomerModel getUserDetails(String firstName, String lastName) {
		return cRepo.getUserDetails(firstName, lastName);
	}

	public List<CustomerModel> getAllCustomer() {
		
		return cRepo.getAllCustomer();
	}

	public boolean isUpdateCustomer(CustomerModel cModel1) {
		
		return cRepo.isUpdateCustomer(cModel1);
	}

	public boolean isDeleteCustomerDetails(int cId) {
		
		return cRepo.isDeleteCustomerDetails(cId);
	}

}
