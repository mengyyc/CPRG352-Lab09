/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.sql.SQLException;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author lixia
 */
public class UserService {
	RoleService rs = null;

	public User get(String email) throws SQLException {
		UserDB userDB = new UserDB();
		User user = userDB.get(email);
		return user;
	}

	public List<User> getAll(int roleId) throws SQLException {
		UserDB userDB = new UserDB();
		List<User> users = userDB.getAll(roleId);
		return users;
	}  	

	public void insert(String email, boolean active, String firstName, String lastName, String password, int roleId) throws SQLException {
		rs = new RoleService();
		Role role = rs.get(roleId);
		User user = new User(email, active, firstName, lastName, password);
		user.setRole(role);
		UserDB userDB = new UserDB();
		userDB.insert(user);
	}

	public void update(String email, boolean active, String firstName, String lastName, String password, int roleId) throws SQLException {
		rs = new RoleService();
		Role role = rs.get(roleId);
		User user = new User(email, active, firstName, lastName, password);
		user.setRole(role);
		UserDB userDB = new UserDB();
		userDB.update(user);
	}
	
	public void delete(String email) throws SQLException {
		UserDB userDB = new UserDB();
		User user = userDB.get(email);
		userDB.delete(user);
	}
}
