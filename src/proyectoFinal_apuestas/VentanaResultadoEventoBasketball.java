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

public class VentanaResultadoEventoBasketball extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPuntosLocal;
	private JTextField textFieldPuntosVisitante;
	private JTextField textFieldTriplesLocal;
	private JTextField textFieldTriplesVisitante;
	private JTextField textFieldFaltasLocal;
	private JTextField textFieldFaltasVisitante;
	private JTextField textFieldNombreLocal;
	private JTextField textFieldNombreVisitante;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaResultadoEventoBasketball frame = new VentanaResultadoEventoBasketball();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaResultadoEventoBasketball() {
		setTitle("Resultado Evento UCBet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panTitulo = new JPanel();
		contentPane.add(panTitulo, BorderLayout.NORTH);
		JLabel lblTitulo = new JLabel("UCBet");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		panTitulo.add(lblTitulo);

		JPanel panPrincipal = new JPanel();
		contentPane.add(panPrincipal, BorderLayout.CENTER);
		panPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panSubtitulo = new JPanel();
		panPrincipal.add(panSubtitulo, BorderLayout.NORTH);
		JLabel lblSubtitulo = new JLabel("Resultado Evento de Basketball");
		lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panSubtitulo.add(lblSubtitulo);

		JPanel panBotones = new JPanel();
		panPrincipal.add(panBotones, BorderLayout.SOUTH);

		JButton btnRegistrarResultado = new JButton("Registrar Resultado");
		btnRegistrarResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int puntos1 = Integer.parseInt(textFieldPuntosLocal.getText());
					int puntos2 = Integer.parseInt(textFieldPuntosVisitante.getText());
					int triples1 = Integer.parseInt(textFieldTriplesLocal.getText());
					int triples2 = Integer.parseInt(textFieldTriplesVisitante.getText());
					int faltas1 = Integer.parseInt(textFieldFaltasLocal.getText());
					int faltas2 = Integer.parseInt(textFieldFaltasVisitante.getText());

					ResultadoBasketball resultado = new ResultadoBasketball(puntos1, puntos2, triples1, triples2, faltas1, faltas2);
					if (resultado.registrarResultadosBasketballTxt(Archivos.archivosResultadoBasketball)) {
						System.out.println("Resultado registrado correctamente.");
					} else {
						System.out.println("Error al registrar resultado.");
					}
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
			}
		});
		panBotones.add(btnRegistrarResultado);
		
		JButton btnCambiarEvento = new JButton("Cambiar Evento");
		btnCambiarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaResultadoEventoFutbol frame = new VentanaResultadoEventoFutbol();
				frame.setVisible(true);
			}
		});
		panBotones.add(btnCambiarEvento);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panPrincipal.add(panel, BorderLayout.CENTER);

		JLabel lblPuntos = new JLabel("Puntos:");
		lblPuntos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPuntos.setBounds(20, 99, 109, 19);
		panel.add(lblPuntos);

		textFieldPuntosLocal = new JTextField();
		textFieldPuntosLocal.setColumns(10);
		textFieldPuntosLocal.setBounds(139, 100, 130, 20);
		panel.add(textFieldPuntosLocal);

		textFieldPuntosVisitante = new JTextField();
		textFieldPuntosVisitante.setColumns(10);
		textFieldPuntosVisitante.setBounds(315, 100, 130, 20);
		panel.add(textFieldPuntosVisitante);

		JLabel lblTriples = new JLabel("Triples:");
		lblTriples.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTriples.setBounds(20, 139, 109, 19);
		panel.add(lblTriples);

		textFieldTriplesLocal = new JTextField();
		textFieldTriplesLocal.setColumns(10);
		textFieldTriplesLocal.setBounds(139, 140, 130, 20);
		panel.add(textFieldTriplesLocal);

		textFieldTriplesVisitante = new JTextField();
		textFieldTriplesVisitante.setColumns(10);
		textFieldTriplesVisitante.setBounds(315, 140, 130, 20);
		panel.add(textFieldTriplesVisitante);

		JLabel lblFaltas = new JLabel("Faltas:");
		lblFaltas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFaltas.setBounds(20, 175, 109, 19);
		panel.add(lblFaltas);

		textFieldFaltasLocal = new JTextField();
		textFieldFaltasLocal.setColumns(10);
		textFieldFaltasLocal.setBounds(139, 176, 130, 20);
		panel.add(textFieldFaltasLocal);

		textFieldFaltasVisitante = new JTextField();
		textFieldFaltasVisitante.setColumns(10);
		textFieldFaltasVisitante.setBounds(315, 176, 130, 20);
		panel.add(textFieldFaltasVisitante);

		JLabel lblEquipoLocal = new JLabel("Equipo Local:");
		lblEquipoLocal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEquipoLocal.setBounds(152, 26, 109, 19);
		panel.add(lblEquipoLocal);

		JLabel lblEquipoVisitante = new JLabel("Equipo Visitante:");
		lblEquipoVisitante.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEquipoVisitante.setBounds(317, 26, 128, 19);
		panel.add(lblEquipoVisitante);

		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombres.setBounds(20, 63, 109, 19);
		panel.add(lblNombres);

		textFieldNombreLocal = new JTextField();
		textFieldNombreLocal.setColumns(10);
		textFieldNombreLocal.setBounds(139, 64, 130, 20);
		panel.add(textFieldNombreLocal);

		textFieldNombreVisitante = new JTextField();
		textFieldNombreVisitante.setColumns(10);
		textFieldNombreVisitante.setBounds(315, 64, 130, 20);
		panel.add(textFieldNombreVisitante);
	}
}