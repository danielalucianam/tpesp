package Rapido_y_furioso;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;


public class Vertice {
	private String nombre;
	private double latitud;
	private double longitud;
	private ArrayList<Arista> vecinos;
	private MapMarker marcador;
	private int cantVecinos;
	
	//variables para el Algoritmo de Disjktra
	private int longitudCamino = Integer.MAX_VALUE; 
	private Vertice anterior = null;
	private boolean visitado = false;
	
	public Vertice(String n, double lat, double lon) {
		nombre = n;
		latitud = lat;
		longitud = lon;
		vecinos = new ArrayList<Arista>();
		cantVecinos = 0;
	}
	
	public String nombre() {
		return nombre;
	}
	
	public Coordinate coordenadas() {
		return new Coordinate(this.latitud,this.longitud);
	}
	
	public ArrayList<Arista> vecinos() {
		return vecinos;
	}
	
	public int cantVecinos() {
		return cantVecinos;
	}
	
	public void agregarVecino(Vertice vecino, int longitud, boolean peaje) {	
		Arista ruta = new Arista(vecino, longitud, peaje);		
		vecinos.add(ruta);
		cantVecinos++;
	}
	
	public void quitarVecino(Vertice vecino) {
		boolean cond = true;
		int indice = 0;
		while (cond && indice < cantVecinos) {
			Arista ruta = vecinos.get(indice);
			if (ruta.vecino().equals(vecino)) {
				vecinos.remove(ruta);
				cond = false;
				cantVecinos--;
			}
			indice++;
		}	
	}
	
	public boolean esVecino(Vertice v) {
		for (Arista ruta : vecinos) {
			Vertice vecino = ruta.vecino();
			if (vecino.equals(v))
				return true;
		}
		return false;
	}
	
	//metodos para el Algoritmo de Disjktra
	
	public void editarVisitado(boolean v) {
        visitado = v;
    }
	
    public boolean estaVisitado() {
        return visitado;
    }
	
	public int longitudCamino() {
	    return longitudCamino;
	}
	
	public void editarLongitudCamino(int l) {
        longitudCamino = l;
    }
	
	public Vertice anterior() {
        return anterior;
    }

    public void editarAnterior(Vertice a) {
        anterior = a;
    }
    
    public void crearMarcador(int n) {
    	marcador = new MapMarkerDot(" " + nombre + " (" + n + ")", new Coordinate(latitud, longitud));  	
    }
    
    public MapMarker marcar() {
    	return marcador;
    }

}
