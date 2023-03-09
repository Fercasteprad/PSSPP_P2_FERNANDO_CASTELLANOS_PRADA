package dam2.add.p22.model;

public class Cuenta {
	
	private int idCuenta;
	private int idUsuario;
	private long dinero;
	
	public Cuenta() {

	}

	public Cuenta(int idUsuario, long dinero) {
		this.idUsuario = idUsuario;
		this.dinero = dinero;
	}


	public Cuenta(int idCuenta, int idUsuario, long dinero) {
		this.idCuenta = idCuenta;
		this.idUsuario = idUsuario;
		this.dinero = dinero;
	}
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getDinero() {
		return dinero;
	}
	public void setDinero(long dinero) {
		this.dinero = dinero;
	}
	
	
}
