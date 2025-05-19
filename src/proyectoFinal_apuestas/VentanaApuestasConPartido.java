package proyectoFinal_apuestas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaApuestasConPartido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMonto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaApuestasConPartido(String tipo, String evento, Apostador user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitulo = new JPanel();
		panel.add(panTitulo, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("UCBet");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		panTitulo.add(lblTitulo);
		
		JPanel panPrincipal = new JPanel();
		panel.add(panPrincipal, BorderLayout.CENTER);
		panPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panSubtitulo = new JPanel();
		panPrincipal.add(panSubtitulo, BorderLayout.NORTH);
		
		JLabel lblApuesta = new JLabel("Apuesta");
		lblApuesta.setFont(new Font("Tahoma", Font.BOLD, 20));
		panSubtitulo.add(lblApuesta);
		
		JPanel panBotones = new JPanel();
		panPrincipal.add(panBotones, BorderLayout.SOUTH);
		
		JButton btnApostar = new JButton("Apostar");
		btnApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Restar Saldo - Monto para apuesta
			}
		});
		panBotones.add(btnApostar);
		
		JPanel panCentro = new JPanel();
		panPrincipal.add(panCentro, BorderLayout.CENTER);
		panCentro.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo de Evento:");
		lblTipo.setBounds(58, 31, 91, 14);
		panCentro.add(lblTipo);
		
		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setBounds(58, 65, 91, 14);
		panCentro.add(lblEvento);
		
		JLabel lblNombreTipoEvento = new JLabel(tipo);
		lblNombreTipoEvento.setBounds(184, 31, 199, 14);
		panCentro.add(lblNombreTipoEvento);
		
		JLabel lblNombreEvento = new JLabel(evento);
		lblNombreEvento.setBounds(184, 65, 199, 14);
		panCentro.add(lblNombreEvento);
		
		JLabel lblTipoApuesta = new JLabel("Tipo de Apuesta:");
		lblTipoApuesta.setBounds(58, 100, 122, 14);
		panCentro.add(lblTipoApuesta);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(212, 96, 171, 22);
		panCentro.add(comboBox);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(58, 141, 91, 14);
		panCentro.add(lblSaldo);
		
		JLabel lblSaldoNumero = new JLabel("" + user.getSaldo());
		lblSaldoNumero.setBounds(212, 141, 168, 14);
		panCentro.add(lblSaldoNumero);
		
		JLabel lblMonto = new JLabel("Monto a Apostar:");
		lblMonto.setBounds(58, 179, 122, 14);
		panCentro.add(lblMonto);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setBounds(212, 176, 171, 20);
		panCentro.add(textFieldMonto);
		textFieldMonto.setColumns(10);
	}
}
