package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class VentanaModCuenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaModCuenta(Apostador user, VentanaPerfil ventana) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 50, 500, 324);
		setTitle("UCBet");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(235, 245, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panPrincipal = new JPanel();
		contentPane.add(panPrincipal, BorderLayout.CENTER);
		panPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitulo = new JPanel();
		panTitulo.setBackground(new Color(45, 104, 184));
		panPrincipal.add(panTitulo, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("Modificar Cuenta");
		lblTitulo.setForeground(new Color(255, 232, 0));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		panTitulo.add(lblTitulo);
		
		JPanel panBotones = new JPanel();
		panBotones.setBackground(new Color(235, 245, 251));
		panPrincipal.add(panBotones, BorderLayout.SOUTH);
		panBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		
		JButton btnActualizar = new JButton("Actualizar Cuenta");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String antigua = new String(passwordField_1.getPassword());
				if (user.getContrasenia().equals(antigua)) {
					user.setNombre(textFieldUsuario.getText());
					user.setContrasenia(new String(passwordField.getPassword()));
					JOptionPane.showMessageDialog(btnActualizar, "Cuenta actualizada");
					ventana.dispose();
					VentanaModCuenta.this.dispose();
				} else {
					JOptionPane.showMessageDialog(btnActualizar, "Contraseña incorrecta");
					passwordField.setText("");
					passwordField_1.setText("");
					passwordField_1.requestFocusInWindow();
				}
			}
		});
		panBotones.add(btnActualizar);
		
		JPanel panCentro = new JPanel();
		panCentro.setBackground(new Color(235, 245, 251));
		panPrincipal.add(panCentro, BorderLayout.CENTER);
		panCentro.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsuario.setBounds(51, 43, 90, 20);
		panCentro.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Nueva Contraseña:");
		lblContrasenia.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblContrasenia.setBounds(51, 130, 150, 20);
		panCentro.add(lblContrasenia);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(211, 43, 192, 20);
		panCentro.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setText(user.getNombre());
		
		
		JLabel lblContrasenia1 = new JLabel("Contraseña:");
		lblContrasenia1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblContrasenia1.setBounds(51, 88, 90, 20);
		panCentro.add(lblContrasenia1);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(211, 130, 192, 20);
		panCentro.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(211, 88, 192, 20);
		panCentro.add(passwordField_1);
	}
}
