package com.csc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csc.model.Fresher;
import com.csc.service.FresherService;
@Controller
@RequestMapping("/ajax")
public class AjaxController {

	@Autowired
	private FresherService service;
	
	@RequestMapping(value = "getFbyId", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Fresher getFresherByDd(@RequestParam(value="id", required=true) String id){
		return service.getById(Integer.parseInt(id));
	}
	
	@RequestMapping(value = "getAllF", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Fresher> getAllFresher(){
		return service.getAll();
	}
	
	
}
