package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String correoAdmin;
	private String contraseniaAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAdministrador(String nombre, String correo, String contrasenia) {
		this.correoAdmin = correo;
		this.contraseniaAdmin = contrasenia;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 384);
		setTitle("UCBet");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panPrincipal = new JPanel();
		contentPane.add(panPrincipal, BorderLayout.CENTER);
		panPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitulo = new JPanel();
		panTitulo.setBackground(new Color(169, 204, 227));
		panPrincipal.add(panTitulo, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("Opciones Administrador UCBet");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		panTitulo.add(lblTitulo);
		
		JPanel panBotones = new JPanel();
		panBotones.setBackground(new Color(169, 204, 227));
		panPrincipal.add(panBotones, BorderLayout.SOUTH);
		panBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnIngresar = new JButton("Ingresar como Usuario");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Apostador usuario = Apostador.iniciarSesion(correoAdmin, contraseniaAdmin);
				if (usuario != null) {
					JOptionPane.showMessageDialog(btnIngresar, "Ingresaste como usuario.");
					VentanaPrincipal frame = new VentanaPrincipal(usuario);
					frame.setVisible(true);
					VentanaAdministrador.this.dispose();
				} else {
					JOptionPane.showMessageDialog(btnIngresar, "Este administrador no está registrado como usuario.");
				}
			}
		});

		btnIngresar.setBackground(new Color(234, 242, 248));
		panBotones.add(btnIngresar);
		
		JButton btnRegistrar = new JButton("Registrar Nueva Cuenta");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistrarAdministrador frame = new VentanaRegistrarAdministrador();
				frame.setVisible(true);
				VentanaAdministrador.this.dispose();
			}
		});
		panBotones.add(btnRegistrar);
		
		JPanel panCentro = new JPanel();
		panCentro.setBackground(new Color(235, 245, 251));
		panPrincipal.add(panCentro, BorderLayout.CENTER);
		panCentro.setLayout(null);
		
		JLabel lblCrearEvento = new JLabel("Crear Evento: ");
		lblCrearEvento.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCrearEvento.setBounds(45, 86, 118, 20);
		panCentro.add(lblCrearEvento);
		
		JButton btnNuevoEvento = new JButton("Nuevo Evento");
		btnNuevoEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCrearEvento frame = new VentanaCrearEvento();
				frame.setVisible(true);
				VentanaAdministrador.this.dispose();
			}
		});
		btnNuevoEvento.setBounds(228, 86, 147, 23);
		panCentro.add(btnNuevoEvento);
		
		JLabel lblAdministrador = new JLabel("Usuario Administrador: ");
		lblAdministrador.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAdministrador.setBounds(45, 25, 172, 20);
		panCentro.add(lblAdministrador);
		
		JLabel lblNombreAdmin = new JLabel(nombre);
		lblNombreAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNombreAdmin.setBounds(228, 25, 172, 20);
		panCentro.add(lblNombreAdmin);
		
		JLabel lblCorreoAdministrador = new JLabel("Correo Administrador: ");
		lblCorreoAdministrador.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCorreoAdministrador.setBounds(45, 56, 172, 20);
		panCentro.add(lblCorreoAdministrador);
		
		JLabel lblNombreAdmin_1 = new JLabel(correo);
		lblNombreAdmin_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNombreAdmin_1.setBounds(228, 55, 172, 20);
		panCentro.add(lblNombreAdmin_1);
		
		JLabel lblActualizarEvento = new JLabel("Actualizar Evento: ");
		lblActualizarEvento.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblActualizarEvento.setBounds(45, 122, 156, 20);
		panCentro.add(lblActualizarEvento);
		
		JButton btnActualizarEvento = new JButton("Actualizar Evento");
		btnActualizarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] opciones = {"Fútbol", "Basketball"};
				int seleccion = JOptionPane.showOptionDialog(
					btnActualizarEvento,
					"Selecciona el tipo de evento a actualizar:",
					"Tipo de Evento",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					opciones,
					opciones[0]
				);

				if (seleccion == 0) {
					VentanaActualizarEventoFutbol frame = new VentanaActualizarEventoFutbol();
					frame.setVisible(true);
					VentanaAdministrador.this.dispose();
				} else if (seleccion == 1) {
					VentanaActualizarEventoBasketball frame = new VentanaActualizarEventoBasketball();
					frame.setVisible(true);
					VentanaAdministrador.this.dispose();
				}
			}
		});
		btnActualizarEvento.setBounds(228, 122, 147, 23);
		panCentro.add(btnActualizarEvento);
		
		JLabel lblResultadosEvento = new JLabel("Resultados del Evento: ");
		lblResultadosEvento.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblResultadosEvento.setBounds(45, 162, 172, 20);
		panCentro.add(lblResultadosEvento);
		
		JButton btnResultadosEvento = new JButton("Resultados del Evento");
		btnResultadosEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnResultadosEvento.setBounds(228, 162, 147, 23);
		panCentro.add(btnResultadosEvento);
	}
}
