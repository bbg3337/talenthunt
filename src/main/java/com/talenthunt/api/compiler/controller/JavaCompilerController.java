package com.talenthunt.api.compiler.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.util.CommonUtil;
import com.talenthunt.api.util.CompileRunJavaProgram;

@RestController
@RequestMapping("/api/v1/javaCompiler")
@CrossOrigin
public class JavaCompilerController {
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PostMapping("/compilerJavaCode")
	public String compilerJavaCode(@RequestParam(value = "code") String code) throws Exception{
		if(code == null){
			//error
		}else{
			CompileRunJavaProgram compileRunJavaProgram =  new CompileRunJavaProgram();
			String dir = "src/test123/javaCompiler/";
			File file =  new File(dir);
			file.mkdirs();
			commonUtil.createFile(dir +"HelloWorld", code, "java");
			compileRunJavaProgram.runProcess("javac -cp src "+dir+"HelloWorld.java");
			return compileRunJavaProgram.runProcess("java -cp "+dir+" HelloWorld").toString();
		}
		return "";
	}
}
