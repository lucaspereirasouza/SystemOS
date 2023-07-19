package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Fornecedor extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fornecedor dialog = new Fornecedor();
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
	public Fornecedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedor.class.getResource("/img/fornecedor.png")));
		setResizable(false);
		setTitle("Fornecedor");
		setBounds(100, 100, 664, 434);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/fornecedor.png")));
		lblNewLabel.setBounds(546, 11, 64, 64);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("nome");
		lblNewLabel_1.setBounds(10, 48, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(66, 45, 167, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fone Principal");
		lblNewLabel_1_1.setBounds(10, 167, 75, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(95, 164, 86, 20);
		getContentPane().add(textField_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Email");
		lblNewLabel_1_1_1.setBounds(270, 113, 75, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(345, 110, 121, 20);
		getContentPane().add(textField_2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Bairro");
		lblNewLabel_1_1_2.setBounds(10, 217, 75, 14);
		getContentPane().add(lblNewLabel_1_1_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(95, 214, 86, 20);
		getContentPane().add(textField_3);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Complemento");
		lblNewLabel_1_1_2_1.setBounds(270, 160, 75, 14);
		getContentPane().add(lblNewLabel_1_1_2_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(343, 157, 86, 20);
		getContentPane().add(textField_4);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Cidade");
		lblNewLabel_1_1_2_2.setBounds(10, 256, 75, 14);
		getContentPane().add(lblNewLabel_1_1_2_2);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(95, 253, 86, 20);
		getContentPane().add(textField_5);
		
		JLabel lblNewLabel_1_1_2_2_1 = new JLabel("CEP");
		lblNewLabel_1_1_2_2_1.setBounds(270, 202, 75, 14);
		getContentPane().add(lblNewLabel_1_1_2_2_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(355, 199, 86, 20);
		getContentPane().add(textField_6);
		
		JLabel lblNewLabel_1_1_2_2_1_1 = new JLabel("UF");
		lblNewLabel_1_1_2_2_1_1.setBounds(270, 239, 75, 14);
		getContentPane().add(lblNewLabel_1_1_2_2_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(294, 235, 30, 22);
		getContentPane().add(comboBox);

	}
}
