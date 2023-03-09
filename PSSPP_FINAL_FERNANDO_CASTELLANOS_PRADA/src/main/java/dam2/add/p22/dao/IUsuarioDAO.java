package dam2.add.p22.dao;

import java.util.ArrayList;

import dam2.add.p22.model.Usuario;

public interface IUsuarioDAO {
	
	public void anadirContacto (Usuario usuarioNuevo);
	public boolean comprobarCorreo (String correoComprobar);
	public boolean comprobarDatosAcceso (String correo, String pass);
	public Usuario devolverUsuario (String correo);
	public Usuario devolverUsuarioId (int idUsuario);
	public void actualizarUsuario (int idUsuario, Usuario usuarioActualizado);
	public boolean comprobarPass (int idUsuario, String pass);
	public ArrayList<Usuario> devolverUsuarios ();
	public ArrayList<Usuario> buscadorUsuarios (String texto);
	public void eliminarUsuario (int idUsuario);
				


}
