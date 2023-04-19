package view.suporte;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SuporteMenu extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuporteMenu dialog = new SuporteMenu();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public SuporteMenu() {
		setBounds(100, 100, 386, 471);
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 94, 161, 20);
		getContentPane().add(comboBox);
		
		JLabel lblSelecioneOProblema = new JLabel("Selecione o problema");
		lblSelecioneOProblema.setBounds(10, 69, 143, 14);
		getContentPane().add(lblSelecioneOProblema);
		
		JTextPane txtpnASoluoDo = new JTextPane();
		txtpnASoluoDo.setBounds(10, 220, 286, 75);
		getContentPane().add(txtpnASoluoDo);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 36, 161, 22);
		getContentPane().add(comboBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione o aparelho");
		lblNewLabel_1.setBounds(10, 11, 116, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Possiveis soluções");
		lblNewLabel_2.setBounds(10, 195, 97, 14);
		getContentPane().add(lblNewLabel_2);
		
		JButton bttnSubmit_1 = new JButton("Enviar");
		bttnSubmit_1.setBounds(10, 121, 89, 23);
		getContentPane().add(bttnSubmit_1);
		
		JButton btnNewButton = new JButton("\nEntre em contato");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Contato contato = new Contato();
			contato.setVisible(true);
			}
		});
		btnNewButton.setBounds(217, 387, 137, 35);
		getContentPane().add(btnNewButton);

	}

}
