package negocio;

import java.io.File;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

import UI.MenuPrincipal;


public class Perfil extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Perfil";
		public Perfil() {
			setSizeFull();
			HorizontalLayout layout = new HorizontalLayout();
			GridLayout grid = new GridLayout(4,4);
			grid.setWidth("400px");
			grid.setHeight("400px");
			
			 	Button MiPerfil = new Button("Menu", new Button.ClickListener(){
					@Override
					public void buttonClick(ClickEvent event) {
						getUI().getNavigator().navigateTo(MenuPrincipal.NAME);
					
					}
				});
				MiPerfil.setStyleName(ValoTheme.BUTTON_LINK);
			
			
				layout.addComponent(MiPerfil);
			
				grid.addComponent(layout,0, 0, 1, 1);
		
			
               grid.addComponent(nuevaAreaPost(),0,2,0,2);
              
           
		
		
			addComponent(grid);

			
		     
			 }
		
		public VerticalLayout nuevaAreaPost() {
			VerticalLayout componente = new VerticalLayout();
		
			TextArea tf = new TextArea();
			tf.setMaxLength(300);
			
			
		
			
					Button button = new Button("Aceptar", new Button.ClickListener(){
				@Override
				public void buttonClick(ClickEvent event) {
					Post p = new Post();
					p.setContenido(tf.getValue());
					DAO.SavePost(p);
					tf.clear();
					
				}
			});
			componente.addComponents(tf,button);
			componente.setMargin(true);
			componente.setSpacing(true);
			return componente;
			
		}
 @Override
 public void enter(ViewChangeEvent event) {
//si quiero código que se ejecute al ingresar.
 
 }
 }

