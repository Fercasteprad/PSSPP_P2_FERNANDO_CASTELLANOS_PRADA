package dam2.add.p22.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import dam2.add.p22.dao.CuentaDAO;
import dam2.add.p22.dao.ProvinciaDAO;
import dam2.add.p22.model.Cuenta;
import dam2.add.p22.model.Provincia;
import dam2.add.p22.model.Usuario;
import dam2.add.p22.service.CuentaService;
import dam2.add.p22.service.MailService;
import dam2.add.p22.service.UsuariosService;
import dam2.add.p22.util.ConfigService;

/**
 * Servlet implementation class Form
 */
@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String ruta = request.getServletContext().getRealPath("");
		ConfigService.ruta = ruta;
		Logger log = ConfigService.configLogger();
				
		String vista = request.getParameter("view");
		String cerrarSesion = request.getParameter("sesion");
		String edit = request.getParameter("edit");
		String buscador = request.getParameter("buscador");
		String id = request.getParameter("id");
		String eliminar = request.getParameter("eliminar");
		String idioma = request.getParameter("idioma");
		String emailIngreso = request.getParameter("emailIngreso");
		String transferencia = request.getParameter("dineroTransferencia");
		String ingreso = request.getParameter("dineroIngreso");



		if (idioma != null) {

			if (idioma.equals("es")) {
				String idiomaSeleccionado = idioma;
				request.getSession().setAttribute("idioma", idiomaSeleccionado);
				log.info("Idioma cambiado a español");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}

			else {

				String idiomaSeleccionado = "en";
				request.getSession().setAttribute("idioma", idiomaSeleccionado);
				log.info("Idioma cambiado a inglés");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}

		}

		else if (vista != null && vista.equals("login")) { // Si pinchan en login en la pantalla principal
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}

		else if (vista != null && vista.equals("form")) { // Si pinchan en form en la pantalla principal
			ArrayList<Provincia> listaProvincias = ProvinciaDAO.getProvincias();
			ArrayList<Provincia> listaMunicipios = ProvinciaDAO.getMunicipios();
			request.getSession().setAttribute("municipios", listaMunicipios);
			request.getSession().setAttribute("provincias", listaProvincias);
			response.sendRedirect(request.getContextPath() + "/views/form.jsp");
		}

		else if (cerrarSesion != null && cerrarSesion.equals("close")) { // Si pinchan en cualquier momento cerrar

			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			log.info("El usuario " + usuario.getNombre() + " ha cerrado sesión");
			request.getSession().setAttribute("nombre", null);
			request.getSession().setAttribute("usuario", null);
			request.getSession().setAttribute("usuarios", null);
			request.getSession().setAttribute("confirmar", null);
			request.getSession().setAttribute("error", null);
			request.getSession().setAttribute("buscador", null);
			request.getSession().setAttribute("id", null);
			request.getSession().setAttribute("municipios", null);
			request.getSession().setAttribute("provincias", null);
			request.getSession().setAttribute("cuenta", null);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);

		}

		else if (cerrarSesion != null && cerrarSesion.equals("edit")) { // Si pinchan en el nombre

			request.getSession().setAttribute("error", null);
			response.sendRedirect(request.getContextPath() + "/views/edit.jsp");

		}

		else if (edit != null) { // Si pinchan en editar campos o Pass

			if (edit.equals("campos")) {

				request.getSession().setAttribute("confirmar", null);
				request.getSession().setAttribute("error", null);
				request.getSession().setAttribute("error", null);
				response.sendRedirect(request.getContextPath() + "/views/editcampos.jsp");
			}

			else if (edit.equals("campos") && id != null) {

				request.getSession().setAttribute("error", null);
				response.sendRedirect(request.getContextPath() + "/views/editcampos.jsp");

			}

			else if (edit.equals("camposad") && id != null) {
				int idInt = Integer.parseInt(id);

				Usuario usuarioEditar = UsuariosService.devolverUsuarioId(idInt);
				request.getSession().setAttribute("usuarioEditar", usuarioEditar);
				request.getSession().setAttribute("error", null);
				ArrayList<Usuario> listaUsuarios = UsuariosService.devolverUsuarios();
				request.getSession().setAttribute("usuarios", listaUsuarios);
				response.sendRedirect(request.getContextPath() + "/views/editcamposad.jsp");

			}

			else if (edit.equals("eliminar") && id != null) {

				request.getSession().setAttribute("id", id);
				response.sendRedirect(request.getContextPath() + "/views/home.jsp");

			}

			else {

				request.getSession().setAttribute("error", null);
				response.sendRedirect(request.getContextPath() + "/views/editpass.jsp");
			}

		}

		else if (buscador != null) {

			ArrayList<Usuario> usuariosBuscados = UsuariosService.buscarUsuarios(buscador);
			request.getSession().setAttribute("buscador", usuariosBuscados);
			request.getSession().setAttribute("id", null);
			response.sendRedirect(request.getContextPath() + "/views/home.jsp");

		}

		else if (eliminar != null) {

			if (eliminar.equals("no")) {

				request.getSession().setAttribute("id", null);
				response.sendRedirect(request.getContextPath() + "/views/home.jsp");

			}

			else {
				int idInt = Integer.parseInt(id);
				UsuariosService.eliminarUsuario(idInt);
				ArrayList<Usuario> listaUsuarios = UsuariosService.devolverUsuarios();
				request.getSession().setAttribute("usuarios", listaUsuarios);
				request.getSession().setAttribute("buscador", null);
				request.getSession().setAttribute("id", null);
				response.sendRedirect(request.getContextPath() + "/views/home.jsp");

			}

		}
		
		else if (emailIngreso != null) {
			
			Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
			Cuenta cuentaUsuario = CuentaService.devolverCuenta(usuarioSesion.getId());
			//si el mail de ingreso es diferente al del usuario y el correo que recibimos existe
			if(!(usuarioSesion.getEmail().equals(emailIngreso)) && UsuariosService.existeCorreo(emailIngreso)) {
				
				long dineroTransferencia = Long.parseLong(transferencia);
				
				if(dineroTransferencia > cuentaUsuario.getDinero()) {
					
					String mensaje = "La transferencia supera tu saldo disponible";
					request.getSession().setAttribute("error", mensaje);
					response.sendRedirect(request.getContextPath() + "/views/home.jsp");
					
				}
				
				else {
					
					Usuario usuarioRecibeTransferencia = UsuariosService.devolverUsuario(emailIngreso);
					CuentaService.extraerDinero(usuarioSesion.getId(), dineroTransferencia);
					CuentaService.ingresarDinero(usuarioRecibeTransferencia.getId(), dineroTransferencia);
					cuentaUsuario = CuentaService.devolverCuenta(usuarioSesion.getId());
					log.info(usuarioSesion.getNombre()+" ha enviado " + dineroTransferencia +"€ a "+usuarioRecibeTransferencia.getNombre());
					String mensaje = "Transferencia realizada correctamente";
//					try {
//						MailService.enviarMail(emailIngreso, transferencia, usuarioSesion.getNombre());
//					} catch (MessagingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					request.getSession().setAttribute("error", mensaje);
					request.getSession().setAttribute("cuenta", cuentaUsuario);
					response.sendRedirect(request.getContextPath() + "/views/home.jsp");
					
				}
				
			}
			
			else if (usuarioSesion.getEmail().equals(emailIngreso)){
				
				String mensaje = "No puedes realizar un ingreso a ti mismo";
				request.getSession().setAttribute("error", mensaje);
				response.sendRedirect(request.getContextPath() + "/views/home.jsp");
				
			}
			
			else {
				String mensaje = "No existe un usuario con ese correo";
				request.getSession().setAttribute("error", mensaje);
				response.sendRedirect(request.getContextPath() + "/views/home.jsp");
			}

		}
		
		else if (ingreso != null) {
			
			Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
			long dineroTransferencia = Long.parseLong(ingreso);
			CuentaService.ingresarDinero(usuarioSesion.getId(), dineroTransferencia);
			log.info(usuarioSesion.getNombre()+" ha realizado un ingreso de " + dineroTransferencia +"€");
			Cuenta cuentaUsuario = CuentaService.devolverCuenta(usuarioSesion.getId());
			request.getSession().setAttribute("cuenta", cuentaUsuario);
			response.sendRedirect(request.getContextPath() + "/views/home.jsp");
			
		}
		

		else {
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Logger log = ConfigService.configLogger();

		// Parametros vista LOGIN
		String usuarioLogin = request.getParameter("user"); // Parametro user del Login
		String passLogin = request.getParameter("passLogin"); // Parámetro pass del Login

		// Parametros vista FORM
		String valor_nombre = request.getParameter("nombre");
		String valor_apellido = request.getParameter("apellido");
		String valor_pass = request.getParameter("pass1");
		String valor_pass2 = request.getParameter("pass2");
		String valor_email = request.getParameter("email");
		String valor_telefono = request.getParameter("telefono");
		String valor_provincia = request.getParameter("provincia");
		String valor_municipio = request.getParameter("municipio");

		// Parametros vista EDITCAMPOS
		String nombreEdit = request.getParameter("nombreEdit");
		String apellidoEdit = request.getParameter("apellidoEdit");
		String emailEdit = request.getParameter("emailEdit");
		String telefonoEdit = request.getParameter("telefonoEdit");
		String provinciaEdit = request.getParameter("provinciaEdit");
		String municipioEdit = request.getParameter("municipioEdit");
		String id = request.getParameter("id");
		String editAdmin = request.getParameter("editAdmin");

		// Parametros vista EDITPASS
		String passActual = request.getParameter("passActual");
		String passNueva1 = request.getParameter("nuevaPass1");
		String passNueva2 = request.getParameter("nuevaPass2");
		// String id = request.getParameter("id"); También se usa este parámetro en
		// edición de contraseña

		ArrayList<Provincia> listaProvincias = ProvinciaDAO.getProvincias();

		// Si viene del Login pueden pasar tres cosas -> Acceso correcto, el correo no
		// existe o la contraseña es incorrecta
		if (usuarioLogin != null && passLogin != null) {
			passLogin = UsuariosService.encriptar(passLogin);
			// Si los datos de acceso son correctos te manda a la vista
			if (UsuariosService.existeCorreo(usuarioLogin)
					&& UsuariosService.comprobarDatosAcceso(usuarioLogin, passLogin)) {

				Usuario usuario = UsuariosService.devolverUsuario(usuarioLogin);
				request.getSession().setAttribute("nombre", usuario.getNombre());
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("id", null);
				if (usuario.isAdministrador()) {
					ArrayList<Usuario> listaUsuarios = UsuariosService.devolverUsuarios();
					request.getSession().setAttribute("usuarios", listaUsuarios);
				}
				log.info("Login realizado por " + usuario.getNombre());
				Cuenta cuentaUsuario = CuentaService.devolverCuenta(usuario.getId());
				request.getSession().setAttribute("cuenta", cuentaUsuario);
				request.getSession().setAttribute("provincias", listaProvincias);
				response.sendRedirect(request.getContextPath() + "/views/home.jsp");

			}
			// Si no existe el correo electrónico te manda al formulario
			else if ((UsuariosService.existeCorreo(usuarioLogin) == false)) {
				request.getSession().setAttribute("provincias", listaProvincias);
				response.sendRedirect(request.getContextPath() + "/views/form.jsp");
				// request.getRequestDispatcher("/views/form.jsp").forward(request, response);
			}
			// Si no te manda a la vista y te da el mensaje de error en la contrasena
			else {

				String mensajeContrasenaIncorrecta = "La contraseña es incorrecta";
				request.setAttribute("mensajeContrasenaIncorrecta", mensajeContrasenaIncorrecta);
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);

			}

		}

		// Si viene del formulario se comprueba en primer lugar si el mail existe, en
		// segundo lugar si las contraseñas son iguales.
		if (valor_email != null) {

			valor_pass = UsuariosService.encriptar(valor_pass);
			valor_pass2 = UsuariosService.encriptar(valor_pass2);

			if (UsuariosService.existeCorreo(valor_email)) { // Si el correo existe manda mensaje de error
				request.getSession().setAttribute("mensajeCorreoExiste", "Ese correo electrónico ya está en uso");
				response.sendRedirect(request.getContextPath() + "/views/form.jsp");
			} else if (UsuariosService.comprobarPass(valor_pass, valor_pass2)) { // si el correo no existe y las
																					// contraseñas son iguales te manda
																					// a inicio
				int telefono = Integer.parseInt(valor_telefono);
				String provinciaNombre = ProvinciaDAO.nombreProvincia(listaProvincias, valor_email);
				Usuario nuevoUsuario = new Usuario(valor_nombre, valor_apellido, provinciaNombre, telefono, valor_pass,
						valor_pass2, valor_provincia, valor_municipio);
				UsuariosService.anadirUsuario(nuevoUsuario);
				Usuario UsuarioEnvio = UsuariosService.devolverUsuario(nuevoUsuario.getEmail());
				CuentaService.crearCuenta(new Cuenta(UsuarioEnvio.getId(),0));
				String nombreUsuario = UsuarioEnvio.getNombre();
				Cuenta nuevaCuenta = CuentaService.devolverCuenta(UsuarioEnvio.getId());
				request.getSession().setAttribute("nombre", nombreUsuario);
				request.getSession().setAttribute("usuario", UsuarioEnvio);
				request.getSession().setAttribute("id", null);
				request.getSession().setAttribute("provincias", listaProvincias);
				request.getSession().setAttribute("cuenta", nuevaCuenta);
				log.debug("Registro realizado por " + nombreUsuario);
				response.sendRedirect(request.getContextPath() + "/views/home.jsp");

			}

			else { // si el correo no existe y las contraseñas son diferentes manda el mensaje de
					// error en contraseña
				request.getSession().setAttribute("mensajeCorreoExiste", "Las contraseñas son diferentes");
				response.sendRedirect(request.getContextPath() + "/views/form.jsp");
			}

		}

		// Si el campo id no es nulo hay dos opciones que se quiera editar campos o
		// editar contraseña
		if (id != null) {

			Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
			int idInt = Integer.parseInt(id);
			Usuario usuarioEdicion = UsuariosService.devolverUsuarioId(idInt);
			// Si viene el nombre edit, es que quieren editar campos
			if (nombreEdit != null && editAdmin == null) {
				// Si el correo es diferente al actual y existe otro igual manda error
				if (!(usuarioSesion.getEmail().equals(emailEdit)) && UsuariosService.existeCorreo(emailEdit)) {
					request.getSession().setAttribute("error", "Ese correo ya está en uso");
					response.sendRedirect(request.getContextPath() + "/views/editcampos.jsp");
				} else { // Si no actualiza los datos del usuario
					int telefonoEditpush = Integer.parseInt(telefonoEdit);
					usuarioSesion.setNombre(nombreEdit);
					usuarioSesion.setApellidos(apellidoEdit);
					usuarioSesion.setEmail(emailEdit);
					usuarioSesion.setTelefono(telefonoEditpush);
					String provinciaNombre = ProvinciaDAO.nombreProvincia(listaProvincias, provinciaEdit);
					usuarioSesion.setProvincia(provinciaNombre);
					usuarioSesion.setMunicipio(municipioEdit);
					UsuariosService.actualizarUsuario(usuarioSesion.getId(), usuarioSesion);
					request.getSession().setAttribute("usuario", usuarioSesion);
					request.getSession().setAttribute("error", null);
					request.getSession().setAttribute("confirmar", "El usuario ha sido actualizado");
					log.debug("Datos actualizados de " + usuarioSesion.getNombre());
					response.sendRedirect(request.getContextPath() + "/views/edit.jsp");

				}

			}
			// Si viene contraseña es que quieren editar contraseña
			else if (passActual != null) {

				passActual = UsuariosService.encriptar(passActual);
				passNueva1 = UsuariosService.encriptar(passNueva1);
				passNueva2 = UsuariosService.encriptar(passNueva2);

				// Si la contraseña actual y las contraseñas que mandan son iguales se actualiza
				// contraseña
				if (UsuariosService.comprobarPass(usuarioSesion.getId(), passActual)
						&& UsuariosService.comprobarPass(passNueva1, passNueva2)) {
					usuarioSesion.setPass(passNueva1);
					usuarioSesion.setPass2(passNueva2);
					UsuariosService.actualizarUsuario(usuarioSesion.getId(), usuarioSesion);
					request.getSession().setAttribute("error", null);
					request.getSession().setAttribute("confirmar", "La contraseña ha sido actualizada");
					log.debug("Contraseña cambiada");
					response.sendRedirect(request.getContextPath() + "/views/edit.jsp");
				}
				// Si la contraseña actual es diferente manda mensaje de error
				else if (!(UsuariosService.comprobarPass(usuarioSesion.getId(), passActual))) {

					request.getSession().setAttribute("confirmar", null);
					request.getSession().setAttribute("error", "La contraseña actual no coincide");
					response.sendRedirect(request.getContextPath() + "/views/editpass.jsp");
				} else { // Si las contraseñas nuevas son diferentes manda error

					request.getSession().setAttribute("confirmar", null);
					request.getSession().setAttribute("error", "Las contraseñas nuevas no coinciden");
					response.sendRedirect(request.getContextPath() + "/views/editpass.jsp");
				}
			}

			else if (editAdmin != null) {

				if (!(usuarioEdicion.getEmail().equals(emailEdit)) && UsuariosService.existeCorreo(emailEdit)) {

					request.getSession().setAttribute("error", "Ese correo ya está en uso");
					response.sendRedirect(request.getContextPath() + "/views/editcamposad.jsp");

				} else { // Si no, actualiza los datos del usuario

					int telefonoEditpush = Integer.parseInt(telefonoEdit);
					usuarioEdicion.setNombre(nombreEdit);
					usuarioEdicion.setApellidos(apellidoEdit);
					usuarioEdicion.setEmail(emailEdit);
					usuarioEdicion.setTelefono(telefonoEditpush);
					String provinciaNombre = ProvinciaDAO.nombreProvincia(listaProvincias, provinciaEdit);
					usuarioEdicion.setProvincia(provinciaNombre);
					usuarioEdicion.setMunicipio(municipioEdit);
					UsuariosService.actualizarUsuario(usuarioEdicion.getId(), usuarioEdicion);
					request.getSession().setAttribute("error", null);
					request.getSession().setAttribute("id", null);
					request.getSession().setAttribute("confirmar", "El usuario ha sido actualizado");
					log.debug("Datos actualizados de " + usuarioEdicion.getNombre() + " por el administrador "
							+ usuarioSesion.getNombre());
					ArrayList<Usuario> listaUsuarios = UsuariosService.devolverUsuarios();
					request.getSession().setAttribute("usuarios", listaUsuarios);
					response.sendRedirect(request.getContextPath() + "/views/home.jsp");

				}

			}

		}

	}

}
