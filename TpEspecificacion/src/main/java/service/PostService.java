package service;

import java.time.LocalDate;
import java.util.List;

import daos.iface.DAOPost;
import daos.imp.DAOPostHibernate;
import negocio.Post;

public class PostService {

	private static PostService pservice;
	private static  DAOPost daoPost;
	
	private PostService(){
		daoPost= new DAOPostHibernate();
		
	}
	public static PostService getService(){
		if(pservice == null) pservice= new PostService();
		
		return pservice;
	}
	public List<Post> obtenerPosts(){
		return daoPost.getAll();
	}
	
//	public  void crearPost(String contenido) {
//		Post post = new Post();
//		post.setContenido(contenido);
//		daoPost.save(post);
	
	public Post getEmptyPost() {
		Post toRet = new Post ();
		toRet.setFechaCreacion(LocalDate.now());
		return toRet;
		
	}
	
	public void save(Post posteo){
		daoPost.save(posteo);
	}
	

}
