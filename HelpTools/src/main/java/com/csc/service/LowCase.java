package com.csc.service;

import java.io.IOException;
import java.util.LinkedHashMap;

public interface LowCase {

	public String getOutputpath();
	public void setOutputpath(String outputpath);
	public String getFilename();
	public void setFilename(String filename);
	public void covertFileName(String name);
	public void Generate(String path,String output) throws IOException;
	public void setContent(LinkedHashMap<String, String> hash) throws IOException;
	public void setHeader(String table) throws IOException ;
	public void write(String string) throws IOException; 
	
}
