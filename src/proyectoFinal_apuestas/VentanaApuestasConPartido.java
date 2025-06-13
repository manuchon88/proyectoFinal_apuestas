package proyectoFinal_apuestas;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaApuestasConPartido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxTipoApuesta , comboBoxGanador;
	private JTextField textFieldMontoApuesta;
	private JLabel lblCuotaApuesta;
	private int tipo;
	private ApuestaBasketball apuestBask;
	private ApuestaFutbol apuest;
	private ArrayList<ApuestaFutbol> listApuestasFut;
	private ArrayList<ApuestaBasketball> listApuestasBask;
	
	
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
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaApuestasConPartido(String equipo1, String equipo2, String competencia, Apostador user) {
	    listApuestasFut = new ArrayList<>();
	    listApuestasBask = new ArrayList<>();
	    comboBoxGanador = new JComboBox<>();
		
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
		
		
		
		
		
		JPanel panCentro = new JPanel(new GridLayout(14, 2, 10, 10));
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

		JLabel lblEquipo1 = new JLabel("Equipo 1:");
		panCentro.add(lblEquipo1);

		JLabel lblNombreEquipo1 = new JLabel(equipo1);
		panCentro.add(lblNombreEquipo1);

		JLabel lblEquipo2 = new JLabel("Equipo 2:");
		panCentro.add(lblEquipo2);


		JLabel lblNombreEquipo2 = new JLabel(equipo2);
		panCentro.add(lblNombreEquipo2);
			
		
		JLabel lblTipoApuesta = new JLabel("Tipo de Apuesta:");
		panCentro.add(lblTipoApuesta);
	    comboBoxTipoApuesta = new JComboBox<>();
		panCentro.add(comboBoxTipoApuesta);
		
		if (determinarDeporte(competencia).equals("Fútbol")) {
		    listApuestasFut = apuestasFut(equipo1, equipo2);
		    String[] values = new String[listApuestasFut.size()];
		    for (int i = 0; i < values.length; i++) {
		        values[i] = listApuestasFut.get(i).getDescripcion();
		    }
		    comboBoxTipoApuesta.setModel(new DefaultComboBoxModel<String>(values));
		    comboBoxGanador.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Ganador", "Empate", equipo1, equipo2}));

		} else if (determinarDeporte(competencia).equals("Basketball")) {
		    listApuestasBask = apuestasBask(equipo1, equipo2);
		    String[] values = new String[listApuestasBask.size()];
		    for (int i = 0; i < values.length; i++) {
		        values[i] = listApuestasBask.get(i).getDescripcion();
		    }
		    comboBoxTipoApuesta.setModel(new DefaultComboBoxModel<String>(values));
		    comboBoxGanador.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Ganador", "Empate", equipo1, equipo2}));
		}
		
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
		comboBoxGanador.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Ganador", equipo1, equipo2}));	
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
						comboBoxGanador.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccionar Ganador"}));
						comboBoxTipoApuesta.setModel(new DefaultComboBoxModel<>(new String[]{}));
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
						comboBoxGanador.setModel(new DefaultComboBoxModel<>(new String[]{"Seleccionar Ganador"}));
						comboBoxTipoApuesta.setModel(new DefaultComboBoxModel<>(new String[]{}));
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
	
	 
	public ArrayList<ApuestaFutbol> apuestasFut(String equipo1, String equipo2) {
	    ArrayList<ApuestaFutbol> apuestasRegistradas = ApuestaFutbol.leerApuestaFutbolTxt(Archivos.archivosApuestasFutbol);
	    ArrayList<ApuestaFutbol> apuestasEvento = new ArrayList<ApuestaFutbol>();
	    for (ApuestaFutbol apuesta : apuestasRegistradas) {
	        if (apuesta.getEvent().getEquipo1().equals(equipo1) && 
	            apuesta.getEvent().getEquipo2().equals(equipo2)) {
	            apuestasEvento.add(apuesta);
	        }
	    }
	    return apuestasEvento;
	}
		
	public ArrayList<ApuestaBasketball> apuestasBask(String equipo1, String equipo2) {
	    ArrayList<ApuestaBasketball> apuestasRegistradas = ApuestaBasketball.leerApuestaBasketballTxt(Archivos.archivosApuestasBasketball);
	    ArrayList<ApuestaBasketball> apuestasEvento = new ArrayList<ApuestaBasketball>();
	    for (ApuestaBasketball apuesta : apuestasRegistradas) {
	        if (apuesta.getEvent().getEquipo1().equals(equipo1) && 
	            apuesta.getEvent().getEquipo2().equals(equipo2)) {
	            apuestasEvento.add(apuesta);
	        }
	    }
	    return apuestasEvento;
	}
	

}