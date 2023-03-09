package dam2.add.p22.dao;

import java.util.ArrayList;

import dam2.add.p22.model.Usuario;

public class UsuarioMEM {
	
	private static ArrayList<Usuario> listaUsuarios = poblarUsuarios();

	public static ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	
	public static ArrayList<Usuario> poblarUsuarios() {
		
		ArrayList<Usuario> usuariosPrueba = new ArrayList <Usuario>();
		Usuario usuario1 = new Usuario (1,"Fer", "Castellanos", "fercasteprad@gmail.com",667026260,"MTIzNA==","MTIzNA==", true);
		Usuario usuario2 = new Usuario (2,"Carlos", "López", "Carloslo@gmail.com",667546260,"MTIzNA==","MTIzNA==", false);
		Usuario usuario3 = new Usuario (3,"Antonio", "Pérez", "AntonioPe@gmail.com",667056260,"MTIzNA==","MTIzNA==", false);
		usuariosPrueba.add(usuario1);
		usuariosPrueba.add(usuario2);
		usuariosPrueba.add(usuario3);
		
		return usuariosPrueba;
		
	}
	
	public static void anadirContacto (Usuario usuarioNuevo) {
		
		usuarioNuevo.setId(listaUsuarios.size()+1);
		usuarioNuevo.setAdministrador(false);
		
		listaUsuarios.add(usuarioNuevo);
		
	}
	
	public static boolean comprobarCorreo (String correoComprobar) {
		
		
		boolean existeCorreo = false;
		
		for (int i = 0; i<listaUsuarios.size();i++) {
			
			Usuario auxUsuario = listaUsuarios.get(i);
			
			if (correoComprobar.equals(auxUsuario.getEmail())) {
				existeCorreo = true;
			}
		
		}
		
		return existeCorreo;
		
	}
	
	public static boolean comprobarDatosAcceso (String correo, String pass) {
		
		boolean acceso = false;
		
		for (int i = 0; i<listaUsuarios.size();i++) {
			
			Usuario auxiliar = listaUsuarios.get(i);
			if (auxiliar.getEmail().equalsIgnoreCase(correo) && auxiliar.getPass().equals(pass)) {
				acceso = true;
			}
			
		}
		
		return acceso;
		
	}
	
	public static Usuario devolverUsuario (String correo) {
		
		Usuario usuario = new Usuario();
		
		for (int i = 0; i<listaUsuarios.size();i++) {
			
			Usuario auxiliar = listaUsuarios.get(i);
			if (auxiliar.getEmail().equalsIgnoreCase(correo)) {
				usuario = auxiliar;
			}
			
		}
		
		return usuario;
		
		
	}
	
	public static Usuario devolverUsuarioId (int idUsuario) {
		
		Usuario usuario = new Usuario();
		
		for (int i = 0; i<listaUsuarios.size();i++) {
			
			Usuario auxiliar = listaUsuarios.get(i);
			if (auxiliar.getId() == idUsuario) {
				usuario = auxiliar;
			}
			
		}
		
		return usuario;
		
	}
	
	public static void actualizarUsuario (int idUsuario, Usuario usuarioActualizado) {
		
		for (int i = 0; i<listaUsuarios.size();i++) {
			
			Usuario auxiliar = listaUsuarios.get(i);
			if (auxiliar.getId() == idUsuario) {
				listaUsuarios.remove(i);
			}
			
		}
		
		listaUsuarios.add(usuarioActualizado);
		
	}
	
	public static boolean comprobarPass (int idUsuario, String pass) {
		
		boolean comprobacionPass = false;
		for (int i = 0; i<listaUsuarios.size();i++) {
			
			Usuario auxiliar = listaUsuarios.get(i);
			if (auxiliar.getId() == idUsuario && auxiliar.getPass().equals(pass)) {
				comprobacionPass = true;
			}
			
		}
		return comprobacionPass;
		
		
	}
	
	public static ArrayList<Usuario> devolverUsuarios () {
		
		ArrayList<Usuario> listaUsers = new ArrayList<Usuario>();
		
		for (int i = 0; i<listaUsuarios.size();i++) {
			Usuario auxiliar = listaUsuarios.get(i);
			if (!(auxiliar.isAdministrador())) {
				listaUsers.add(auxiliar);
			}
			
		}
		return listaUsers;
		
	}
	
	public static ArrayList<Usuario> buscadorUsuarios (String texto){
		
		ArrayList<Usuario> listaUsers = new ArrayList<Usuario>();
		
		for (int i = 0; i<listaUsuarios.size();i++) {
			Usuario auxiliar = listaUsuarios.get(i);
			if ((auxiliar.getNombre().contains(texto) || auxiliar.getApellidos().contains(texto) || auxiliar.getEmail().contains(texto)) && !(auxiliar.isAdministrador())) {
				listaUsers.add(auxiliar);
			}
			
		}
		return listaUsers;
		
		
	}
	
	public static void eliminarUsuario (int idUsuario) {
				
		for (int i = 0; i<listaUsuarios.size();i++) {
			Usuario auxiliar = listaUsuarios.get(i);
			if (auxiliar.getId() == idUsuario) {
				listaUsuarios.remove(i);
			}
			
		}
		
		
	}

}
