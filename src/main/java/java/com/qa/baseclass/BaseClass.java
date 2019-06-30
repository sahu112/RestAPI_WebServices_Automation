package java.com.qa.baseclass;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
	
	public int ATATUS_CODE_200=200;
	
	public Properties prop;
	
	public BaseClass() {
	
	try {
		FileInputStream file=new FileInputStream("D:\\JavaProject\\RestWebServicesAutomation\\src\\main\\java\\com\\qa\\config\\config.Properties") ;
		prop.load(file);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {	
		
		e.printStackTrace();
	}
}
}