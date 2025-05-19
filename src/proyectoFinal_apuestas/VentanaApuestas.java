package proyectoFinal_apuestas;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaApuestas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTipoApuesta;
	private JTextField textFieldEquipo1;
	private JTextField textFieldEquipo2;
	private JTextField textFieldMonto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaApuestas frame = new VentanaApuestas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaApuestas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JPanel panTitulo = new JPanel();
		panTitulo.setBackground(new Color(45, 104, 184));
		panPrincipal.add(panTitulo, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("UCBet");
		lblTitulo.setForeground(new Color(255, 232, 0));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
		panTitulo.add(lblTitulo);
		
		JPanel panCentro = new JPanel();
		panPrincipal.add(panCentro, BorderLayout.CENTER);
		panCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panSubtitulo = new JPanel();
		panCentro.add(panSubtitulo, BorderLayout.NORTH);
		
		JLabel lblSubtitulo = new JLabel("APUESTAS");
		lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panSubtitulo.add(lblSubtitulo);
		
		JPanel panBotones = new JPanel();
		panCentro.add(panBotones, BorderLayout.SOUTH);
		
		JButton btnRealizarApuesta = new JButton("Realizar Apuesta");
		panBotones.add(btnRealizarApuesta);
		
		JPanel panCentroCentro = new JPanel();
		panCentro.add(panCentroCentro, BorderLayout.CENTER);
		panCentroCentro.setLayout(null);
		
		JLabel lblTipoEvento = new JLabel("Tipo de Evento:");
		lblTipoEvento.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTipoEvento.setBounds(118, 65, 200, 32);
		panCentroCentro.add(lblTipoEvento);
		
		textFieldTipoApuesta = new JTextField();
		textFieldTipoApuesta.setBounds(368, 68, 244, 32);
		panCentroCentro.add(textFieldTipoApuesta);
		textFieldTipoApuesta.setColumns(10);
		
		JLabel lblEquipo_1 = new JLabel("Equipo 1: ");
		lblEquipo_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEquipo_1.setBounds(146, 129, 75, 32);
		panCentroCentro.add(lblEquipo_1);
		
		textFieldEquipo1 = new JTextField();
		textFieldEquipo1.setColumns(10);
		textFieldEquipo1.setBounds(68, 172, 244, 32);
		panCentroCentro.add(textFieldEquipo1);
		
		JLabel lblEquipo = new JLabel("Equipo 2: ");
		lblEquipo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEquipo.setBounds(455, 129, 75, 32);
		panCentroCentro.add(lblEquipo);
		
		textFieldEquipo2 = new JTextField();
		textFieldEquipo2.setColumns(10);
		textFieldEquipo2.setBounds(368, 172, 244, 32);
		panCentroCentro.add(textFieldEquipo2);
		
		JComboBox comboBoxCompeticion = new JComboBox();

		String[] listaFutbol = {"Champions", "Mundial de Clubes", "La Liga", "Serie A", "Premier League"};
		String[] listaBasket = {"NBA", "Europa", "FIBA", "A", "O"};

		if (textFieldTipoApuesta.getText().trim().equalsIgnoreCase("Futbol")) {
		    comboBoxCompeticion.setModel(new DefaultComboBoxModel(listaFutbol));
		} else if (textFieldTipoApuesta.getText().trim().equalsIgnoreCase("Basketball")) {
		    comboBoxCompeticion.setModel(new DefaultComboBoxModel(listaBasket));
		} else {
		    String[] listaMixta = new String[listaFutbol.length + listaBasket.length];
		    System.arraycopy(listaFutbol, 0, listaMixta, 0, listaFutbol.length);
		    System.arraycopy(listaBasket, 0, listaMixta, listaFutbol.length, listaBasket.length);
		    comboBoxCompeticion.setModel(new DefaultComboBoxModel(listaMixta));
		}

		
		comboBoxCompeticion.setBounds(368, 312, 244, 32);
		panCentroCentro.add(comboBoxCompeticion);
		
		JLabel lblCopetición = new JLabel("Tipo de Apuesta: ");
		lblCopetición.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCopetición.setBounds(118, 245, 200, 32);
		panCentroCentro.add(lblCopetición);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setColumns(10);
		textFieldMonto.setBounds(368, 376, 244, 32);
		panCentroCentro.add(textFieldMonto);
		
		JLabel lblCopeticion = new JLabel("Competición: ");
		lblCopeticion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCopeticion.setBounds(118, 312, 200, 32);
		panCentroCentro.add(lblCopeticion);
		
		JLabel lblMonto = new JLabel("Monto de Apuesta:");
		lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMonto.setBounds(118, 376, 200, 32);
		panCentroCentro.add(lblMonto);
		
		JComboBox comboBoxTipoApuesta = new JComboBox();
		comboBoxTipoApuesta.setBounds(368, 245, 244, 32);
		panCentroCentro.add(comboBoxTipoApuesta);
	}
}
