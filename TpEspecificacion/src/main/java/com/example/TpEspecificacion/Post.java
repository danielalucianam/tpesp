package com.example.TpEspecificacion;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
@Entity
public class Post{
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private long idPost;
	
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Post";
	String contenido ;
	
	@ManyToOne (cascade = CascadeType.ALL)
	Usuario usuarioDueño;
	
	public	Post() {
		
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
	
	public void setUsuario(Usuario u){
		usuarioDueño= u;
		
	}
}