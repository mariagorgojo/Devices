package projectJPA;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import projectIfaces.UserManager;
import projectPOJOs.Role;
import projectPOJOs.User;

public class JPAUserManager implements UserManager {
	private EntityManager em;
	
	
	
	public JPAUserManager() {
		super();
		this.connect();
	}

	@Override
	public User checkPassword(String email, String pass) {
		// TODO Auto-generated method stub
		User u = null;
		
		Query q = em.createNativeQuery("SELECT * from users where email =? and password=?", User.class);
		q.setParameter(1, email);
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] pw = md.digest();
			
			q.setParameter(2, pw);
			
		}catch(Exception e)
		{e.printStackTrace();}
			
		
		try {
			u = (User) q.getSingleResult();
			
		}catch(NoResultException e) {}
		
		return u;
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		
		em = Persistence.createEntityManagerFactory("devices-provider").createEntityManager();
	
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();
		
		if(this.getRoles().isEmpty())
		{
			Role doctor = new Role("doctor");
			Role patient = new Role("patient");
			Role manufacturer = new Role("manufacturer");
			this.newRole(doctor);
			this.newRole(patient);
			this.newRole(manufacturer);
		}
		
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		
		return roles;
	}

	@Override
	public void newRole(Role r) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
		
	}

	@Override
	public void newUser(User u) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		em.close();
	}

	@Override
	public Role getRole(Integer id) {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM roles where id="+id, Role.class);
		Role r = (Role) q.getSingleResult();
		
		return r;
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM users where email="+email, User.class);
		User u = (User) q.getSingleResult();
		
		return u;
	}

	@Override
	public User changePassword(User u, String new_passwd) {
		// TODO Auto-generated method stub
		try{
			//u.setPassword(new_passwd);
			Query q = em.createNativeQuery("SELECT * FROM users WHERE email=? AND password=?", User.class);
			q.setParameter(1, u.getEmail());
			q.setParameter(2, u.getPassword());
			u = (User) q.getSingleResult();
			
			em.getTransaction().begin();
			u.setPassword(new_passwd.getBytes());
			em.getTransaction().commit();
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}


}
