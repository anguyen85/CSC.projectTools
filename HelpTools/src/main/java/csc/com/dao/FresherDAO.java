package csc.com.dao;

import java.util.List;

import com.csc.model.Fresher;

public interface FresherDAO {

	public void saveOrUpdate(Fresher fresher);
	public Fresher getById(int id);
	public List<Fresher> getAll();
}
