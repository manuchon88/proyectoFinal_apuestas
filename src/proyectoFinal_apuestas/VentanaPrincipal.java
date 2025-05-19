package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(Apostador user) {
		ArrayList<EventoFutbol> eventosFutbol = EventoFutbol.leerEventosFutbolTxt(Archivos.archivosEventosFutbol);
		ArrayList<EventoBasketball> eventosBasket = EventoBasketball.leerEventosBasketballTxt(Archivos.archivosEventosBasketball);
		

		
		setTitle("UCBet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

        Rectangle bounds = env.getMaximumWindowBounds().getBounds();

        setBounds(bounds);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panPrincipal = new JPanel();
		contentPane.add(panPrincipal, BorderLayout.CENTER);
		panPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel header = new JPanel();
		header.setBackground(new Color(45, 104, 184));
		panPrincipal.add(header, BorderLayout.NORTH);
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
		
		JButton btnPerfil = new JButton();
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPerfil frame = new VentanaPerfil(user, VentanaPrincipal.this);
				frame.setVisible(true);
			}
		});
		btnPerfil.setText(user.getNombre());
		btnPerfil.setBackground(new Color(45, 104, 184));
		btnPerfil.setForeground(new Color(255, 255, 255));
		panHeadDer.add(btnPerfil);
		
		JPanel panIzquierda = new JPanel();
		panPrincipal.add(panIzquierda, BorderLayout.WEST);
		panIzquierda.setLayout(new BorderLayout(0, 0));
		
		JPanel panIzqArriba = new JPanel();
		panIzquierda.add(panIzqArriba, BorderLayout.NORTH);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Tahoma", Font.BOLD, 17));
		panIzqArriba.add(lblPerfil);
		
		JPanel panIzqAbajo = new JPanel();
		panIzquierda.add(panIzqAbajo, BorderLayout.SOUTH);
		panIzqAbajo.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton btnSaldo = new JButton("Agregar Saldo");
		panIzqAbajo.add(btnSaldo);
		
		JButton btnHistorial = new JButton("Ver Historial");
		panIzqAbajo.add(btnHistorial);
		
		JButton btnEditarPerfil = new JButton("Editar Perfil");
		panIzqAbajo.add(btnEditarPerfil);
		
		JPanel panIzqCentro = new JPanel();
		panIzquierda.add(panIzqCentro, BorderLayout.CENTER);
		panIzqCentro.setLayout(null);
		
		JLabel lblNombre = new JLabel("Usuario:");
		lblNombre.setBounds(27, 11, 64, 14);
		panIzqCentro.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 34, 81, 20);
		panIzqCentro.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblSaldo2 = new JLabel("Saldo:");
		lblSaldo2.setBounds(37, 69, 36, 14);
		panIzqCentro.add(lblSaldo2);
		
		textField = new JTextField();
		textField.setBounds(10, 94, 81, 20);
		panIzqCentro.add(textField);
		textField.setColumns(10);
		
		JPanel panCentro = new JPanel();
		panPrincipal.add(panCentro, BorderLayout.CENTER);
		panCentro.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panCentroArriba = new JPanel();
		panCentro.add(panCentroArriba);
		panCentroArriba.setLayout(new BorderLayout(20, 0));
		
		JPanel panCAA = new JPanel();
		panCentroArriba.add(panCAA, BorderLayout.NORTH);
		
		JLabel lblTipoApuesta = new JLabel("Tipos de Apuestas");
		lblTipoApuesta.setFont(new Font("Tahoma", Font.BOLD, 17));
		panCAA.add(lblTipoApuesta);
		
		JPanel panBotones = new JPanel();
		panCentroArriba.add(panBotones, BorderLayout.CENTER);
		panBotones.setLayout(new GridLayout(7, 2, 0, 0));
		
		JLabel lblApuestaFutbol = new JLabel("Fútbol");
		lblApuestaFutbol.setFont(new Font("Tahoma", Font.BOLD, 15));
		panBotones.add(lblApuestaFutbol);
		
		JLabel lblBasketball = new JLabel("Basketball");
		lblBasketball.setFont(new Font("Tahoma", Font.BOLD, 15));
		panBotones.add(lblBasketball);
		
		JButton btnChampions = new JButton("Champions");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnChampions);
		
		JButton btnNBA = new JButton("NBA");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnNBA);
		
		JButton btnMundialClubes = new JButton("Mundial de Clubes");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnMundialClubes);
		
		JButton btnEuroLeague = new JButton("Euro League");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnEuroLeague);
		
		JButton btnLaLiga = new JButton("La Liga");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnLaLiga);
		
		JButton btnACB = new JButton("ACB");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnACB);
		
		JButton btnSerieA = new JButton("Serie A");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnSerieA);
		
		JButton btnFIBA = new JButton("FIBA");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnFIBA);
		
		JButton btnBundesliga = new JButton("Bundesliga");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnBundesliga);
		
		JButton btnNCAA = new JButton("NCAA");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnNCAA);
		
		JButton btnPremier = new JButton("Premier League");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnChampions.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnPremier);
		
		JButton btnOtros = new JButton("OTROS");
		btnChampions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestas frame = new VentanaApuestas();
				frame.setVisible(true);
			}
		});
		panBotones.add(btnOtros);
		
		JPanel panCentroAbajo = new JPanel();
		panCentro.add(panCentroAbajo);
		panCentroAbajo.setLayout(new BorderLayout(0, 0));
		
		JPanel panCAbA = new JPanel();
		panCentroAbajo.add(panCAbA, BorderLayout.NORTH);
		
		JLabel lblFuturosEventos = new JLabel("Futuros Eventos");
		lblFuturosEventos.setFont(new Font("Tahoma", Font.BOLD, 17));
		panCAbA.add(lblFuturosEventos);
		
		JPanel panEventos = new JPanel();
		panEventos.setLayout(new GridLayout(5, 3, 10, 10)); 

		
		
		String eventosModelo[] = new String[eventosFutbol.size()];
		for(int i=0; i<eventosFutbol.size();i++) {
			eventosModelo[i] = eventosFutbol.get(i).toString();
		}
		
		for (EventoFutbol evento : eventosFutbol) {
		    JTextField textFieldTipoEvento = new JTextField("FÚTBOL");
		    textFieldTipoEvento.setEditable(false);

		    String equipos = evento.getEquipo1() + " vs " + evento.getEquipo2();
		    JTextField textFieldEquipos = new JTextField(equipos);
		    textFieldEquipos.setEditable(false);

		    JButton btnSeleccionar = new JButton("Seleccionar tipo de apuesta");

		    btnSeleccionar.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	VentanaApuestasConPartido frame = new VentanaApuestasConPartido(textFieldTipoEvento.getText(), textFieldEquipos.getText(), user);
    				frame.setVisible(true);
		        }
		    });

		    panEventos.add(textFieldTipoEvento);
		    panEventos.add(textFieldEquipos);
		    panEventos.add(btnSeleccionar);
		}

		panCentroAbajo.add(panEventos, BorderLayout.CENTER);

		
		JPanel panDerecha = new JPanel();
		panPrincipal.add(panDerecha, BorderLayout.EAST);
		panDerecha.setLayout(new BorderLayout(0, 0));
		
		JPanel panDerArriba = new JPanel();
		panDerecha.add(panDerArriba, BorderLayout.NORTH);
		
		JLabel lblApuestas = new JLabel("Apuestas");
		lblApuestas.setFont(new Font("Tahoma", Font.BOLD, 17));
		panDerArriba.add(lblApuestas);
		
		JPanel panDerAbajo = new JPanel();
		panDerecha.add(panDerAbajo, BorderLayout.SOUTH);
		panDerAbajo.setLayout(new GridLayout(2, 1, 0, 0));
		
		JButton btnApuesta = new JButton("Apuesta");
		btnApuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestas frame = new VentanaApuestas();
				frame.setVisible(true);
			}
		});
		panDerAbajo.add(btnApuesta);
	}
}
