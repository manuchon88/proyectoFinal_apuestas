package proyectoFinal_apuestas;

import java.awt.*;
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

import javax.swing.*;
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
		btnNBA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnNBA.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnNBA);
		
		JButton btnLibertadores = new JButton("Copa Libertadores");
		btnLibertadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnLibertadores.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnLibertadores);
		
		JButton btnEuroLeague = new JButton("Euro League");
		btnEuroLeague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnEuroLeague.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnEuroLeague);
		
		JButton btnLaLiga = new JButton("La Liga");
		btnLaLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnLaLiga.getText(), user);
				frame.setVisible(true);
			}
		});
		
		JButton btnLigaBoliviana = new JButton("Premier league Boliviana");
		btnLigaBoliviana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnLigaBoliviana.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnLigaBoliviana);
		
		JButton btnACB = new JButton("ACB");
		btnACB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnACB.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnACB);
		panBotones.add(btnLaLiga);
		
		JButton btnSerieA = new JButton("Serie A");
		btnSerieA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnSerieA.getText(), user);
				frame.setVisible(true);
			}
		});
		
		JButton btnFIBA = new JButton("FIBA");
		btnFIBA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnFIBA.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnFIBA);
		panBotones.add(btnSerieA);
		
		JButton btnNCAA = new JButton("NCAA");
		btnNCAA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnNCAA.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnNCAA);
		
		JButton btnPremier = new JButton("Premier League");
		btnPremier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaApuestasConCompetencia frame = new VentanaApuestasConCompetencia(btnPremier.getText(), user);
				frame.setVisible(true);
			}
		});
		panBotones.add(btnPremier);
		
		JButton btnOtros = new JButton("OTROS");
		btnOtros.addActionListener(new ActionListener() {
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
		panEventos.setLayout(new GridLayout(1, 2, 10, 10)); 
		//panEventos.setLayout(new GridLayout(5, 3, 10, 10)); 

		
		/*
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
		}*/
		
		JLabel lblFut = new JLabel("Futbol");
		lblFut.setHorizontalAlignment(SwingConstants.CENTER);
		lblFut.setFont(new Font("Tahoma", Font.BOLD, 13));
		JPanel panEvents1 = new JPanel(new BorderLayout());
		panEvents1.add(lblFut, BorderLayout.NORTH);

		JPanel panFut = new JPanel();
		JScrollPane ScrollFutbol = new JScrollPane(panFut);
		panFut.setLayout(new BoxLayout(panFut, BoxLayout.Y_AXIS));
		ScrollFutbol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollFutbol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panEvents1.add(ScrollFutbol);
		panEventos.add(panEvents1);
		
		
		for (int i = 0; i < eventosFutbol.size(); i++) {
			String[] torneos = {"Champions league", "Libertadores", "Premier league Boliviana", "La Liga", "Serie A", "Premier League", "Amistoso"};
			String part = torneos[eventosFutbol.get(i).getTorneo()];
			String text = eventosFutbol.get(i).getFecha()+" / "+part+" / "+eventosFutbol.get(i).getEquipo1()+" vs "+eventosFutbol.get(i).getEquipo2();
			JButton btnEv = new JButton(text);
			btnEv.setAlignmentX(Component.CENTER_ALIGNMENT);
			panFut.add(btnEv);
		}
		
		
		JLabel lblBask = new JLabel("Basketball");
		lblBask.setHorizontalAlignment(SwingConstants.CENTER);
		lblBask.setFont(new Font("Tahoma", Font.BOLD, 13));
		JPanel panEvents2 = new JPanel(new BorderLayout());
		panEvents2.add(lblBask, BorderLayout.NORTH);
		
		JPanel panBask = new JPanel();
		panBask.setLayout(new BoxLayout(panBask, BoxLayout.Y_AXIS));
		JScrollPane ScrollBasket = new JScrollPane(panBask);
		ScrollBasket.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollBasket.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panEvents2.add(ScrollBasket);
		panEventos.add(panEvents2);
		for (int i = 0; i < eventosBasket.size(); i++) {
			String[] torneos = {"NBA", "Euro League", "ACB", "FIBA", "NCAA", "Amistoso"};
			String part = torneos[eventosBasket.get(i).getTorneo()];
			String text = eventosBasket.get(i).getFecha()+" / "+part+" / "+eventosBasket.get(i).getEquipo1()+" vs "+eventosBasket.get(i).getEquipo2();
			JButton btnEv = new JButton(text);
			btnEv.setAlignmentX(Component.CENTER_ALIGNMENT);
			panBask.add(btnEv);
		}
		
		
		panCentroAbajo.add(panEventos, BorderLayout.CENTER);

		
		//Eventos futuros--------------------------
		
		
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
