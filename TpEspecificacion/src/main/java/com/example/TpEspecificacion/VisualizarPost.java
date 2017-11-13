package com.example.TpEspecificacion;

import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class VisualizarPost extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "VisualizarPosts";
	public VisualizarPost() {
		setSizeFull();
		
		VerticalLayout vLayout = new VerticalLayout();
		List<Post> posts = DAO.getPosts();
		
		for (Post p: posts) {
			System.out.println(p.getContenido());
			Label lbl = new Label();
			 lbl.setValue(p.getContenido());
			 vLayout.addComponent(lbl);
		}
		
		addComponent(vLayout);
		
		
		}
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
