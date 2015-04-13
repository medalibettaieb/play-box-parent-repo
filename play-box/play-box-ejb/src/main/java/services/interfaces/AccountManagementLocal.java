package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.User;

@Local
public interface AccountManagementLocal {
	Boolean addUser(User user);

	User findUserById(Integer id);

	Boolean deleteUser(Integer id);

	Boolean updateUser(User user);

	List<User> findAllUsers();

	User login(String login, String password);

	User loginBis(User user);
}
