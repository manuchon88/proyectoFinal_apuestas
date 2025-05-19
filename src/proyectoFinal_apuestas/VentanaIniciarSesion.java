package proyectoFinal_apuestas;

import java.awt.EventQueue;
import java.awt.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaIniciarSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIniciarSesion frame = new VentanaIniciarSesion();
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
	public VentanaIniciarSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		JLabel lblTitulo = new JLabel("Ingresar a Apuestas");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		panTitulo.add(lblTitulo);
		
		JPanel panBotones = new JPanel();
		panBotones.setBackground(new Color(169, 204, 227));
		panPrincipal.add(panBotones, BorderLayout.SOUTH);
		panBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int estado = Apostador.VerificarInicioSesion(textFieldUsuario.getText(), new String(passwordField.getPassword()));
				if (estado == 2) {
					JOptionPane.showMessageDialog(btnIngresar, "Bienvenido de nuevo a UCBet");
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
					VentanaIniciarSesion.this.dispose();
				}else if (estado == 1) {
					JOptionPane.showMessageDialog(btnIngresar, "Contraseña incorrecta, vuelva a intentarlo");
					passwordField.setText("");
					passwordField.requestFocusInWindow();
				}else if (estado == 0) {
					JOptionPane.showMessageDialog(btnIngresar, "Usuario no existente");
					textFieldUsuario.setText("");
					textFieldUsuario.requestFocusInWindow();
					passwordField.setText("");
				}
			}
		});
		btnIngresar.setBackground(new Color(234, 242, 248));
		panBotones.add(btnIngresar);
		
		JButton btnRegistrar = new JButton("Registrarse");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistrarUsuario frame = new VentanaRegistrarUsuario();
				frame.setVisible(true);
				VentanaIniciarSesion.this.dispose();
			}
		});
		panBotones.add(btnRegistrar);
		
		JPanel panCentro = new JPanel();
		panCentro.setBackground(new Color(235, 245, 251));
		panPrincipal.add(panCentro, BorderLayout.CENTER);
		panCentro.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsuario.setBounds(51, 43, 90, 20);
		panCentro.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblContrasenia.setBounds(51, 93, 90, 20);
		panCentro.add(lblContrasenia);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(174, 44, 192, 20);
		panCentro.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 94, 192, 20);
		panCentro.add(passwordField);
	}
}
