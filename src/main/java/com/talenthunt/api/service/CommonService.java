package com.talenthunt.api.service;

import java.util.Date;
import java.util.Random;

public class CommonService {

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
}
