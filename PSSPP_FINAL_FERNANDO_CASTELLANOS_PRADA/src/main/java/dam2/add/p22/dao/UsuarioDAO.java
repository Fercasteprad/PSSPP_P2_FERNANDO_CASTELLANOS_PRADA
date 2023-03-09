package dam2.add.p22.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dam2.add.p22.model.Usuario;
import dam2.add.p22.util.Conexion;

public class UsuarioDAO {

	private static Connection conexion;
	private static Statement st;

	public static void anadirContacto(Usuario usuarioNuevo) {
		
		int idNuevo = conteoUsuarios();
		conexion = Conexion.getConexion();
		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "INSERT INTO usuarios (id, nombre, apellidos, email, telefono, pass, pass2, administrador) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conexion.prepareStatement(query);

				ps.setInt(1, idNuevo);
				ps.setString(2, usuarioNuevo.getNombre());
				ps.setString(3, usuarioNuevo.getApellidos());
				ps.setString(4, usuarioNuevo.getEmail());
				ps.setInt(5, usuarioNuevo.getTelefono());
				ps.setString(6, usuarioNuevo.getPass());
				ps.setString(7, usuarioNuevo.getPass2());
				ps.setInt(8, 0); // 0 es igual a usuario normal >0 es igual a administrador

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

	public static boolean comprobarCorreo(String correoComprobar) {

		boolean existeCorreo = false;

		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT * FROM usuarios WHERE email = '" + correoComprobar + "'";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {

					String nombre = rs.getString(2); // rs.getString("nombre");

					if (nombre != null) {
						existeCorreo = true;
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

		return existeCorreo;
	}

	public static boolean comprobarDatosAcceso(String correo, String pass) {
		boolean acceso = false;

		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT * FROM usuarios WHERE email = '" + correo + "'";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {

					String nombre = rs.getString(2); // rs.getString("nombre");
					String passBBDD = rs.getString(6); // rs.getString("nombre");

					if (nombre != null && passBBDD.equals(pass)) {
						acceso = true;
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

		return acceso;
	}

	public static Usuario devolverUsuario(String correo) {
		conexion = Conexion.getConexion();
		Usuario envioUsuario = new Usuario();
		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT * FROM usuarios WHERE email = '" + correo + "'";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {

					int id = rs.getInt(1); // rs.getString("nombre");
					String nombre = rs.getString(2); // rs.getString("nombre");
					String apellidos = rs.getString(3); // rs.getString("apellidos");
					String email = rs.getString(4); // rs.getString("email");
					int telefono = rs.getInt(5); // rs.getInt("telefono");
					String pass = rs.getString(6); // rs.getString("pass");
					String pass1 = rs.getString(7); // rs.getString("pass1");
					int admin = rs.getInt(8);
					if (admin == 0) {
						envioUsuario.setAdministrador(false);
					} else {
						envioUsuario.setAdministrador(true);
					}
					envioUsuario.setNombre(nombre);
					envioUsuario.setApellidos(apellidos);
					envioUsuario.setEmail(email);
					envioUsuario.setTelefono(telefono);
					envioUsuario.setPass(pass);
					envioUsuario.setPass2(pass1);
					envioUsuario.setId(id);
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
		return envioUsuario;
	}

	public static Usuario devolverUsuarioId(int idUsuario) {

		Usuario usuario = new Usuario();

		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT * FROM usuarios WHERE id = '" + idUsuario + "'";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {

					usuario.setId(rs.getInt(1));
					usuario.setNombre(rs.getString(2));
					usuario.setApellidos(rs.getString(3));
					usuario.setEmail(rs.getString(4));
					usuario.setTelefono(rs.getInt(5));
					usuario.setPass(rs.getString(6));
					usuario.setPass2(rs.getString(7));
					usuario.setAdministrador(false);
					int admin = rs.getInt(8);

					if (admin > 0) {
						usuario.setAdministrador(true);
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

		return usuario;
	}

	public static void actualizarUsuario(int idUsuario, Usuario usuarioActualizado) {

		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				int administrador = 0;
				if (usuarioActualizado.isAdministrador()) {
					administrador = 1;
				}

				String query = "UPDATE usuarios SET  nombre = '" + usuarioActualizado.getNombre() + "' , apellidos ='"
						+ usuarioActualizado.getApellidos() + "',`email`='" + usuarioActualizado.getEmail()
						+ "',`telefono`='" + usuarioActualizado.getTelefono() + "',`pass`='"
						+ usuarioActualizado.getPass() + "',`pass2`='" + usuarioActualizado.getPass2()
						+ "',`administrador`='" + administrador + "' WHERE id = '"+idUsuario+"'";
				// System.out.println(query);
				
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

	public static boolean comprobarPass(int idUsuario, String pass) {
		boolean comprobacionPass = false;
		
		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT * FROM usuarios WHERE id = '"+idUsuario+"'";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {

					String passBBDD = rs.getString(6); // rs.getString("nombre");

					if (passBBDD.equals(pass)) {
						comprobacionPass = true;
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
		
		return comprobacionPass;
	}

	public static ArrayList<Usuario> devolverUsuarios() {
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT * FROM usuarios";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt(1));
					usuario.setNombre(rs.getString(2));
					usuario.setApellidos(rs.getString(3));
					usuario.setEmail(rs.getString(4));
					usuario.setTelefono(rs.getInt(5));
					usuario.setPass(rs.getString(6));
					usuario.setPass2(rs.getString(7));
					usuario.setAdministrador(false);
					int admin = rs.getInt(8);

					if (admin == 0) {
						
						usuarios.add(usuario);
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
		
		return usuarios;
	}

	public static ArrayList<Usuario> buscadorUsuarios(String texto) {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		ArrayList<Usuario> usuariosBuscador = new ArrayList<Usuario>();
		
		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT * FROM usuarios";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt(1));
					usuario.setNombre(rs.getString(2));
					usuario.setApellidos(rs.getString(3));
					usuario.setEmail(rs.getString(4));
					usuario.setTelefono(rs.getInt(5));
					usuario.setPass(rs.getString(6));
					usuario.setPass2(rs.getString(7));
					usuario.setAdministrador(false);
					int admin = rs.getInt(8);

					if (admin > 0) {
						usuario.setAdministrador(true);
					}
					usuarios.add(usuario);

				}
				
				for (int i = 0; i<usuarios.size();i++) {
					Usuario auxiliar = usuarios.get(i);
					if ((auxiliar.getNombre().toLowerCase().contains(texto.toLowerCase()) || auxiliar.getApellidos().toLowerCase().contains(texto.toLowerCase()) || auxiliar.getEmail().toLowerCase().contains(texto.toLowerCase())) && !(auxiliar.isAdministrador())) {
						usuariosBuscador.add(auxiliar);
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
		
		return usuariosBuscador;
	}

	public static void eliminarUsuario(int idUsuario) {
		
		conexion = Conexion.getConexion();

		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "DELETE FROM usuarios WHERE id = '"+idUsuario+"'";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);
				
				int resultado = ps.executeUpdate();
				
				if (resultado == 0) {
					System.out.println("NO se ha podido borrar");
				}

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

	public static int conteoUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		conexion = Conexion.getConexion();
		
		int idCount = -1;
		try {
			st = conexion.createStatement();

			if (conexion != null) {

				String query = "SELECT * FROM usuarios";
				// System.out.println(query);
				PreparedStatement ps = conexion.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				// Columnas de la tabla: nombre, apellidos y telefono
				while (rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt(1));
					usuario.setNombre(rs.getString(2));
					usuario.setApellidos(rs.getString(3));
					usuario.setEmail(rs.getString(4));
					usuario.setTelefono(rs.getInt(5));
					usuario.setPass(rs.getString(6));
					usuario.setPass2(rs.getString(7));
					usuario.setAdministrador(false);
					int admin = rs.getInt(8);

					if (admin > 0) {
						usuario.setAdministrador(true);
					}
					usuarios.add(usuario);

				}
				//En este caso se comprueba el id del último usuario registrado
				idCount = usuarios.get(usuarios.size()-1).getId()+1;
				
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
