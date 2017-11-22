package UI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class VisualizadorEmisiones extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "VisualizarEmisiones";
	public  VisualizadorEmisiones() {
		setSizeFull();
		
		HorizontalLayout hl = new HorizontalLayout();
		HorizontalLayout hl1 = new HorizontalLayout();
		Label contenido = new Label("HOLA");
		Label contenido1= new Label();
		Label contenido2= new Label();
		Label contenido3= new Label();
		Label contenido4= new Label();
		contenido.addStyleName("animacion");
		contenido1.addStyleName("animacion");
		contenido2.addStyleName("animacion");
		contenido3.addStyleName("animacion");
		contenido4.addStyleName("animacion");
		
		
		hl.addComponents(contenido,contenido1,contenido2);
		hl1.addComponents(contenido3, contenido4);
		addComponents(hl,hl1);
		
              
	}
	

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}