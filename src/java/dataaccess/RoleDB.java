package dataaccess;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import models.Role;

public class RoleDB {
	
	public List<Role> getAll() throws SQLException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
			return roles;
		}
		finally {
			em.close();
		}
	}	

	public Role get(int roleId) throws SQLException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			Role role = em.find(Role.class, roleId);
			return role;
		} finally {
			em.close();
		}
	}

	public Role get(String roleName) throws SQLException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			Role role = em.find(Role.class, roleName);
			return role;
		} finally {
			em.close();
		}
	}
}
