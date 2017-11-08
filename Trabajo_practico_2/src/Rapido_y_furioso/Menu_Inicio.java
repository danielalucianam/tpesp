package Rapido_y_furioso;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

public class Menu_Inicio {
	private JMapViewer mapa;
	private JButton addCiudad_btn;
	private JButton removeCiudad_btn;
	private JButton addRuta_btn;
	private JButton removeRuta_btn;
	private JButton caminoMinimo_btn;
	private JButton volver_btn;
	private ArrayList<JButton> botones;
	private Font letra;
	
	public Menu_Inicio(JMapViewer mapa) {
		this.mapa = mapa;
		
		botones = new ArrayList<JButton>();
		
		botones.add(addCiudad_btn = new JButton("Agregar una ciudad"));
		botones.add(removeCiudad_btn = new JButton("Quitar una ciudad"));
		botones.add(addRuta_btn = new JButton("Agregar una ruta"));
		botones.add(removeRuta_btn = new JButton("Quitar una ruta"));
		botones.add(caminoMinimo_btn = new JButton("Camino mínimo"));
		
		volver_btn = new JButton("Volver al menú");
		
		letra = new Font("Century Gothic", Font.BOLD, 14);

	}
	
	public JButton addCiudad_btn() {
		return addCiudad_btn;
	}

	public JButton removeCiudad_btn() {
		return removeCiudad_btn;
	}

	public JButton addRuta_btn() {
		return addRuta_btn;
	}

	public JButton removeRuta_btn() {
		return removeRuta_btn;
	}

	public JButton caminoMinimo_btn() {
		return caminoMinimo_btn;
	}

	public JButton volver_btn() {
		return volver_btn;
	}

	public void ejecutar() {
		int y = 250;
		for (JButton boton : botones) {
			boton.setForeground(Color.BLACK);
			boton.setFont(letra);
			boton.setBounds(690, y, 171, 42);
			mapa.add(boton);
			y += 50;
		}
		
		volver_btn.setForeground(Color.BLACK);
		volver_btn.setFont(letra);
		volver_btn.setBounds(690, 635, 171, 42);
		mapa.add(volver_btn);
		
		volver_btn.setEnabled(false);
		volver_btn.setVisible(false);	
	}
	
	public void limpiar(boolean estado) {
		for (JButton boton : botones) {
			boton.setEnabled(!estado);
			boton.setVisible(!estado);
		}
		
		volver_btn.setEnabled(estado);
		volver_btn.setVisible(estado);
	}

}
