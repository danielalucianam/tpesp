package Rapido_y_furioso;

import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class Arista {
	private Vertice vecino;
	private int largo;
	private boolean peaje;
	private MapPolygon poligono;
	
	public Arista(Vertice v, int l, boolean p) {
		vecino = v;
		largo = l;
		peaje = p;
	}
	
	public Vertice vecino() {
		return vecino;
	}
	
	public int largo() {
		return largo;
	}
	
	public boolean peaje() {
		return peaje;
	}
	
	public MapPolygon poligono() {
		return poligono;
	}
	
	public void editarPoligono(MapPolygon p) {
		poligono = p;
	}
	
	

}
