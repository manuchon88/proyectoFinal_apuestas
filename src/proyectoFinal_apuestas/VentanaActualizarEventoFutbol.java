package proyectoFinal_apuestas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class VentanaActualizarEventoFutbol extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombreLocal;
	private JTextField textFieldNombreVisitante;
	private JTextField textFieldGolesLocal;
	private JTextField textFieldGolesVisitante;
	private JTextField textFieldAmarillasLocal;
	private JTextField textFieldAmarillasVisitante;
	private JTextField textFieldRojasLocal;
	private JTextField textFieldRojasVisitante;
	private int index;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaActualizarEventoFutbol frame = new VentanaActualizarEventoFutbol();
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
	public VentanaActualizarEventoFutbol() {
		ArrayList<EventoFutbol> eventsList = EventoFutbol.leerEventosFutbolTxt(Archivos.archivosEventosFutbol);
		setTitle("Actualizar Evento UCBet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(235, 245, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panTitulo = new JPanel();
		panTitulo.setBackground(new Color(45, 104, 184));
		contentPane.add(panTitulo, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("UCBet");
		lblTitulo.setForeground(new Color(255, 232, 0));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		panTitulo.add(lblTitulo);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panSubtitulo = new JPanel();
		panSubtitulo.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		panel.add(panSubtitulo, BorderLayout.NORTH);
		
		JLabel lblSubtitulo = new JLabel("Actualizar Evento de Fútbol");
		lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panSubtitulo.add(lblSubtitulo);
		
		JPanel panBotones = new JPanel();
		panBotones.setBackground(new Color(235, 245, 251));
		panel.add(panBotones, BorderLayout.SOUTH);
		
		JButton btnActualizar = new JButton("Actualizar");
		
		
		panBotones.add(btnActualizar);
		
		JButton btnCambiarEvento = new JButton("Cambiar a Basketball");
		btnCambiarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaActualizarEventoBasketball frame = new VentanaActualizarEventoBasketball();
				frame.setVisible(true);
				VentanaActualizarEventoFutbol.this.dispose();
			}
		});
		panBotones.add(btnCambiarEvento);
		
		JPanel panCentro = new JPanel();
		panCentro.setBackground(new Color(235, 245, 251));
		panel.add(panCentro, BorderLayout.CENTER);
		panCentro.setLayout(null);
		
		JLabel lblFecha = new JLabel("Eventos:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setBounds(20, 35, 109, 19);
		panCentro.add(lblFecha);
		
		JLabel lblEquipoLocal = new JLabel("Equipo Local:");
		lblEquipoLocal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEquipoLocal.setBounds(150, 83, 109, 19);
		panCentro.add(lblEquipoLocal);
		
		textFieldNombreLocal = new JTextField();
		textFieldNombreLocal.setBounds(139, 127, 130, 20);
		panCentro.add(textFieldNombreLocal);
		textFieldNombreLocal.setColumns(10);
		
		JLabel lblGoles = new JLabel("Goles:");
		lblGoles.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGoles.setBounds(20, 169, 109, 19);
		panCentro.add(lblGoles);
		
		JLabel lblEquipoVisitante = new JLabel("Equipo Visitante:");
		lblEquipoVisitante.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEquipoVisitante.setBounds(315, 83, 152, 19);
		panCentro.add(lblEquipoVisitante);
		
		textFieldNombreVisitante = new JTextField();
		textFieldNombreVisitante.setColumns(10);
		textFieldNombreVisitante.setBounds(315, 127, 130, 20);
		panCentro.add(textFieldNombreVisitante);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombres.setBounds(20, 128, 109, 19);
		panCentro.add(lblNombres);
		
		textFieldGolesLocal = new JTextField();
		textFieldGolesLocal.setColumns(10);
		textFieldGolesLocal.setBounds(139, 170, 130, 20);
		panCentro.add(textFieldGolesLocal);
		
		textFieldGolesVisitante = new JTextField();
		textFieldGolesVisitante.setColumns(10);
		textFieldGolesVisitante.setBounds(315, 170, 130, 20);
		panCentro.add(textFieldGolesVisitante);
		
		textFieldAmarillasLocal = new JTextField();
		textFieldAmarillasLocal.setColumns(10);
		textFieldAmarillasLocal.setBounds(139, 216, 130, 20);
		panCentro.add(textFieldAmarillasLocal);
		
		textFieldAmarillasVisitante = new JTextField();
		textFieldAmarillasVisitante.setColumns(10);
		textFieldAmarillasVisitante.setBounds(315, 216, 130, 20);
		panCentro.add(textFieldAmarillasVisitante);
		
		JLabel lblAmarillas = new JLabel("Amarillas:");
		lblAmarillas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAmarillas.setBounds(20, 215, 109, 19);
		panCentro.add(lblAmarillas);
		
		JLabel lblRojas = new JLabel("Rojas:");
		lblRojas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRojas.setBounds(20, 257, 109, 19);
		panCentro.add(lblRojas);
		
		textFieldRojasLocal = new JTextField();
		textFieldRojasLocal.setColumns(10);
		textFieldRojasLocal.setBounds(139, 258, 130, 20);
		panCentro.add(textFieldRojasLocal);
		
		textFieldRojasVisitante = new JTextField();
		textFieldRojasVisitante.setColumns(10);
		textFieldRojasVisitante.setBounds(315, 258, 130, 20);
		panCentro.add(textFieldRojasVisitante);
		
		JComboBox comboBoxEventos = new JComboBox();
		comboBoxEventos.setBounds(139, 36, 306, 21);
		panCentro.add(comboBoxEventos);
		
		String[] events = new String[eventsList.size()+1];
		events[0]="Seleccionar Evento";
		for (int i = 1; i < events.length; i++) {
			events[i] = eventsList.get(i-1).toString();
		}
		comboBoxEventos.setModel(new DefaultComboBoxModel<String>(events));
		
		JLabel lblTerminarEv = new JLabel("Terminar evento:");
		lblTerminarEv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTerminarEv.setBounds(20, 296, 163, 19);
		panCentro.add(lblTerminarEv);
		
		JToggleButton btnTerminar = new JToggleButton("Terminar Evento");
		btnTerminar.setBounds(182, 290, 216, 34);
		panCentro.add(btnTerminar);
		
		
		comboBoxEventos.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				index = comboBoxEventos.getSelectedIndex()-1;
				if (index==-1) {
					textFieldNombreLocal.setText("");
					textFieldNombreVisitante.setText("");
					textFieldGolesLocal.setText("");
					textFieldGolesVisitante.setText("");
					textFieldAmarillasLocal.setText("");
					textFieldAmarillasVisitante.setText("");
					textFieldRojasLocal.setText("");
					textFieldRojasVisitante.setText("");
					btnTerminar.setSelected(false);
				} else {
					EventoFutbol partido  = eventsList.get(index);
					textFieldNombreLocal.setText(partido.getEquipo1());
					textFieldNombreVisitante.setText(partido.getEquipo2());
					textFieldGolesLocal.setText(""+partido.getGoles1());
					textFieldGolesVisitante.setText(""+partido.getGoles2());
					textFieldAmarillasLocal.setText(""+partido.getAmarillas1());
					textFieldAmarillasVisitante.setText(""+partido.getAmarillas2());
					textFieldRojasLocal.setText(""+partido.getRojas1());
					textFieldRojasVisitante.setText(""+partido.getRojas2());
					btnTerminar.setSelected(partido.isTerminado());
				}
			}
		});
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsList.get(index).setAmarillas1(Integer.parseInt(textFieldAmarillasLocal.getText()));
				eventsList.get(index).setAmarillas2(Integer.parseInt(textFieldAmarillasVisitante.getText()));
				eventsList.get(index).setEquipo1(textFieldNombreLocal.getText());
				eventsList.get(index).setEquipo2(textFieldNombreVisitante.getText());
				eventsList.get(index).setGoles1(Integer.parseInt(textFieldGolesLocal.getText()));
				eventsList.get(index).setGoles2(Integer.parseInt(textFieldGolesVisitante.getText()));
				eventsList.get(index).setRojas1(Integer.parseInt(textFieldRojasLocal.getText()));
				eventsList.get(index).setRojas2(Integer.parseInt(textFieldRojasVisitante.getText()));
				if (btnTerminar.isSelected()) {
					eventsList.get(index).setTerminado(true);
				}else {
					eventsList.get(index).setTerminado(false);
				}
				
				if (EventoFutbol.reescribirEventosFutbolTxt(eventsList, Archivos.archivosEventosFutbol)) {
					JOptionPane.showMessageDialog(btnActualizar, "Evento actualizado con éxito");
					VentanaActualizarEventoFutbol.this.dispose();
				} else {
					JOptionPane.showMessageDialog(btnActualizar, "Error: No se pudo actualizar el evento");
				}
			}
		});
		
	}
}
