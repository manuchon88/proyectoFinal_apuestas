package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.PublicKey;
import java.awt.event.ActionEvent;

public class VentanaDepositosRetiros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDepositar;
	private JTextField textFieldRetirar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaDepositosRetiros(Apostador user, VentanaPerfil ventana2) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(550, 50, 500, 324);;
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panPrincipal = new JPanel();
		panPrincipal.setLayout(new BorderLayout());
		contentPane.add(panPrincipal, BorderLayout.CENTER);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(45, 104, 184));
		panPrincipal.add(header, BorderLayout.NORTH);
		header.setLayout(new GridLayout(1, 2, 5, 15));
		
		JLabel UCBetTITLE = new JLabel("UCBet");
		UCBetTITLE.setForeground(new Color(255, 232, 0));
		UCBetTITLE.setFont(new Font("Tahoma", Font.BOLD, 25));
		UCBetTITLE.setHorizontalAlignment(SwingConstants.LEFT);
		header.add(UCBetTITLE);
		
		JPanel panHeadDer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panHeadDer.setBackground(new Color(45, 104, 184));
		header.add(panHeadDer);
		
		
		JLabel lblSaldo = new JLabel();
		lblSaldo.setText("Bs. "+user.getSaldo());
		lblSaldo.setForeground(new Color(255,255,255));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		panHeadDer.add(lblSaldo);
		
		JButton btnPerfil = new JButton();
		btnPerfil.setText(user.getNombre());
		btnPerfil.setBackground(new Color(45, 104, 184));
		btnPerfil.setForeground(new Color(255, 255, 255));
		panHeadDer.add(btnPerfil);
		
		JPanel panCenter = new JPanel(new GridLayout(2, 1));
		panPrincipal.add(panCenter, BorderLayout.CENTER);
		
		JPanel panArriba = new JPanel(new BorderLayout());
		panCenter.add(panArriba);
		JLabel lblSaldoBig = new JLabel("Saldo: Bs. "+user.getSaldo());
		lblSaldoBig.setFont(new Font("Tahoma", Font.PLAIN|Font.ITALIC, 22));
		lblSaldoBig.setHorizontalAlignment(SwingConstants.CENTER);
		panArriba.add(lblSaldoBig, BorderLayout.CENTER);
		
		JPanel panOptions = new JPanel(new GridLayout(1, 2,8,8));
		panCenter.add(panOptions);
		
		JPanel panDepositar = new JPanel(new GridLayout(2, 1));
		panOptions.add(panDepositar);
		
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double montoDepositar = Double.parseDouble(textFieldDepositar.getText());
				user.setSaldo(user.getSaldo()+montoDepositar);
				lblSaldo.setText("Bs. "+user.getSaldo());
				lblSaldoBig.setText("Bs. "+user.getSaldo());
				textFieldDepositar.setText("");
			}
		});
		btnDepositar.setHorizontalAlignment(SwingConstants.CENTER);
		btnDepositar.setFont(new Font("Tahoma", Font.BOLD, 15));
		panDepositar.add(btnDepositar, BorderLayout.NORTH);
		
		textFieldDepositar = new JTextField();
		textFieldDepositar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panDepositar.add(textFieldDepositar);
		textFieldDepositar.setColumns(10);
		
		
		
		JPanel panRetirar = new JPanel(new GridLayout(2, 1));
		panOptions.add(panRetirar);

		JButton btnRetirar = new JButton("Retirar");
		btnRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double montoRetirar = Double.parseDouble(textFieldRetirar.getText());
				user.setSaldo(user.getSaldo()-montoRetirar);
				lblSaldo.setText("Bs. "+user.getSaldo());
				lblSaldoBig.setText("Bs. "+user.getSaldo());
				textFieldRetirar.setText("");
			}
		});
		btnRetirar.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetirar.setFont(new Font("Tahoma", Font.BOLD, 15));
		panRetirar.add(btnRetirar, BorderLayout.NORTH);
		
		textFieldRetirar = new JTextField();
		textFieldRetirar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panRetirar.add(textFieldRetirar);
		textFieldRetirar.setColumns(10);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Este método se llama DESPUÉS de que la ventana ha sido cerrada
        		ventana2.dispose();
            }
        });
		
	}
	

}
