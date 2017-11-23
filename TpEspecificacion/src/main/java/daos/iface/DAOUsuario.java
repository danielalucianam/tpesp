package daos.iface;

import java.util.List;


import negocio.Usuario;

public  interface DAOUsuario extends DAO<Usuario> {
	public List<Usuario> getAll();
	public boolean isUsernameInUse(String username);
	public boolean existeUsuario(String username, String password);
	public Usuario getUsuario(String username, String password);
	public boolean existeCuil(String cuil);
}
