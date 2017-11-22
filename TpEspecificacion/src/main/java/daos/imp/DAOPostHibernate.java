package daos.imp;

import java.util.List;

import javax.persistence.EntityManager;

import daos.iface.DAOPost;
import negocio.Post;
import negocio.Usuario;

public class DAOPostHibernate extends DAOHibernate<Post> implements DAOPost{

	@Override
	public List<Post> getAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
        List<Post> result = entityManager.createQuery( "from Post", Post.class ).getResultList();
		entityManager.getTransaction().commit();
        entityManager.close();
        return result;

	}

	@Override
	public List<Post> getAllPostof(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
