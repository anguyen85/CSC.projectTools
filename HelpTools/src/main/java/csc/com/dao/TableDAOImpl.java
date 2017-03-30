package csc.com.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.swing.text.TabableView;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csc.model.Fresher;
@Repository
public class TableDAOImpl implements TableDAO{

	
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
	public List<String> get(String sname, String s2) {
		// TODO Auto-generated method stub
		String hql = "select DATA_TYPE from INFORMATION_SCHEMA.COLUMNS IC where TABLE_NAME = :TABLE_NAME and COLUMN_NAME = :COLUMN_NAME";
		Query query = sessionFatory.openSession().createSQLQuery(hql);
		query.setParameter("TABLE_NAME",sname);
		query.setParameter("COLUMN_NAME",s2);
		List<String> rs =  query.list();
		return rs;
	}

}
