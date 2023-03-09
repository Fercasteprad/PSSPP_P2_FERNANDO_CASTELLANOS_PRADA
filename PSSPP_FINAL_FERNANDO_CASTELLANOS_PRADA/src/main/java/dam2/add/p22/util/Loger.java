package dam2.add.p22.util;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Loger {
	
	private static Logger log = Logger.getLogger(Loger.class);
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure(ConfigService.rutaTomcat()+"/ficheros/log4j.properties");
	
	}

}
