package com.csc.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.csc.model.InfoDB;

public class run {
	@Autowired
	private static WriAndRe wriservice;
	public static void main(String[] args) {
		InfoDB info= new InfoDB();
		info= wriservice.readPro();
		System.out.println(info.getUser());
	}
	
}
