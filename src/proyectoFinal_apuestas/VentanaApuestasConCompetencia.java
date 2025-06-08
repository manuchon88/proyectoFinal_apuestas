package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaApuestasConCompetencia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEquipo2;
	private JComboBox<String> comboBoxTipoApuesta;
	private JTextField textFieldMontoApuesta;
	private JComboBox comboBoxPartidos, comboBoxGanador;
	private JLabel lblCuotaApuesta;
	private int indexPartido;
	private ArrayList<ApuestaFutbol> listApuestasFut;
	private int tipo;
	private ApuestaFutbol apuest;
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
	private JTextField textFieldEquipo1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaApuestasConCompetencia(String competencia, Apostador user) {
		ArrayList<EventoFutbol> listPartidosFut = partidosFut(competencia);
		ArrayList<EventoBasketball> listPartidosBask = partidosBask(competencia);
		//ArrayList<ApuestaBasketball> listApuestasBask = apuestasBask(listPartidosBask.get(indexPartido).toString());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Apuesas Usuario");
		setBounds(100, 100, 500, 664);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(235, 245, 251));
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
		
		
		
		
		JPanel panCentro = new JPanel(new GridLayout(15, 2, 10, 10));
		panCentro.setBackground(new Color(235, 245, 251));
		contentPane.add(panCentro, BorderLayout.CENTER);

		JLabel lblDeporte = new JLabel("Deporte:");
		panCentro.add(lblDeporte);
		
		JLabel lblTipoDeporte = new JLabel(determinarDeporte(competencia));
		panCentro.add(lblTipoDeporte);
		

		JLabel lblTorneo = new JLabel("Torneo:");
		panCentro.add(lblTorneo);
		
		JLabel lblCompetencia = new JLabel(competencia);
		panCentro.add(lblCompetencia);

		JLabel lblPartidos = new JLabel("Partidos:");
		panCentro.add(lblPartidos);
		
		comboBoxPartidos = new JComboBox();
		panCentro.add(comboBoxPartidos);
		
		if (determinarDeporte(competencia).equals("Fútbol")) {
			String[] values = new String[listPartidosFut.size()+1];
			values[0]="Seleccionar partido";
			for (int i = 1; i < values.length; i++) {
				values[i] = listPartidosFut.get(i-1).toString();
			}
			comboBoxPartidos.setModel(new DefaultComboBoxModel<String>(values));
		}else {
			String[] values = new String[listPartidosBask.size()];
			for (int i = 0; i < values.length; i++) {
				values[i] = listPartidosBask.get(i).toString();
			}
			comboBoxPartidos.setModel(new DefaultComboBoxModel<String>(values));
		}
		
		comboBoxPartidos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				indexPartido = comboBoxPartidos.getSelectedIndex()-1;
				if (indexPartido==-1) {
					textFieldEquipo1.setText("");
					textFieldEquipo2.setText("");
					textFieldEstadistica1Equipo1.setText("");
					textFieldEstadistica1Equipo2.setText("");
					textFieldEstadistica2Equipo1.setText("");
					textFieldEstadistica2Equipo2.setText("");
					textFieldEstadistica3Equipo1.setText("");
					textFieldEstadistica3Equipo2.setText("");
					textFieldMontoApuesta.setText("");
					lblCuotaApuesta.setText("");
					comboBoxTipoApuesta.setModel(new DefaultComboBoxModel<String>(new String[] {}));
				} else {
					if (determinarDeporte(competencia).equals("Fútbol")) {
						listApuestasFut = apuestasFut(listPartidosFut.get(indexPartido));
						textFieldEquipo1.setText(listPartidosFut.get(indexPartido).getEquipo1());
						textFieldEquipo2.setText(listPartidosFut.get(indexPartido).getEquipo2());
						textFieldEquipo1.setEditable(false);
						textFieldEquipo2.setEditable(false);
						String[] values = new String[listApuestasFut.size()];
						for (int i = 0; i < values.length; i++) {
							values[i] = listApuestasFut.get(i).getDescripcion();
						}
						comboBoxTipoApuesta.setModel(new DefaultComboBoxModel<String>(values));
						comboBoxGanador.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Ganador", textFieldEquipo1.getText(), textFieldEquipo2.getText(), "Empate"}));

					}else if (determinarDeporte(competencia).equals("Basketball")) {
						textFieldEquipo1.setText(listPartidosBask.get(indexPartido).getEquipo1());
						textFieldEquipo2.setText(listPartidosBask.get(indexPartido).getEquipo2());
						textFieldEquipo1.setEditable(false);
						textFieldEquipo2.setEditable(false);
					}

				}
			}
		});
		
		JLabel lblEquipo1 = new JLabel("Equipo 1:");
		panCentro.add(lblEquipo1);
		
		textFieldEquipo1 = new JTextField();
		panCentro.add(textFieldEquipo1);
		
				JLabel lblEquipo2 = new JLabel("Equipo 2:");
				panCentro.add(lblEquipo2);
		textFieldEquipo2 = new JTextField();
		panCentro.add(textFieldEquipo2);
		textFieldEquipo2.addActionListener(e -> {
		});
			

		JLabel lblTipoApuesta = new JLabel("Tipo de Apuesta:");
		panCentro.add(lblTipoApuesta);
		comboBoxTipoApuesta = new JComboBox<>();
		panCentro.add(comboBoxTipoApuesta);
		comboBoxTipoApuesta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				apuest = listApuestasFut.get(comboBoxTipoApuesta.getSelectedIndex());
				if (apuest.getTipoApuesta() ==1) {
					tipo = 1;
					textFieldEstadistica1Equipo1.setEditable(false);
					textFieldEstadistica2Equipo1.setEditable(false);
					textFieldEstadistica3Equipo1.setEditable(false);
					textFieldEstadistica1Equipo2.setEditable(false);
					textFieldEstadistica2Equipo2.setEditable(false);
					textFieldEstadistica3Equipo2.setEditable(false);
					lblCuotaApuesta.setText(""+apuest.getCuota());
				}else if (apuest.getTipoApuesta()==2) {
					tipo=2;
					textFieldEstadistica1Equipo1.setEditable(true);
					textFieldEstadistica2Equipo1.setEditable(false);
					textFieldEstadistica3Equipo1.setEditable(false);
					textFieldEstadistica1Equipo2.setEditable(true);
					textFieldEstadistica2Equipo2.setEditable(false);
					textFieldEstadistica3Equipo2.setEditable(false);
					lblCuotaApuesta.setText(""+apuest.getCuota());

				}else if (apuest.getTipoApuesta()==3) {
					tipo=3;
					textFieldEstadistica1Equipo1.setEditable(true);
					textFieldEstadistica2Equipo1.setEditable(true);
					textFieldEstadistica3Equipo1.setEditable(true);
					textFieldEstadistica1Equipo2.setEditable(true);
					textFieldEstadistica2Equipo2.setEditable(true);
					textFieldEstadistica3Equipo2.setEditable(true);
					lblCuotaApuesta.setText(""+apuest.getCuota());

				}
			}
		});

		

		JLabel lblCuota = new JLabel("Cuota:");
		panCentro.add(lblCuota);
		
		
		lblCuotaApuesta = new JLabel("");
		panCentro.add(lblCuotaApuesta);

		/*comboBoxTipoApuesta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (ApuestaFutbol aF : listApuestasFut) {
					if (aF.getTipoApuesta()==comboBoxTipoApuesta.getSelectedIndex()+1) {
						lblCuotaApuesta.setText(""+aF.getCuota());			
					}
				}
			}
		});*/
		
		JLabel lblMonto = new JLabel("Monto Apostado:");
		panCentro.add(lblMonto);
		
		textFieldMontoApuesta = new JTextField();
		panCentro.add(textFieldMontoApuesta);
		
		JLabel lblElegirEquipoGanador = new JLabel("Equipo Ganador:");
		panCentro.add(lblElegirEquipoGanador);
		
		comboBoxGanador = new JComboBox();
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
		
		if (determinarDeporte(competencia).equals("Fútbol")) {
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
		}else if (determinarDeporte(competencia).equals("Basketball")) {
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
		}
		
		
		
		JPanel panBotones = new JPanel();
		panBotones.setBackground(new Color(235, 245, 251));
		JButton btnApuesta = new JButton("Apuesta");
		btnApuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (determinarDeporte(competencia).equals("Fútbol")) {
					if (tipo ==3) {
						PrediccionFutbol predict = new PrediccionFutbol(Double.parseDouble(textFieldMontoApuesta.getText()), comboBoxGanador.getSelectedIndex()-1, Integer.parseInt(textFieldEstadistica1Equipo1.getText()), Integer.parseInt(textFieldEstadistica1Equipo2.getText()), Integer.parseInt(textFieldEstadistica2Equipo1.getText()), Integer.parseInt(textFieldEstadistica2Equipo2.getText()), Integer.parseInt(textFieldEstadistica3Equipo1.getText()), Integer.parseInt(textFieldEstadistica3Equipo2.getText()));
						apuest.setPredict(predict);
					}else if (tipo==2) {
						PrediccionFutbol predict = new PrediccionFutbol(Double.parseDouble(textFieldMontoApuesta.getText()), comboBoxGanador.getSelectedIndex()-1, Integer.parseInt(textFieldEstadistica1Equipo1.getText()), Integer.parseInt(textFieldEstadistica1Equipo2.getText()));
						apuest.setPredict(predict);
						
					}else if (tipo==1) {
						PrediccionFutbol predict = new PrediccionFutbol(Double.parseDouble(textFieldMontoApuesta.getText()), comboBoxGanador.getSelectedIndex()-1);
						apuest.setPredict(predict);
						
					}
					if (user.apostar(1, apuest.toString())) {						
						JOptionPane.showMessageDialog(btnApuesta, "Apuesta realizada");
						comboBoxPartidos.setSelectedIndex(0);
						comboBoxGanador.setSelectedIndex(0);
						comboBoxTipoApuesta.setSelectedIndex(0);
					} else {
						JOptionPane.showMessageDialog(btnApuesta, "No se pudo apostar");

					}
				}
			}
		});
		panBotones.add(btnApuesta);
		contentPane.add(panBotones, BorderLayout.SOUTH);

		/*btnApuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eq1 = textFieldEquipo1.getText().trim();
				String eq2 = textFieldEquipo2.getText().trim();

				if (eq1.isEmpty() || eq2.isEmpty()) {
					JOptionPane.showMessageDialog(btnApuesta, "Debe ingresar nombres de ambos equipos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
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
				if (determinarDeporte(competencia).equals("Fútbol")) {
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
				}else if (determinarDeporte(competencia).equals("Basketball")) {
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
		});*/
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
	
	 public String determinarDeporte(String nombreCompetencia) {
		ArrayList<String> competenciasFutbol = new ArrayList<>();
        competenciasFutbol.add("Champions league");
        competenciasFutbol.add("Copa Libertadores");
        competenciasFutbol.add("Premier league Boliviana");
        competenciasFutbol.add("La Liga");
        competenciasFutbol.add("Serie A");
        competenciasFutbol.add("Premier League");

        ArrayList<String> competenciasBasketball = new ArrayList<>();
        competenciasBasketball.add("NBA");
        competenciasBasketball.add("Euro League");
        competenciasBasketball.add("ACB");
        competenciasBasketball.add("FIBA");
        competenciasBasketball.add("NCAA");

        for (String competenciaFutbol : competenciasFutbol) {
            if (nombreCompetencia.equals(competenciaFutbol)) {
                return "Fútbol";
            }
        }

        for (String competenciaBasketball : competenciasBasketball) {
            if (nombreCompetencia.equals(competenciaBasketball)) {
                return "Basketball";
            }
        }

        return "Ninguno";
    }
	 
	 
	 public ArrayList<EventoFutbol> partidosFut(String competencia){
		 String[] torneos = {"Champions league", "Libertadores", "Premier league Boliviana", "La Liga", "Serie A", "Premier League", "Amistoso"};
		 ArrayList<EventoFutbol> partidos = new ArrayList<EventoFutbol>();
		 ArrayList<EventoFutbol> eventsFut = EventoFutbol.leerEventosFutbolTxt(Archivos.archivosEventosFutbol);
		 for (int i = 0; i < torneos.length; i++) {
			 if (torneos[i].equals(competencia)) {
				 for (EventoFutbol ef : eventsFut) {
					 if (ef.getTorneo() == i) {
						 partidos.add(ef);
					 }
				 }
			 }
		 }

		return partidos;
	 }
	 
	 public ArrayList<EventoBasketball> partidosBask(String competencia) {
		String[] torneos = {"NBA", "Euro League", "ACB", "FIBA", "NCAA", "Amistoso"};
		ArrayList<EventoBasketball> partidos = new ArrayList<EventoBasketball>();
		if (determinarDeporte(competencia).equals("Basketball")) {
			ArrayList<EventoBasketball> eventsBask = EventoBasketball.leerEventosBasketballTxt(Archivos.archivosEventosBasketball);
			for (int i = 0; i < torneos.length; i++) {
				if (torneos[i].equals(competencia)) {
					for (EventoBasketball eb : eventsBask) {
						if (eb.getTorneo()==i) {
							partidos.add(eb);
						}
					}
				}
			}
		}
		return partidos;
	}
	 
	public ArrayList<ApuestaFutbol> apuestasFut(EventoFutbol event) {
		ArrayList<ApuestaFutbol> apuestasRegistradas = ApuestaFutbol.leerApuestaFutbolTxt(Archivos.archivosApuestasFutbol);
		ArrayList<ApuestaFutbol> apuestasEvento = new ArrayList<ApuestaFutbol>();
		for (ApuestaFutbol apuesta : apuestasRegistradas) {
			if (apuesta.getEvent().toString().equals(event.toString())) {
				apuestasEvento.add(apuesta);
			}
		}
		return apuestasEvento;
	}
	
	public ArrayList<ApuestaBasketball> apuestasBask(String event) {
		ArrayList<ApuestaBasketball> apuestasRegistradas = ApuestaBasketball.leerApuestaBasketballTxt(Archivos.archivosApuestasBasketball);
		ArrayList<ApuestaBasketball> apuestasEvento = new ArrayList<ApuestaBasketball>();
		for (ApuestaBasketball apuesta : apuestasRegistradas) {
			if (apuesta.getEvent().toString().equals(event)) {
				apuestasEvento.add(apuesta);
			}
		}
		return apuestasEvento;
	}
}
