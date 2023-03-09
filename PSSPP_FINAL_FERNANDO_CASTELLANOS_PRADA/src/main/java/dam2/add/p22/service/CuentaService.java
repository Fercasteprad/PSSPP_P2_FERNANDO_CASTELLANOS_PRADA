package dam2.add.p22.service;



import dam2.add.p22.dao.CuentaDAO;
import dam2.add.p22.model.Cuenta;


public class CuentaService {
	
public static void crearCuenta(Cuenta cuenta) {
		
		CuentaDAO.crearCuenta(cuenta);

	}
	
	public static Cuenta devolverCuenta(int idUsuario) {
		
		Cuenta extraerCuenta = CuentaDAO.devolverCuenta(idUsuario);
		return extraerCuenta;

	}
	
	public static void ingresarDinero(int idUsuario, long dineroIngreso) {
		
		CuentaDAO.ingresarDinero(idUsuario, dineroIngreso);

	}
	
	public static void extraerDinero(int idUsuario, long dineroIngreso) {

		CuentaDAO.extraerDinero(idUsuario, dineroIngreso);
		
		
	}
	

}
