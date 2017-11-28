package service;

import java.util.List;

import daos.iface.DAOPost;
import daos.iface.DAOUsuario;
import daos.imp.DAOPostHibernate;
import daos.imp.DAOUsuarioHibernate;
import negocio.Usuario;

public class UsuarioService {
	private DAOUsuario daoUsuario;
	private DAOPost daoPost;
	private Usuario logedUser;
	private static UsuarioService singleton;
	private UsuarioService(){
		daoUsuario = new DAOUsuarioHibernate();
		daoPost = new DAOPostHibernate();
	}
	
	public void altaUsuario(String email, String password, String cuil, String username){
		if(!daoUsuario.existeCuil(cuil)) {
			Usuario nuevo = new Usuario(username, email,cuil,password);
			System.out.println("ALTA" + nuevo);
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
	
	public Usuario getLoggedUser(){
		return logedUser;
	}
	
	public DAOUsuario getDAOUsuario() {
		return daoUsuario;
	}

	
	public static UsuarioService getService() {
		if(singleton == null) singleton = new UsuarioService();
		
		return singleton;
		
	}
	
}
