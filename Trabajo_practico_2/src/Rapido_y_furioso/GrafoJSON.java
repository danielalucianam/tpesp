package Rapido_y_furioso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Rapido_y_furioso.Vertice;

public class GrafoJSON {
	ArrayList<Vertice> ciudades;
	ArrayList<Ruta> rutas;


	public GrafoJSON()
	{
		ciudades = new ArrayList<Vertice>();

		rutas = new ArrayList<Ruta>();

	}

	public void agregarCiudad(String nombre, double latitud, double longitud) {
		Vertice ciudad = new Vertice(nombre, latitud, longitud );
		ciudades.add(ciudad);
	}
	public void agregarRuta(int v1, int v2, int longitud, boolean peaje) {
		Ruta ruta= new Ruta (v1, v2, longitud, peaje);
		
		for (int i = 0; i < rutas.size(); i++) {
			if (rutas.get(i).v1 == v1  && rutas.get(i).v2 == v2)
				return;
			if (rutas.get(i).v1 == v2 && rutas.get(i).v2 == v1) 
				return;
		}
		rutas.add(ruta);
	}

	public void quitarCiudad(int v) {
		int indice = 0;
		while (indice < rutas.size()) {
			if (rutas.get(indice).v1 == v || rutas.get(indice).v2 == v)
				rutas.remove(indice);
			else
				indice++;
		}
		actualizarIndices(v);
		ciudades.remove(v);
	}
	
	private void actualizarIndices(int v) {
		for (int i = 0; i < rutas.size(); i++) {
			if (rutas.get(i).v1 > v)
				rutas.get(i).v1--;
			if (rutas.get(i).v2 > v)
				rutas.get(i).v2--;
		}
		
	}

	public void quitarRuta(int v1, int v2) {
		for (int i = 0; i < rutas.size(); i++) {
			if (rutas.get(i).v1 == v1  && rutas.get(i).v2 == v2) {
				rutas.remove(i);
			}
			if (rutas.get(i).v1 == v2 && rutas.get(i).v2 == v1) {
				rutas.remove(i);
			}

		}
	}	

	public void generarJSON(String archivo) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);

		try {
			FileWriter writer = new FileWriter(archivo);
			writer.write(json);
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static GrafoJSON leerJSON(String archivo) {
		Gson gson = new Gson();
		GrafoJSON ret = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			ret = gson.fromJson(br, GrafoJSON.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public class Ruta {
		int v1;
		int v2;
		int longitud;
		boolean peaje;

		Ruta(int i, int o, int L, boolean p)
		{
			v1 = i;
			v2 = o;
			longitud= L;
			peaje= p;
		}
	}
	
	public static void main(String[] args) {
		GrafoJSON grafoInicial = new GrafoJSON();

		grafoInicial.agregarCiudad("Tartagal, Salta", -22.5333, -63.8167);
		grafoInicial.agregarCiudad("San Salvador de Jujuy", -24.1833, -65.3);
		grafoInicial.agregarCiudad("Río Cuarto, Córdoba", -33.1333, -64.35);
		grafoInicial.agregarCiudad("Resistencia, Chaco", -27.45, -58.9833);
		grafoInicial.agregarCiudad("Bahía Blanca, Buenos Aires",  -38.7167,-62.2833);
		grafoInicial.agregarCiudad("Mar del Plata, Buenos Aires", -38, -57.55);
		grafoInicial.agregarCiudad("Tandil, Buenos Aires", -37.3167,-59.15);
		grafoInicial.agregarCiudad("La Plata, Buenos Aires", -34.9314, -57.9489);
		grafoInicial.agregarCiudad("Rosario, Santa Fe",-32.9511, -60.6664);
		grafoInicial.agregarCiudad("Santa Rosa, La Pampa", -36.6167,-64.2833);
		grafoInicial.agregarCiudad("San Rafael, Mendoza",-34.6,-68.3333);
		grafoInicial.agregarCiudad("San Carlos de Bariloche, Río Negro", -41.15,-71.3);
		grafoInicial.agregarCiudad("Comodoro Rivadavia, Chubut", -45.6083,-67.75);
		grafoInicial.agregarCiudad("Río Gallegos, Santa Cruz", -51.6333, -69.2167);
		grafoInicial.agregarCiudad("Ushuaia, Tierra del Fuego", -54.8,-68.3);
		grafoInicial.agregarCiudad("Posadas, Misiones", -27.3833,-55.8833);
		grafoInicial.agregarCiudad("Concordia, Entre Ríos", -31.4, -58.0333);
		grafoInicial.agregarCiudad("La Rioja, La Rioja",-29.4333,-66.85);
		grafoInicial.agregarCiudad("Recreo, Catamarca",-29.2667, -65.0667);
		grafoInicial.agregarCiudad("San Miguel de Tucumán, Tucumán", -26.8167,-65.2167);
		grafoInicial.agregarCiudad("Valcheta, Río Negro", -40.7, -66.15);
		grafoInicial.agregarCiudad("Lago Posadas, Santa Cruz", -47.5333, -71.75);
		
		

		grafoInicial.agregarRuta(14, 13, 200, false);
		grafoInicial.agregarRuta(13, 12, 400, true);
		grafoInicial.agregarRuta(11, 12, 300, false);
		grafoInicial.agregarRuta(20, 12, 300, false);
		grafoInicial.agregarRuta(4, 20, 150, false);
		grafoInicial.agregarRuta(11, 10, 400, true);
		grafoInicial.agregarRuta(4, 9, 100, false);
		grafoInicial.agregarRuta(4, 6, 100, true);
		grafoInicial.agregarRuta(5, 6, 50, false);
		grafoInicial.agregarRuta(10, 9, 200, false);
		grafoInicial.agregarRuta(2, 9, 200, true);
		grafoInicial.agregarRuta(7, 6, 100, false);
		grafoInicial.agregarRuta(2, 8, 150, false);
		grafoInicial.agregarRuta(8, 7, 150, false);
		grafoInicial.agregarRuta(8, 16, 100, false);
		grafoInicial.agregarRuta(2, 17, 200, false);
		grafoInicial.agregarRuta(17, 18, 50, true);
		grafoInicial.agregarRuta(16, 7, 150, true);
		grafoInicial.agregarRuta(16, 3, 200, false);
		grafoInicial.agregarRuta(15, 3, 100, false);
		grafoInicial.agregarRuta(19, 18, 100, false);
		grafoInicial.agregarRuta(1, 19, 100, false);
		grafoInicial.agregarRuta(1, 0, 50, false);
		grafoInicial.agregarRuta(3, 19, 300, true);
		grafoInicial.agregarRuta(17, 10, 250, false);
		grafoInicial.agregarRuta(13, 21, 300, false);
		grafoInicial.agregarRuta(12, 21, 150, false);
		grafoInicial.agregarRuta(11, 21, 400, false);
		grafoInicial.agregarRuta(11, 20, 150, false);
		grafoInicial.agregarRuta(9, 20, 200, true);
		grafoInicial.agregarRuta(0, 3, 350, false);
		

		grafoInicial.generarJSON("GrafoInicial.JSON");
	}

}
