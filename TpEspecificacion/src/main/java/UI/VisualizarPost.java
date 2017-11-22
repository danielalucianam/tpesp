package UI;

import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import negocio.DAO;
import negocio.Post;

public class VisualizarPost extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "VisualizarPosts";
	public VisualizarPost() {
		setSizeFull();
		
		HorizontalLayout horizontalL = new HorizontalLayout();
		Label label = new Label ();
		label.setValue("Posts");
		label.addStyleName("hl-estilo");
		horizontalL.addComponent(label);
		horizontalL.addStyleName("horizontal");
		addComponent(horizontalL);
		VerticalLayout vLayout = new VerticalLayout();
		List<Post> posts = DAO.getPosts();
		HorizontalLayout hLayout= new HorizontalLayout();
		for (Post p: posts) {
			Label lbl = new Label(p.getContenido()+'\n'+"Creado el 14/11/2017 por autor-anonymus", ContentMode.PREFORMATTED); 
			lbl.addStyleName("estilos-post");
			Button btn = new Button();
			
			
			
			/*CustomLayout content = new CustomLayout("calificacion");
			content.setSizeUndefined();
			content.addComponent(new Label(),"star-rating");
			
		
			hLayout.addComponents(content);*/
			/*btn.setIcon(FontAwesome.STAR);
			btn.addStyleName("calificacion-estilos");
			btn.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			Button btn1 = new Button();
			btn1.setIcon(FontAwesome.STAR);
			btn1.addStyleName("calificacion-estilos");
			btn1.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			Button btn2 = new Button();
			btn2.setIcon(FontAwesome.STAR);
			btn2.addStyleName("calificacion-estilos");
			btn2.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			Button btn3 = new Button();
			btn3.setIcon(FontAwesome.STAR);
			btn3.addStyleName("calificacion-estilos");
			btn3.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			Button btn4 = new Button();
			btn4.setIcon(FontAwesome.STAR);
			btn4.addStyleName("calificacion-estilos");
			btn4.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			hLayout.addStyleName("contenedor-estilos");*/
			
			vLayout.addComponents(lbl,hLayout);
			
		}
		
		
		addComponent(vLayout);
		
		}
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}