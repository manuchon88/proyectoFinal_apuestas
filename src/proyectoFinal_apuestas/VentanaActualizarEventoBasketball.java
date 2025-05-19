package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaActualizarEventoBasketball extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombreLocal;
	private JTextField textFieldNombreVisitante;
	private JTextField textFieldPuntosLocal;
	private JTextField textFieldPuntosVisitante;
	private JTextField textFieldTriplesLocal;
	private JTextField textFieldTriplesVisitante;
	private JTextField textFieldFaltasLocal;
	private JTextField textFieldFaltasVisitante;
	private JTextField textFieldDia;
	private JTextField textFieldMes;
	private JTextField textFieldAnio;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaActualizarEventoBasketball frame = new VentanaActualizarEventoBasketball();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaActualizarEventoBasketball() {
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

		JLabel lblSubtitulo = new JLabel("Actualizar Evento de Basketball");
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
					int puntos1 = Integer.parseInt(textFieldPuntosLocal.getText());
					int puntos2 = Integer.parseInt(textFieldPuntosVisitante.getText());
					int triples1 = Integer.parseInt(textFieldTriplesLocal.getText());
					int triples2 = Integer.parseInt(textFieldTriplesVisitante.getText());
					int faltas1 = Integer.parseInt(textFieldFaltasLocal.getText());
					int faltas2 = Integer.parseInt(textFieldFaltasVisitante.getText());

					EventoBasketball evento = new EventoBasketball(anio, mes, dia, eq1, eq2, puntos1, puntos2, triples1, triples2, faltas1, faltas2);
					if (evento.registrarEventoBasketballTxt(Archivos.archivosEventosBasketball)) {
						System.out.println("Evento de Basketball registrado correctamente");
					} else {
						System.out.println("Error al registrar evento de Basketball");
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
				VentanaActualizarEventoFutbol frame = new VentanaActualizarEventoFutbol();
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

		textFieldDia = new JTextField();
		textFieldDia.setBounds(139, 36, 86, 20);
		panCentro.add(textFieldDia);
		textFieldDia.setColumns(10);

		textFieldMes = new JTextField();
		textFieldMes.setBounds(235, 36, 86, 20);
		panCentro.add(textFieldMes);
		textFieldMes.setColumns(10);

		textFieldAnio = new JTextField();
		textFieldAnio.setBounds(331, 36, 86, 20);
		panCentro.add(textFieldAnio);
		textFieldAnio.setColumns(10);

		JLabel lblDia = new JLabel("Día:");
		lblDia.setBounds(170, 11, 46, 14);
		panCentro.add(lblDia);

		JLabel lblMes = new JLabel("Mes:");
		lblMes.setBounds(264, 11, 46, 14);
		panCentro.add(lblMes);

		JLabel lblAnio = new JLabel("Año:");
		lblAnio.setBounds(359, 11, 46, 14);
		panCentro.add(lblAnio);

		JLabel lblEquipoLocal = new JLabel("Equipo Local:");
		lblEquipoLocal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEquipoLocal.setBounds(150, 83, 109, 19);
		panCentro.add(lblEquipoLocal);

		textFieldNombreLocal = new JTextField();
		textFieldNombreLocal.setBounds(139, 127, 130, 20);
		panCentro.add(textFieldNombreLocal);
		textFieldNombreLocal.setColumns(10);

		JLabel lblEquipoVisitante = new JLabel("Equipo Visitante:");
		lblEquipoVisitante.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEquipoVisitante.setBounds(315, 83, 130, 19);
		panCentro.add(lblEquipoVisitante);

		textFieldNombreVisitante = new JTextField();
		textFieldNombreVisitante.setBounds(315, 127, 130, 20);
		panCentro.add(textFieldNombreVisitante);
		textFieldNombreVisitante.setColumns(10);

		JLabel lblPuntos = new JLabel("Puntos:");
		lblPuntos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPuntos.setBounds(20, 169, 109, 19);
		panCentro.add(lblPuntos);

		textFieldPuntosLocal = new JTextField();
		textFieldPuntosLocal.setBounds(139, 170, 130, 20);
		panCentro.add(textFieldPuntosLocal);
		textFieldPuntosLocal.setColumns(10);

		textFieldPuntosVisitante = new JTextField();
		textFieldPuntosVisitante.setBounds(315, 170, 130, 20);
		panCentro.add(textFieldPuntosVisitante);
		textFieldPuntosVisitante.setColumns(10);

		JLabel lblTriples = new JLabel("Triples:");
		lblTriples.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTriples.setBounds(20, 215, 109, 19);
		panCentro.add(lblTriples);

		textFieldTriplesLocal = new JTextField();
		textFieldTriplesLocal.setBounds(139, 216, 130, 20);
		panCentro.add(textFieldTriplesLocal);
		textFieldTriplesLocal.setColumns(10);

		textFieldTriplesVisitante = new JTextField();
		textFieldTriplesVisitante.setBounds(315, 216, 130, 20);
		panCentro.add(textFieldTriplesVisitante);
		textFieldTriplesVisitante.setColumns(10);

		JLabel lblFaltas = new JLabel("Faltas:");
		lblFaltas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFaltas.setBounds(20, 257, 109, 19);
		panCentro.add(lblFaltas);

		textFieldFaltasLocal = new JTextField();
		textFieldFaltasLocal.setBounds(139, 258, 130, 20);
		panCentro.add(textFieldFaltasLocal);
		textFieldFaltasLocal.setColumns(10);

		textFieldFaltasVisitante = new JTextField();
		textFieldFaltasVisitante.setBounds(315, 258, 130, 20);
		panCentro.add(textFieldFaltasVisitante);
		textFieldFaltasVisitante.setColumns(10);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombres.setBounds(20, 130, 109, 19);
		panCentro.add(lblNombres);
	}
}
