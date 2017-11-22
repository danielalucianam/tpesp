package daos.imp;



import daos.iface.DAOPost;
import daos.iface.DAOUsuario;
import negocio.Usuario;

public class DAOUsuarioHibernate extends DAOHibernate<Usuario> implements DAOUsuario {

	@Override
	public boolean isUsernameInUse(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existeUsuario(String username, String md5) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario getUsuario(String username, String md5) {
		// TODO Auto-generated method stub
		return null;
	}

}
