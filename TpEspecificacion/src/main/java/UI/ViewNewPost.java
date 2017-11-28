package UI;

import java.io.File;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
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

import negocio.DAO;
import negocio.Post;
import service.PostService;
import service.UsuarioService;


public class ViewNewPost extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Perfil";
	private PostService postService;
	private UsuarioService usuarioService;
		public ViewNewPost() {
			 postService = PostService.getService();
			 usuarioService = UsuarioService.getService();
			init();
			setSizeFull();
			
			
		}
				
		private void init() {
			HorizontalLayout horizontalLayout= new HorizontalLayout();
			VerticalLayout verticalLayout = new VerticalLayout();
			
			
				Label nuevoPost = new Label("Nuevo Post");
			 	Button Menu= new Button("", new Button.ClickListener(){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						getUI().getNavigator().navigateTo(ViewMenuPrincipal.NAME);
					
					}
				});
			Menu.addStyleName(ValoTheme.BUTTON_BORDERLESS);
			
			Menu.setIcon(FontAwesome.TH_LIST);
			horizontalLayout.setStyleName("hl-estilo");
			horizontalLayout.addComponents(nuevoPost,Menu);
			
			verticalLayout.addComponents(horizontalLayout, nuevaAreaPost());
		
			
			addComponent(verticalLayout);

			
		     
			 }
		
		public VerticalLayout nuevaAreaPost() {
			VerticalLayout componente = new VerticalLayout();
		
			TextArea tf = new TextArea();
			tf.setMaxLength(300);
			tf.setHeight("200px");
			tf.setWidth("300px");
			
			
			
		
			
					Button button = new Button("Aceptar", new Button.ClickListener(){
				/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					Post posteo = postService.getEmptyPost();
					posteo.setContenido(tf.getValue());
					posteo.setUsuario(usuarioService.getLoggedUser());
					postService.save(posteo);
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

