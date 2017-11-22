package daos.imp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import daos.iface.DAO;

public class DAOHibernate<T> implements DAO<T> {

	protected static EntityManagerFactory  entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );

	public DAOHibernate() {

		
	}
	@Override
	public void save(T t) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
}
