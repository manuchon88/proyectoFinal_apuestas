package proyectoFinal_apuestas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaActualizarEventoBasketball extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombreLocal;
	private JTextField textFieldNombreVisitante;
	private JTextField textFieldPuntosLocal;
	private JTextField textFieldPuntosVisitante;
	private JTextField textFieldTriplesLocal;
	private JTextField textFieldTriplesVisitante;
	private JTextField textFieldFaltasLocal;
	private JTextField textFieldFaltasVisitante;
	private int index;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaActualizarEventoBasketball frame = new VentanaActualizarEventoBasketball();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaActualizarEventoBasketball() {
		ArrayList<EventoBasketball> eventsList = EventoBasketball.leerEventosBasketballTxt(Archivos.archivosEventosBasketball);
		setTitle("Actualizar Evento UCBet");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 501);
		contentPane = new JPanel();
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
		panel.add(panSubtitulo, BorderLayout.NORTH);
		
		JLabel lblSubtitulo = new JLabel("Actualizar Evento de Basketball");
		lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panSubtitulo.add(lblSubtitulo);
		
		JPanel panBotones = new JPanel();
		panel.add(panBotones, BorderLayout.SOUTH);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsList.get(index).setFaltas1(Integer.parseInt(textFieldFaltasLocal.getText()));
				eventsList.get(index).setFaltas2(Integer.parseInt(textFieldFaltasVisitante.getText()));
				eventsList.get(index).setPuntos1(Integer.parseInt(textFieldPuntosLocal.getText()));
				eventsList.get(index).setPuntos2(Integer.parseInt(textFieldPuntosVisitante.getText()));
				eventsList.get(index).setTriples1(Integer.parseInt(textFieldTriplesLocal.getText()));
				eventsList.get(index).setTriples2(Integer.parseInt(textFieldTriplesVisitante.getText()));
				if (EventoBasketball.reescribirEventosBasketballTxt(eventsList, Archivos.archivosEventosBasketball)) {
					JOptionPane.showMessageDialog(btnActualizar, "Evento actualizado con éxito");
					VentanaActualizarEventoBasketball.this.dispose();
				} else {
					JOptionPane.showMessageDialog(btnActualizar, "Error: No se pudo actualizar el evento");
				}
			}
		});
		
		panBotones.add(btnActualizar);
		
		JButton btnCambiarEvento = new JButton("Cambiar a Futbol");
		btnCambiarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaActualizarEventoFutbol frame = new VentanaActualizarEventoFutbol();
				frame.setVisible(true);
				VentanaActualizarEventoBasketball.this.dispose();
			}
		});
		panBotones.add(btnCambiarEvento);
		
		JPanel panCentro = new JPanel();
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
		
		JLabel lblPuntos = new JLabel("Puntos:");
		lblPuntos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPuntos.setBounds(20, 169, 109, 19);
		panCentro.add(lblPuntos);
		
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
		
		textFieldPuntosLocal = new JTextField();
		textFieldPuntosLocal.setColumns(10);
		textFieldPuntosLocal.setBounds(139, 170, 130, 20);
		panCentro.add(textFieldPuntosLocal);
		
		textFieldPuntosVisitante = new JTextField();
		textFieldPuntosVisitante.setColumns(10);
		textFieldPuntosVisitante.setBounds(315, 170, 130, 20);
		panCentro.add(textFieldPuntosVisitante);
		
		textFieldTriplesLocal = new JTextField();
		textFieldTriplesLocal.setColumns(10);
		textFieldTriplesLocal.setBounds(139, 216, 130, 20);
		panCentro.add(textFieldTriplesLocal);
		
		textFieldTriplesVisitante = new JTextField();
		textFieldTriplesVisitante.setColumns(10);
		textFieldTriplesVisitante.setBounds(315, 216, 130, 20);
		panCentro.add(textFieldTriplesVisitante);
		
		JLabel lblTriples = new JLabel("Triples:");
		lblTriples.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTriples.setBounds(20, 215, 109, 19);
		panCentro.add(lblTriples);
		
		JLabel lblFaltas = new JLabel("Faltas:");
		lblFaltas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFaltas.setBounds(20, 257, 109, 19);
		panCentro.add(lblFaltas);
		
		textFieldFaltasLocal = new JTextField();
		textFieldFaltasLocal.setColumns(10);
		textFieldFaltasLocal.setBounds(139, 258, 130, 20);
		panCentro.add(textFieldFaltasLocal);
		
		textFieldFaltasVisitante = new JTextField();
		textFieldFaltasVisitante.setColumns(10);
		textFieldFaltasVisitante.setBounds(315, 258, 130, 20);
		panCentro.add(textFieldFaltasVisitante);
		
		JComboBox comboBoxEventos = new JComboBox();
		comboBoxEventos.setBounds(139, 36, 306, 21);
		panCentro.add(comboBoxEventos);
		
		String[] events = new String[eventsList.size()+1];
		events[0]="Seleccionar Evento";
		for (int i = 1; i < events.length; i++) {
			events[i] = eventsList.get(i-1).toString();
		}
		comboBoxEventos.setModel(new DefaultComboBoxModel<String>(events));
		comboBoxEventos.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				index = comboBoxEventos.getSelectedIndex()-1;
				if (index==-1) {
					textFieldNombreLocal.setText("");
					textFieldNombreVisitante.setText("");
					textFieldPuntosLocal.setText("");
					textFieldPuntosVisitante.setText("");
					textFieldTriplesLocal.setText("");
					textFieldTriplesVisitante.setText("");
					textFieldFaltasLocal.setText("");
					textFieldFaltasVisitante.setText("");
					
				} else {
					EventoBasketball partido  = eventsList.get(index);
					textFieldNombreLocal.setText(partido.getEquipo1());
					textFieldNombreVisitante.setText(partido.getEquipo2());
					textFieldPuntosLocal.setText(""+partido.getPuntos1());
					textFieldPuntosVisitante.setText(""+partido.getPuntos2());
					textFieldTriplesLocal.setText(""+partido.getTriples1());
					textFieldTriplesVisitante.setText(""+partido.getTriples2());
					textFieldFaltasLocal.setText(""+partido.getFaltas1());
					textFieldFaltasVisitante.setText(""+partido.getFaltas2());
				}
			}
		});
		
	}
}
