package proyectoFinal_apuestas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaActualizarEventoFutbol extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombreLocal;
	private JTextField textFieldNombreVisitante;
	private JTextField textFieldGolesLocal;
	private JTextField textFieldGolesVisitante;
	private JTextField textFieldAmarillasLocal;
	private JTextField textFieldAmarillasVisitante;
	private JTextField textFieldRojasLocal;
	private JTextField textFieldRojasVisitante;
	private JTextField textFieldDia;
	private JTextField textFieldMes;
	private JTextField textFieldAnio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaActualizarEventoFutbol frame = new VentanaActualizarEventoFutbol();
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
	public VentanaActualizarEventoFutbol() {
		setTitle("Actualizar Evento UCBet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitulo = new JPanel();
		contentPane.add(panTitulo, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("UCBet");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		panTitulo.add(lblTitulo);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panSubtitulo = new JPanel();
		panel.add(panSubtitulo, BorderLayout.NORTH);
		
		JLabel lblSubtitulo = new JLabel("Actualizar Evento de Fútbol");
		lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panSubtitulo.add(lblSubtitulo);
		
		JPanel panBotones = new JPanel();
		panel.add(panBotones, BorderLayout.SOUTH);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int dia = Integer.parseInt(textFieldDia.getText());
					int mes = Integer.parseInt(textFieldMes.getText());
					int anio = Integer.parseInt(textFieldAnio.getText());
					String eq1 = textFieldNombreLocal.getText();
					String eq2 = textFieldNombreVisitante.getText();
					int goles1 = Integer.parseInt(textFieldGolesLocal.getText());
					int goles2 = Integer.parseInt(textFieldGolesVisitante.getText());
					int am1 = Integer.parseInt(textFieldAmarillasLocal.getText());
					int am2 = Integer.parseInt(textFieldAmarillasVisitante.getText());
					int roj1 = Integer.parseInt(textFieldRojasLocal.getText());
					int roj2 = Integer.parseInt(textFieldRojasVisitante.getText());

					EventoFutbol evento = new EventoFutbol(anio, mes, dia, eq1, eq2, goles1, goles2, am1, am2, roj1, roj2);
					if (evento.registrarEventosFutbolTxt(Archivos.archivosEventosFutbol)) {
						System.out.println("Evento registrado correctamente");
					} else {
						System.out.println("Error al registrar evento");
					}
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
			}
		});
		panBotones.add(btnActualizar);
		
		JButton btnCambiarEvento = new JButton("Cambiar Evento");
		btnCambiarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaActualizarEventoBasketball frame = new VentanaActualizarEventoBasketball();
				frame.setVisible(true);
			}
		});
		panBotones.add(btnCambiarEvento);
		
		JPanel panCentro = new JPanel();
		panel.add(panCentro, BorderLayout.CENTER);
		panCentro.setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setBounds(20, 35, 145, 19);
		panCentro.add(lblFecha);
		
		JLabel lblEquipoLocal = new JLabel("Equipo Local:");
		lblEquipoLocal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEquipoLocal.setBounds(150, 83, 109, 19);
		panCentro.add(lblEquipoLocal);
		
		textFieldNombreLocal = new JTextField();
		textFieldNombreLocal.setBounds(139, 127, 130, 20);
		panCentro.add(textFieldNombreLocal);
		textFieldNombreLocal.setColumns(10);
		
		JLabel lblGoles = new JLabel("Goles:");
		lblGoles.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGoles.setBounds(20, 169, 109, 19);
		panCentro.add(lblGoles);
		
		JLabel lblEquipoVisitante = new JLabel("Equipo Visitante:");
		lblEquipoVisitante.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEquipoVisitante.setBounds(315, 83, 130, 19);
		panCentro.add(lblEquipoVisitante);
		
		textFieldNombreVisitante = new JTextField();
		textFieldNombreVisitante.setColumns(10);
		textFieldNombreVisitante.setBounds(315, 127, 130, 20);
		panCentro.add(textFieldNombreVisitante);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombres.setBounds(20, 128, 109, 19);
		panCentro.add(lblNombres);
		
		textFieldGolesLocal = new JTextField();
		textFieldGolesLocal.setColumns(10);
		textFieldGolesLocal.setBounds(139, 170, 130, 20);
		panCentro.add(textFieldGolesLocal);
		
		textFieldGolesVisitante = new JTextField();
		textFieldGolesVisitante.setColumns(10);
		textFieldGolesVisitante.setBounds(315, 170, 130, 20);
		panCentro.add(textFieldGolesVisitante);
		
		textFieldAmarillasLocal = new JTextField();
		textFieldAmarillasLocal.setColumns(10);
		textFieldAmarillasLocal.setBounds(139, 216, 130, 20);
		panCentro.add(textFieldAmarillasLocal);
		
		textFieldAmarillasVisitante = new JTextField();
		textFieldAmarillasVisitante.setColumns(10);
		textFieldAmarillasVisitante.setBounds(315, 216, 130, 20);
		panCentro.add(textFieldAmarillasVisitante);
		
		JLabel lblAmarillas = new JLabel("Amarillas:");
		lblAmarillas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAmarillas.setBounds(20, 215, 109, 19);
		panCentro.add(lblAmarillas);
		
		JLabel lblRojas = new JLabel("Rojas:");
		lblRojas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRojas.setBounds(20, 257, 109, 19);
		panCentro.add(lblRojas);
		
		textFieldRojasLocal = new JTextField();
		textFieldRojasLocal.setColumns(10);
		textFieldRojasLocal.setBounds(139, 258, 130, 20);
		panCentro.add(textFieldRojasLocal);
		
		textFieldRojasVisitante = new JTextField();
		textFieldRojasVisitante.setColumns(10);
		textFieldRojasVisitante.setBounds(315, 258, 130, 20);
		panCentro.add(textFieldRojasVisitante);
		
		textFieldDia = new JTextField();
		textFieldDia.setBounds(139, 36, 86, 20);
		panCentro.add(textFieldDia);
		textFieldDia.setColumns(10);
		
		textFieldMes = new JTextField();
		textFieldMes.setColumns(10);
		textFieldMes.setBounds(235, 36, 86, 20);
		panCentro.add(textFieldMes);
		
		textFieldAnio = new JTextField();
		textFieldAnio.setColumns(10);
		textFieldAnio.setBounds(331, 36, 86, 20);
		panCentro.add(textFieldAnio);
		
		JLabel lblDia = new JLabel("Día:");
		lblDia.setBounds(170, 11, 46, 14);
		panCentro.add(lblDia);
		
		JLabel lblMes = new JLabel("Mes:");
		lblMes.setBounds(264, 11, 46, 14);
		panCentro.add(lblMes);
		
		JLabel lblAnio = new JLabel("Año:");
		lblAnio.setBounds(359, 11, 46, 14);
		panCentro.add(lblAnio);
	}
}
