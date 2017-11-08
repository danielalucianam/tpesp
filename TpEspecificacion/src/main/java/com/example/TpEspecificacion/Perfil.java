package com.example.TpEspecificacion;

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
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


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
			 	Button MiPerfil= new Button("Mi Perfil");
				MiPerfil.setStyleName(ValoTheme.BUTTON_LINK);
				Button InicioButton = new Button("Inicio");
				InicioButton.setStyleName(ValoTheme.BUTTON_LINK);
				Button Logout = new Button("Salir");
				Logout.setStyleName(ValoTheme.BUTTON_LINK);
				
				
				
				layout.setWidth("400px");
				layout.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
				layout.addComponent(MiPerfil);
				layout.addComponent(InicioButton);
				layout.addComponent(Logout);
				layout.addStyleName("layout-with-border");
				grid.addComponent(layout,0, 0, 1, 1);
			Tree tree = new Tree("Mi Perfil");
			tree.addItem("Mis Posts");
		
			tree.addItem("Crear Post");
			tree.addItem("Borrar Post");
			tree.addItem("Configuración");
			tree.addItem("Privacidad");
			tree.addItem("Sistema");
			// Creamos la jerarquía
			tree.setParent("Crear Post", "Mis Posts");
			tree.setParent("Borrar Post", "Mis Posts");
			tree.setParent("Privacidad", "Configuración");
			tree.setParent("Sistema", "Configuración");
			// Hacemos que las hojas se vean como tales
			tree.setChildrenAllowed("Crear Post", false);
			tree.setChildrenAllowed("Borrar", false);
			tree.setChildrenAllowed("Privacidad", false);
			tree.setChildrenAllowed("Sistema", false);
			
			 tree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
		    @Override
            public void itemClick(ItemClickEvent event) {
                Object itemId = event.getItemId();
                if( "Crear Post".equals(itemId)) {
                	Post post = new Post();
                  	grid.addComponent(nuevaAreaPost(),0,2,0,2);
                }
            }
        });
			
			tree.setSizeUndefined();
			String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
			VerticalLayout vLayout = new VerticalLayout();
			vLayout.addComponent(new Embedded(null,new FileResource(new File(basepath +"/WEB-INF/images/imagen.png")))); 
		    
			vLayout.addComponent(tree);
						
			HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
			hsplit.setFirstComponent(vLayout);
			
			hsplit.setSecondComponent(grid);
			hsplit.setResponsive(false);
			hsplit.setMaxSplitPosition(30, Unit.PERCENTAGE);
			hsplit.setMinSplitPosition(30, Unit.PERCENTAGE);
		
			addComponent(hsplit);

			
		     
			 }
		
		public VerticalLayout nuevaAreaPost() {
			VerticalLayout componente = new VerticalLayout();
			Post p = new Post();
			TextArea tf = new TextArea("Escribe el contenido");
			
			tf.setMaxLength(100);
			tf.setHeight("200px");
			tf.setWidth("300px");
			
			Button button = new Button("Aceptar");
			button.addClickListener(clickEvent ->
			p.contenido = tf.getValue());
			button.addClickListener(clickEvent ->
			DAO.SavePost(p));
			
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

