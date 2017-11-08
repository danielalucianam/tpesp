package Rapido_y_furioso;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Rapido_y_furioso.GrafoMapa;
import Rapido_y_furioso.Vertice;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Mapa {

	public JFrame frame;
	private JMapViewer mapa;
	private GrafoMapa grafo;
	private Menu_Inicio menuInicio;
	private Menu_Ciudad menuCiudad;
	private Menu_Ruta menuRuta;
	private Menu_Camino menuCamino;
	private GrafoJSON grafoInicial;
	private ArrayList<MapMarker> puntos;
	private ArrayList<MapPolygon> camino;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mapa window = new Mapa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mapa() {
		initialize();
	}

	public GrafoMapa grafo() {
		return grafo;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 15, 900, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mapa = new JMapViewer();

		mapa.setZoomContolsVisible(false);
		mapa.setDisplayPositionByLatLon(-40.5, -64, 5);

		grafo = new GrafoMapa();
		
		grafoInicial = GrafoJSON.leerJSON("GrafoInicial.JSON");

		for (Vertice v: grafoInicial.ciudades)
			grafo.agregarCiudad(v);

		for (int i = 0 ; i < grafoInicial.rutas.size(); i++) {
			int v1 = grafoInicial.rutas.get(i).v1;
			int v2 = grafoInicial.rutas.get(i).v2;
			int dist = grafoInicial.rutas.get(i).longitud;
			boolean peaje = grafoInicial.rutas.get(i).peaje;
			
			grafo.agregarRuta(v1, v2, dist, peaje);
		}

		menuInicio = new Menu_Inicio(mapa);
		menuInicio.ejecutar();

		menuCiudad = new Menu_Ciudad(mapa, this);	
		menuCiudad.limpiar();

		menuRuta = new Menu_Ruta(mapa, this);
		menuRuta.limpiar();

		menuCamino = new Menu_Camino(mapa, this);
		menuCamino.limpiar(true);

		puntos = new ArrayList<MapMarker>();
		
		camino = new ArrayList<MapPolygon>();

		menuInicio.addCiudad_btn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuCiudad.ejecutar(grafo.cantCiudades());
				menuInicio.limpiar(true);
				menuCiudad.agregar_ciudad();
			}
		});

		menuInicio.removeCiudad_btn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuCiudad.ejecutar(grafo.cantCiudades());
				menuInicio.limpiar(true);	
				menuCiudad.quitar_ciudad();
			}
		});

		menuInicio.addRuta_btn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuRuta.ejecutar(grafo.cantCiudades());
				menuInicio.limpiar(true);	
				menuRuta.agregar_ruta();
			}
		});

		menuInicio.removeRuta_btn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuRuta.ejecutar(grafo.cantCiudades());
				menuInicio.limpiar(true);	
				menuRuta.quitar_ruta();
			}
		});

		menuInicio.caminoMinimo_btn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuCamino.ejecutar(grafo.cantCiudades());
				menuInicio.limpiar(true);	
				menuCamino.limpiar(false);
			}
		});

		menuInicio.volver_btn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuInicio.limpiar(false);
				menuCiudad.limpiar();
				menuRuta.limpiar();
				menuCamino.limpiar(true);
				
				if (camino.size() != 0)
					borrarCaminoMinimo();
			}
		});

		marcar_grafo();

		frame.getContentPane().add(mapa, BorderLayout.CENTER);

	}

	public void marcar_grafo() {

		MapPolygon poligono;

		for (Vertice ciudad : grafo.ciudades()) 
			for (Arista ruta : ciudad.vecinos()) {
				ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
				
				coordenadas.add(ciudad.coordenadas());
				coordenadas.add(ruta.vecino().coordenadas());
				coordenadas.add(ciudad.coordenadas());

				poligono = new MapPolygonImpl("" + ruta.largo(), coordenadas);
				ruta.editarPoligono(poligono);
				if (ruta.peaje())
					poligono.getStyle().setColor(Color.BLUE);
				else
					poligono.getStyle().setColor(Color.BLACK);
				poligono.getStyle().setBackColor(null);
				mapa.addMapPolygon(poligono);
			}

		MapMarker punto;
		for (Vertice ciudad : grafo.ciudades()) {
			punto = ciudad.marcar();
			mapa.addMapMarker(punto);
			puntos.add(punto);
		}
	}

	public void marcar_ciudad_nueva() {
		int indice = grafo.cantCiudades()-1;
		MapMarker punto = grafo.ciudades().get(indice).marcar();
		mapa.addMapMarker(punto);
		puntos.add(punto);
	}

	public void borrar_ciudad(int indice) {
		MapMarker punto = puntos.get(indice);
		mapa.removeMapMarker(punto);
		puntos.remove(indice);

		Vertice ciudad = grafo.ciudades().get(indice);

		for (Arista ruta : ciudad.vecinos()) {
			Vertice vecino = ruta.vecino();
			int indice_vecino = grafo.ciudades().indexOf(vecino);
			borrar_ruta(indice, indice_vecino);
		}
	}

	public void actualizar_ciudades() {
		for (MapMarker punto : puntos) {
			mapa.removeMapMarker(punto);
		}

		MapMarker punto;
		int indice = 0;
		for (Vertice ciudad : grafo.ciudades()) {
			ciudad.crearMarcador(indice);
			punto = ciudad.marcar();
			mapa.addMapMarker(punto);
			puntos.add(punto);
			indice++;
		}
	}

	public void marcar_ruta_nueva(int v1, int v2, int dist, boolean peaje) {
		Vertice ciudad1 = grafo.ciudades().get(v1);
		Vertice ciudad2 = grafo.ciudades().get(v2);

		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		
		coordenadas.add(ciudad1.coordenadas());
		coordenadas.add(ciudad2.coordenadas());
		coordenadas.add(ciudad1.coordenadas());

		MapPolygon poligono = new MapPolygonImpl("" + dist, coordenadas);
		if (peaje)
			poligono.getStyle().setColor(Color.BLUE);
		else
			poligono.getStyle().setColor(Color.BLACK);
		poligono.getStyle().setBackColor(null);
		mapa.addMapPolygon(poligono);

		Arista ruta = ciudad1.vecinos().get(ciudad1.cantVecinos()-1);
		ruta.editarPoligono(poligono);
	}

	public void borrar_ruta(int valor1, int valor2) {

		Vertice ciudad1 = grafo.ciudades().get(valor1);
		Vertice ciudad2 = grafo.ciudades().get(valor2);

		for (Arista ruta : ciudad1.vecinos()) {
			Vertice vecino = ruta.vecino();
			if (vecino.equals(ciudad2)) {
				MapPolygon poligono = ruta.poligono();
				mapa.removeMapPolygon(poligono);
			}
		}

		for (Arista ruta : ciudad2.vecinos()) {
			Vertice vecino = ruta.vecino();
			if (vecino.equals(ciudad1)) {
				MapPolygon poligono = ruta.poligono();
				mapa.removeMapPolygon(poligono);
			}
		}
	}

	public void marcar_camino_minimo(int indice_1, int indice_2, int peajes) {
		
		if (camino.size() != 0) 
			borrarCaminoMinimo();
		
		ArrayList<Integer> mejorCamino = grafo.rutaMinEntre(indice_1, indice_2, peajes);
		
		int largo = mejorCamino.get(mejorCamino.size()-1);
		
		menuCamino.mostrarDistancia(largo);
		
		mejorCamino.remove(mejorCamino.size()-1);
		
		MapPolygon poligono;
		
		for (int i = 0; i < mejorCamino.size()-1; i++) {
			ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
			
			coordenadas.add(grafo.ciudades().get(mejorCamino.get(i)).coordenadas());
			coordenadas.add(grafo.ciudades().get(mejorCamino.get(i+1)).coordenadas());
			coordenadas.add(grafo.ciudades().get(mejorCamino.get(i)).coordenadas());

			poligono = new MapPolygonImpl(coordenadas);
			poligono.getStyle().setColor(Color.RED);
			poligono.getStyle().setBackColor(null);
			mapa.addMapPolygon(poligono);	
			
			camino.add(poligono);
		}		
	}
	
	private void borrarCaminoMinimo() {
		for (MapPolygon p : camino)
			mapa.removeMapPolygon(p);
		camino.clear();
	}

}
