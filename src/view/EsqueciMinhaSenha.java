package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

public class EsqueciMinhaSenha extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EsqueciMinhaSenha dialog = new EsqueciMinhaSenha();
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
	public EsqueciMinhaSenha() {
		setBounds(100, 100, 547, 270);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SE LASCO, SE VIRA");
		lblNewLabel.setFont(new Font("Felix Titling", Font.BOLD | Font.ITALIC, 54));
		lblNewLabel.setBounds(10, 36, 502, 148);
		getContentPane().add(lblNewLabel);

	}

}
