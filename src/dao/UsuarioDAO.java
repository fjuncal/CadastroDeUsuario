package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import entidade.Usuario;

@Repository
public class UsuarioDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public Usuario cria(Usuario u) {
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		s.save(u);
		t.commit();
		s.close();
		return u;
	}
	
	public Usuario buscaPorId(Long id) {
		Session s = sessionFactory.openSession();
		Usuario u = s.find(Usuario.class, id);
		return u;		
	}
	
	public Usuario atualiza(Usuario u) {
		Session s= sessionFactory.openSession();
		Transaction transaction = s.beginTransaction();
		s.merge(u);
		transaction.commit();
		s.close();
		return u;
	}
	
	public void remove(Usuario u) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(u);
		transaction.commit();
		session.close();
	}
	
	public Usuario buscaPorEmail(String email) {
		Session s = sessionFactory.openSession();
		Query<Usuario> q = s.createNativeQuery("select * from usuario where email = '" + email + "'", Usuario.class);
		Usuario usu = (Usuario) q.getSingleResult();
		
		return usu;
		
	}

}
