package view.login;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DockerEngineRedirectWindow extends JDialog{
	public DockerEngineRedirectWindow() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(69, 0, 60, 17);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(188, 35, 105, 27);
		getContentPane().add(btnNewButton_1);
	}
	private static final long serialVersionUID = 1L;
}
