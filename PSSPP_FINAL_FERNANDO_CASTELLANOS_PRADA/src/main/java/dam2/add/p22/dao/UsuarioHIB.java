package dam2.add.p22.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dam2.add.p22.hibernate.HibernateManager;
import dam2.add.p22.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioHIB {

	public static void anadirContacto(Usuario usuario) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); // Se crea una transaccion
		session.save(usuario);// Guarda el objeto creado en la BBDD.
		tx.commit(); // Materializa la transaccion
		session.close();
	}

	public static Usuario devolverUsuario(String correo) {

		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Usuario WHERE email = :correo");
		q.setParameter("correo", correo);
		List<Usuario> results = q.list();
		session.close();
		if (results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}

	}

	public static Usuario devolverUsuarioId(int id) {

		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		Usuario auxiliar = (Usuario) session.get(Usuario.class, id);
		session.close();
		return auxiliar;

	}

	public static void actualizarUsuario(int id, Usuario usuario) {

		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		Usuario auxiliar = devolverUsuarioId(id);
		auxiliar.setNombre(usuario.getNombre());
		auxiliar.setApellidos(usuario.getApellidos());
		auxiliar.setEmail(usuario.getEmail());
		auxiliar.setAdministrador(usuario.isAdministrador());
		auxiliar.setPass(usuario.getPass());
		auxiliar.setPass2(usuario.getPass2());
		auxiliar.setTelefono(usuario.getTelefono());
		auxiliar.setProvincia(usuario.getProvincia());
		auxiliar.setMunicipio(usuario.getMunicipio());

		session.update(auxiliar);
		session.getTransaction().commit();
		session.close();

	}

	public static void eliminarUsuario(int id) {

		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		Usuario auxiliar = devolverUsuarioId(id);
		session.delete(auxiliar);
		session.getTransaction().commit();
		session.close();

	}

	public static ArrayList<Usuario> devolverUsuarios() {

		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM Usuario");
		List<Usuario> results = q.list();
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();

		for (Usuario usuario : results) {

			if (!(usuario.isAdministrador())) {
				listaUsuarios.add(usuario);
			}

		}

		return listaUsuarios;
	}

}
