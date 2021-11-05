package dataaccess;

import java.sql.SQLException;
import java.util.List;
import models.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author lixia
 */
public class UserDB {

	public List<User> getAll() throws SQLException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
			return users;
		} finally {
			em.close();
		}
	}

	public User get(String email)throws SQLException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			User user = em.find(User.class, email);
			return user;
		} finally {
			em.close();
		}
	}

	public void insert(User user) throws SQLException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(user);
			em.merge(user);
			trans.commit();
		} catch(Exception ex) {
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public void delete(User user) throws SQLException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
			users.remove(user);
			trans.begin();
			em.remove(em.merge(user));
			em.merge(user);
			trans.commit();
		} catch(Exception ex) {
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public void update(User user) throws SQLException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		} catch(Exception ex) {
			trans.rollback();
		} finally {
			em.close();
		}
	}
}
