package by.academy.alekhno.bundle;


import java.util.ResourceBundle;

public class Bundle {
	 private static String pathQueryResource = "query";
	 
	 public static String getQueryResource(String key) {
		 ResourceBundle bundle = ResourceBundle.getBundle(pathQueryResource);
	     return bundle.getString(key);
	    }
	 
}
