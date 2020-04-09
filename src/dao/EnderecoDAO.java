package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import entidade.Endereco;

@Repository
public class EnderecoDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public Endereco cria(Endereco endereco) {
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		s.save(endereco);
		t.commit();
		s.close();
		return endereco;
	}
	
	public Endereco busca(Long id) {
		Session s = sessionFactory.openSession();
		Endereco end = s.find(Endereco.class, id);
		return end;		
	}
	
	
	public Endereco buscaPorCep(String cep) {
		Session s = sessionFactory.openSession();
		Query<Endereco> q = s.createNativeQuery("select * from endereco where email = '" + cep + "'", Endereco.class);
		Endereco end = (Endereco) q.getSingleResult();
		
		return end;
	}
		

}
