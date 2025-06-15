package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaHistorial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<ApuestaFutbol> HistFutbol;
	private ArrayList<ApuestaBasketball> HistBasketball;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaHistorial(Apostador user) {
		
		HistFutbol = user.verHistorialFutbol();
		HistBasketball = user.verHistorialBasketball();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500, 50, 700, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
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
		
		
		JPanel panCenter = new JPanel(new GridLayout(1, 2));
		panPrincipal.add(panCenter);
		
		JPanel panFutbol = new JPanel();
		panFutbol.setLayout(new BoxLayout(panFutbol, BoxLayout.Y_AXIS));
		JScrollPane ScrollFutbol = new JScrollPane(panFutbol);
		ScrollFutbol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollFutbol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panCenter.add(ScrollFutbol);
		
		for (ApuestaFutbol bet : HistFutbol) {
			String[] torneos = {"Champions league", "Libertadores", "Premier league Boliviana", "La Liga", "Serie A", "Premier League", "Amistoso"};
			String part = torneos[bet.getEvent().getTorneo()];
			
			JPanel panBet = new JPanel(new GridLayout(5, 2));
			panBet.setPreferredSize(new Dimension(350, 120)); // Ajusta el ancho y alto según tus necesidades
		    panBet.setMaximumSize(new Dimension(350, 120));   // Opcional: asegura que no se expanda
		    panBet.setMinimumSize(new Dimension(350, 120));   // Opcional: asegura que no se reduzca
		    panBet.setBorder(new EmptyBorder(5, 5, 5, 5));
			if (bet.getEvent().isTerminado()) {
				if (bet.isGanador()) {
					panBet.setLayout(new GridLayout(6, 2));
					panBet.setPreferredSize(new Dimension(350, 130)); // Ajusta el ancho y alto según tus necesidades
				    panBet.setMaximumSize(new Dimension(350, 130));   // Opcional: asegura que no se expanda
				    panBet.setMinimumSize(new Dimension(350, 130));   // Opcional: asegura que no se reduzca
				    panBet.setBorder(new EmptyBorder(5, 5, 5, 5));
					
					JLabel lblDescrip = new JLabel(bet.getDescripcion());
					lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblDescrip.setForeground(new Color(147, 255, 51));
					panBet.add(lblDescrip);
					
					JLabel lblCuota = new JLabel(""+bet.getCuota());
					lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblCuota);
					
					JLabel lblTorneo = new JLabel(part);
					panBet.add(lblTorneo);
					panBet.add(new JLabel());
					
					JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1()+" vs. "+bet.getEvent().getEquipo2());
					lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
					panBet.add(lblMatch);
					JLabel lblResult = new JLabel(bet.getEvent().getGoles1()+":"+bet.getEvent().getGoles2());
					lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblResult.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblResult);
					
					JLabel lblFecha = new JLabel(""+bet.getEvent().getFecha());
					panBet.add(lblFecha);
					panBet.add(new JLabel());
					
					JLabel lblMonto = new JLabel("Monto: ");
					panBet.add(lblMonto);
					
					JLabel lblMonto2 = new JLabel("Bs. "+bet.getPredict().getMonto());
					lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblMonto2);

					JLabel lblGanancia = new JLabel("Ganancia: ");
					panBet.add(lblGanancia);
					
					JLabel lblGanancia2 = new JLabel("Bs. "+(bet.getPredict().getMonto()* bet.getCuota()));
					lblGanancia2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblGanancia2);
					
				} else {
					JLabel lblDescrip = new JLabel(bet.getDescripcion());
					lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblDescrip.setForeground(new Color(214, 0, 0));
					panBet.add(lblDescrip);
					
					JLabel lblCuota = new JLabel(""+bet.getCuota());
					lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblCuota);
					
					JLabel lblTorneo = new JLabel(part);
					panBet.add(lblTorneo);
					panBet.add(new JLabel());
					
					JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1()+" vs. "+bet.getEvent().getEquipo2());
					lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
					panBet.add(lblMatch);
					JLabel lblResult = new JLabel(bet.getEvent().getGoles1()+":"+bet.getEvent().getGoles2());
					lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblResult.setHorizontalAlignment(SwingConstants.CENTER);

					panBet.add(lblResult);
					
					JLabel lblFecha = new JLabel(""+bet.getEvent().getFecha());
					panBet.add(lblFecha);
					panBet.add(new JLabel());
					
					JLabel lblMonto = new JLabel("Monto: ");
					panBet.add(lblMonto);
					
					JLabel lblMonto2 = new JLabel("Bs. "+bet.getPredict().getMonto());
					lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblMonto2);
				}
			}else {
				JLabel lblDescrip = new JLabel(bet.getDescripcion());
				lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblDescrip.setForeground(new Color(2, 124, 213));
				panBet.add(lblDescrip);
				
				JLabel lblCuota = new JLabel(""+bet.getCuota());
				lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
				panBet.add(lblCuota);
				
				JLabel lblTorneo = new JLabel(part);
				panBet.add(lblTorneo);
				panBet.add(new JLabel());
				
				JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1()+" vs. "+bet.getEvent().getEquipo2());
				lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
				panBet.add(lblMatch);
				JLabel lblResult = new JLabel(bet.getEvent().getGoles1()+":"+bet.getEvent().getGoles2());
				lblResult.setHorizontalAlignment(SwingConstants.CENTER);
				lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
				panBet.add(lblResult);
				
				JLabel lblFecha = new JLabel(""+bet.getEvent().getFecha());
				panBet.add(lblFecha);
				panBet.add(new JLabel());
				
				JLabel lblMonto = new JLabel("Monto: ");
				panBet.add(lblMonto);
				
				JLabel lblMonto2 = new JLabel("Bs. "+bet.getPredict().getMonto());
				lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
				panBet.add(lblMonto2);
			}
			
			panFutbol.add(panBet);
			panFutbol.add(new JSeparator());
		}
		
		//-------------------Basketball--------------------
		
		JPanel panBasketball = new JPanel();
		panBasketball.setLayout(new BoxLayout(panBasketball, BoxLayout.Y_AXIS));
		JScrollPane ScrollBasketball = new JScrollPane(panBasketball);
		ScrollBasketball.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollBasketball.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panCenter.add(ScrollBasketball);
		
		for (ApuestaBasketball bet : HistBasketball) {
			String[] torneos = {"NBA", "Euro League", "ACB", "FIBA", "NCAA", "Amistoso"};
			String part = torneos[bet.getEvent().getTorneo()];
			
			JPanel panBet = new JPanel(new GridLayout(5, 2));
			panBet.setPreferredSize(new Dimension(350, 120)); // Ajusta el ancho y alto según tus necesidades
		    panBet.setMaximumSize(new Dimension(350, 120));   // Opcional: asegura que no se expanda
		    panBet.setMinimumSize(new Dimension(350, 120));   // Opcional: asegura que no se reduzca
		    panBet.setBorder(new EmptyBorder(5, 5, 5, 5));
			if (bet.getEvent().isTerminado()) {
				if (bet.isGanador()) {
					panBet.setLayout(new GridLayout(6, 2));
					panBet.setPreferredSize(new Dimension(350, 130)); // Ajusta el ancho y alto según tus necesidades
				    panBet.setMaximumSize(new Dimension(350, 130));   // Opcional: asegura que no se expanda
				    panBet.setMinimumSize(new Dimension(350, 130));   // Opcional: asegura que no se reduzca
				    panBet.setBorder(new EmptyBorder(5, 5, 5, 5));
					
					JLabel lblDescrip = new JLabel(bet.getDescripcion());
					lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblDescrip.setForeground(new Color(147, 255, 51));
					panBet.add(lblDescrip);
					
					JLabel lblCuota = new JLabel(""+bet.getCuota());
					lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblCuota);
					
					JLabel lblTorneo = new JLabel(part);
					panBet.add(lblTorneo);
					panBet.add(new JLabel());
					
					JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1()+" vs. "+bet.getEvent().getEquipo2());
					lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
					panBet.add(lblMatch);
					JLabel lblResult = new JLabel(bet.getEvent().getPuntos1()+":"+bet.getEvent().getPuntos2());
					lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblResult.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblResult);
					
					JLabel lblFecha = new JLabel(""+bet.getEvent().getFecha());
					panBet.add(lblFecha);
					panBet.add(new JLabel());
					
					JLabel lblMonto = new JLabel("Monto: ");
					panBet.add(lblMonto);
					
					JLabel lblMonto2 = new JLabel("Bs. "+bet.getPredict().getMonto());
					lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblMonto2);

					JLabel lblGanancia = new JLabel("Ganancia: ");
					panBet.add(lblGanancia);
					
					JLabel lblGanancia2 = new JLabel("Bs. "+(bet.getPredict().getMonto()* bet.getCuota()));
					lblGanancia2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblGanancia2);
				} else {
					JLabel lblDescrip = new JLabel(bet.getDescripcion());
					lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblDescrip.setForeground(new Color(214, 0, 0));
					panBet.add(lblDescrip);
					
					JLabel lblCuota = new JLabel(""+bet.getCuota());
					lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblCuota);
					
					JLabel lblTorneo = new JLabel(part);
					panBet.add(lblTorneo);
					panBet.add(new JLabel());
					
					JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1()+" vs. "+bet.getEvent().getEquipo2());
					lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
					panBet.add(lblMatch);
					JLabel lblResult = new JLabel(bet.getEvent().getPuntos1()+":"+bet.getEvent().getPuntos2());
					lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblResult.setHorizontalAlignment(SwingConstants.CENTER);

					panBet.add(lblResult);
					
					JLabel lblFecha = new JLabel(""+bet.getEvent().getFecha());
					panBet.add(lblFecha);
					panBet.add(new JLabel());
					
					JLabel lblMonto = new JLabel("Monto: ");
					panBet.add(lblMonto);
					
					JLabel lblMonto2 = new JLabel("Bs. "+bet.getPredict().getMonto());
					lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblMonto2);
				}
			}else {
				JLabel lblDescrip = new JLabel(bet.getDescripcion());
				lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblDescrip.setForeground(new Color(2, 124, 213));
				panBet.add(lblDescrip);
				
				JLabel lblCuota = new JLabel(""+bet.getCuota());
				lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
				panBet.add(lblCuota);
				
				JLabel lblTorneo = new JLabel(part);
				panBet.add(lblTorneo);
				panBet.add(new JLabel());
				
				JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1()+" vs. "+bet.getEvent().getEquipo2());
				lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
				panBet.add(lblMatch);
				JLabel lblResult = new JLabel(bet.getEvent().getPuntos1()+":"+bet.getEvent().getPuntos2());
				lblResult.setHorizontalAlignment(SwingConstants.CENTER);
				lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
				panBet.add(lblResult);
				
				JLabel lblFecha = new JLabel(""+bet.getEvent().getFecha());
				panBet.add(lblFecha);
				panBet.add(new JLabel());
				
				JLabel lblMonto = new JLabel("Monto: ");
				panBet.add(lblMonto);
				
				JLabel lblMonto2 = new JLabel("Bs. "+bet.getPredict().getMonto());
				lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
				panBet.add(lblMonto2);
			}
			
			panBasketball.add(panBet);
			panBasketball.add(new JSeparator());
		}
	}

}
