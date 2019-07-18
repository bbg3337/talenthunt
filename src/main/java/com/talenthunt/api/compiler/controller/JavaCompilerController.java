package com.talenthunt.api.compiler.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talenthunt.api.service.CommonService;
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
		String dir = "src/javaCompiler/t"+CommonService.generateText(12)+"/";
		if(code == null){
			//error
		}else{
			CompileRunJavaProgram compileRunJavaProgram =  new CompileRunJavaProgram();
			
			File file =  new File(dir);
			file.mkdirs();
			String className  = commonUtil.validateJavaCode(code);
			String result = "";
			if(!"Package Key word not allowed".equals(className)){
				commonUtil.createFile(dir +className, code, "java");
				compileRunJavaProgram.runProcess("javac -cp src "+dir+className+".java");
				result = compileRunJavaProgram.runProcess("java -cp "+dir+" "+className).toString();
				commonUtil.deleteFolderWithFile(dir);
				return result;
			}else{
				return "Package Key word not allowed";
			}
			
		}
		return "";
	}
}
