package Rapido_y_furioso;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

public class Menu_Ruta {
	private JMapViewer mapa;
	private JLabel num_id1_label;
	private JLabel num_id2_label;
	private JLabel longitud_label;
	private JLabel peaje_label;
	private JSpinner num_id1_spin;
	private JSpinner num_id2_spin;
	private JTextField long_text;
	private JRadioButton si_rdbtn;
	private JRadioButton no_rdbtn;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton agregar_btn;
	private JButton quitar_btn;
	private ArrayList<JLabel> labels1;
	private ArrayList<JLabel> labels2;
	private ArrayList<JSpinner> spinners;
	private ArrayList<JRadioButton> radios;
	private Font letra_label;
	private Font letra_texto;
	private Font letra_boton;
	private Mapa mapa_class;
	private GrafoJSON grafoJSON;

	public Menu_Ruta(JMapViewer mapa, Mapa mapa2) {
		this.mapa = mapa;
		mapa_class = mapa2;
		labels1 = new ArrayList<JLabel>();
		labels2 = new ArrayList<JLabel>();
		spinners = new ArrayList<JSpinner>();
		radios = new ArrayList<JRadioButton>();

		labels1.add(num_id1_label = new JLabel("Número ID ciudad 1:"));
		labels1.add(num_id2_label = new JLabel("Número ID ciudad 2:"));
		labels2.add(longitud_label = new JLabel("Longitud:"));
		labels2.add(peaje_label = new JLabel("¿Tiene peaje?"));

		spinners.add(num_id1_spin = new JSpinner());
		spinners.add(num_id2_spin = new JSpinner());

		long_text = new JTextField();

		radios.add(si_rdbtn = new JRadioButton("Si"));
		radios.add(no_rdbtn = new JRadioButton("No"));

		agregar_btn = new JButton("Agregar");
		quitar_btn = new JButton("Quitar");

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

	public JTextField long_text() {
		return long_text;
	}

	public JButton agregar_btn() {
		return agregar_btn;
	}

	public JButton quitar_btn() {
		return quitar_btn;
	}

	public JRadioButton si_rdbtn() {
		return si_rdbtn;
	}

	public JRadioButton no_rdbtn() {
		return no_rdbtn;
	}

	public void ejecutar(int cantCiudades) {

		int y = 271;
		for (JLabel label : labels1) {
			label.setFont(letra_label);
			label.setBounds(600, y, 300, 35);
			mapa.add(label);
			y += 60;
		}

		for (JLabel label : labels2) {
			label.setFont(letra_label);
			label.setBounds(600, y, 150, 35);
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

		long_text.setFont(letra_texto);
		long_text.setBounds(743, 391, 100, 27);
		long_text.setText("");
		mapa.add(long_text);

		int x = 750;
		for (JRadioButton radio : radios) {
			radio.setBounds(x, 460, 42, 14);
			mapa.add(radio);
			buttonGroup.add(radio);
			x += 50;
		}
		no_rdbtn.setSelected(true);

		agregar_btn.setForeground(Color.BLACK);
		agregar_btn.setFont(letra_boton);
		agregar_btn.setBounds(690, 525, 171, 42);
		mapa.add(agregar_btn);

		quitar_btn.setForeground(Color.BLACK);
		quitar_btn.setFont(letra_boton);
		quitar_btn.setBounds(690, 400, 171, 42);
		mapa.add(quitar_btn);
	}

	public void agregar_ruta() {
		for (JLabel label : labels1) 
			label.setVisible(true);
		for (JLabel label : labels2) 
			label.setVisible(true);

		for (JSpinner spinner : spinners) {
			spinner.setEnabled(true);
			spinner.setVisible(true);
		}
		long_text.setEnabled(true);
		long_text.setVisible(true);

		for (JRadioButton radio : radios) {
			radio.setEnabled(true);
			radio.setVisible(true);
		}
		agregar_btn.setEnabled(true);
		agregar_btn.setVisible(true);

		agregar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int indice_1 = Integer.parseInt(num_id1_spin.getValue().toString());
					int indice_2 = Integer.parseInt(num_id2_spin.getValue().toString());
					int distancia = Integer.parseInt(long_text.getText());
					boolean peaje = si_rdbtn.isSelected();

					if (indice_1 != indice_2) {
						grafoJSON = GrafoJSON.leerJSON("GrafoInicial.JSON");
						grafoJSON.agregarRuta(indice_1, indice_2, distancia, peaje);
						grafoJSON.agregarRuta(indice_2, indice_1, distancia, peaje);
						grafoJSON.generarJSON("GrafoInicial.JSON");
						
						if (!mapa_class.grafo().sonVecinos(indice_1, indice_2)) {
							mapa_class.grafo().agregarRuta(indice_1, indice_2, distancia, peaje);
							mapa_class.marcar_ruta_nueva(indice_1, indice_2, distancia, peaje);
							ejecutar(mapa_class.grafo().cantCiudades());
						}
					}
				} catch (Exception n) {
					
				}
			}
		});

	}

	public void quitar_ruta() {
		for (JLabel label : labels1) 
			label.setVisible(true);

		for (JSpinner spinner : spinners) {
			spinner.setEnabled(true);
			spinner.setVisible(true);
		}
		quitar_btn.setEnabled(true);
		quitar_btn.setVisible(true);

		quitar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indice_1 = Integer.parseInt(num_id1_spin.getValue().toString());
				int indice_2 = Integer.parseInt(num_id2_spin.getValue().toString());
				
				try {
					if (indice_1 != indice_2) {
						grafoJSON = GrafoJSON.leerJSON("GrafoInicial.JSON");
						grafoJSON.quitarRuta(indice_1, indice_2);
						grafoJSON.generarJSON("GrafoInicial.JSON");

						mapa_class.borrar_ruta(indice_1, indice_2);
						mapa_class.grafo().quitarRuta(indice_1, indice_2);

						ejecutar(mapa_class.grafo().cantCiudades());
					}
				} catch (Exception n){

				}

			}
		});

	}

	public void limpiar() {
		for (JLabel label : labels1)
			label.setVisible(false);
		for (JLabel label : labels2)
			label.setVisible(false);

		for (JSpinner spinner : spinners) {
			spinner.setEnabled(false);
			spinner.setVisible(false);
		}

		long_text.setEnabled(false);
		long_text.setVisible(false);

		for (JRadioButton radio : radios) {
			radio.setEnabled(false);
			radio.setVisible(false);
		}
		agregar_btn.setEnabled(false);
		agregar_btn.setVisible(false);

		quitar_btn.setEnabled(false);
		quitar_btn.setVisible(false);
	}

}
