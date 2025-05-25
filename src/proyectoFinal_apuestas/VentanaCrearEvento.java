package proyectoFinal_apuestas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEquipo1;
	private JTextField textFieldEquipo2;
	private JComboBox<String> comboBoxDeporte;
	private JComboBox<Integer> comboBoxDia, comboBoxMes, comboBoxAnio, comboBoxTorneo;

	public VentanaCrearEvento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("UCBet - Crear Evento");
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));

		JPanel panTitulo = new JPanel();
		panTitulo.setBackground(new Color(45, 104, 184));
		
		JLabel lblTitulo = new JLabel("Crear Nuevo Evento");
		lblTitulo.setForeground(new Color(255, 232, 0));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		panTitulo.add(lblTitulo);
		contentPane.add(panTitulo, BorderLayout.NORTH);

		JPanel panCentro = new JPanel(new GridLayout(7, 2, 10, 10));
		contentPane.add(panCentro, BorderLayout.CENTER);

		JLabel lblDeporte = new JLabel("Deporte:");
		panCentro.add(lblDeporte);
		comboBoxDeporte = new JComboBox<>(new String[] {"Seleccionar deporte", "Fútbol", "Basketball"});
		panCentro.add(comboBoxDeporte);
		comboBoxDeporte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBoxDeporte.getSelectedIndex()==1) {
					comboBoxTorneo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar torneo", "Champions league", "Libertadores", "Premier league Boliviana", "La Liga", "Serie A", "Premier League", "Amistoso"}));					
				}else if (comboBoxDeporte.getSelectedIndex()==2) {
					comboBoxTorneo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar torneo", "NBA", "Euro League", "ACB", "FIBA", "NCAA", "Amistoso"}));	
				}else {
					comboBoxTorneo.setModel(new DefaultComboBoxModel(new String[] {""}));						
				}
				
			}
		});

		JLabel lblEquipo1 = new JLabel("Equipo 1:");
		panCentro.add(lblEquipo1);
		textFieldEquipo1 = new JTextField();
		panCentro.add(textFieldEquipo1);

		JLabel lblEquipo2 = new JLabel("Equipo 2:");
		panCentro.add(lblEquipo2);
		textFieldEquipo2 = new JTextField();
		panCentro.add(textFieldEquipo2);
			
		JLabel lblTorneo = new JLabel("Torneo:");
		panCentro.add(lblTorneo);
		
		comboBoxTorneo = new JComboBox<Integer>();
		panCentro.add(comboBoxTorneo);

		JLabel lblDia = new JLabel("Día:");
		panCentro.add(lblDia);
		comboBoxDia = new JComboBox<>();
		for (int i = 1; i <= 31; i++) comboBoxDia.addItem(i);
		panCentro.add(comboBoxDia);

		JLabel lblMes = new JLabel("Mes:");
		panCentro.add(lblMes);
		comboBoxMes = new JComboBox<>();
		for (int i = 1; i <= 12; i++) comboBoxMes.addItem(i);
		panCentro.add(comboBoxMes);
		
		JLabel lblAnio = new JLabel("Año:");
		panCentro.add(lblAnio);
		comboBoxAnio = new JComboBox<>();
		panCentro.add(comboBoxAnio);
		for (int i = 2025; i <= 2030; i++) comboBoxAnio.addItem(i);
		
		JPanel panBotones = new JPanel();
		JButton btnCrearEvento = new JButton("Crear Evento");
		panBotones.add(btnCrearEvento);
		contentPane.add(panBotones, BorderLayout.SOUTH);

		btnCrearEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eq1 = textFieldEquipo1.getText().trim();
				String eq2 = textFieldEquipo2.getText().trim();
				int tor = comboBoxTorneo.getSelectedIndex()-1;
				int d = (int) comboBoxDia.getSelectedItem();
				int m = (int) comboBoxMes.getSelectedItem();
				int y = (int) comboBoxAnio.getSelectedItem();

				if (eq1.isEmpty() || eq2.isEmpty()) {
					JOptionPane.showMessageDialog(btnCrearEvento, "Debe ingresar nombres de ambos equipos.");
					return;
				}

				boolean exito = false;
				if (comboBoxDeporte.getSelectedIndex()==1) {
					EventoFutbol ef = new EventoFutbol(y, m, d, eq1, eq2, tor);
					exito = ef.registrarEventosFutbolTxt(Archivos.archivosEventosFutbol);
				}else if (comboBoxDeporte.getSelectedIndex()==2) {
					EventoBasketball eb = new EventoBasketball(y, m, d, eq1, eq2, tor);
					exito = eb.registrarEventoBasketballTxt(Archivos.archivosEventosBasketball);
				}
				

				if (exito) {
					JOptionPane.showMessageDialog(btnCrearEvento, "Evento creado exitosamente.");
					dispose();
				} else {
					JOptionPane.showMessageDialog(btnCrearEvento, "Error al registrar evento.");
				}
			}
		});
	}
}
