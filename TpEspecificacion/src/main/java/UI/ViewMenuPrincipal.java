package UI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class ViewMenuPrincipal  extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Menu";
	public	ViewMenuPrincipal() {
		setSizeFull();
		HorizontalLayout hLayout = new HorizontalLayout();
	
		 Button  botonPosts = new Button("Ver Posts",  new Button.ClickListener() {
	           
				@Override
	            public void buttonClick(ClickEvent event) {
					
					getUI().getNavigator().addView(ViewAllPost.NAME, new ViewAllPost());
					getUI().getNavigator().navigateTo(ViewAllPost.NAME);
				}
					
	            
	        });
	
		botonPosts.addStyleName("estilo-uno");
		botonPosts.setIcon(FontAwesome.NEWSPAPER_O);
		botonPosts.addStyleName("font-awesome-variants");
		botonPosts.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		botonPosts.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
		
			Button botonCrear = new Button("Crear Post", new Button.ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(ViewNewPost.NAME);
				
			}
		});
		
		botonCrear.setIcon(FontAwesome.FILE);
		botonCrear.addStyleName("estilo-dos");
		botonCrear.addStyleName("font-awesome-variants");
		botonCrear.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		botonCrear.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
		
		hLayout.setMargin(true);
		hLayout.setSpacing(true);
		hLayout.addComponents(botonPosts,botonCrear);
		hLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		hLayout.addStyleName("layout-estilo");
		addComponent(hLayout);
		
		
		HorizontalLayout vLayout = new HorizontalLayout();
		
		Button emisiones = new Button("Ver emisiones", new Button.ClickListener(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(ViewEmisiones.NAME);
				
			}
		});
		emisiones.setIcon(FontAwesome.OBJECT_GROUP);
		emisiones.addStyleName("estilo-dos");
		emisiones.addStyleName("font-awesome-variants");
		emisiones.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		emisiones.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
		
		Button salir = new Button("Salir", new Button.ClickListener(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(ViewLogin.NAME);
				
			}
		});
		salir.setIcon(FontAwesome.SIGN_OUT);
		salir.addStyleName("estilo-uno");
		salir.addStyleName("font-awesome-variants");
		salir.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		salir.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
		vLayout.setMargin(true);
		vLayout.setSpacing(true);
		vLayout.addStyleName("layout-estilo");
		vLayout.addComponents(emisiones,salir);
		addComponent(vLayout);
}
	/*.ver-post-estilo .v-icon{
        font-size: 30px;
    }*/
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
