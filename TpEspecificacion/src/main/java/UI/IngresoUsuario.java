package UI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import service.UsuarioService;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;

public class IngresoUsuario extends VerticalLayout implements View {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public static final String NAME = "";
		private UsuarioService usuarioService;
			public IngresoUsuario() {
				usuarioService = UsuarioService.getService();
				init();
				this.setSizeFull(); 
		    
}
			private void init() {
				
				
				final VerticalLayout layout = new VerticalLayout();
				addComponent(layout);
				
				TextField username= new TextField("Usuario");
				PasswordField password = new PasswordField("Contraseña");
		        
		        
		        username.setIcon(FontAwesome.USER);
		        password.setIcon(FontAwesome.LOCK);
		        
		        
				
		        Button ingresarButton = new Button("Ingresar",
		                new Button.ClickListener() {
		           
					/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

					@Override
		            public void buttonClick(ClickEvent event) {
						
						if(usuarioService.loginUsuario(username.getValue(), password.getValue()))
						getUI().getNavigator().navigateTo(MenuPrincipal.NAME);
		            
						else 
						Notification.show("El usuario ingresado no es válido");
					
					}
		        });
		        
		       ingresarButton.setIcon(FontAwesome.SIGN_IN);
		        
		        Button registroButton = new Button("Registrarme",  new Button.ClickListener() {
			           
						/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

						@Override
			            public void buttonClick(ClickEvent event) {
							
							getUI().getNavigator().navigateTo(RegistroUsuario.NAME);
							}
							
			            
			        });
				registroButton.addStyleName(ValoTheme.BUTTON_LINK);
		      
				layout.addComponents(username, password,ingresarButton, registroButton);
				
		        layout.setMargin(true);
		        layout.setSpacing(true);
		      
				
		    
			}
			@Override
			public void enter(ViewChangeEvent event) {
				// TODO Auto-generated method stub
				
			}
}
			
