package proyectoFinal_apuestas;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaApuestas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEquipo1;
	private JTextField textFieldEquipo2;
	private JComboBox<String> comboBoxDeporte, comboBoxTipoApuesta;
	private JComboBox<String> comboBoxTorneo;
	private JTextField textFieldMontoApuesta;
	JLabel lblEstadistica1Equipo1 = new JLabel("Estadística 1 Equipo 1:");
	JTextField textFieldEstadistica1Equipo1 = new JTextField();
	JLabel lblEstadistica1Equipo2 = new JLabel("Estadística 1 Equipo 2:");
	JTextField textFieldEstadistica1Equipo2 = new JTextField();

	JLabel lblEstadistica2Equipo1 = new JLabel("Estadística 2 Equipo 1:");
	JTextField textFieldEstadistica2Equipo1 = new JTextField();
	JLabel lblEstadistica2Equipo2 = new JLabel("Estadística 2 Equipo 2:");
	JTextField textFieldEstadistica2Equipo2 = new JTextField();

	JLabel lblEstadistica3Equipo1 = new JLabel("Estadística 3 Equipo 1:");
	JTextField textFieldEstadistica3Equipo1 = new JTextField();
	JLabel lblEstadistica3Equipo2 = new JLabel("Estadística 3 Equipo 2:");
	JTextField textFieldEstadistica3Equipo2 = new JTextField();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		
	}

	/**
	 * Create the frame.
	 */
	public VentanaApuestas(Apostador user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Apuesas Usuario");
		setBounds(100, 100, 500, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));

		
		
		JPanel header = new JPanel();
		header.setBackground(new Color(45, 104, 184));
		contentPane.add(header, BorderLayout.NORTH);
		header.setLayout(new GridLayout(1, 2, 5, 15));
		
		JLabel UCBetTITLE = new JLabel("UCBet");
		UCBetTITLE.setForeground(new Color(255, 232, 0));
		UCBetTITLE.setFont(new Font("Tahoma", Font.BOLD, 25));
		UCBetTITLE.setHorizontalAlignment(SwingConstants.LEFT);
		header.add(UCBetTITLE);
		
		JPanel panHeadDer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panHeadDer.setBackground(new Color(45, 104, 184));
		header.add(panHeadDer);
		
		
		JLabel lblSaldo = new JLabel();
		lblSaldo.setText("Bs. "+user.getSaldo());
		lblSaldo.setForeground(new Color(255,255,255));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		panHeadDer.add(lblSaldo);
		
		JLabel lblPerfil = new JLabel();
		lblPerfil.setText(user.getNombre());
		lblPerfil.setBackground(new Color(45, 104, 184));
		lblPerfil.setForeground(new Color(255, 255, 255));
		panHeadDer.add(lblPerfil);
		
		

		JPanel panCentro = new JPanel(new GridLayout(14, 2, 10, 10));
		contentPane.add(panCentro, BorderLayout.CENTER);

		JLabel lblDeporte = new JLabel("Deporte:");
		panCentro.add(lblDeporte);
		comboBoxDeporte = new JComboBox<>(new String[] {"Seleccionar deporte", "Fútbol", "Basketball"});
		panCentro.add(comboBoxDeporte);
		

		JLabel lblTorneo = new JLabel("Torneo:");
		panCentro.add(lblTorneo);
		
		comboBoxTorneo = new JComboBox<String>();
		panCentro.add(comboBoxTorneo);

		JLabel lblEquipo1 = new JLabel("Equipo 1:");
		panCentro.add(lblEquipo1);
		textFieldEquipo1 = new JTextField();
		panCentro.add(textFieldEquipo1);

		JLabel lblEquipo2 = new JLabel("Equipo 2:");
		panCentro.add(lblEquipo2);
		textFieldEquipo2 = new JTextField();
		panCentro.add(textFieldEquipo2);
			

		JLabel lblTipoApuesta = new JLabel("Tipo de Apuesta:");
		panCentro.add(lblTipoApuesta);
		comboBoxTipoApuesta = new JComboBox<>(new String[] {"Seleccionar tipo de Apuesta", "Gana uno de los dos equipos", "Ganador y goles acertados", "Todas las estadísticas"});
		panCentro.add(comboBoxTipoApuesta);

		

		JLabel lblCuota = new JLabel("Cuota:");
		panCentro.add(lblCuota);
		
		
		JLabel lblCuotaApuesta = new JLabel("");
		panCentro.add(lblCuotaApuesta);

			comboBoxTipoApuesta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBoxTipoApuesta.getSelectedIndex()==1) {
					lblCuotaApuesta.setText("1.5");	
				}else if (comboBoxTipoApuesta.getSelectedIndex()==2) {
					lblCuotaApuesta.setText("3.0");
				} else if (comboBoxTipoApuesta.getSelectedIndex()==3) {
					lblCuotaApuesta.setText("20.0");
				}
			}
		});
		
		JLabel lblMonto = new JLabel("Monto Apostado:");
		panCentro.add(lblMonto);
		
		textFieldMontoApuesta = new JTextField();
		panCentro.add(textFieldMontoApuesta);
		
		JLabel lblElegirEquipoGanador = new JLabel("Equipo Ganador:");
		panCentro.add(lblElegirEquipoGanador);
		
		JComboBox comboBoxGanador = new JComboBox();
		comboBoxGanador.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Ganador"}));	
		panCentro.add(comboBoxGanador);
		
		
		panCentro.add(lblEstadistica1Equipo1);
		panCentro.add(textFieldEstadistica1Equipo1);
		textFieldEstadistica1Equipo1.setColumns(10); 

		panCentro.add(lblEstadistica1Equipo2);
		panCentro.add(textFieldEstadistica1Equipo2);
		textFieldEstadistica1Equipo2.setColumns(10);

		panCentro.add(lblEstadistica2Equipo1);
		panCentro.add(textFieldEstadistica2Equipo1);
		textFieldEstadistica2Equipo1.setColumns(10);

		panCentro.add(lblEstadistica2Equipo2);
		panCentro.add(textFieldEstadistica2Equipo2);
		textFieldEstadistica2Equipo2.setColumns(10);

		panCentro.add(lblEstadistica3Equipo1);
		panCentro.add(textFieldEstadistica3Equipo1);
		textFieldEstadistica3Equipo1.setColumns(10);

		panCentro.add(lblEstadistica3Equipo2);
		panCentro.add(textFieldEstadistica3Equipo2);
		textFieldEstadistica3Equipo2.setColumns(10);
		
		comboBoxDeporte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBoxDeporte.getSelectedIndex()==1) {
					comboBoxTorneo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar torneo", "Champions league", "Libertadores", "Premier league Boliviana", "La Liga", "Serie A", "Premier League", "Amistoso"}));
					lblEstadistica1Equipo1.setText("Goles Equipo 1:");
					textFieldEstadistica1Equipo1.setText(""); 
					lblEstadistica1Equipo2.setText("Goles Equipo 2:");
					textFieldEstadistica1Equipo2.setText("");

					lblEstadistica2Equipo1.setText("Amarillas Equipo 1:");
					textFieldEstadistica2Equipo1.setText("");
					lblEstadistica2Equipo2.setText("Amarillas Equipo 2:");
					textFieldEstadistica2Equipo2.setText("");

					lblEstadistica3Equipo1.setText("Rojas Equipo 1:");
					textFieldEstadistica3Equipo1.setText("");
					lblEstadistica3Equipo2.setText("Rojas Equipo 2:");
					textFieldEstadistica3Equipo2.setText("");

				}else if (comboBoxDeporte.getSelectedIndex()==2) {
					comboBoxTorneo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar torneo", "NBA", "Euro League", "ACB", "FIBA", "NCAA", "Amistoso"}));
					lblEstadistica1Equipo1.setText("Puntos Equipo 1:");
					textFieldEstadistica1Equipo1.setText("");
					lblEstadistica1Equipo2.setText("Puntos Equipo 2:");
					textFieldEstadistica1Equipo2.setText("");

					lblEstadistica2Equipo1.setText("Triples Equipo 1:");
					textFieldEstadistica2Equipo1.setText("");
					lblEstadistica2Equipo2.setText("Triples Equipo 2:");
					textFieldEstadistica2Equipo2.setText("");

					lblEstadistica3Equipo1.setText("Faltas Equipo 1:");
					textFieldEstadistica3Equipo1.setText("");
					lblEstadistica3Equipo2.setText("Faltas Equipo 2:");
					textFieldEstadistica3Equipo2.setText("");
				}else {
					comboBoxTorneo.setModel(new DefaultComboBoxModel(new String[] {""}));	
					lblEstadistica1Equipo1.setText("Estadística 1 Equipo 1:");
					textFieldEstadistica1Equipo1.setText("");
					lblEstadistica1Equipo2.setText("Estadística 1 Equipo 2:");
					textFieldEstadistica1Equipo2.setText("");

					lblEstadistica2Equipo1.setText("Estadística 2 Equipo 1:");
					textFieldEstadistica2Equipo1.setText("");
					lblEstadistica2Equipo2.setText("Estadística 2 Equipo 2:");
					textFieldEstadistica2Equipo2.setText("");

					lblEstadistica3Equipo1.setText("Estadística 3 Equipo 1:");
					textFieldEstadistica3Equipo1.setText("");
					lblEstadistica3Equipo2.setText("Estadística 3 Equipo 2:");
					textFieldEstadistica3Equipo2.setText("");
				}
				panCentro.revalidate();
				panCentro.repaint();
				actualizarComboBoxGanador(comboBoxGanador, textFieldEquipo1.getText(), textFieldEquipo2.getText());
				}
		});
		textFieldEquipo1.addActionListener(e -> {
			actualizarComboBoxGanador(comboBoxGanador, textFieldEquipo1.getText(), textFieldEquipo2.getText());
		});
		textFieldEquipo2.addActionListener(e -> {
			actualizarComboBoxGanador(comboBoxGanador, textFieldEquipo1.getText(), textFieldEquipo2.getText());
		});
		
		
		
		JPanel panBotones = new JPanel();
		JButton btnApuesta = new JButton("Apuesta");
		panBotones.add(btnApuesta);
		contentPane.add(panBotones, BorderLayout.SOUTH);

		btnApuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eq1 = textFieldEquipo1.getText().trim();
				String eq2 = textFieldEquipo2.getText().trim();

				if (eq1.isEmpty() || eq2.isEmpty()) {
					JOptionPane.showMessageDialog(btnApuesta, "Debe ingresar nombres de ambos equipos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (comboBoxDeporte.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(btnApuesta, "Debe seleccionar un deporte.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (comboBoxTorneo.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(btnApuesta, "Debe seleccionar un torneo.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (comboBoxTipoApuesta.getSelectedIndex() == 0) { 
				    JOptionPane.showMessageDialog(btnApuesta, "Debe seleccionar un tipo de apuesta.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
				    return;
				}
				if (comboBoxGanador.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(btnApuesta, "Debe seleccionar un equipo ganador.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
					return;
				}

				double montoAp = 0;
				try {
					String montoText = textFieldMontoApuesta.getText().trim();
					if (montoText.isEmpty()) {
						JOptionPane.showMessageDialog(btnApuesta, "Debe ingresar el monto de la apuesta.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
						return;
					}
					montoAp = Double.parseDouble(montoText);
					if (montoAp <= 0) {
						JOptionPane.showMessageDialog(btnApuesta, "El monto de la apuesta debe ser mayor que cero.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
						return;
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(btnApuesta, "El monto de la apuesta debe ser un número válido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int tor = comboBoxTorneo.getSelectedIndex()-1;
				int tipoAp = comboBoxTipoApuesta.getSelectedIndex()-1;
				double cuota =  Double.parseDouble(lblCuotaApuesta.getText()); 
				int eqGanador = comboBoxGanador.getSelectedIndex() - 1;
				
				int est1eq1 = 0, est1eq2 = 0, est2eq1 = 0, est2eq2 = 0, est3eq1 = 0, est3eq2 = 0;


				if (tipoAp == 1 || tipoAp == 2) {
					String s1e1 = textFieldEstadistica1Equipo1.getText().trim();
					String s1e2 = textFieldEstadistica1Equipo2.getText().trim();

					if (s1e1.isEmpty() || s1e2.isEmpty()) {
						JOptionPane.showMessageDialog(btnApuesta, "Debe ingresar valores para " + lblEstadistica1Equipo1.getText().replace(":", "") + " y " + lblEstadistica1Equipo2.getText().replace(":", "") + ".", "Error de Validación", JOptionPane.WARNING_MESSAGE);
						return;
					}

					try {
						est1eq1 = Integer.parseInt(s1e1);
						est1eq2 = Integer.parseInt(s1e2);
						if (est1eq1 < 0 || est1eq2 < 0) {
							JOptionPane.showMessageDialog(btnApuesta, "Las estadísticas (" + lblEstadistica1Equipo1.getText().replace(":", "") + ") no pueden ser negativas.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
							return;
						}
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(btnApuesta, "Los campos '" + lblEstadistica1Equipo1.getText().replace(":", "") + "' deben ser números válidos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				
				if (tipoAp == 2) {
					String s2e1 = textFieldEstadistica2Equipo1.getText().trim();
					String s2e2 = textFieldEstadistica2Equipo2.getText().trim();
					String s3e1 = textFieldEstadistica3Equipo1.getText().trim();
					String s3e2 = textFieldEstadistica3Equipo2.getText().trim();

					if (s2e1.isEmpty() || s2e2.isEmpty() || s3e1.isEmpty() || s3e2.isEmpty()) {
						JOptionPane.showMessageDialog(btnApuesta, "Debe ingresar valores para todas las estadísticas requeridas (Estadística 2 y 3).", "Error de Validación", JOptionPane.WARNING_MESSAGE);
						return;
					}

					try {
						est2eq1 = Integer.parseInt(s2e1);
						if (est2eq1 < 0) {
							JOptionPane.showMessageDialog(btnApuesta, lblEstadistica2Equipo1.getText().replace(":", "") + " no puede ser negativo.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
							return;
						}
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(btnApuesta, lblEstadistica2Equipo1.getText().replace(":", "") + " debe ser un número válido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
						return;
					}

					try {
						est2eq2 = Integer.parseInt(s2e2);
						if (est2eq2 < 0) {
							JOptionPane.showMessageDialog(btnApuesta, lblEstadistica2Equipo2.getText().replace(":", "") + " no puede ser negativo.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
							return;
						}
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(btnApuesta, lblEstadistica2Equipo2.getText().replace(":", "") + " debe ser un número válido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
						return;
					}

					try {
						est3eq1 = Integer.parseInt(s3e1);
						if (est3eq1 < 0) {
							JOptionPane.showMessageDialog(btnApuesta, lblEstadistica3Equipo1.getText().replace(":", "") + " no puede ser negativo.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
							return;
						}
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(btnApuesta, lblEstadistica3Equipo1.getText().replace(":", "") + " debe ser un número válido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
						return;
					}

					try {
						est3eq2 = Integer.parseInt(s3e2);
						if (est3eq2 < 0) {
							JOptionPane.showMessageDialog(btnApuesta, lblEstadistica3Equipo2.getText().replace(":", "") + " no puede ser negativo.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
							return;
						}
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(btnApuesta, lblEstadistica3Equipo2.getText().replace(":", "") + " debe ser un número válido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}

				boolean exito = false;
				if (comboBoxDeporte.getSelectedIndex()==1) {
					if (tipoAp == 0) {
						PrediccionFutbol predictFut = new PrediccionFutbol(montoAp,eqGanador,0,0,0,0,0,0);
						exito = predictFut.registrarPrediccionesFutbolTxt(Archivos.archivosPrediccionesFutbol);
					} else if (tipoAp == 1) {
						PrediccionFutbol predictFut = new PrediccionFutbol(montoAp,eqGanador,est1eq1,est1eq2,0,0,0,0);
						exito = predictFut.registrarPrediccionesFutbolTxt(Archivos.archivosPrediccionesFutbol);
					} else if (tipoAp == 2) {
						PrediccionFutbol predictFut = new PrediccionFutbol(montoAp,eqGanador,est1eq1,est1eq2,est2eq1,est2eq2,est3eq1,est3eq2);
						exito = predictFut.registrarPrediccionesFutbolTxt(Archivos.archivosPrediccionesFutbol);
					}
				}else if (comboBoxDeporte.getSelectedIndex()==2) {
					if (tipoAp == 0) {
						PrediccionBasketball predictBas = new PrediccionBasketball(montoAp,eqGanador,0,0,0,0,0,0);
						exito = predictBas.registrarPrediccionesBasketballTxt(Archivos.archivosPrediccionesBasketball);
					} else if (tipoAp == 1) {
						PrediccionBasketball predictBas = new PrediccionBasketball(montoAp,eqGanador,est1eq1,est1eq2,0,0,0,0);
						exito = predictBas.registrarPrediccionesBasketballTxt(Archivos.archivosPrediccionesBasketball);
					} else if (tipoAp == 2) {
						PrediccionBasketball predictBas = new PrediccionBasketball(montoAp,eqGanador,est1eq1,est1eq2,est2eq1,est2eq2,est3eq1,est3eq2);
						exito = predictBas.registrarPrediccionesBasketballTxt(Archivos.archivosPrediccionesBasketball);
				    }
				}
				

				if (exito) {
					JOptionPane.showMessageDialog(btnApuesta, "Predicción realizada correctamente.");
					dispose();
				} else {
					JOptionPane.showMessageDialog(btnApuesta, "Error al realizar Predicción.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
	}
	private void actualizarComboBoxGanador(JComboBox<String> combo, String equipo1, String equipo2) {
		combo.removeAllItems();
		combo.addItem("Seleccionar Ganador"); 
		if (!equipo1.isEmpty()) {
			combo.addItem(equipo1);
		}
		if (!equipo2.isEmpty()) {
			combo.addItem(equipo2);
		}
		combo.revalidate(); 
		combo.repaint();
	}
}
