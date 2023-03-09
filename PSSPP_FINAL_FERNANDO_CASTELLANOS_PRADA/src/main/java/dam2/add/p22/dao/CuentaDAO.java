package dam2.add.p22.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dam2.add.p22.model.Cuenta;
import dam2.add.p22.model.Usuario;
import dam2.add.p22.util.Conexion;

public class CuentaDAO {
	
	private static Connection conexion;
	private static Statement st;
	
	public static void crearCuenta(Cuenta cuenta) {
		
		int idNuevo = conteoCuentas();
		conexion = Conexion.getConexion();
		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "INSERT INTO cuentas (idCuenta, idUsuario, dinero) VALUES (?, ?, ?)";
				PreparedStatement ps = conexion.prepareStatement(query);

				ps.setInt(1, idNuevo);
				ps.setInt(2, cuenta.getIdUsuario());
				ps.setLong(3, cuenta.getDinero());


				int resultado = ps.executeUpdate();

				if (resultado == 0)
					System.out.println("NO se ha podido insertar");
				conexion.commit();

				ps.close();

				st.close();
				Conexion.desconectar();
			} else {
				System.out.println("Conexion no realizada");
			}
		} catch (SQLException e) {

			System.out.println(e);
			if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		}

	}
	
	public static Cuenta devolverCuenta(int idUsuario) {

		Cuenta sacarCuenta = new Cuenta();

		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT * FROM cuentas WHERE idUsuario = '" + idUsuario + "'";
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {

					sacarCuenta.setIdCuenta(rs.getInt(1));
					sacarCuenta.setIdUsuario(rs.getInt(2));
					sacarCuenta.setDinero(rs.getInt(3));
					
				}
				rs.close();
				ps.close();

				st.close();
				Conexion.desconectar();
			} else {
				System.out.println("Conexion no realizada");
			}
		} catch (SQLException e) {

			System.out.println(e);
			if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		}

		return sacarCuenta;
	}
	
	public static void ingresarDinero(int idUsuario, long dineroIngreso) {

		Cuenta sacarCuenta = devolverCuenta(idUsuario);

		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				sacarCuenta.setDinero(sacarCuenta.getDinero()+dineroIngreso);

				String query = "UPDATE cuentas SET dinero = '" + sacarCuenta.getDinero() + "' WHERE idUsuario = '"+idUsuario+"'";

				int resultado = st.executeUpdate(query);
				
				if (resultado == 0)
					System.out.println("NO se ha podido actualizar");
				conexion.commit();
				}

				st.close();
				Conexion.desconectar();

		} catch (SQLException e) {

			System.out.println(e);
			if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void extraerDinero(int idUsuario, long dineroIngreso) {

		Cuenta sacarCuenta = devolverCuenta(idUsuario);

		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {
				
				sacarCuenta.setDinero(sacarCuenta.getDinero()-dineroIngreso);

				String query = "UPDATE cuentas SET dinero = '" + sacarCuenta.getDinero() + "' WHERE idUsuario = '"+idUsuario+"'";

				int resultado = st.executeUpdate(query);
				
				if (resultado == 0)
					System.out.println("NO se ha podido actualizar");
				conexion.commit();
				}

				st.close();
				Conexion.desconectar();

		} catch (SQLException e) {

			System.out.println(e);
			if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		}
	}
	
	public static int conteoCuentas() {
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		conexion = Conexion.getConexion();
		
		int idCount = -1;
		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT * FROM cuentas";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {
					Cuenta cuenta = new Cuenta();
					cuenta.setIdCuenta(rs.getInt(1));
					cuenta.setIdCuenta(rs.getInt(2));
					cuenta.setDinero(rs.getLong(3));
					cuentas.add(cuenta);

				}
				//En este caso se comprueba el id del último usuario registrado
				idCount = cuentas.size()+1;
				
				rs.close();
				ps.close();

				st.close();
				
			} else {
				System.out.println("Conexion no realizada");
			}
		} catch (SQLException e) {

			System.out.println(e);
			if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		}
		return idCount;
	}
	

}
