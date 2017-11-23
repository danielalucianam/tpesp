package service;

import java.util.List;

import daos.iface.DAOUsuario;
import daos.imp.DAOUsuarioHibernate;
import negocio.Usuario;

public class UsuarioService {
	private DAOUsuario daoUsuario;
	private Usuario logedUser;
	private static UsuarioService singleton;
	private UsuarioService(){
		daoUsuario = new DAOUsuarioHibernate();
		
	}
	
	public void altaUsuario(String email, String password, String cuil, String username){
		if(!daoUsuario.existeCuil(cuil)) {
			Usuario nuevo = new Usuario(username, email,cuil,password);
			daoUsuario.save(nuevo);
		}
		else throw new IllegalArgumentException("CUIL/CUIT existente");
	}
	
	
	public boolean loginUsuario(String nombre, String password){
		
		Usuario user = daoUsuario.getUsuario(nombre,password);
		
		if(user != null){
			
			logedUser = user;
			
			return true;
		}
		else {
			return false;
		}
		
	}

	public boolean isUsernameInUse(String nombre){
		return daoUsuario.isUsernameInUse(nombre);
	}
	
	public static UsuarioService getService() {
		if(singleton == null) singleton = new UsuarioService();
		
		return singleton;
		
	}
	
}
