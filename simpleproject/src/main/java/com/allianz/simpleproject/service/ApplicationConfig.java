package com.allianz.simpleproject.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
public class ApplicationConfig extends Application{
	public static final String PROPERTIES_FILE = "db.properties";
	public static Properties properties = new Properties();

	private Properties readProperties() {
	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
	    if (inputStream != null) {
	        try {
	            properties.load(inputStream);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return properties;
	}

	@Override
	public Set<Class<?>> getClasses() {     
	    // Read the properties file
	    readProperties();

	    // Set up your Jersey resources
	    Set<Class<?>> rootResources = new HashSet<Class<?>>();
	    rootResources.add(StudentService.class);
	    return rootResources;
	}

}
