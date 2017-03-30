package com.csc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csc.model.Fresher;
import com.csc.model.InOut;
import com.csc.model.InfoDB;
import com.csc.service.FresherService;
import com.csc.service.LowCase;
import com.csc.service.WriAndRe;
import com.csc.service.WriAndReImpl;

@Controller
public class IndexController {

	@Autowired
	private FresherService service;
	@Autowired
	private WriAndRe wriservice;
	@Autowired
	private LowCase lowcase;
	public FresherService getService() {
		return service;
	}

	public void setService(FresherService service) {
		this.service = service;
	}

	@RequestMapping(value = "/")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Map<String, Object> model ,HttpServletRequest request){
		Fresher userForm = new Fresher();	
		Fresher usertest = new Fresher();
		usertest = service.getById(1);
		System.out.println(usertest.getEmail());

		model.put("userForm", userForm);
		
		List<String> professionList = new ArrayList<String>();
		professionList.add("Developer");
		professionList.add("Designer");
		professionList.add("IT Manager");
		model.put("professionList", professionList);
		
		 HttpSession session = request.getSession();
         ServletContext sc = session.getServletContext();
         String x = sc.getRealPath("/");
         System.out.println(x);
		return "Registration";
	}
	
	
	
	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public String generate(Map<String, Object> model){
		
		InfoDB info = new InfoDB();
		info=wriservice.readPro();
	
		System.out.println(info.getUser());
//	info.setHost("localhost");
//	info.setNamedb("AnDB");
//	info.setUser("annguyen");
//	info.setPass("123456");
	model.put("infoForm", info);


		
		return "form_generate";
	}
//-------------------------------------------------------------------------
	@RequestMapping(value = "/inputgenerate", method = RequestMethod.GET)
	public String putgenerate(Map<String, Object> model){
		
		InOut io = new InOut();
		io.setInput("C:/Users/training/Desktop/CSC.project/Final Assignment/Final Assignment/Model Entity/Input/PremTableDAM.java");
		io.setOutput("C:/Users/training/Desktop/CSC.project/output/");

		model.put("actionForm", io);
//		try {
//			lowcase.Generate("C:/Users/training/Desktop/CSC.project/Final Assignment/Final Assignment/Model Entity/Input/PremTableDAM.java","C:/Users/training/Desktop/CSC.project/output/");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "form_generate_action";
	}
	
	@RequestMapping(value = "/inputgenerate" ,method = RequestMethod.POST)
	@ResponseBody
	public String doputGenerate(@ModelAttribute("actionForm") InOut io, Map<String, Object> model){
		System.out.println(io.getInput());
		try {
//			lowcase.Generate("C:/Users/training/Desktop/CSC.project/Final Assignment/Final Assignment/Model Entity/Input/PremTableDAM.java","C:/Users/training/Desktop/CSC.project/output/");
			lowcase.Generate(io.getInput(),io.getOutput());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return io.toString();
	}
//--------------------------------------------------------------------
	@RequestMapping(value = "/generate" ,method = RequestMethod.POST)
	@ResponseBody
	public String doGenerate(@ModelAttribute("infoForm") InfoDB ifo, Map<String, Object> model){
		
		wriservice.saveParamChanges(ifo.getHost(),ifo.getUser(),ifo.getPass());
		return ifo.toString();
	}
	@RequestMapping(value = "/register" ,method = RequestMethod.POST)
	@ResponseBody
	public String doRegister(@ModelAttribute("userForm") InfoDB ifo, Map<String, Object> model){
//		wriservice.saveParamChanges(ifo.getHost(),ifo.getUser(),ifo.getPass());
		return ifo.toString();
	}
	
//	@RequestMapping(value = "/update", method = RequestMethod.GET)
//	public String getFresherByDd(@RequestParam(value="id", required=true) String id){
//		
//		return "Registration";
//	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String getId(Map<String, Object> model,@RequestParam(value="id", required=true) String fresherId){
		//Fresher userForm = new Fresher();
		Fresher fr =service.getById(Integer.parseInt(fresherId));
		
		model.put("userForm", fr);
		
		List<String> professionList = new ArrayList<String>();
		professionList.add("Developer");
		professionList.add("Designer");
		professionList.add("IT Manager");
		model.put("professionList", professionList);
		return "Registration";
	}
	@RequestMapping(value = "/doupdate" ,method = RequestMethod.POST)
	
	public void doUpdate(@ModelAttribute("userForm") Fresher fresher, Map<String, Object> model){
		service.saveOrUpdate(fresher);
	}
}
