package Rapido_y_furioso;

import java.util.ArrayList;

public class GrafoMapa {
	private ArrayList<Vertice> ciudades;
	private int cantCiudades;
	

	public GrafoMapa() {
		ciudades = new ArrayList<Vertice>();
		cantCiudades = 0;
	}

	public ArrayList<Vertice> ciudades() {
		return ciudades;
	}

	public int cantCiudades() {
		return cantCiudades;
	}

	public void agregarCiudad(String nombre, double latitud, double longitud) {
		Vertice ciudad = new Vertice(nombre, latitud, longitud);
		ciudades.add(ciudad);
		ciudad.crearMarcador(ciudades.indexOf(ciudad));
		cantCiudades++;
	}

	public void agregarCiudad(Vertice ciudad) {
		ciudades.add(ciudad);
		ciudad.crearMarcador(ciudades.indexOf(ciudad));
		cantCiudades++;
	}

	public void quitarCiudad(int v) {
		Vertice ciudad = ciudades.get(v);
		for (Arista ruta : ciudad.vecinos()) {
			Vertice vecino = ruta.vecino();
			vecino.quitarVecino(ciudad);	
		}
		ciudades.remove(v);
		cantCiudades--;
	}

	public void agregarRuta(int v1, int v2, int longitud, boolean peaje) {
		Vertice ciudad1 = ciudades.get(v1);
		Vertice ciudad2 = ciudades.get(v2);
		ciudad1.agregarVecino(ciudad2, longitud, peaje);
		ciudad2.agregarVecino(ciudad1, longitud, peaje);
	}

	public boolean sonVecinos(int v1, int v2) {
		Vertice ciudad1 = ciudades.get(v1);
		Vertice ciudad2 = ciudades.get(v2);
		return ciudad1.esVecino(ciudad2) && ciudad2.esVecino(ciudad1);
	}

	public void quitarRuta(int v1, int v2) {
		Vertice ciudad1 = ciudades.get(v1);
		Vertice ciudad2 = ciudades.get(v2);
		ciudad1.quitarVecino(ciudad2);
		ciudad2.quitarVecino(ciudad1);
	}

	public ArrayList<Integer> rutaMinEntre(int inicio, int fin, int peajes) {

		GrafoMapa nuevoGrafo = new GrafoMapa();
		
		aumentarCiudades(nuevoGrafo);
		
		grafoSinPeajes(nuevoGrafo, inicio);

		ArrayList<Integer> rutaCorta = caminoMinimo(nuevoGrafo, inicio, fin, peajes);
		
		rutaCorta = bajarIndices(rutaCorta);
		
		return rutaCorta;
	}

	private void grafoSinPeajes(GrafoMapa nuevo, int inicio) {
		String visitados = "";
		grafoSinPeajes(nuevo, inicio, 0, visitados);
	}

	private void grafoSinPeajes(GrafoMapa nuevo, int inicio, int peajesPagados, String visitados) {	
		visitados += "(" + inicio + ")";
		int inicio_actualizado = inicio + this.cantCiudades * peajesPagados;
		
		Vertice ciudad = this.ciudades.get(inicio);
		for (Arista ruta : ciudad.vecinos()) {
			Vertice vecino = ruta.vecino();
			int vecino_indice = this.ciudades.indexOf(vecino);

			if (!visitados.contains("(" + vecino_indice + ")")) {	
				
				int indiceMax = this.cantCiudades * (peajesPagados);
				int indice = vecino_indice;
				while (indice < indiceMax) {
					if (nuevo.sonVecinos(inicio_actualizado, indice))
						nuevo.quitarRuta(inicio_actualizado, indice);
					indice += this.cantCiudades;
				}
				
				if (ruta.peaje()) {
					peajesPagados++;
					int vecino_actualizado = vecino_indice + this.cantCiudades * peajesPagados;
					if (vecino_actualizado >= nuevo.cantCiudades)
						aumentarCiudades(nuevo);
					if (!nuevo.sonVecinos(inicio_actualizado, vecino_actualizado))
						nuevo.agregarRuta(inicio_actualizado, vecino_actualizado, ruta.largo(), false);
					grafoSinPeajes(nuevo, vecino_indice, peajesPagados, visitados);
					peajesPagados--;
				}
				
				else {
					int vecino_actualizado = vecino_indice + this.cantCiudades * peajesPagados;	
					if (!nuevo.sonVecinos(inicio_actualizado, vecino_actualizado))
						nuevo.agregarRuta(inicio_actualizado, vecino_actualizado, ruta.largo(), false);
					grafoSinPeajes(nuevo, vecino_indice, peajesPagados, visitados);
				}
			}
		}
	}
	
	private void aumentarCiudades(GrafoMapa grafo) {
		for (int i=0; i < this.cantCiudades; i++)
			grafo.agregarCiudad("", 0, 0);
	}
	
	private ArrayList<Integer> caminoMinimo(GrafoMapa grafo, int inicio, int fin, int peajes) {
		
		Dijkstra dijkstra = new Dijkstra(grafo);
		
		Vertice vInicio = grafo.ciudades.get(inicio);
			
		dijkstra.ejecutar(vInicio);
		
		ArrayList<Integer> masCorto = new ArrayList<Integer>();
		masCorto.add(Integer.MAX_VALUE);
		
		for (int i=0; i <= peajes; i++) {
			int fin_actualizado = fin + this.cantCiudades * i;
			
			if (fin_actualizado < grafo.cantCiudades) {
				Vertice vFin = grafo.ciudades.get(fin_actualizado);
				ArrayList<Integer> camino = dijkstra.rutaCorta(vInicio, vFin);
				
				int camino_largo = camino.get(camino.size()-1);
				int masCorto_largo = masCorto.get(masCorto.size()-1);
				
				if (camino_largo < masCorto_largo)
					masCorto = camino;
			}	
		}
		return masCorto;
	}
	
	private ArrayList<Integer> bajarIndices(ArrayList<Integer> rutaCorta) {
		ArrayList<Integer> nuevo = new ArrayList<Integer>();
		
		for (int i=0; i < rutaCorta.size()-1; i++) {
			int indice = rutaCorta.get(i);
			while (indice >= this.cantCiudades)
				indice -= this.cantCiudades;
			nuevo.add(indice);
		}
		int largo = rutaCorta.get(rutaCorta.size()-1);
		nuevo.add(largo);
		return nuevo;
	}


}

