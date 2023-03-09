package dam2.add.p22.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "usuarios")
public class Usuario {
	
	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	@Column(name = "id")
  	private int id;
  	@Column(name = "nombre", length = 255)
	private String nombre;
  	@Column(name = "apellidos", length = 255)
	private String apellidos;
  	@Column(name = "email", length = 255)
	private String email;
  	@Column(name = "telefono")
	private int telefono;
  	@Column(name = "pass", length = 255)
	private String pass;
  	@Column(name = "pass2", length = 255)
	private String pass2;
  	@Column(name = "administrador")
	private boolean administrador;
  	@Column(name = "provincia", length = 255)
	private String provincia;
  	@Column(name = "municipio", length = 255)
	private String municipio;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String nombre, String apellidos, String email, int telefono, String pass, String pass2,
			boolean administrador) {

		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
		this.pass2 = pass2;
		this.administrador = administrador;
	}
	

	public Usuario(String nombre, String apellidos, String email, int telefono, String pass, String pass2,
			boolean administrador, String provincia, String municipio) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
		this.pass2 = pass2;
		this.administrador = administrador;
		this.provincia = provincia;
		this.municipio = municipio;
	}
	
	public Usuario(String nombre, String apellidos, String email, int telefono, String pass, String pass2, String provincia, String municipio) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
		this.pass2 = pass2;
		this.provincia = provincia;
		this.municipio = municipio;
	}

	public Usuario(String nombre, String apellidos, String email, int telefono, String pass, String pass2) {
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
		this.pass2 = pass2;
	
	}



	public Usuario(String nombre, String apellidos, String email, int telefono, String pass, String pass2,
			boolean administrador) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.pass = pass;
		this.pass2 = pass2;
		this.administrador = administrador;
	}
	
	

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	
	
	
	

}
