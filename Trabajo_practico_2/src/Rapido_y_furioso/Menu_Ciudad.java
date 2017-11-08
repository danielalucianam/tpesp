package Rapido_y_furioso;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

public class Menu_Ciudad {
	private JMapViewer mapa;
	private JLabel nombre_label;
	private JLabel latitud_label;
	private JLabel longitud_label;
	private JLabel elegir_label;
	private JTextField nombre_text;
	private JTextField latitud_text;
	private JTextField longitud_text;
	private JSpinner num_id_spin;
	private JButton agregar_btn;
	private JButton quitar_btn;
	private ArrayList<JLabel> labels;
	private ArrayList<JTextField> textos;
	private Font letra_label;
	private Font letra_texto;
	private Font letra_boton;
	private GrafoJSON grafoJSON;
	private Mapa mapa_class;
	
	public Menu_Ciudad(JMapViewer mapa, Mapa mapa2)  {
		this.mapa = mapa;
		mapa_class = mapa2;
		labels = new ArrayList<JLabel>();	
		textos = new ArrayList<JTextField>();
		
		labels.add(nombre_label = new JLabel("Nombre:"));
		labels.add(latitud_label = new JLabel("Latitud:"));
		labels.add(longitud_label = new JLabel("Longitud:"));
		
		textos.add(nombre_text = new JTextField());		
		textos.add(latitud_text = new JTextField());	
		textos.add(longitud_text = new JTextField());	
		
		elegir_label = new JLabel("Número ID de la ciudad:");
		num_id_spin = new JSpinner();
		agregar_btn = new JButton("Agregar");
		quitar_btn = new JButton("Quitar");
		
		letra_label = new Font("Consolas", Font.BOLD, 18);
		letra_texto = new Font("Consolas", Font.PLAIN, 12);
		letra_boton = new Font("Century Gothic", Font.BOLD, 14);
	}
	
	
	public JTextField nombre_text() {
		return nombre_text;
	}

	public JTextField latitud_text() {
		return latitud_text;
	}

	public JTextField longitud_text() {
		return longitud_text;
	}
	
	public JSpinner num_id_spin() {
		return num_id_spin;
	}

	public JButton agregar_btn() {
		return agregar_btn;
	}
	
	public JButton quitar_btn() {
		return quitar_btn;
	}

	public void ejecutar(int cantCiudades) {
		
		//Botones para agregar una ciudad.
		int y = 271;
		for (JLabel label : labels) {
			label.setFont(letra_label);
			label.setBounds(545, y, 100, 35);
			mapa.add(label);
			y += 90;
		}
		y = 271;
		for (JTextField texto : textos) {
			texto.setFont(letra_texto);
			texto.setBounds(676, y, 185, 27);
			texto.setText("");
			mapa.add(texto);
			
			y += 90;
		}
		
		agregar_btn.setForeground(Color.BLACK);
		agregar_btn.setFont(letra_boton);
		agregar_btn.setBounds(690, 525, 171, 42);
		mapa.add(agregar_btn);
		
		//Botones para quitar una ciudad.
		elegir_label.setFont(letra_label);
		elegir_label.setBounds(560, 361, 300, 35);
		mapa.add(elegir_label);
		
		num_id_spin.setFont(letra_texto);
		num_id_spin.setBounds(810, 361, 50, 27);
		if (cantCiudades == 0)
			cantCiudades ++;
		num_id_spin.setModel(new SpinnerNumberModel(0, 0, cantCiudades-1, 1));
		mapa.add(num_id_spin);
		
		quitar_btn.setForeground(Color.BLACK);
		quitar_btn.setFont(letra_boton);
		quitar_btn.setBounds(690, 435, 171, 42);
		mapa.add(quitar_btn);
	}
	
	public void agregar_ciudad() {
		for (JLabel label : labels)
			label.setVisible(true);
		for (JTextField texto : textos) {
			texto.setEnabled(true);
			texto.setVisible(true);
		}
		agregar_btn.setEnabled(true);
		agregar_btn.setVisible(true);
		
		agregar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nombre = nombre_text.getText();
					double latitud = Double.parseDouble(latitud_text.getText());
					double longitud = Double.parseDouble(longitud_text.getText());
					
					grafoJSON = GrafoJSON.leerJSON("GrafoInicial.JSON");
					grafoJSON.agregarCiudad(nombre, latitud, longitud);
					grafoJSON.generarJSON("GrafoInicial.JSON");
					
					mapa_class.grafo().agregarCiudad(nombre, latitud, longitud);
					mapa_class.marcar_ciudad_nueva();
					
					for (JTextField texto : textos)
						texto.setText("");
				} catch (Exception n) {
					
				}
			}
		});
		
	}
	
	public void quitar_ciudad() {
		elegir_label.setVisible(true);
		
		num_id_spin.setEnabled(true);
		num_id_spin.setVisible(true);
		
		quitar_btn.setEnabled(true);
		quitar_btn.setVisible(true);
		
		quitar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				int indice = Integer.parseInt(num_id_spin.getValue().toString());
				
				try {
					grafoJSON = GrafoJSON.leerJSON("GrafoInicial.JSON");
					grafoJSON.quitarCiudad(indice);
					grafoJSON.generarJSON("GrafoInicial.JSON");
					
					mapa_class.borrar_ciudad(indice);
					mapa_class.grafo().quitarCiudad(indice);
					mapa_class.actualizar_ciudades();
					
					ejecutar(mapa_class.grafo().cantCiudades());
				} catch (Exception n) {
					
				}

			}
		});	
	}
	
	public void limpiar() {
		for (JLabel label : labels)
			label.setVisible(false);
		for (JTextField texto : textos) {
			texto.setEnabled(false);
			texto.setVisible(false);
		}
		agregar_btn.setEnabled(false);
		agregar_btn.setVisible(false);
		
		elegir_label.setVisible(false);
		
		num_id_spin.setEnabled(false);
		num_id_spin.setVisible(false);
		
		quitar_btn.setEnabled(false);
		quitar_btn.setVisible(false);
	}
	
}
