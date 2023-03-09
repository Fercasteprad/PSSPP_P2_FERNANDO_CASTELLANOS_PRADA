package dam2.add.p22.util;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConfigService {
	
	public static String ruta;
	
	public static Properties datosConexion() {
		
		String rutaTomcat = rutaTomcat();
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(rutaTomcat+"/properties/bbdd.properties")) {
		    prop.load(input);
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		return prop;
		
	}
	
	
	public static String rutaTomcat() {
			
		return ruta;		
				
	}
	
	public static Properties modeloPersistencia() {
		
		Properties prop = new Properties();
		
		String rutaTomcat = rutaTomcat();
		String rutaFichero = rutaTomcat+"/properties/app.properties";
		
		try {
			prop.load(new FileInputStream(rutaFichero));
		} catch (IOException ex) {
			System.out.println("No existe el fichero de propiedades");
			ex.printStackTrace();
		}
		
		return prop;
		
	}
	
	public static void establecerPersistencia(String modelo) {
		
		
		String rutaTomcat = rutaTomcat();
		String ruta = rutaTomcat+"/properties/app.properties";
		
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(ruta)) {
		    prop.load(input);
		} catch (IOException io) {
		    io.printStackTrace();
		}
				
		prop.setProperty("persistencia", modelo);
				
		try (OutputStream output = new FileOutputStream(ruta)) {
		    prop.store(output, "");
		} catch (IOException io) {
		    io.printStackTrace();
		}
		
		
	}
	
	public static Logger configLogger () {
		
		Logger log = Logger.getLogger(ConfigService.class);
		
		PropertyConfigurator.configure(ruta+"/properties/log4j.properties");
		
		return log;
	}
	

}
