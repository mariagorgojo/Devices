package projectInterfaces;

import projectPOJOs.User;

public interface UserManager {

	public User checkPassword(String email, String pass);

}
