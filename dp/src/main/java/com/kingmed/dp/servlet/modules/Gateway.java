package com.kingmed.dp.servlet.modules;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.kingmed.dp.servlet.config.GenericBootstrapConstants;

public class Gateway {

	public static String gateways(String key) {
		Properties prop = new Properties();
		try {
			InputStream is = new Gateway().getClass().getResourceAsStream("/"+GenericBootstrapConstants.GATEWAY_SYS);
			prop.load(is);   
            return prop.getProperty(key).trim();   
		}  catch (IOException e) {
	        System.out.println(e);
	    }
		return "";
	}
}
