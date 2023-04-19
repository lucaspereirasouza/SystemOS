package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Contato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Contato dialog = new Contato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Contato() {
		setBounds(100, 100, 388, 370);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			textField_3 = new JTextField();
			textField_3.setBounds(10, 147, 326, 128);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Nome");
			lblNewLabel.setBounds(29, 13, 27, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(61, 10, 86, 20);
			textField.setColumns(10);
			contentPanel.add(textField);
		}
		{
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setBounds(29, 44, 24, 14);
			contentPanel.add(lblEmail);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(61, 41, 86, 20);
			textField_1.setColumns(10);
			contentPanel.add(textField_1);
		}
		{
			JLabel lblTelefone = new JLabel("Telefone");
			lblTelefone.setBounds(10, 75, 42, 14);
			contentPanel.add(lblTelefone);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(61, 72, 86, 20);
			textField_2.setColumns(10);
			contentPanel.add(textField_2);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Selecione o aparelho");
			lblNewLabel_1.setBounds(175, 11, 116, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setBounds(175, 36, 161, 22);
			contentPanel.add(comboBox_1);
		}
		{
			JLabel lblSelecioneOProblema = new JLabel("Selecione o problema");
			lblSelecioneOProblema.setBounds(175, 69, 143, 14);
			contentPanel.add(lblSelecioneOProblema);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(175, 94, 161, 20);
			contentPanel.add(comboBox);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Descreva em detalhes o problema:");
			lblNewLabel_2.setBounds(10, 122, 178, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JButton bttnSubmit_1 = new JButton("Enviar");
			bttnSubmit_1.setBounds(273, 297, 89, 23);
			getContentPane().add(bttnSubmit_1);
		}
	}

}
