package dam2.add.p22.service;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

import dam2.add.p22.dao.UsuarioDAO;
import dam2.add.p22.dao.UsuarioHIB;
import dam2.add.p22.dao.UsuarioMEM;
import dam2.add.p22.model.Usuario;
import dam2.add.p22.util.ConfigService;

public class UsuariosService {

	private static Properties propApp = configuracionModeloPersistencia();
	private static String persistencia = datoPersistencia();
	
	public static String datoPersistencia () {
		
		String persis = propApp.getProperty("persistencia");
		return persis;
		
		
	}
	
	public static Properties configuracionModeloPersistencia() {
		
		Properties propiedades = ConfigService.modeloPersistencia();
		return propiedades;
		
	}

	public static ArrayList<Usuario> listarUsuarios() {

		if (persistencia.equals("jdbc")) {
			ArrayList<Usuario> usuariosPrueba = UsuarioDAO.devolverUsuarios();
			return usuariosPrueba;
		}

		else {
			ArrayList<Usuario> usuariosPrueba = UsuarioMEM.getListaUsuarios();
			return usuariosPrueba;
		}

	}

	public static void anadirUsuario(Usuario nuevoUsuario) {

		if (persistencia.equals("jdbc")) {
			//UsuarioDAO.anadirContacto(nuevoUsuario);
			UsuarioHIB.anadirContacto(nuevoUsuario);
		}

		else {
			UsuarioMEM.anadirContacto(nuevoUsuario);
		}

	}

	public static boolean existeCorreo(String correoComprobar) {

		if (persistencia.equals("jdbc")) {
			boolean existeCorreo = UsuarioDAO.comprobarCorreo(correoComprobar);
			return existeCorreo;
		}

		else {
			boolean existeCorreo = UsuarioMEM.comprobarCorreo(correoComprobar);
			return existeCorreo;
		}

	}

	public static boolean comprobarDatosAcceso(String usuario, String pass) {

		if (persistencia.equals("jdbc")) {
			boolean acceso = UsuarioDAO.comprobarDatosAcceso(usuario, pass);
			return acceso;
		}

		else {
			boolean acceso = UsuarioMEM.comprobarDatosAcceso(usuario, pass);
			return acceso;
		}

	}

	public static boolean comprobarPass(String pass1, String pass2) {

		boolean passIguales = false;

		if (pass1.equals(pass2)) {
			passIguales = true;
		}

		return passIguales;

	}

	public static Usuario devolverUsuario(String correo) {

		if (persistencia.equals("jdbc")) {
			//Usuario usuario = UsuarioDAO.devolverUsuario(correo);
			Usuario usuario = UsuarioHIB.devolverUsuario(correo);
			return usuario;
		}

		else {
			Usuario usuario = UsuarioMEM.devolverUsuario(correo);
			return usuario;
		}

	}

	public static Usuario devolverUsuarioId(int idUsuario) {

		if (persistencia.equals("jdbc")) {
			//Usuario usuario = UsuarioDAO.devolverUsuarioId(idUsuario);
			Usuario usuario = UsuarioHIB.devolverUsuarioId(idUsuario);
			return usuario;
		}

		else {
			Usuario usuario = UsuarioMEM.devolverUsuarioId(idUsuario);
			return usuario;
		}

	}

	public static void actualizarUsuario(int idUsuario, Usuario usuarioActualizado) {

		if (persistencia.equals("jdbc")) {
			//UsuarioDAO.actualizarUsuario(idUsuario, usuarioActualizado);
			UsuarioHIB.actualizarUsuario(idUsuario, usuarioActualizado);
		}

		else {
			UsuarioMEM.actualizarUsuario(idUsuario, usuarioActualizado);
		}

	}

	public static boolean comprobarPass(int idUsuario, String pass) {

		if (persistencia.equals("jdbc")) {
			boolean comprobacionPass = UsuarioDAO.comprobarPass(idUsuario, pass);
			return comprobacionPass;
		}

		else {
			boolean comprobacionPass = UsuarioMEM.comprobarPass(idUsuario, pass);
			return comprobacionPass;
		}

	}

	public static ArrayList<Usuario> devolverUsuarios() {

		if (persistencia.equals("jdbc")) {
			//ArrayList<Usuario> listaUsuarios = UsuarioDAO.devolverUsuarios();
			ArrayList<Usuario> listaUsuarios = UsuarioHIB.devolverUsuarios();
			return listaUsuarios;
		}

		else {
			ArrayList<Usuario> listaUsuarios = UsuarioMEM.devolverUsuarios();
			return listaUsuarios;
		}

	}

	public static ArrayList<Usuario> buscarUsuarios(String texto) {

		if (persistencia.equals("jdbc")) {
			ArrayList<Usuario> listaUsuarios = UsuarioDAO.buscadorUsuarios(texto);
			return listaUsuarios;
		}

		else {
			ArrayList<Usuario> listaUsuarios = UsuarioMEM.buscadorUsuarios(texto);
			return listaUsuarios;
		}

	}

	public static void eliminarUsuario(int id) {

		if (persistencia.equals("jdbc")) {
			//UsuarioDAO.eliminarUsuario(id);
			UsuarioHIB.eliminarUsuario(id);
		}

		else {
			UsuarioMEM.eliminarUsuario(id);
		}

	}
	
	public static String encriptar(String pass) {
		
		Base64 base64 = new Base64();
		String passEncriptada = new String (base64.encode(pass.getBytes()));
		return passEncriptada;
		
	}

}
