package resources.bundle;

import java.util.ResourceBundle;

public class Bundle {
	 private static String pathBaseResource = "../base";
	 

	 public static String getBaseResource(String key) {
		 ResourceBundle bundle = ResourceBundle.getBundle(pathBaseResource);
	     return bundle.getString(key);
	    }
}
