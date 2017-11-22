package daos.iface;

import java.util.List;

import negocio.Post;
import negocio.Usuario;

public interface DAOPost extends DAO<Post> {
	
	public List<Post> getAll();
	
	public  List<Post> getAllPostof(Usuario u);

}
