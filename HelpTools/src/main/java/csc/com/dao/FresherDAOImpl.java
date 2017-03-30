package csc.com.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csc.model.Fresher;

@Repository
public class FresherDAOImpl implements FresherDAO {
	
	
	private SessionFactory sessionFatory;
	
	public void saveOrUpdate(Fresher fresher) {
		sessionFatory.openSession().saveOrUpdate(fresher);
	}

	public SessionFactory getSessionFatory() {
		return sessionFatory;
	}
	
	@Autowired
	public void setSessionFatory(SessionFactory sessionFatory) {
		this.sessionFatory = sessionFatory;
	}

	public Fresher getById(int id) {
		return (Fresher) sessionFatory.openSession().get(Fresher.class, id);
	}

	public List<Fresher> getAll() {
		return sessionFatory.openSession().createQuery("From FRESHER").list();
	}

	
	
}
