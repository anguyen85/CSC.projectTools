package com.csc.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DefaultPropertiesPersister;

import com.csc.model.InfoDB;
import com.sun.org.apache.bcel.internal.util.ClassPath;
@Service
@Transactional
@EnableTransactionManagement
public class WriAndReImpl implements WriAndRe {
//	@Autowired
//	ServletContext context;
	public void writeBD(String host,String user, String pass) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		FileWriter fw = null;
	
//		String workingDir =context.getRealPath("");
		String workingDir = System.getProperty("user.dir");
		String FILENAME = "C:/Users/training/Desktop/spring/DemoSpringMVC/DemoSpringMVC/src/main/webapp/WEB-INF/test.properties";
		System.out.println(FILENAME);
		File f = new File(FILENAME);
		if (f.exists() && !f.isDirectory()) {
			f.delete();
		}
		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(host);
			bw.newLine();
			bw.write(user);
			bw.newLine();
			bw.write(pass);
			bw.newLine();
			System.out.println("Done");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void saveParamChanges(String host,String user, String pass) {
		   try {
		     // create and set properties into properties object
		     Properties props = new Properties();
		     props.setProperty("jdbc.driverClassName", "net.sourceforge.jtds.jdbc.Driver");
		     props.setProperty("jdbc.url", host);
		     props.setProperty("jdbc.username", user);
		     props.setProperty("jdbc.password", pass);
		     // get or create the file
		     File f = new File("C:/Users/training/Desktop/spring/DemoSpringMVC/DemoSpringMVC/src/main/resources/app-properties.properties");
		     OutputStream out = new FileOutputStream( f );
		     // write into it
		     DefaultPropertiesPersister p = new DefaultPropertiesPersister();
		     p.store(props, out, "Header COmment");
		     System.out.println("thanh cong !!");
		   } catch (Exception e ) {
		    e.printStackTrace();
		   }
		}
	public InfoDB readBD() {
		// TODO Auto-generated method stub
		String workingDir = System.getProperty("user.dir");
		String FILENAME = workingDir + "\\logDB.txt";
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));
			List<String> list = new ArrayList<String>();
			while ((sCurrentLine = br.readLine()) != null) {
				list.add(sCurrentLine);
			}
			InfoDB info = new InfoDB();
			info.setHost(list.get(0));
			info.setUser(list.get(2));
			info.setPass(list.get(3));
//			

		} catch (IOException e) {
			e.printStackTrace();
		}
	

		return null;
	}
	public InfoDB readPro(){
		org.springframework.context.ApplicationContext context = new ClassPathXmlApplicationContext("DataSource.xml");
		DriverManagerDataSource data= (DriverManagerDataSource) context.getBean("dataSource");
		InfoDB info = new InfoDB();
		info.setUser(data.getUsername());
		info.setPass(data.getPassword());
		info.setHost(data.getUrl());
		return info;
	}
	}


