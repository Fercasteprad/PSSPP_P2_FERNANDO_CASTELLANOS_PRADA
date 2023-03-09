package dam2.add.p22.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dam2.add.p22.model.Provincia;
import dam2.add.p22.util.Conexion;


public class ProvinciaDAO {
	
	private static Connection conexion;
	private static Statement st;
	
	public static ArrayList<Provincia> getProvincias(){
		
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		
		conexion = Conexion.getConexion();
	
		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT id,nombre FROM provincias ORDER BY nombre ASC";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {
					Provincia auxiliar = new Provincia();
					
					auxiliar.setId(rs.getString(1));
					auxiliar.setNm(rs.getString(2));

					if (auxiliar.getId().length() == 2) {
						provincias.add(auxiliar);
					}
											

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
		
		return provincias;
		
		
		
	}
	
	public static ArrayList<Provincia> getMunicipios(){
		
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		
		conexion = Conexion.getConexion();
		
		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT id,nombre FROM provincias ORDER BY id ASC";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {
					Provincia auxiliar = new Provincia();
					
					auxiliar.setId(rs.getString(1));
					auxiliar.setNm(rs.getString(2));

					if (auxiliar.getId().length() > 2) {
						provincias.add(auxiliar);
					}
											

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
		
		return provincias;
		
		
		
	}
	
	public static ArrayList<Provincia> getMunicipios(String provincia){
		
		
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		
		conexion = Conexion.getConexion();
		
		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT id,nombre FROM provincias ORDER BY id ASC";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {
					Provincia auxiliar = new Provincia();
					
					auxiliar.setId(rs.getString(1));
					auxiliar.setNm(rs.getString(2));

					if (auxiliar.getId().length() > 2 && auxiliar.getId().substring(0, 2).equals(provincia)) {
						provincias.add(auxiliar);
					}
											

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
		
		return provincias;
		
		
	}
	
	public static ArrayList<Provincia> buscarMunicipios(String municipio){
		
		
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		
		conexion = Conexion.getConexion();
		
		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT id,nombre FROM provincias ORDER BY id ASC";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {
					Provincia auxiliar = new Provincia();
					
					auxiliar.setId(rs.getString(1));
					auxiliar.setNm(rs.getString(2));

					if (auxiliar.getNm().equalsIgnoreCase(municipio) || auxiliar.getNm().toLowerCase().contains(municipio.toLowerCase())) {
						provincias.add(auxiliar);
					}
											

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
		
		return provincias;
		
		
	}
	
	public static String nombreProvincia(ArrayList<Provincia> provincias, String numeroProvincia) {
		
		String provincia = "";
		
		for (int i = 0; i<provincias.size();i++) {
			
			if (provincias.get(i).getId().equals(numeroProvincia)){
				
				provincia = provincias.get(i).getNm();
				
			}
			
		}
		
		return provincia;
		
	}
	
//	private static Connection conexion;
//	private static Statement st;
	
//	public static void anadirProvincia(Provincia provincia) {
//		Session session = HibernateManager.getSessionFactory().openSession();
//		Transaction tx = session.beginTransaction(); // Se crea una transaccion
//		session.save(provincia);// Guarda el objeto creado en la BBDD.
//		tx.commit(); // Materializa la transaccion
//		session.close();
//	}
	
//	public static void anadirProvincia(Provincia provinciaNuevo) {
//		
//		conexion = Conexion.getConexion();
//		try {
//			st = conexion.createStatement();
//
//			if (conexion != null) {
//
//				String query = "INSERT INTO provincias (id, nombre) VALUES (?, ?)";
//				PreparedStatement ps = conexion.prepareStatement(query);
//
//	
//				ps.setString(1, provinciaNuevo.getId());
//				ps.setString(2, provinciaNuevo.getNm());
//
//				int resultado = ps.executeUpdate();
//
//				if (resultado == 0)
//					System.out.println("NO se ha podido insertar");
//				conexion.commit();
//
//				ps.close();
//
//				st.close();
//				Conexion.desconectar();
//			} else {
//				System.out.println("Conexion no realizada");
//			}
//		} catch (SQLException e) {
//
//			System.out.println(e);
//			if (conexion != null) {
//				try {
//					conexion.rollback();
//				} catch (SQLException e1) {
//
//					e1.printStackTrace();
//				}
//			}
//		}
//
//	}

}
