package com.talenthunt.api.util;

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
}
