package Rapido_y_furioso;

import java.util.ArrayList;

public class Dijkstra {
	GrafoMapa grafo;
	ArrayList<Vertice> verticesSinVerificar;
	
	public Dijkstra (GrafoMapa grafo) {
		this.grafo = grafo;
		verticesSinVerificar = new ArrayList<Vertice>();
	}
	
	public void ejecutar(Vertice inicio) {
		inicio.editarLongitudCamino(0);
		verticesSinVerificar.add(inicio);
		while (!verticesSinVerificar.isEmpty()) {
			Vertice cercano = buscarCercano (verticesSinVerificar);
			cercano.editarVisitado(true);
			verticesSinVerificar.remove(cercano);
			buscarCamino(cercano);
		}		
	}

	private Vertice buscarCercano(ArrayList<Vertice> verticesVecinos) {
		Vertice cercano = new Vertice("", 0, 0);
		cercano.editarLongitudCamino(Integer.MAX_VALUE);
		for (Vertice v : verticesVecinos)
			if (v.longitudCamino() < cercano.longitudCamino())
				cercano = v;
		return cercano;
	}
	
	private void buscarCamino(Vertice ciudad) {
		ArrayList<Arista> vecinos = ciudad.vecinos();
		if (vecinos.size() != 0) 
			for (Arista camino : vecinos) {
				Vertice vecino = camino.vecino();
				if (!vecino.estaVisitado()) {
					if (verticesSinVerificar.contains(vecino)) {
						int longitud = ciudad.longitudCamino() + camino.largo();
						if (vecino.longitudCamino() > longitud) {
							vecino.editarLongitudCamino(longitud);
							vecino.editarAnterior(ciudad);
						}
					}
					else {
						vecino.editarLongitudCamino(ciudad.longitudCamino() + camino.largo());
						vecino.editarAnterior(ciudad);
						verticesSinVerificar.add(vecino);
					}
				}
			}
		
	}
	
	public ArrayList<Integer> rutaCorta(Vertice inicio, Vertice fin) {
		ArrayList<Integer> ruta = new ArrayList<Integer>();
		Vertice actual = fin;
		while (actual.anterior() != null) {
			ruta.add(grafo.ciudades().indexOf(actual));
			actual = actual.anterior();
		}
		ruta.add(grafo.ciudades().indexOf(inicio));
		ruta.add(fin.longitudCamino());
		return ruta;
		
	}

}

