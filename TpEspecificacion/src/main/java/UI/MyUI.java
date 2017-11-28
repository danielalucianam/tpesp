package UI;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@StyleSheet({"https://fonts.googleapis.com/css?family=Slabo+27px"})
public class MyUI extends UI {
		private static final long serialVersionUID = 1L;
	Navigator navigator;
	public static final String NAME = "";
	
    @Override
    protected void init(VaadinRequest request) {
    	getPage().setTitle("Ejemplo de navegación");
    	navigator = new Navigator(this, this);
    	navigator.addView(ViewLogin.NAME, new ViewLogin());
    	navigator.addView(ViewNewPost.NAME, new ViewNewPost());
    	navigator.addView(ViewMenuPrincipal.NAME, new ViewMenuPrincipal());
        navigator.addView(ViewRegistro.NAME, new ViewRegistro());
        navigator.addView(ViewEmisiones.NAME, new ViewEmisiones());
       }
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
    } 
    
    public  Navigator getNavigator() {
    	return navigator;
    }
}
