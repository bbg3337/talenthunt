package com.talenthunt.api.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

@Service
public class CommonUtil {
	public boolean createFile(String fileName, String content, String extension) {
		System.out.println("File location : " + fileName + "." + extension);
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName + "." + extension, "UTF-8");
			writer.println(content);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public boolean deleteFolderWithFile(String filePath){
		File index = new File(filePath);
		String[]entries = index.list();
		for(String s: entries){
		    File currentFile = new File(index.getPath(),s);
		    currentFile.delete();
		}
		return index.delete();
	}

	public String validateJavaCode(String code) {
		code = code.trim();
		if(!code.startsWith("package")){
			return code.substring(code.indexOf("class")+5, code.indexOf("{")).trim();
		}else{
			return "Package Key word not allowed";	
		}
		
	}
}
