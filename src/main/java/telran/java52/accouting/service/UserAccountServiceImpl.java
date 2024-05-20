package telran.java52.accouting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.java52.accouting.dao.UserAccountRepository;
import telran.java52.accouting.dto.RolesDto;
import telran.java52.accouting.dto.UserDto;
import telran.java52.accouting.dto.UserEditDto;
import telran.java52.accouting.dto.UserRegisterDto;
import telran.java52.accouting.model.UserAccount;
import telran.java52.accouting.model.Role;

import java.util.stream.Collectors;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	UserAccountRepository userAccountRepository;

	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		if (userAccountRepository.existsById(userRegisterDto.getLogin())) {
			throw new RuntimeException("User already exists");
		}
		UserAccount userAccount = new UserAccount(userRegisterDto.getLogin(), userRegisterDto.getFirstName(),
				userRegisterDto.getLastName(), userRegisterDto.getPassword());
		userAccountRepository.save(userAccount);
		return convertToDto(userAccount);
	}

	public UserDto login(UserRegisterDto userRegisterDto) {
		UserAccount userAccount = userAccountRepository.findById(userRegisterDto.getLogin())
				.orElseThrow(() -> new RuntimeException("User not found"));
		if (!userAccount.getPassword().equals(userRegisterDto.getPassword())) {
			throw new RuntimeException("Incorrect password");
		}
		return convertToDto(userAccount);
	}

	@Override
	public UserDto getUser(String login) {
		UserAccount userAccount = userAccountRepository.findById(login)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return convertToDto(userAccount);
	}

	@Override
	public UserDto removeUser(String login) {
		UserAccount userAccount = userAccountRepository.findById(login)
				.orElseThrow(() -> new RuntimeException("User not found"));
		userAccountRepository.delete(userAccount);
		return convertToDto(userAccount);
	}

	@Override
	public UserDto updateUser(String login, UserEditDto userEditDto) {
		UserAccount userAccount = userAccountRepository.findById(login)
				.orElseThrow(() -> new RuntimeException("User not found"));
		if (userEditDto.getFirstName() != null) {
			userAccount.setFirstName(userEditDto.getFirstName());
		}
		if (userEditDto.getLastName() != null) {
			userAccount.setLastName(userEditDto.getLastName());
		}
		userAccountRepository.save(userAccount);
		return convertToDto(userAccount);
	}

	@Override
	public RolesDto changeRolesList(String login, String role, Boolean isAddRole) {
		UserAccount userAccount = userAccountRepository.findById(login)
				.orElseThrow(() -> new RuntimeException("User not found"));
		boolean result;
		if (isAddRole) {
			result = userAccount.addRole(role);
		} else {
			result = userAccount.removeRole(role);
		}
		userAccountRepository.save(userAccount);
		return convertToRolesDto(userAccount);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		UserAccount userAccount = userAccountRepository.findById(login)
				.orElseThrow(() -> new RuntimeException("User not found"));
		userAccount.setPassword(newPassword);
		userAccountRepository.save(userAccount);
	}

	private UserDto convertToDto(UserAccount userAccount) {
		return UserDto.builder().login(userAccount.getLogin()).firstName(userAccount.getFirstName())
				.lastName(userAccount.getLastName())
				.roles(userAccount.getRoles().stream().map(Role::name).collect(Collectors.toSet())).build();
	}

	private RolesDto convertToRolesDto(UserAccount userAccount) {
		return RolesDto.builder().login(userAccount.getLogin())
				.roles(userAccount.getRoles().stream().map(Role::name).collect(Collectors.toSet())).build();
	}
}
