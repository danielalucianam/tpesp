package com.example.TpEspecificacion;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class RegistroUsuario extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Registro";
	public	RegistroUsuario() {
		setSizeFull();
		
		VerticalLayout layout = new VerticalLayout();
		addComponent(layout);
		
		TextField usuario = new TextField("Username");
		 usuario.setIcon(FontAwesome.USER);
		 
		TextField direccionEmail = new TextField("Correo electrónico");
		direccionEmail.setIcon(FontAwesome.ENVELOPE);
			
		TextField cuilOcuit= new TextField("Cuil/Cuit");
				
		 PasswordField password = new PasswordField("Contraseña");
	     password.setIcon(FontAwesome.LOCK);
			
		
		
		Button button = new Button("Aceptar", new Button.ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(IngresoUsuario.NAME);
				Usuario nuevo = new Usuario(usuario.getValue(),direccionEmail.getValue(),cuilOcuit.getValue(),password.getValue());
				DAO.SaveUsuario(nuevo);
			}
		});
		 button.setIcon(FontAwesome.CHECK);
		layout.addComponents(usuario,direccionEmail,cuilOcuit,password,button);
		setComponentAlignment(layout, Alignment.BOTTOM_CENTER);
		addComponent(layout);
		layout.setMargin(true);
        layout.setSpacing(true);
        
}
	
		



@Override
public void enter(ViewChangeEvent event) {
//si quiero código que se ejecute al ingresar.
}}


