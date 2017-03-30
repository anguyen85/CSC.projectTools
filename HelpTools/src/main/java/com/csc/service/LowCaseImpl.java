package com.csc.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import csc.com.dao.TableDAO;

@Service
@Transactional
@EnableTransactionManagement
public class LowCaseImpl implements LowCase {
	@Autowired
	private TableDAO tableDAO;
	boolean hasGenarea = false;
	private String outputpath;
	private String filename;
	BufferedWriter bw = null;
	FileWriter fw = null;
	@Autowired
	public String getOutputpath() {
		// TODO Auto-generated method stub
		return outputpath;
	}

	public void setOutputpath(String outputpath) {
		// TODO Auto-generated method stub
		this.outputpath = outputpath;

	}

	public String getFilename() {
		// TODO Auto-generated method stub
		return filename;
	}

	public void setFilename(String filename) {
		// TODO Auto-generated method stub
		this.filename = filename;

	}

	public void covertFileName(String name) {
		// TODO Auto-generated method stub
		String string = name.toUpperCase().substring(0, 1) + name.toLowerCase().substring(1); 
		setFilename(string);
	}
	
	public void Generate(String path, String output) throws IOException {
		// TODO Auto-generated method stub
		


		// Please input address file
		boolean first = false;
		String sTotal = "";
		String sName = "";
		try {
			for (String line : Files.readAllLines(Paths.get(path))) {
				if (line.contains("getTableName")) {
					line = line.trim();
					int start = line.indexOf("(");
					int end = line.indexOf(")");
					sName = line.substring(start + 2, end - 1);
					covertFileName(sName);
				}
				if (line.contains("QUALIFIEDCOLUMNS")) {
					first = true;
					continue;
				}
				if (first) {
					if (line.contains("+")) {
						if (line.toLowerCase().contains("genarea"))
							hasGenarea = true;
						line = line.trim();
						int comman = line.indexOf(",");
						line = line.substring(1, comman + 1);
						sTotal = sTotal + line;
					}
					if (line.contains(";")) {
						sTotal = "UNIQUE_NUMBER," + sTotal ;
						break;
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		setOutputpath(output);
		//write file export
		String dir = getOutputpath() +getFilename()+".java";
		System.out.println(dir);
		File f = new File(dir);
		if (f.exists() && !f.isDirectory()) {
			f.delete();
		}
		fw = new FileWriter(dir);
		bw = new BufferedWriter(fw);
		//write end
		String[] split = sTotal.split(",");
		setHeader(sName);
		LinkedHashMap<String, String> hash = new LinkedHashMap<String, String>();
		for (String s2 : split) {
			String type = "";
			
			List<String> rs=tableDAO.get(sName, s2);
			for(String st: rs) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				switch (st) {
				case "bigint":
					type = "Long";
					break;
				case "nchar":
					type = "String";
					break;
				case "datetime2":
					type = "Timestamp";
					break;
				case "int":
					type = "Integer";
					break;
				case "numeric":
					type = "BigDecimal";
					break;
				case "varbinary":
					type = "byte[]";
					break;
				default:
					type = "String";
					break;
				}
			}
			write("\tprivate " + type + " " + s2.toLowerCase() + ";");
			hash.put(s2.toLowerCase(), type);
		}
		setContent(hash);
		// close tag
		write("}");
		System.out.println("Done");
		bw.close();
		// Đóng kết nối
//		connection.close();
		
	}

	public void setContent(LinkedHashMap<String, String> hash) throws IOException {
		// TODO Auto-generated method stub
		Iterator it = hash.entrySet().iterator();
		while (it.hasNext())
			try {
				{
					
					HashMap.Entry pair = (HashMap.Entry) it.next();
					String key = pair.getKey().toString().toUpperCase().substring(0, 1)
							+ pair.getKey().toString().toLowerCase().substring(1);
					try {
						if (key.equalsIgnoreCase("genarea")){
							write("\tpublic void setOriginGenarea(FixedLengthStringData origin ){");
							write("\t\ttry {");
							write("\t\t\tsetGenarea(origin.toString().getBytes(\"UTF-8\"));");
							write("\t\t} catch (UnsupportedEncodingException e) {");
							write("\t\t\te.printStackTrace();");
							write("\t\t}");
							write("\t}");
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					write("\tpublic " + pair.getValue() + " get" + key + "() {");
					write("\t\treturn " + pair.getKey() + ";");
					write("\t}");
					write("\tpublic void set" + key + "(" + pair.getValue() + " " + pair.getKey() + ") {");
					write("\t\tthis." + pair.getKey() + " = " + pair.getKey() + ";");
					write("\t}");
					it.remove(); // avoids a ConcurrentModificationException
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void setHeader(String table) throws IOException {
		try {
	
		
			// TODO Auto-generated method stub
			if(hasGenarea){
				write("import java.io.UnsupportedEncodingException;");
				write("import com.quipoz.framework.datatype.FixedLengthStringData;");
			}
			write("import java.math.BigDecimal;");
			write("import java.sql.Timestamp;");
			write("import javax.persistence.Column;");
			write("import javax.persistence.Entity;");
			write("import javax.persistence.Id;");
			write("import javax.persistence.Table;");
			write("");
			// import end
			// intro start
			write("/**");
			write(" * model class for mapping of table " + table + ".");
			write(" * @author pdo3");
			write(" */");
			// intro end
			// header start
			write("@Entity");
			write("@Table(name = " + '"' + table + '"' + ")");
			write("public class " + getFilename() + " {");
			write("\tpublic " + getFilename() + "() {");
			write("\t}");
			write("\t@Id");
			write("\t@Column(name = " + '"' + "UNIQUE_NUMBER" + '"' + ", unique = true)");
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}}

	public void write(String string) throws IOException {
		// TODO Auto-generated method stub
		bw.write(string);
		bw.newLine();
	}

}
