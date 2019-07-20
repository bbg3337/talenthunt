package com.talenthunt.api.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.AuthenticationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public static String generatePassword(int j) {
		Random random = new Random((new Date()).getTime());

		char[] values = { '@', '#', '%', '*', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9' };
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < j; i++) {
			int idx = random.nextInt(values.length);
			out.append(values[idx]);
		}
		return out.toString();
	}
	
	public static String generateText(int j) {
		Random random = new Random((new Date()).getTime());

		char[] values = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9' };
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < j; i++) {
			int idx = random.nextInt(values.length);
			out.append(values[idx]);
		}
		return out.toString();
	}
	public boolean sendEmail(String to,String subject,String text) throws AuthenticationFailedException{
		 
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(to);
	        msg.setSubject(subject);
	        msg.setText(text);
	        javaMailSender.send(msg);
	        return true;
		}catch(MailAuthenticationException e){
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			 return false;
		}
		
	}
	
	public boolean isListEmpty(List list){
		return list == null || list.isEmpty();
	}
}
