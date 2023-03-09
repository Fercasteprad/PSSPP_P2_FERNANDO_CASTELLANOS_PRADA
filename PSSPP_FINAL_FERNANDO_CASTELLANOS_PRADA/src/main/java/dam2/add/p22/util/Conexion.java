package dam2.add.p22.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Conexion {
	
	static Properties prop = ConfigService.datosConexion();
	static String bd = prop.getProperty("nombre");
	static String login = prop.getProperty("login");
	static String password = prop.getProperty("pass");
	static String host = prop.getProperty("host");
	
//	static String bd = "usuarios_login";
//	static String login = "root";
//	static String password = "";
//	static String host = "127.0.0.1";
	
	static Connection conexion; //atributo para guardar el objeto Connection
	

	public static void setProp(Properties prop) {
		Conexion.prop = prop;
	}

	public static Connection getConexion() {
	    if (conexion == null) {
	    	crearConexion();
	    }
	    return conexion;
    }
    
     //devuelve true si se ha creado correctamente
    private static boolean crearConexion() {
    	    	
	    try {
	        //cargo el driver
	        Class.forName(prop.getProperty("driver"));
	    	String url = prop.getProperty("url");
	    	conexion = DriverManager.getConnection(url + host + "/"+ bd, login, password);
	    	
	    	//String url = "jdbc:sqlite:"+"./ficheros/misqlite.db";
	        //conexion = DriverManager.getConnection(url);
	        
	    	conexion.setAutoCommit(false);
	        
	    	// Para comprobar el driver JDBC utilizado
            //DatabaseMetaData meta = conexion.getMetaData();
            //System.out.println("The driver name is " + meta.getDriverName());

	        
	    } catch (SQLException e) {
	    	System.out.println(e);
	    	return false;
	    }
	    catch (Exception e) {
	    	System.out.println(e);
	    	return false;
	    }
	    return true;
    }
    
//    private static boolean crearConexion() {
//    	
//	    try {
//	        //cargo el driver
//	        Class.forName("com.mysql.cj.jdbc.Driver");
//	    	String url = "jdbc:mysql://";
//	    	conexion = DriverManager.getConnection(url + host + "/"+ bd, login, password);
//	    	
//	    	//String url = "jdbc:sqlite:"+"./ficheros/misqlite.db";
//	        //conexion = DriverManager.getConnection(url);
//	        
//	    	conexion.setAutoCommit(false);
//	        
//	    	// Para comprobar el driver JDBC utilizado
//            //DatabaseMetaData meta = conexion.getMetaData();
//            //System.out.println("The driver name is " + meta.getDriverName());
//
//	        
//	    } catch (SQLException e) {
//	    	System.out.println(e);
//	    	return false;
//	    }
//	    catch (Exception e) {
//	    	System.out.println(e);
//	    	return false;
//	    }
//	    return true;
//    }

    public static void desconectar(){
    	try {
    		conexion.close();
            conexion = null;
 
    	
    	} catch (SQLException e) {
    		System.out.println("Error al cerrar la conexion");
        }
    }
      
	

}
