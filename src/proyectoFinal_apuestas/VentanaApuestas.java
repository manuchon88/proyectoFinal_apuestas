package proyectoFinal_apuestas;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private JComboBox<String> comboBoxTorneo, comboBoxPartidos, comboBoxGanador;
	private JTextField textFieldMontoApuesta;
	private int indexPartido;
	private JLabel lblCuotaApuesta;
	private int tipo;
	private ApuestaBasketball apuestBask;
	private ApuestaFutbol apuest;
	private ArrayList<ApuestaFutbol> listApuestasFut;
	private ArrayList<ApuestaBasketball> listApuestasBask;
	private String competencia;
	
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
	    listApuestasFut = new ArrayList<>();
	    listApuestasBask = new ArrayList<>();
		
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Apuesas Usuario");
		setBounds(100, 100, 500, 664);
	    setLocation(0, 0);
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
		comboBoxDeporte = new JComboBox<>(new String[] {"Seleccionar deporte", "Fútbol", "Basketball"});
		panCentro.add(comboBoxDeporte);
		

		JLabel lblTorneo = new JLabel("Torneo:");
		panCentro.add(lblTorneo);
		
		comboBoxTorneo = new JComboBox<String>();
		panCentro.add(comboBoxTorneo);

		Object itemSeleccionado = comboBoxTorneo.getSelectedItem();
		String valor = ""; 

		if (itemSeleccionado != null) {
			valor = itemSeleccionado.toString();
		}
		
		competencia = valor; 
		
		ArrayList<EventoFutbol> listPartidosFut = partidosFut(competencia);
		ArrayList<EventoBasketball> listPartidosBask = partidosBask(competencia);

		comboBoxTorneo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (comboBoxTorneo.getSelectedIndex() > 0) {
		            competencia = comboBoxTorneo.getSelectedItem().toString();
		        }
		        actualizarPartidosSegunTorneo();
		    }
		});
		
		JLabel lblPartidos = new JLabel("Partidos:");
		panCentro.add(lblPartidos);
		
		comboBoxPartidos = new JComboBox();
		panCentro.add(comboBoxPartidos);
		
		if (determinarDeporte(comboBoxDeporte.getSelectedItem().toString()).equals("Fútbol")) {
			String[] values = new String[listPartidosFut.size()+1];
			values[0]="Seleccionar partido";
			for (int i = 1; i < values.length; i++) {
				values[i] = listPartidosFut.get(i-1).toString();
			}
			comboBoxPartidos.setModel(new DefaultComboBoxModel<String>(values));
		}else {
			String[] values = new String[listPartidosBask.size()+1];
			values[0]="Seleccionar partido";
			for (int i = 1; i < values.length; i++) {
				values[i] = listPartidosBask.get(i-1).toString();
			}
			comboBoxPartidos.setModel(new DefaultComboBoxModel<String>(values));
		}
		
		textFieldEquipo1 = new JTextField();
		textFieldEquipo2 = new JTextField();
		
		comboBoxPartidos.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (comboBoxTorneo.getSelectedIndex() > 0) {
		            String competencia = comboBoxTorneo.getSelectedItem().toString();
		            ArrayList<EventoFutbol> listPartidosFut = partidosFut(competencia);
		            ArrayList<EventoBasketball> listPartidosBask = partidosBask(competencia);
		            
		            indexPartido = comboBoxPartidos.getSelectedIndex()-1;
		            if (indexPartido<0) {
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
							comboBoxGanador.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Ganador", "Empate", textFieldEquipo1.getText(), textFieldEquipo2.getText()}));
	
						}else if (determinarDeporte(competencia).equals("Basketball")) {
							listApuestasBask = apuestasBask(listPartidosBask.get(indexPartido));
							textFieldEquipo1.setText(listPartidosBask.get(indexPartido).getEquipo1());
							textFieldEquipo2.setText(listPartidosBask.get(indexPartido).getEquipo2());
							textFieldEquipo1.setEditable(false);
							textFieldEquipo2.setEditable(false);
							String[] values = new String[listApuestasBask.size()];
							for (int i = 0; i < values.length; i++) {
								values[i] = listApuestasBask.get(i).getDescripcion();
							}
							comboBoxTipoApuesta.setModel(new DefaultComboBoxModel<String>(values));
							comboBoxGanador.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Ganador", "Empate", textFieldEquipo1.getText(), textFieldEquipo2.getText()}));
						}
	
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
				 if (comboBoxTipoApuesta.getSelectedIndex() >= 0) {
				// TODO Auto-generated method stub
				if (determinarDeporte(competencia).equals("Fútbol")) {
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
				} else {
					apuestBask = listApuestasBask.get(comboBoxTipoApuesta.getSelectedIndex());
					if (apuestBask.getTipo() ==1) {
						tipo = 1;
						textFieldEstadistica1Equipo1.setEditable(false);
						textFieldEstadistica2Equipo1.setEditable(false);
						textFieldEstadistica3Equipo1.setEditable(false);
						textFieldEstadistica1Equipo2.setEditable(false);
						textFieldEstadistica2Equipo2.setEditable(false);
						textFieldEstadistica3Equipo2.setEditable(false);
						lblCuotaApuesta.setText(""+apuestBask.getCuota());
					}else if (apuestBask.getTipo()==2) {
						tipo=2;
						textFieldEstadistica1Equipo1.setEditable(true);
						textFieldEstadistica2Equipo1.setEditable(false);
						textFieldEstadistica3Equipo1.setEditable(false);
						textFieldEstadistica1Equipo2.setEditable(true);
						textFieldEstadistica2Equipo2.setEditable(false);
						textFieldEstadistica3Equipo2.setEditable(false);
						lblCuotaApuesta.setText(""+apuestBask.getCuota());
					}else if (apuestBask.getTipo()==3) {
						tipo=3;
						textFieldEstadistica1Equipo1.setEditable(true);
						textFieldEstadistica2Equipo1.setEditable(true);
						textFieldEstadistica3Equipo1.setEditable(true);
						textFieldEstadistica1Equipo2.setEditable(true);
						textFieldEstadistica2Equipo2.setEditable(true);
						textFieldEstadistica3Equipo2.setEditable(true);
						lblCuotaApuesta.setText(""+apuestBask.getCuota());
					}
				}
			}else {
                JOptionPane.showMessageDialog(null, "No hay apuestas disponibles para este partido.", "Error", JOptionPane.WARNING_MESSAGE);
            	}
			}
		});
		

		JLabel lblCuota = new JLabel("Cuota:");
		panCentro.add(lblCuota);
		
		
		lblCuotaApuesta = new JLabel("");
		panCentro.add(lblCuotaApuesta);

		
		JLabel lblMonto = new JLabel("Monto Apostado:");
		panCentro.add(lblMonto);
		
		textFieldMontoApuesta = new JTextField();
		panCentro.add(textFieldMontoApuesta);
		
		JLabel lblElegirEquipoGanador = new JLabel("Equipo Ganador:");
		panCentro.add(lblElegirEquipoGanador);
		
		comboBoxGanador = new JComboBox();
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
		panBotones.setBackground(new Color(235, 245, 251));


		JButton btnApuesta = new JButton("APOSTAR");
		btnApuesta.setFont(new Font("Tahoma", Font.BOLD, 13));
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
						comboBoxTorneo.setModel(new DefaultComboBoxModel<>(new String[]{}));
						comboBoxPartidos.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccionar partido"}));
						comboBoxGanador.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccionar Ganador"}));
						comboBoxTipoApuesta.setModel(new DefaultComboBoxModel<>(new String[]{}));
						textFieldEquipo1.setText("");
			            textFieldEquipo2.setText("");
			            textFieldMontoApuesta.setText("");
			            lblCuotaApuesta.setText("");
			            textFieldEstadistica1Equipo1.setText("");
			            textFieldEstadistica1Equipo2.setText("");
						textFieldEstadistica2Equipo1.setText("");
						textFieldEstadistica2Equipo2.setText("");
			            textFieldEstadistica3Equipo1.setText("");
			            textFieldEstadistica3Equipo2.setText("");
			            
					} else {
						JOptionPane.showMessageDialog(btnApuesta, "No se pudo apostar");

					}
				}else {
					if (tipo ==3) {
						PrediccionBasketball predict = new PrediccionBasketball(Double.parseDouble(textFieldMontoApuesta.getText()), comboBoxGanador.getSelectedIndex()-1, Integer.parseInt(textFieldEstadistica1Equipo1.getText()), Integer.parseInt(textFieldEstadistica1Equipo2.getText()), Integer.parseInt(textFieldEstadistica2Equipo1.getText()), Integer.parseInt(textFieldEstadistica2Equipo2.getText()), Integer.parseInt(textFieldEstadistica3Equipo1.getText()), Integer.parseInt(textFieldEstadistica3Equipo2.getText()));
						apuestBask.setPredict(predict);
					}else if (tipo==2) {
						PrediccionBasketball predict = new PrediccionBasketball(Double.parseDouble(textFieldMontoApuesta.getText()), comboBoxGanador.getSelectedIndex()-1, Integer.parseInt(textFieldEstadistica1Equipo1.getText()), Integer.parseInt(textFieldEstadistica1Equipo2.getText()));
						apuestBask.setPredict(predict);
						
					}else if (tipo==1) {
						PrediccionBasketball predict = new PrediccionBasketball(Double.parseDouble(textFieldMontoApuesta.getText()), comboBoxGanador.getSelectedIndex()-1);
						apuestBask.setPredict(predict);
						
					}
					if (user.apostar(2, apuestBask.toString())) {						
						JOptionPane.showMessageDialog(btnApuesta, "Apuesta realizada");
						comboBoxTorneo.setModel(new DefaultComboBoxModel<>(new String[]{}));
						comboBoxPartidos.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccionar partido"}));
						comboBoxGanador.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccionar Ganador"}));
						comboBoxTipoApuesta.setModel(new DefaultComboBoxModel<>(new String[]{}));
						textFieldEquipo1.setText("");
			            textFieldEquipo2.setText("");
			            textFieldMontoApuesta.setText("");
			            lblCuotaApuesta.setText("");
			            textFieldEstadistica1Equipo1.setText("");
			            textFieldEstadistica1Equipo2.setText("");
						textFieldEstadistica2Equipo1.setText("");
						textFieldEstadistica2Equipo2.setText("");
			            textFieldEstadistica3Equipo1.setText("");
			            textFieldEstadistica3Equipo2.setText("");
					} else {
						JOptionPane.showMessageDialog(btnApuesta, "No se pudo apostar");
						
					}
					
				}
			}
		});
		panBotones.add(btnApuesta);
		contentPane.add(panBotones, BorderLayout.SOUTH);
		
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
					 if (ef.getTorneo() == i && !ef.isTerminado()) {
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
						if (eb.getTorneo()==i && !eb.isTerminado()) {
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
		
		public ArrayList<ApuestaBasketball> apuestasBask(EventoBasketball event) {
			ArrayList<ApuestaBasketball> apuestasRegistradas = ApuestaBasketball.leerApuestaBasketballTxt(Archivos.archivosApuestasBasketball);
			ArrayList<ApuestaBasketball> apuestasEvento = new ArrayList<ApuestaBasketball>();
			for (ApuestaBasketball apuesta : apuestasRegistradas) {
				if (apuesta.getEvent().toString().equals(event.toString())) {
					apuestasEvento.add(apuesta);
				}
			}
			return apuestasEvento;
		}
	
		public void actualizarPartidosSegunTorneo() {
		    if (comboBoxTorneo.getSelectedIndex() > 0) { 
		        String competencia = comboBoxTorneo.getSelectedItem().toString();
		        ArrayList<EventoFutbol> listPartidosFut = partidosFut(competencia);
		        ArrayList<EventoBasketball> listPartidosBask = partidosBask(competencia);
		        
		        comboBoxPartidos.removeAllItems();
		        comboBoxPartidos.addItem("Seleccionar partido");
		        
		        if (determinarDeporte(competencia).equals("Fútbol")) {
		            for (EventoFutbol partido : listPartidosFut) {
		                comboBoxPartidos.addItem(partido.toString());
		            }
		        } else if (determinarDeporte(competencia).equals("Basketball")) {
		            for (EventoBasketball partido : listPartidosBask) {
		                comboBoxPartidos.addItem(partido.toString());
		            }
		        }
		    } else {
		        comboBoxPartidos.removeAllItems();
		        comboBoxPartidos.addItem("Seleccionar partido");
		    }
		}
		
		
}
