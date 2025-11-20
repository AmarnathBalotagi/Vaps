package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class configReader {

	
	private static Properties Prop;
		
	public static Properties initProp() {
		
		Prop= new Properties();
		try {
			FileInputStream fis = new FileInputStream( System.getProperty("user.dir") + "/src/test/resources/config.properties");
			Prop.load(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return Prop;
		
	}
	
	public static String getProperty(String key) {
		if (key==null) {
			
			initProp();
		
		}
		return Prop.getProperty(key);
		
		
	}
		
		
		
		
	
}
