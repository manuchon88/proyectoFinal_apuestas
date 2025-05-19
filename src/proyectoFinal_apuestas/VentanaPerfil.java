package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class VentanaPerfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
	public VentanaPerfil(Apostador user) {
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
		
		JPanel panArriba = new JPanel(new GridLayout(1,2));
		panCenter.add(panArriba);
		
		JLabel lblNombre = new JLabel(user.getNombre());
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 22));
		panArriba.add(lblNombre);
		
		JLabel lblSaldoBig = new JLabel("Saldo: Bs. "+user.getSaldo());
		lblSaldoBig.setFont(new Font("Tahoma", Font.PLAIN|Font.ITALIC, 22));
		panArriba.add(lblSaldoBig);
		
		JPanel panAbajo = new JPanel(new GridLayout(2,1));
		panCenter.add(panAbajo);
		
		JSeparator separator = new JSeparator();
		panAbajo.add(separator);
		
		
		
		JPanel panAbajo2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panAbajo.add(panAbajo2);
		
		JButton btnDepoReti	= new JButton("Depositar / Retirar");
		panAbajo2.add(btnDepoReti);
		
		JButton btnEditarPerfil = new JButton("Editar Perfil");
		panAbajo2.add(btnEditarPerfil);
		
		JButton btnHistorial = new JButton("Historial");
		panAbajo2.add(btnHistorial);
		
		
	}

}
