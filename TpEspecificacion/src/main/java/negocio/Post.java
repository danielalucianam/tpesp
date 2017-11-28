package negocio;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Post{
	/**
	 * 
	 */

	private double calificacion;
	
//	private enum Estrella {
//		OneStar, TwoStar, ThreeStar, FourStar, FiveStar
//	}
	
	private LocalDate fechaCreacion;
	@Id
	@GeneratedValue
	private long idPost;
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Post";
	String contenido ;
	

	
	@ManyToOne
	Usuario usuarioDueño;
	
	public	Post() {
		 fechaCreacion = LocalDate.now();
		 calificacion = 0;
		
	}
	
	public void setId(long id){
		idPost = id;
	}
	
	public long getId(){
		return idPost;
	}

	public String getContenido(){
		return contenido;
	}
	public void setContenido(String contenido){
		this.contenido=contenido;
	}
	
	public void setUsuario(Usuario u){
		usuarioDueño= u;
	}
	
	public Usuario getUsuarioDueño() {
		return usuarioDueño;
	}

	
	public void setCalificacion(double calificacion) {
		this.calificacion= calificacion;
	}
	
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public double getCalificacion() {
		return this.calificacion;
	}
	
	
}