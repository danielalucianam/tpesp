package com.example.TpEspecificacion;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;

public class IngresoUsuario extends VerticalLayout implements View {
		public static final String NAME = "";
			public IngresoUsuario() {
				setSizeFull();
				
				final VerticalLayout layout = new VerticalLayout();
				addComponent(layout);
				
				TextField usuario = new TextField("Usuario");
				 usuario.setIcon(FontAwesome.USER);
				
		        
		        PasswordField password = new PasswordField("Contraseña");
		        password.setIcon(FontAwesome.LOCK);
		        
		        
				
		        Button button = new Button("Ingresar",
		                new Button.ClickListener() {
		           
					@Override
		            public void buttonClick(ClickEvent event) {
						
						//if(Usuario.existeUsuario(DAO.getUsuarios(), usuario.getValue(), password.getValue()))
						getUI().getNavigator().navigateTo(MenuPrincipal.NAME);
		            
						//else 
						//Notification.show("El usuario ingresado no es válido");
					
					}
		        });
		        
		        button.setIcon(FontAwesome.SIGN_IN);
		        
		        Button link = new Button("Registrarme",  new Button.ClickListener() {
			           
						@Override
			            public void buttonClick(ClickEvent event) {
							
							getUI().getNavigator().navigateTo(RegistroUsuario.NAME);
							}
							
			            
			        });
				link.addStyleName(ValoTheme.BUTTON_LINK);
		      
				layout.addComponents(usuario, password,button, link);
		        layout.setMargin(true);
		        layout.setSpacing(true);
		      
				
		    
		        
		    
}
			@Override
			public void enter(ViewChangeEvent event) {
				// TODO Auto-generated method stub
				
			}
}
			
