package com.kingmed.dp.servlet.config;

/**
 * Generic constants for Bootstrapping the WebApp
 * 
 * @author pablo.biagioli
 *
 */
public class GenericBootstrapConstants {

	/**
	 * packages where the RestEasy Resources are, separated by commas
	 */
	//public static final String REST_EASY_REST_PACKAGES="com.kingmed.dp.rest";
        public static final String REST_EASY_REST_PACKAGES="com.kingmed.dp.gateway.web.service";
	
	public static final String REST_EASY_REST_PACKAGES_SUFFIX=".service";
	
	/**
	 * main properties file that gets loaded at first
	 */
	public static final String BOOTSTRAP_PROPERTIES_FILE="bootstrap.properties";
	
	public static final String GATEWAY_SYS = "gateway.properties";
}
