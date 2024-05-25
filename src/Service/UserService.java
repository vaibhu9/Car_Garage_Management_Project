package Service;

import java.util.List;

import Model.UserModel;
import Repository.UserRepository;

public class UserService {
	
	UserRepository uRepo=new UserRepository();

	public int isAddUser(UserModel uModel,int RId) {
		
		return uRepo.isAddUser(uModel,RId);
	}
	
	public List<UserModel> getAllUser(){
		
		return uRepo.getAllUser();
	}
	
	public boolean verifyUser(String username, String password) {
		
		return uRepo.verifyUser(username, password);

	}

	public UserModel getUserDetails(int UId) {
		return uRepo.getUserDetails(UId);
	}

	public UserModel getUserDetails(String firstName, String lastName) {
		return uRepo.getUserDetails(firstName,lastName);
	}

	public int getUserId(String Username, String Password) {
		
		return uRepo.getUserId(Username, Password);
	}

	public boolean isUpdateUser(UserModel uModel) {
		
		return uRepo.isUpdateUser(uModel);
	}

	public boolean isUserDetailsDelete(int uId) {
		
		return uRepo.isUserDetailsDelete(uId);
	}

}
