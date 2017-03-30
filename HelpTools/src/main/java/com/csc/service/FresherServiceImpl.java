package com.csc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.csc.model.Fresher;

import csc.com.dao.FresherDAO;



@Service
@Transactional
@EnableTransactionManagement
public class FresherServiceImpl implements FresherService {

	private FresherDAO fresherDAO;
	
	public FresherDAO getFresherDAO() {
		return fresherDAO;
	}

	@Autowired
	public void setFresherDAO(FresherDAO fresherDAO) {
		this.fresherDAO = fresherDAO;
	}

	public void saveOrUpdate(Fresher fresher) {
		fresherDAO.saveOrUpdate(fresher);
	}

	public Fresher getById(int id) {
		return fresherDAO.getById(id);
	}

	public List<Fresher> getAll() {
		return fresherDAO.getAll();
	}


}
