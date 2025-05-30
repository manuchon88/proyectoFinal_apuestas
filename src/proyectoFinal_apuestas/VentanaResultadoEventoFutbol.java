package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.UIManager;

public class VentanaResultadoEventoFutbol extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldGolesLocal;
	private JTextField textFieldGolesVisitante;
	private JTextField textFieldAmarillasLocal;
	private JTextField textFieldAmarillasVisitante;
	private JTextField textFieldRojasLocal;
	private JTextField textFieldRojasVisitante;
	private JTextField textFieldNombreLocal;
	private JTextField textFieldNombreVisitante;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaResultadoEventoFutbol frame = new VentanaResultadoEventoFutbol();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaResultadoEventoFutbol() {
		setTitle("Resultado Evento UCBet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 396);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(235, 245, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panTitulo = new JPanel();
		panTitulo.setBackground(new Color(45, 104, 184));
		contentPane.add(panTitulo, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("UCBet");
		lblTitulo.setForeground(new Color(255, 232, 0));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		panTitulo.add(lblTitulo);
		
		JPanel panPrincipal = new JPanel();
		contentPane.add(panPrincipal, BorderLayout.CENTER);
		panPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panSubtitulo = new JPanel();
		panSubtitulo.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		panPrincipal.add(panSubtitulo, BorderLayout.NORTH);
		
		JLabel lblSubtitulo = new JLabel("Resultado Evento de Fútbol");
		lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panSubtitulo.add(lblSubtitulo);
		
		JPanel panBotones = new JPanel();
		panBotones.setBackground(new Color(235, 245, 251));
		panPrincipal.add(panBotones, BorderLayout.SOUTH);
		
		JButton btnRegistrarResultado = new JButton("Registrar Resultado");
		btnRegistrarResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int goles1 = Integer.parseInt(textFieldGolesLocal.getText());
					int goles2 = Integer.parseInt(textFieldGolesVisitante.getText());
					int amarillas1 = Integer.parseInt(textFieldAmarillasLocal.getText());
					int amarillas2 = Integer.parseInt(textFieldAmarillasVisitante.getText());
					int rojas1 = Integer.parseInt(textFieldRojasLocal.getText());
					int rojas2 = Integer.parseInt(textFieldRojasVisitante.getText());

					ResultadoFutbol resultado = new ResultadoFutbol(goles1, goles2, amarillas1, amarillas2, rojas1, rojas2);
					if (resultado.registrarResultadosFutbolTxt(Archivos.archivosResultadoFutbol)) {
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
				VentanaResultadoEventoBasketball frame = new VentanaResultadoEventoBasketball();
				frame.setVisible(true);
			}
		});
		panBotones.add(btnCambiarEvento);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(235, 245, 251));
		panel.setLayout(null);
		panPrincipal.add(panel, BorderLayout.CENTER);
		
		JLabel lblGoles = new JLabel("Goles:");
		lblGoles.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGoles.setBounds(20, 99, 109, 19);
		panel.add(lblGoles);
		
		textFieldGolesLocal = new JTextField();
		textFieldGolesLocal.setColumns(10);
		textFieldGolesLocal.setBounds(139, 100, 130, 20);
		panel.add(textFieldGolesLocal);
		
		textFieldGolesVisitante = new JTextField();
		textFieldGolesVisitante.setColumns(10);
		textFieldGolesVisitante.setBounds(315, 100, 130, 20);
		panel.add(textFieldGolesVisitante);
		
		JLabel lblAmarillas = new JLabel("Amarillas:");
		lblAmarillas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAmarillas.setBounds(20, 139, 109, 19);
		panel.add(lblAmarillas);
		
		textFieldAmarillasLocal = new JTextField();
		textFieldAmarillasLocal.setColumns(10);
		textFieldAmarillasLocal.setBounds(139, 140, 130, 20);
		panel.add(textFieldAmarillasLocal);
		
		textFieldAmarillasVisitante = new JTextField();
		textFieldAmarillasVisitante.setColumns(10);
		textFieldAmarillasVisitante.setBounds(315, 140, 130, 20);
		panel.add(textFieldAmarillasVisitante);
		
		JLabel lblRojas = new JLabel("Rojas:");
		lblRojas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRojas.setBounds(20, 175, 109, 19);
		panel.add(lblRojas);
		
		textFieldRojasLocal = new JTextField();
		textFieldRojasLocal.setColumns(10);
		textFieldRojasLocal.setBounds(139, 176, 130, 20);
		panel.add(textFieldRojasLocal);
		
		textFieldRojasVisitante = new JTextField();
		textFieldRojasVisitante.setColumns(10);
		textFieldRojasVisitante.setBounds(315, 176, 130, 20);
		panel.add(textFieldRojasVisitante);
		
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
