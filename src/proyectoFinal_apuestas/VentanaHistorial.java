package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private ArbolHistorialApuesta arbol_futbol;
	private ArbolHistorialApuesta arbol_basket;
	private JComboBox<Integer> cbDesdeDia;
	private JComboBox<Integer> cbDesdeMes;
	private JComboBox<Integer> cbDesdeAnio;
	private JComboBox<Integer> cbHastaDia;
	private JComboBox<Integer> cbHastaMes;
	private JComboBox<Integer> cbHastaAnio;
	
	private JPanel panFutbol;
	private JPanel panBasketball;

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

		// TERCERA PRESENTACIÓN - ESTRUCTURA DE DATOS
		// Árbol para apuestas de FÚTBOL
		arbol_futbol = new ArbolHistorialApuesta();
		for (int i = 0; i < HistFutbol.size(); i++) {
			ApuestaFutbol bet = HistFutbol.get(i);
			arbol_futbol.insertar(bet.getEvent().getFecha(), bet);
		}

		// Árbol para apuestas de BASKETBALL
		arbol_basket = new ArbolHistorialApuesta();
		for (int i = 0; i < HistBasketball.size(); i++) {
			ApuestaBasketball bet = HistBasketball.get(i);
			arbol_basket.insertar(bet.getEvent().getFecha(), bet);
		}

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
		lblSaldo.setText("Bs. " + user.getSaldo());
		lblSaldo.setForeground(new Color(255, 255, 255));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		panHeadDer.add(lblSaldo);

		JButton btnPerfil = new JButton();
		btnPerfil.setText(user.getNombre());
		btnPerfil.setBackground(new Color(45, 104, 184));
		btnPerfil.setForeground(new Color(255, 255, 255));
		panHeadDer.add(btnPerfil);

		JPanel panCenter = new JPanel(new GridLayout(1, 2));
		panPrincipal.add(panCenter, BorderLayout.CENTER);

		panFutbol = new JPanel();
		panFutbol.setLayout(new BoxLayout(panFutbol, BoxLayout.Y_AXIS));
		JScrollPane ScrollFutbol = new JScrollPane(panFutbol);
		ScrollFutbol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollFutbol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panCenter.add(ScrollFutbol);

		panBasketball = new JPanel();
		panBasketball.setLayout(new BoxLayout(panBasketball, BoxLayout.Y_AXIS));
		JScrollPane ScrollBasketball = new JScrollPane(panBasketball);
		ScrollBasketball.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollBasketball.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panCenter.add(ScrollBasketball);

		// ------------------- PANEL DE FILTROS -------------------

		JPanel panFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER));
		cbDesdeDia = new JComboBox<Integer>();
		cbDesdeMes = new JComboBox<Integer>();
		cbDesdeAnio = new JComboBox<Integer>();

		cbHastaDia = new JComboBox<Integer>();
		cbHastaMes = new JComboBox<Integer>();
		cbHastaAnio = new JComboBox<Integer>();

		for (int d = 1; d <= 31; d++) {
			cbDesdeDia.addItem(d);
			cbHastaDia.addItem(d);
		}

		for (int m = 1; m <= 12; m++) {
			cbDesdeMes.addItem(m);
			cbHastaMes.addItem(m);
		}

		int anioActual = LocalDate.now().getYear();
		for (int a = 2000; a <= anioActual; a++) {
			cbDesdeAnio.addItem(a);
			cbHastaAnio.addItem(a);
		}

		cbHastaDia.setSelectedItem(LocalDate.now().getDayOfMonth());
		cbHastaMes.setSelectedItem(LocalDate.now().getMonthValue());
		cbHastaAnio.setSelectedItem(anioActual);

		cbDesdeDia.setSelectedItem(LocalDate.now().getDayOfMonth());
		cbDesdeMes.setSelectedItem(LocalDate.now().getMonthValue());
		cbDesdeAnio.setSelectedItem(anioActual);

		JPanel panDesdeFechas = new JPanel(new GridLayout(2, 3, 5, 2));
		panDesdeFechas.add(new JLabel("Día"));
		panDesdeFechas.add(new JLabel("Mes"));
		panDesdeFechas.add(new JLabel("Año"));
		panDesdeFechas.add(cbDesdeDia);
		panDesdeFechas.add(cbDesdeMes);
		panDesdeFechas.add(cbDesdeAnio);

		JPanel panHastaFechas = new JPanel(new GridLayout(2, 3, 5, 2));
		panHastaFechas.add(new JLabel("Día"));
		panHastaFechas.add(new JLabel("Mes"));
		panHastaFechas.add(new JLabel("Año"));
		panHastaFechas.add(cbHastaDia);
		panHastaFechas.add(cbHastaMes);
		panHastaFechas.add(cbHastaAnio);

		JLabel lblDesde = new JLabel("Desde: ");
		JLabel lblHasta = new JLabel("Hasta: ");
		JButton btnFiltrar = new JButton("Filtrar");

		panFiltros.add(lblDesde);
		panFiltros.add(panDesdeFechas);
		panFiltros.add(lblHasta);
		panFiltros.add(panHastaFechas);
		panFiltros.add(btnFiltrar);

		panPrincipal.add(panFiltros, BorderLayout.SOUTH);

		mostrarHistorialEnRango(LocalDate.MIN, LocalDate.MAX);

		// FILTRAR

		btnFiltrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int dDesde = (Integer) cbDesdeDia.getSelectedItem();
					int mDesde = (Integer) cbDesdeMes.getSelectedItem();
					int aDesde = (Integer) cbDesdeAnio.getSelectedItem();

					int dHasta = (Integer) cbHastaDia.getSelectedItem();
					int mHasta = (Integer) cbHastaMes.getSelectedItem();
					int aHasta = (Integer) cbHastaAnio.getSelectedItem();

					LocalDate desde = LocalDate.of(aDesde, mDesde, dDesde);
					LocalDate hasta = LocalDate.of(aHasta, mHasta, dHasta);

					mostrarHistorialEnRango(desde, hasta);

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
	}

	private void mostrarHistorialEnRango(LocalDate desde, LocalDate hasta) {
		// Limpiar paneles para el filtrado
		panFutbol.removeAll();
		panBasketball.removeAll();

		ArrayList<Apuesta> listaFut = new ArrayList<Apuesta>();
		ArrayList<Apuesta> listaBask = new ArrayList<Apuesta>();

		arbol_futbol.llenarListaOrdenadaDescRango(desde, hasta, listaFut);
		arbol_basket.llenarListaOrdenadaDescRango(desde, hasta, listaBask);

		String[] torneosFut = { "Champions league", "Libertadores", "Premier league Boliviana", "La Liga", "Serie A", "Premier League", "Amistoso" };

		for (Apuesta ap : listaFut) {
			ApuestaFutbol bet = (ApuestaFutbol) ap;
			String part = torneosFut[bet.getEvent().getTorneo()];

			JPanel panBet = new JPanel(new GridLayout(5, 2));
			panBet.setPreferredSize(new Dimension(350, 120));
			panBet.setMaximumSize(new Dimension(350, 120));
			panBet.setMinimumSize(new Dimension(350, 120));
			panBet.setBorder(new EmptyBorder(5, 5, 5, 5));

			if (bet.getEvent().isTerminado()) {
				if (bet.isGanador()) {
					panBet.setLayout(new GridLayout(6, 2));
					panBet.setPreferredSize(new Dimension(350, 130));
					panBet.setMaximumSize(new Dimension(350, 130));
					panBet.setMinimumSize(new Dimension(350, 130));
					panBet.setBorder(new EmptyBorder(5, 5, 5, 5));

					JLabel lblDescrip = new JLabel(bet.getDescripcion());
					lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblDescrip.setForeground(new Color(147, 255, 51));
					panBet.add(lblDescrip);

					JLabel lblCuota = new JLabel("" + bet.getCuota());
					lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblCuota);

					JLabel lblTorneo = new JLabel(part);
					panBet.add(lblTorneo);
					panBet.add(new JLabel());

					JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1() + " vs. " + bet.getEvent().getEquipo2());
					lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
					panBet.add(lblMatch);
					JLabel lblResult = new JLabel(bet.getEvent().getGoles1() + ":" + bet.getEvent().getGoles2());
					lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblResult.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblResult);

					JLabel lblFecha = new JLabel("" + bet.getEvent().getFecha());
					panBet.add(lblFecha);
					panBet.add(new JLabel());

					JLabel lblMonto = new JLabel("Monto: ");
					panBet.add(lblMonto);

					JLabel lblMonto2 = new JLabel("Bs. " + bet.getPredict().getMonto());
					lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblMonto2);

					JLabel lblGanancia = new JLabel("Ganancia: ");
					panBet.add(lblGanancia);

					JLabel lblGanancia2 = new JLabel("Bs. " + (bet.getPredict().getMonto() * bet.getCuota()));
					lblGanancia2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblGanancia2);

				} else {
					JLabel lblDescrip = new JLabel(bet.getDescripcion());
					lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblDescrip.setForeground(new Color(214, 0, 0));
					panBet.add(lblDescrip);

					JLabel lblCuota = new JLabel("" + bet.getCuota());
					lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblCuota);

					JLabel lblTorneo = new JLabel(part);
					panBet.add(lblTorneo);
					panBet.add(new JLabel());

					JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1() + " vs. " + bet.getEvent().getEquipo2());
					lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
					panBet.add(lblMatch);
					JLabel lblResult = new JLabel(bet.getEvent().getGoles1() + ":" + bet.getEvent().getGoles2());
					lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblResult.setHorizontalAlignment(SwingConstants.CENTER);

					panBet.add(lblResult);

					JLabel lblFecha = new JLabel("" + bet.getEvent().getFecha());
					panBet.add(lblFecha);
					panBet.add(new JLabel());

					JLabel lblMonto = new JLabel("Monto: ");
					panBet.add(lblMonto);

					JLabel lblMonto2 = new JLabel("Bs. " + bet.getPredict().getMonto());
					lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblMonto2);
				}
			} else {
				JLabel lblDescrip = new JLabel(bet.getDescripcion());
				lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblDescrip.setForeground(new Color(2, 124, 213));
				panBet.add(lblDescrip);

				JLabel lblCuota = new JLabel("" + bet.getCuota());
				lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
				panBet.add(lblCuota);

				JLabel lblTorneo = new JLabel(part);
				panBet.add(lblTorneo);
				panBet.add(new JLabel());

				JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1() + " vs. " + bet.getEvent().getEquipo2());
				lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
				panBet.add(lblMatch);
				JLabel lblResult = new JLabel(bet.getEvent().getGoles1() + ":" + bet.getEvent().getGoles2());
				lblResult.setHorizontalAlignment(SwingConstants.CENTER);
				lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
				panBet.add(lblResult);

				JLabel lblFecha = new JLabel("" + bet.getEvent().getFecha());
				panBet.add(lblFecha);
				panBet.add(new JLabel());

				JLabel lblMonto = new JLabel("Monto: ");
				panBet.add(lblMonto);

				JLabel lblMonto2 = new JLabel("Bs. " + bet.getPredict().getMonto());
				lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
				panBet.add(lblMonto2);
			}

			panFutbol.add(panBet);
			panFutbol.add(new JSeparator());
		}

		// -------- BASKETBALL --------
		String[] torneosBask = { "NBA", "Euro League", "ACB", "FIBA", "NCAA", "Amistoso" };

		for (Apuesta ap : listaBask) {
			ApuestaBasketball bet = (ApuestaBasketball) ap;
			String part = torneosBask[bet.getEvent().getTorneo()];

			JPanel panBet = new JPanel(new GridLayout(5, 2));
			panBet.setPreferredSize(new Dimension(350, 120));
			panBet.setMaximumSize(new Dimension(350, 120));
			panBet.setMinimumSize(new Dimension(350, 120));
			panBet.setBorder(new EmptyBorder(5, 5, 5, 5));

			if (bet.getEvent().isTerminado()) {
				if (bet.isGanador()) {
					panBet.setLayout(new GridLayout(6, 2));
					panBet.setPreferredSize(new Dimension(350, 130));
					panBet.setMaximumSize(new Dimension(350, 130));
					panBet.setMinimumSize(new Dimension(350, 130));
					panBet.setBorder(new EmptyBorder(5, 5, 5, 5));

					JLabel lblDescrip = new JLabel(bet.getDescripcion());
					lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblDescrip.setForeground(new Color(147, 255, 51));
					panBet.add(lblDescrip);

					JLabel lblCuota = new JLabel("" + bet.getCuota());
					lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblCuota);

					JLabel lblTorneo = new JLabel(part);
					panBet.add(lblTorneo);
					panBet.add(new JLabel());

					JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1() + " vs. " + bet.getEvent().getEquipo2());
					lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
					panBet.add(lblMatch);
					JLabel lblResult = new JLabel(bet.getEvent().getPuntos1() + ":" + bet.getEvent().getPuntos2());
					lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblResult.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblResult);

					JLabel lblFecha = new JLabel("" + bet.getEvent().getFecha());
					panBet.add(lblFecha);
					panBet.add(new JLabel());

					JLabel lblMonto = new JLabel("Monto: ");
					panBet.add(lblMonto);

					JLabel lblMonto2 = new JLabel("Bs. " + bet.getPredict().getMonto());
					lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblMonto2);

					JLabel lblGanancia = new JLabel("Ganancia: ");
					panBet.add(lblGanancia);

					JLabel lblGanancia2 = new JLabel("Bs. " + (bet.getPredict().getMonto() * bet.getCuota()));
					lblGanancia2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblGanancia2);
				} else {
					JLabel lblDescrip = new JLabel(bet.getDescripcion());
					lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblDescrip.setForeground(new Color(214, 0, 0));
					panBet.add(lblDescrip);

					JLabel lblCuota = new JLabel("" + bet.getCuota());
					lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblCuota);

					JLabel lblTorneo = new JLabel(part);
					panBet.add(lblTorneo);
					panBet.add(new JLabel());

					JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1() + " vs. " + bet.getEvent().getEquipo2());
					lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
					panBet.add(lblMatch);
					JLabel lblResult = new JLabel(bet.getEvent().getPuntos1() + ":" + bet.getEvent().getPuntos2());
					lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblResult.setHorizontalAlignment(SwingConstants.CENTER);

					panBet.add(lblResult);

					JLabel lblFecha = new JLabel("" + bet.getEvent().getFecha());
					panBet.add(lblFecha);
					panBet.add(new JLabel());

					JLabel lblMonto = new JLabel("Monto: ");
					panBet.add(lblMonto);

					JLabel lblMonto2 = new JLabel("Bs. " + bet.getPredict().getMonto());
					lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
					panBet.add(lblMonto2);
				}
			} else {
				JLabel lblDescrip = new JLabel(bet.getDescripcion());
				lblDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblDescrip.setForeground(new Color(2, 124, 213));
				panBet.add(lblDescrip);

				JLabel lblCuota = new JLabel("" + bet.getCuota());
				lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
				panBet.add(lblCuota);

				JLabel lblTorneo = new JLabel(part);
				panBet.add(lblTorneo);
				panBet.add(new JLabel());

				JLabel lblMatch = new JLabel(bet.getEvent().getEquipo1() + " vs. " + bet.getEvent().getEquipo2());
				lblMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
				panBet.add(lblMatch);
				JLabel lblResult = new JLabel(bet.getEvent().getPuntos1() + ":" + bet.getEvent().getPuntos2());
				lblResult.setHorizontalAlignment(SwingConstants.CENTER);
				lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
				panBet.add(lblResult);

				JLabel lblFecha = new JLabel("" + bet.getEvent().getFecha());
				panBet.add(lblFecha);
				panBet.add(new JLabel());

				JLabel lblMonto = new JLabel("Monto: ");
				panBet.add(lblMonto);

				JLabel lblMonto2 = new JLabel("Bs. " + bet.getPredict().getMonto());
				lblMonto2.setHorizontalAlignment(SwingConstants.CENTER);
				panBet.add(lblMonto2);
			}

			panBasketball.add(panBet);
			panBasketball.add(new JSeparator());
		}

		panFutbol.revalidate();
		panFutbol.repaint();
		panBasketball.revalidate();
		panBasketball.repaint();
	}
}
