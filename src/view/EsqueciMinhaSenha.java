package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import javax.swing.JTextField;

import model.DAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EsqueciMinhaSenha extends JDialog {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	
	public JTextField txtNome;
	public JTextField txtSenha;
	private JTextField txtId;

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
		
		setBounds(100, 100, 395, 219);
		getContentPane().setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(123, 55, 86, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(123, 31, 86, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(123, 116, 86, 14);
		getContentPane().add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(123, 149, 86, 20);
		getContentPane().add(txtSenha);
		
		JLabel lblNewLabel_1 = new JLabel("Insira sua nova senha:");
		lblNewLabel_1.setBounds(10, 91, 117, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton Editar = new JButton("Editar senha");
		Editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(txtNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "O nome não pode estar vazio");
				}else if(txtSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "A senha não pode estar vazia");
				}else if(txtId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "O usuario deve ser encontrado");
				}else {
					try {
						String comando = "update usuarios set senha=? where id=?;";
						con = dao.conectar();
						pst = con.prepareStatement(comando);
						pst.setString(1, txtSenha.getText());
						pst.setString(2, txtId.getText());
					
					} catch (SQLException SQLe) {
						
						// TODO: handle exception
						SQLe.printStackTrace();
					}
				}
			
			}
		});
		Editar.setToolTipText("Editar senha");
		Editar.setBounds(254, 148, 115, 21);
		getContentPane().add(Editar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(339, 11, 30, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

	}
}
