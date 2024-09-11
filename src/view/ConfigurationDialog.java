package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Configuration;
import util.ConfigurationController;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ConfigurationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_password;
	private JTextField textField_usuario;
	private JTextField textField_IP;
	
	private static boolean visibleWindow = true;
	// private List<JTextField> JtextFieldList = new ArrayList<JTextField>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfigurationDialog dialog = new ConfigurationDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(visibleWindow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfigurationDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTeste = new JLabel("Senha");
		lblTeste.setBounds(27, 57, 70, 15);
		contentPanel.add(lblTeste);
		{
			JLabel lblTeste_1 = new JLabel("Usuario");
			lblTeste_1.setBounds(27, 28, 70, 15);
			contentPanel.add(lblTeste_1);
		}
		
		textField_password = new JTextField();
		textField_password.setBounds(92, 55, 210, 19);
		contentPanel.add(textField_password);
		textField_password.setColumns(10);
		
		textField_usuario = new JTextField();
		textField_usuario.setColumns(10);
		textField_usuario.setBounds(92, 26, 210, 19);
		contentPanel.add(textField_usuario);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(27, 88, 70, 15);
		contentPanel.add(lblIp);
		
		textField_IP = new JTextField();
		textField_IP.setColumns(10);
		textField_IP.setBounds(92, 86, 210, 19);
		contentPanel.add(textField_IP);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"NoDatabase", "MySQL"}));
		comboBox.setBounds(156, 124, 145, 24);
		contentPanel.add(comboBox);
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.setBounds(208, 233, 72, 25);
		contentPanel.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConfigurationController c = new ConfigurationController();
					Configuration conf = c.getConfiguration();

					// if exists
					textField_IP.setText(conf.getIp());
					textField_usuario.setText(conf.getUser());
					textField_password.setText(conf.getPassword());
					// Configuration conf = new Configuration(String.valueOf(comboBox.getSelectedItem()), textField_IP.getText(), textField_usuario.getText() , textField_password.getText());
					
						// c.setConfiguration(conf);
					
					} catch (Exception IOE) {
						IOE.printStackTrace();
					}
			
		}});
		btnLoad.setActionCommand("OK");
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(288, 233, 54, 25);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConfigurationController controller = new ConfigurationController();
					Configuration conf = new Configuration();
					
					try {
						
						conf.setUser(textField_usuario.getText());
						conf.setPassword(textField_password.getText());
						conf.setIp(textField_IP.getText());
						
						controller.setConfiguration(conf);
						controller.writeConfig(conf);
						
						System.out.println(conf.getUser());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//validation
					//confirm configuration
					//call and set configuration
					//close
					
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(347, 233, 81, 25);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// validate if there's configured content
					// close
					
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
	}
}
