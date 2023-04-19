package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 429, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Não dá sinal de vida", "Não liga", "Preso na tela de Inicialização", "Tela azul", "Tela preta"}));
		comboBox.setBounds(45, 119, 161, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblSelecioneOProblema = new JLabel("Selecione o problema");
		lblSelecioneOProblema.setBounds(45, 94, 143, 14);
		frame.getContentPane().add(lblSelecioneOProblema);
		
		JTextPane txtpnASoluoDo = new JTextPane();
		txtpnASoluoDo.setBounds(45, 245, 286, 75);
		frame.getContentPane().add(txtpnASoluoDo);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Computador", "Console", "Celular"}));
		comboBox_1.setBounds(45, 61, 161, 22);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione o aparelho");
		lblNewLabel_1.setBounds(45, 36, 116, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Possiveis soluções");
		lblNewLabel_2.setBounds(45, 220, 97, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton bttnSubmit_1 = new JButton("Enviar");
		bttnSubmit_1.setBounds(45, 146, 89, 23);
		frame.getContentPane().add(bttnSubmit_1);
		
		JButton btnNewButton = new JButton("\nEntre em contato");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contato contato = new Contato();
				Contato.setVisible(true);
			}
		});
		btnNewButton.setBounds(273, 378, 116, 69);
		frame.getContentPane().add(btnNewButton);
	}
	
}
