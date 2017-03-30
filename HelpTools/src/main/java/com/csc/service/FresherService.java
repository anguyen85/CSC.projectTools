package com.csc.service;

import java.util.List;

import com.csc.model.Fresher;

public interface FresherService {
	public void saveOrUpdate(Fresher fresher);
	
	public Fresher getById(int id);
	public List<Fresher> getAll();
}
