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

public class VentanaRegistrarAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldCorreo;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistrarAdministrador frame = new VentanaRegistrarAdministrador();
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
	public VentanaRegistrarAdministrador() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 310);
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
		
		JLabel lblTitulo = new JLabel("Registrar Nuevo Administrador");
		lblTitulo.setForeground(new Color(255, 232, 0));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		panTitulo.add(lblTitulo);
		
		JPanel panBotones = new JPanel();
		panBotones.setBackground(new Color(235, 245, 251));
		panPrincipal.add(panBotones, BorderLayout.SOUTH);
		panBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		
		JButton btnRegistrar = new JButton("Registrarse");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrador user = new Administrador(textFieldUsuario.getText(), textFieldCorreo.getText(), new String(passwordField.getPassword()));
				if (user.registrarAdministradoresTxt(Archivos.archivosAdministradores)) {
					JOptionPane.showMessageDialog(btnRegistrar, "Usuario registrado con éxito\n"+"Bienvenido a UCBet");
					VentanaAdministrador frame = new VentanaAdministrador(textFieldUsuario.getText(), textFieldCorreo.getText(), new String(passwordField.getPassword()));
					frame.setVisible(true);
					VentanaRegistrarAdministrador.this.dispose();
				} else {
					JOptionPane.showMessageDialog(btnRegistrar, "Registro fallido, vuelva a intentarlo");
					textFieldCorreo.setText("");
					textFieldUsuario.setText("");
					passwordField.setText("");
					textFieldUsuario.requestFocusInWindow();
				}
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
		lblContrasenia.setBounds(51, 130, 90, 20);
		panCentro.add(lblContrasenia);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(174, 44, 192, 20);
		panCentro.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCorreo.setBounds(51, 88, 90, 20);
		panCentro.add(lblCorreo);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBounds(174, 89, 192, 20);
		panCentro.add(textFieldCorreo);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 131, 192, 20);
		panCentro.add(passwordField);
	}

}
