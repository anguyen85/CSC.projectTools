package com.csc.service;

import com.csc.model.InfoDB;

public interface WriAndRe {

	public void writeBD(String host, String user, String pass) ;
	public InfoDB readBD();
	public void saveParamChanges(String host,String user, String pass);
	public InfoDB readPro();
}
