package by.academy.alekhno.web.bundle;

import java.util.ResourceBundle;

public class Bundle {
	private static final String RESOURCE_PATH = "resources";
	
	public static String getResource(String key){
		ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_PATH);
	     return bundle.getString(key);
	}
}
