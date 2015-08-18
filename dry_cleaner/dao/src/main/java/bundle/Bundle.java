package bundle;


import java.util.ResourceBundle;

public class Bundle {
	 private static String pathBaseResource = "base";
	 private static String pathQueryResource = "query";
	 private static String pathExceptionResource = "exception";

	 public static String getBaseResource(String key) {
		 ResourceBundle bundle = ResourceBundle.getBundle(pathBaseResource);
	     return bundle.getString(key);
	    }
	 
	 public static String getQueryResource(String key) {
		 ResourceBundle bundle = ResourceBundle.getBundle(pathQueryResource);
	     return bundle.getString(key);
	    }
	 
	 public static String getExceptionMessage(String key) {
		 ResourceBundle bundle = ResourceBundle.getBundle(pathExceptionResource);
	     return bundle.getString(key);
	    }
}
