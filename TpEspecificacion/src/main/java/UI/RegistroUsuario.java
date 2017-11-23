package UI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;

import negocio.DAO;
import negocio.Usuario;
import service.UsuarioService;

public class RegistroUsuario extends VerticalLayout implements View {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Registro";
	private UsuarioService usuarioService;
	
	public	RegistroUsuario() {
		usuarioService = UsuarioService.getService();
		init();
		setSizeFull();
		
        
}
	private void init() {

		
		VerticalLayout layout = new VerticalLayout();
		addComponent(layout);
		
		TextField username = new TextField("Username");
		 username.setIcon(FontAwesome.USER);
		 
		TextField direccionEmail = new TextField("Correo electrónico");
		direccionEmail.setIcon(FontAwesome.ENVELOPE);
			
		TextField cuilOcuit= new TextField("Cuil/Cuit");
				
		 PasswordField password = new PasswordField("Contraseña");
	     password.setIcon(FontAwesome.LOCK);
			
		
		
		Button registrarseButton = new Button("Aceptar", new Button.ClickListener(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				if(!usuarioService.isUsernameInUse(username.getValue())){
					usuarioService.altaUsuario(direccionEmail.getValue(), password.getValue(), cuilOcuit.getValue(), username.getValue());
					
					Notification n = new Notification("Usuario Registrado", ValoTheme.NOTIFICATION_SUCCESS);
					
					getUI().getNavigator().navigateTo(IngresoUsuario.NAME);
				}
			
				else
					Notification.show("Username ya en uso", Notification.Type.ERROR_MESSAGE);
				}
			
		});
		 registrarseButton.setIcon(FontAwesome.CHECK);
		layout.addComponents(username,direccionEmail,cuilOcuit,password,registrarseButton);
		setComponentAlignment(layout, Alignment.BOTTOM_CENTER);
		addComponent(layout);
		layout.setMargin(true);
        layout.setSpacing(true);
	}
	
		



@Override
public void enter(ViewChangeEvent event) {
//si quiero código que se ejecute al ingresar.
}}


