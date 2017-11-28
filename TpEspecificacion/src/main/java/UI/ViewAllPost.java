package UI;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

import negocio.DAO;
import negocio.Post;
import service.PostService;

public class ViewAllPost extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "VisualizarPosts";
	private final int CANT_ESTRELLAS = 5;
	private List <HorizontalLayout> posteos;
	private List <HorizontalLayout> estrellas;
	private PostService postService;
	public ViewAllPost() {
		postService = PostService.getService();
		setSizeFull();
		posteos = new ArrayList<>();
		estrellas = new ArrayList<>();
		init();
		
		}
	
	private void init() {
		HorizontalLayout horizontalL = new HorizontalLayout();
		horizontalL.setStyleName("hl-estilo");
		
		Label postLabel = new Label("Posts");
	
		Button menu= new Button("", new Button.ClickListener(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(ViewMenuPrincipal.NAME);
			
			}
		});
		
		menu.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		menu.setIcon(FontAwesome.TH_LIST);
		horizontalL.addComponents(postLabel, menu);
	
		
		List<Post> posts = postService.obtenerPosts();
		
		for (Post p: posts) {
		
			HorizontalLayout post = new HorizontalLayout();
			
			Label contenedorPostLabel = new Label(p.getContenido()+'\n'+"Creado el: "+p.getFechaCreacion()
			+" por el autor: " +p.getUsuarioDueño().getUsername(), ContentMode.PREFORMATTED); 
			contenedorPostLabel.setStyleName("estilos-post");
			
			post.addComponents(contenedorPostLabel);
			
			HorizontalLayout estrella = new HorizontalLayout();
			
//				for(int i = 1; i<=CANT_ESTRELLAS;i++) {
//					int aux=i;
//					Button btn = new Button();
//					btn.setIcon(FontAwesome.STAR_O);
//					btn.addStyleName("calificacion-estilos");
//					btn.addStyleName(ValoTheme.BUTTON_BORDERLESS);
//					
//					btn.addClickListener(new Button.ClickListener() {
//						
//						@Override
//						public void buttonClick(ClickEvent event) {
//							Notification.show(aux + " estrellas");						
//							btn.setIcon(FontAwesome.STAR);
//						}
//					});
//								
//		estrella.addComponent(btn);}	
			
			
			Button star1 = new Button();
			Button star2 = new Button();
			Button star3 = new Button();
			Button star4 = new Button();
			Button star5 = new Button();
			
			star1.setIcon(FontAwesome.STAR_O);
			star1.addStyleName("calificacion-estilos");
			star1.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			star1.addClickListener(new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					Notification.show("1 estrella");
					star1.setIcon(FontAwesome.STAR);
					star2.setIcon(FontAwesome.STAR_O);
					star3.setIcon(FontAwesome.STAR_O);
					star4.setIcon(FontAwesome.STAR_O);
					star5.setIcon(FontAwesome.STAR_O);
					p.setCalificacion(1);}
				});

			
			
			star2.setIcon(FontAwesome.STAR_O);
			star2.addStyleName("calificacion-estilos");
			star2.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			star2.addClickListener(new Button.ClickListener() {
					
				@Override
				public void buttonClick(ClickEvent event) {
					Notification.show("2 estrellas");
					star1.setIcon(FontAwesome.STAR);
					star2.setIcon(FontAwesome.STAR);
					star3.setIcon(FontAwesome.STAR_O);
					star4.setIcon(FontAwesome.STAR_O);
					star5.setIcon(FontAwesome.STAR_O);
					p.setCalificacion(2);}
			});
			
			star3.setIcon(FontAwesome.STAR_O);
			star3.addStyleName("calificacion-estilos");
			star3.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			star3.addClickListener(new Button.ClickListener() {
					
				@Override
				public void buttonClick(ClickEvent event) {
					Notification.show("3 estrellas");
					star1.setIcon(FontAwesome.STAR);
					star2.setIcon(FontAwesome.STAR);
					star3.setIcon(FontAwesome.STAR);
					star4.setIcon(FontAwesome.STAR_O);
					star5.setIcon(FontAwesome.STAR_O);
					p.setCalificacion(3);}
				});
			
			
			star4.setIcon(FontAwesome.STAR_O);
			star4.addStyleName("calificacion-estilos");
			star4.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			star4.addClickListener(new Button.ClickListener() {
					
				@Override
				public void buttonClick(ClickEvent event) {
					Notification.show("4 estrellas");
					star1.setIcon(FontAwesome.STAR);
					star2.setIcon(FontAwesome.STAR);
					star3.setIcon(FontAwesome.STAR);
					star4.setIcon(FontAwesome.STAR);
					star5.setIcon(FontAwesome.STAR_O);
					p.setCalificacion(4);}				
				});
			
		
			star5.setIcon(FontAwesome.STAR_O);
			star5.addStyleName("calificacion-estilos");
			star5.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			star5.addClickListener(new Button.ClickListener() {
					
				@Override
				public void buttonClick(ClickEvent event) {
					Notification.show("5 estrellas");
					star1.setIcon(FontAwesome.STAR);
					star2.setIcon(FontAwesome.STAR);
					star3.setIcon(FontAwesome.STAR);
					star4.setIcon(FontAwesome.STAR);
					star5.setIcon(FontAwesome.STAR);
					p.setCalificacion(5);}					
				});
			
			
			
		estrella.addComponents(star1,star2, star3, star4, star5);

		posteos.add(post);
		estrellas.add(estrella);}
	
		addComponent(horizontalL);
	
		for(int x=0; x<posteos.size(); x++){
			addComponents(posteos.get(x), estrellas.get(x));}}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
