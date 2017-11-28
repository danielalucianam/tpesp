package daos.imp;



import java.util.List;

import javax.persistence.EntityManager;
import daos.iface.DAOUsuario;
import negocio.Usuario;

public class DAOUsuarioHibernate extends DAOHibernate<Usuario> implements DAOUsuario {

	
	public DAOUsuarioHibernate(){}
	@Override
	public boolean isUsernameInUse(String username) {
		List<Usuario> usuarios = getAll();
		for ( Usuario usuario : usuarios ) {
			if (usuario.getUsername().equals(username)) 
				return true;
		}
		return false;
	}

	@Override
	public boolean existeUsuario(String username, String password) {
		return getUsuario(username, password) != null;
	
	}
	@Override
	public boolean existeCuil(String cuil) {
		List<Usuario> usuarios = getAll();
		for ( Usuario usuario : usuarios ) {
			if (usuario.getCuilCuit().equals(cuil)) 
				return true;
		}
		return false;
	}
	@Override
	public Usuario getUsuario(String username, String password) {
		
		List<Usuario> usuarios = getAll();
		for ( Usuario usuario : usuarios ) {
			if (usuario.getUsername().equals(username) && usuario.getContraseña().equals(password)) 
				return usuario;
			
		}
		return null;
	}

	@Override
	public List<Usuario> getAll() {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
        List<Usuario> result = entityManager.createQuery( "from Usuario", Usuario.class ).getResultList();
		entityManager.getTransaction().commit();
        entityManager.close();
        return result;
	}

}
