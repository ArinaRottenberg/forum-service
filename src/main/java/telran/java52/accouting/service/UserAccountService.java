package telran.java52.accouting.service;

import telran.java52.accouting.dto.RolesDto;
import telran.java52.accouting.dto.UserDto;
import telran.java52.accouting.dto.UserEditDto;
import telran.java52.accouting.dto.UserRegisterDto;

public interface UserAccountService {
	
	UserDto register(UserRegisterDto userRegisterDto);

	UserDto getUser(String login);
	
	UserDto removeUser(String login);

	UserDto updateUser(String login, UserEditDto userEditDto);

	RolesDto changeRolesList (String login,String role, Boolean isAddRoe);

	void changePassword(String login, String newPassword);

	
}
