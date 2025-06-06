package proyectoFinal_apuestas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaCrearApuesta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDescription;
	private JComboBox<String> comboBoxDeporte, comboBoxEventos;
	private int deporte;
	private JTextField textFieldCuota;

	public VentanaCrearApuesta() {
		ArrayList<EventoFutbol> eventsFut = EventoFutbol.leerEventosFutbolTxt(Archivos.archivosEventosFutbol);
		ArrayList<EventoBasketball> eventsBask = EventoBasketball.leerEventosBasketballTxt(Archivos.archivosEventosBasketball);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("UCBet - Crear Evento");
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(235, 245, 251));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));

		JPanel panTitulo = new JPanel();
		panTitulo.setBackground(new Color(45, 104, 184));
		JLabel lblTitulo = new JLabel("Crear Nueva Apuesta");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitulo.setForeground(new Color(255, 232, 0));
		panTitulo.add(lblTitulo);
		contentPane.add(panTitulo, BorderLayout.NORTH);

		JPanel panCentro = new JPanel(new GridLayout(6, 2, 10, 10));
		panCentro.setBackground(new Color(235, 245, 251));
		contentPane.add(panCentro, BorderLayout.CENTER);

		JLabel lblDeporte = new JLabel("Deporte:");
		panCentro.add(lblDeporte);
		comboBoxDeporte = new JComboBox<>(new String[] {"Fútbol", "Basketball"});
		comboBoxDeporte.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar deporte", "Fútbol", "Basketball"}));
		panCentro.add(comboBoxDeporte);
		comboBoxDeporte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deporte = comboBoxDeporte.getSelectedIndex();
				if (deporte == 1) {
					String[] listedEvents = new String[eventsFut.size()];
					for (int i = 0; i < listedEvents.length; i++) {
						listedEvents[i] = eventsFut.get(i).toString();
					}
					comboBoxEventos.setModel(new DefaultComboBoxModel<String>(listedEvents));
					
				}else if (deporte == 2) {
					String[] listedEvents = new String[eventsBask.size()];
					for (int i = 0; i < listedEvents.length; i++) {
						listedEvents[i] = eventsBask.get(i).toString();
					}
					comboBoxEventos.setModel(new DefaultComboBoxModel<String>(listedEvents));
				}else if (deporte==0) {
					comboBoxEventos.setModel(new DefaultComboBoxModel(new String[] {}));
				}
			}
		});

		JLabel lblEvento = new JLabel("Evento:");
		panCentro.add(lblEvento);
		
		comboBoxEventos = new JComboBox<String>();
		comboBoxEventos.setModel(new DefaultComboBoxModel(new String[] {}));
		panCentro.add(comboBoxEventos);
		
		JLabel lblTipo = new JLabel("Tipo de apuesta:");
		panCentro.add(lblTipo);
		
		JComboBox<String> comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione tipo", "1", "2", "3"}));
		panCentro.add(comboBoxTipo);
		comboBoxTipo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBoxTipo.getSelectedIndex()==1) {
					textFieldDescription.setText("Ganador o empate");
				} else if (comboBoxTipo.getSelectedIndex()==2){
					textFieldDescription.setText("Goles");
				}else if (comboBoxTipo.getSelectedIndex()==3) {
					textFieldDescription.setText("Estadísticas completas");
				}else if (comboBoxTipo.getSelectedIndex()==0) {
					textFieldDescription.setText("");
				}
				textFieldDescription.setEditable(false);
			}
		});
		
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		panCentro.add(lblDescripcion);
		textFieldDescription = new JTextField();
		panCentro.add(textFieldDescription);

		JLabel lblCuota = new JLabel("Cuota:");
		panCentro.add(lblCuota);
		
		textFieldCuota = new JTextField();
		panCentro.add(textFieldCuota);


		JPanel panBotones = new JPanel();
		panBotones.setBackground(new Color(235, 245, 251));
		JButton btnCrearApuesta = new JButton("Crear Apuesta");
		panBotones.add(btnCrearApuesta);
		contentPane.add(panBotones, BorderLayout.SOUTH);

		btnCrearApuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (deporte==1) {
					int indexFut = comboBoxEventos.getSelectedIndex();
					ApuestaFutbol bet = new ApuestaFutbol(textFieldDescription.getText(), Double.parseDouble(textFieldCuota.getText()), eventsFut.get(indexFut), comboBoxTipo.getSelectedIndex());
					if (bet.registrarApuestaFutbolTxt(Archivos.archivosApuestasFutbol)) {
						JOptionPane.showMessageDialog(btnCrearApuesta, "Apuesta registrada");
					} else {
						JOptionPane.showMessageDialog(btnCrearApuesta, "Fallo de registro");
					}
				} else if (deporte==2) {
					int indexBask = comboBoxEventos.getSelectedIndex();
					ApuestaBasketball bet = new ApuestaBasketball(textFieldDescription.getText(), Double.parseDouble(textFieldCuota.getText()), eventsBask.get(indexBask), comboBoxTipo.getSelectedIndex());
					if (bet.registrarApuestaBasketballTxt(Archivos.archivosApuestasBasketball)) {
						JOptionPane.showMessageDialog(btnCrearApuesta, "Apuesta registrada");
						
					} else {
						JOptionPane.showMessageDialog(btnCrearApuesta, "Fallo de registro");

					}
				}
				comboBoxDeporte.setSelectedIndex(0);
				comboBoxTipo.setSelectedIndex(0);
				textFieldCuota.setText("");
			}
		});
	}
}
