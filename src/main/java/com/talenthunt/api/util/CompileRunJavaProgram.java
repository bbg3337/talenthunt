package com.talenthunt.api.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CompileRunJavaProgram {

    public static void main(String[] args) {
    	CompileRunJavaProgram compileRunJavaProgram = new CompileRunJavaProgram();
    	try {
           // runProcess("javac -cp src src/main/java/com/journaldev/files/Test.java");
            System.out.println("**********");
            compileRunJavaProgram.runProcess("java -cp src/test123/javaCompiler/ HelloWorld");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static StringBuilder printLines(String cmd, InputStream ins,StringBuilder stringBuilder) throws Exception {
        StringBuilder sb =  new StringBuilder();
        if(stringBuilder != null){
        	sb.append(stringBuilder.toString());
        }
        String line ;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
        	sb.append(line+"\n");
        }
        return sb;
      }

      public StringBuilder runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        StringBuilder sb = printLines(command + " stdout:", pro.getInputStream(),null);
        sb = printLines(command + " stderr:", pro.getErrorStream(),sb);
        //System.out.println("CompileRunJavaProgram.runProcess() " +sb);
        pro.waitFor();
        //System.out.println(command + " exitValue() " + pro.exitValue());
        return sb;
      }

}