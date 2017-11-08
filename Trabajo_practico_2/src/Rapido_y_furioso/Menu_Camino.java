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

public class Menu_Camino {
	private JMapViewer mapa;
	private JLabel num_id1_label;
	private JLabel num_id2_label;
	private JLabel peajes_label;
	private JLabel distancia_label;
	private JSpinner num_id1_spin;
	private JSpinner num_id2_spin;
	private JTextField peajes_text;
	private JButton calcular_btn;
	private ArrayList<JLabel> labels;
	private ArrayList<JSpinner> spinners;
	private Font letra_label;
	private Font letra_texto;
	private Font letra_boton;
	private Mapa mapa_class;

	public Menu_Camino(JMapViewer mapa, Mapa m2) {
		this.mapa = mapa;
		mapa_class =m2;
		labels = new ArrayList<JLabel>();
		spinners = new ArrayList<JSpinner>();

		labels.add(num_id1_label = new JLabel("Número ID ciudad origen:"));
		labels.add(num_id2_label = new JLabel("Número ID ciudad destino:"));
		labels.add(peajes_label = new JLabel("Cantidad máx. peajes:"));

		spinners.add(num_id1_spin = new JSpinner());
		spinners.add(num_id2_spin = new JSpinner());

		peajes_text = new JTextField();
		
		distancia_label = new JLabel();

		calcular_btn = new JButton("Calcular");

		letra_label = new Font("Consolas", Font.BOLD, 18);
		letra_texto = new Font("Consolas", Font.PLAIN, 12);
		letra_boton = new Font("Century Gothic", Font.BOLD, 14);
	}

	public JSpinner num_id1_spin() {
		return num_id1_spin;
	}

	public JSpinner num_id2_spin() {
		return num_id2_spin;
	}

	public JTextField peajes_text() {
		return peajes_text;
	}

	public JButton calcular_btn() {
		return calcular_btn;
	}

	public void ejecutar(int cantCiudades) {
		int y = 271;
		for (JLabel label : labels) {
			label.setFont(letra_label);
			label.setBounds(550, y, 300, 35);
			mapa.add(label);
			y += 60;
		}
		
		if (cantCiudades == 0)
			cantCiudades ++;
		y = 271;
		for (JSpinner spinner : spinners) {
			spinner.setFont(letra_texto);
			spinner.setBounds(810, y, 50, 27);
			spinner.setModel(new SpinnerNumberModel(0, 0, cantCiudades-1, 1));
			mapa.add(spinner);
			y += 60;
		}

		peajes_text.setFont(letra_texto);
		peajes_text.setBounds(772, 391, 90, 27);
		peajes_text.setText("");
		mapa.add(peajes_text);

		calcular_btn.setForeground(Color.BLACK);
		calcular_btn.setFont(letra_boton);
		calcular_btn.setBounds(690, 470, 171, 42);
		mapa.add(calcular_btn);

		calcular_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int indice_1 = Integer.parseInt(num_id1_spin.getValue().toString());
					int indice_2 = Integer.parseInt(num_id2_spin.getValue().toString());
					int peajes = Integer.parseInt(peajes_text.getText());
					mapa_class.marcar_camino_minimo(indice_1, indice_2, peajes);
					
				} catch (Exception n) {

				}
				
			}
		});

	}

	public void mostrarDistancia(int largo) {
		if (largo == Integer.MAX_VALUE)
			largo = 0;
		distancia_label.setVisible(false);
		distancia_label = new JLabel("Distancia recorrida: " + largo);
		distancia_label.setFont(letra_label);
		distancia_label.setForeground(Color.RED);
		distancia_label.setBounds(600, 550, 300, 35);
		distancia_label.setVisible(true);
		mapa.add(distancia_label);
	
	}
	
	public void limpiar(boolean estado) {
		for (JLabel label : labels) 
			label.setVisible(!estado);

		for (JSpinner spinner : spinners) {
			spinner.setEnabled(!estado);
			spinner.setVisible(!estado);
		}
		peajes_text.setEnabled(!estado);
		peajes_text.setVisible(!estado);

		distancia_label.setVisible(false);
		
		calcular_btn.setEnabled(!estado);
		calcular_btn.setVisible(!estado);

	}


}
