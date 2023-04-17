package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class Usuarios extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
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
	public Usuarios() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/UserIcon.png")));
		setTitle("Usuarios");
		setModal(true);
		setBounds(100, 100, 450, 376);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 26, 46, 28);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(20, 88, 46, 28);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(20, 141, 46, 28);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(20, 204, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(74, 32, 46, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(76, 94, 171, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(76, 147, 171, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			search();
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setIcon(new ImageIcon(Usuarios.class.getResource("/img/search.png")));
		btnNewButton.setBounds(342, 26, 64, 64);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setIcon(new ImageIcon(Usuarios.class.getResource("/img/erase.png")));
		btnNewButton_1.setBounds(342, 113, 64, 64);
		getContentPane().add(btnNewButton_1);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(76, 203, 171, 14);
		getContentPane().add(txtSenha);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(Usuarios.class.getResource("/img/plusIcon.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			add();
			}
		});
		btnNewButton_2.setBounds(342, 204, 64, 64);
		getContentPane().add(btnNewButton_2);

	}
	private void search() {
		//dica - testar o evento primeiro
		//System.out.println("teste do botão buscar");
		
		// Criar uma variável com a query (instrução do banco)
		String read = "select * from usuarios where nome = ?";
		//tratamento de exceções
		try {
			//abrir a conexão
			con = dao.conectar();
			//preparar a execução da query(instrução sql - CRUD Read)
			// O parâmetro 1 substitui a ? pelo conteúdo da caixa de texto
			pst = con.prepareStatement(read);
			pst.setString(1, txtNome.getText());
			// executar a query e buscar o resultado
			rs = pst.executeQuery();
			// uso da estrura if else para verificar se existe o contato
			// rs.next() -> se existir um contato no banco
			if (rs.next()) {
				//preencher as caixas de texto com as informações
				txtId.setText(rs.getString(1)); //1º ID
				txtNome.setText(rs.getString(2)); //2° NOME
				txtEmail.setText(rs.getString(3));//3° EMAIL
				txtSenha.setText(rs.getString(4));
			
			} else {
				//se não existir um contato no banco
				JOptionPane.showMessageDialog(null, "Contato inexistente");
			}
			// fechar a conexão (IMPORTANTE!)
			con.close();
		} catch (Exception e) {
			System.out.println(e);}
	} // fim do metodo buscar
	
	@SuppressWarnings("deprecation")
	private void add() {
		
		// condição
		if (txtNome.getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Nome obrigatorio.");
			txtNome.requestFocus();
		}else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Senha obrigatoria.");
			txtEmail.requestFocus();
		}else {
			
			// Comando crud create
			String create = "Insert into users(nome,fone,email) values ?,?,?";
			
			txtNome.getText();
			txtSenha.getText();
			txtEmail.getText();
		}
		
	}
		private void limparCampos() {
			txtId.setText(null);
			txtNome.setText(null);
			txtSenha.setText(null);
			txtEmail.setText(null);
		}//fim do método limparCampos()
}

