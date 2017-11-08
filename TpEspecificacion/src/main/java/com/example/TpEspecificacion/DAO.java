package com.example.TpEspecificacion;

	

	import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.Persistence;

	public class DAO {
		private static EntityManagerFactory  entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
		
		public static void SavePost(Post p){
			
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(p);
			entityManager.getTransaction().commit();
			entityManager.close();
		}
		
		public static void SaveUsuario(Usuario u) {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(u);
			entityManager.getTransaction().commit();
			entityManager.close();
		}
		
		public static List<Usuario> getUsuarios(){
			
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				entityManager.getTransaction().begin();
		        List<Usuario> result = entityManager.createQuery( "from Usuario", Usuario.class ).getResultList();
				entityManager.getTransaction().commit();
		        entityManager.close();
		        return result;
		
	}

}
