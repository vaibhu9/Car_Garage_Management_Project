package Service;

import java.util.List;

import Model.RoleModel;
import Repository.RoleRepository;

public class RoleService {
	RoleRepository rRepo=new RoleRepository();
	
	public int getRoleId(String Role) {
		
		return rRepo.getRoleId(Role);
	}

	public int isAddRole(RoleModel rModel) {
		
		return (rRepo.isRolePresent(rModel))?-1:(rRepo.isAddRole(rModel))?1:0;
	}

	public List<RoleModel> getAllRole() {
		
		return (rRepo.getAllRole());
	}

	public boolean isRoleDelete(String role) {
		
		return rRepo.isRoleDelete(role);
	}

}
