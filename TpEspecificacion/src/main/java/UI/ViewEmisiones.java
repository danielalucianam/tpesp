package UI;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import com.vaadin.ui.VerticalLayout;

public class ViewEmisiones extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "VisualizarEmisiones";
	private static final String AnimType = null;
	public  ViewEmisiones() {
		setSizeFull();
		
		HorizontalLayout hl = new HorizontalLayout();
		HorizontalLayout hl1 = new HorizontalLayout();
		VerticalLayout vl= new VerticalLayout();
		Label contenido = new Label("Emision1");
		Label contenido1= new Label("Emision2");
		Label contenido2= new Label("Emision3");
		Label contenido3= new Label("Emision4");
		Label contenido4= new Label("Emision5");
		contenido.addStyleName("animacion");
		contenido.addStyleName("someStyle");
		contenido1.addStyleName("animacion");
		contenido1.addStyleName("someStyle");
		contenido2.addStyleName("animacion");
		contenido2.addStyleName("someStyle");
		contenido3.addStyleName("animacion");
		contenido3.addStyleName("someStyle");
		contenido4.addStyleName("animacion");
		contenido4.addStyleName("someStyle");
		
		
	
		hl.addComponents(contenido,contenido1,contenido2);
		hl1.addComponents(contenido3,contenido4);
		vl.addComponents(hl,hl1);
		addComponent(vl);
		              
		
		
		
	}
	

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
	}
	
	public void pausa (){
		try {
			Thread.sleep(5000);
			}
		catch(Exception e) {
			
		}
	}
	
}