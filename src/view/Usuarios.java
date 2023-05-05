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
import util.Validador;

import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;

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
	private JTextField txtFone;
	private JButton bttnAdd;
	private JButton bttnRemove;
	private JButton bttnEditar;
	private JButton bttnBuscar;

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
		setBounds(100, 100, 450, 408);
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
		txtNome.setDocument(new Validador(20));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(76, 147, 171, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		bttnBuscar = new JButton("");
		getRootPane().setDefaultButton(bttnBuscar);
		bttnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			search();
			}
		
		});
		bttnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnBuscar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/search.png")));
		bttnBuscar.setBounds(342, 26, 64, 64);
		getContentPane().add(bttnBuscar);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setIcon(new ImageIcon(Usuarios.class.getResource("/img/erase.png")));
		btnNewButton_1.setBounds(20, 294, 64, 64);
		getContentPane().add(btnNewButton_1);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(76, 203, 171, 14);
		getContentPane().add(txtSenha);
		txtNome.setDocument(new Validador(20));
		
		bttnAdd = new JButton("");
		bttnAdd.setEnabled(false);
		bttnAdd.setIcon(new ImageIcon(Usuarios.class.getResource("/img/plusIcon.png")));
		bttnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			adicionar();
			}
		});
		bttnAdd.setBounds(342, 188, 64, 64);
		getContentPane().add(bttnAdd);
		
		bttnEditar = new JButton("");
		bttnEditar.setEnabled(false);
		bttnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			refresh();
			}
		});
		bttnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/refreshIcon.png")));
		bttnEditar.setBounds(342, 263, 64, 64);
		getContentPane().add(bttnEditar);
		
		JLabel lblNewLabel_3_1 = new JLabel("Fone");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_1.setBounds(20, 253, 46, 14);
		getContentPane().add(lblNewLabel_3_1);
		
		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(74, 252, 171, 20);
		getContentPane().add(txtFone);
		
		bttnRemove = new JButton("X");
		bttnRemove.setEnabled(false);
		bttnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			remove();
			}
		});
		bttnRemove.setFont(new Font("Tahoma", Font.PLAIN, 49));
		bttnRemove.setBounds(342, 110, 64, 64);
		getContentPane().add(bttnRemove);

	}
	private void search() {
		//dica - testar o evento primeiro
		//System.out.println("teste do botão buscar");
		
		// Criar uma variável com a query (instrução do banco)
		String read = "select * from contatos where nome = ?";
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
				txtFone.setText(rs.getString(5));
				//Validação
				bttnAdd.setEnabled(true);
				bttnBuscar.setEnabled(false);
				
			} else {
				//se não existir um contato no banco
				JOptionPane.showMessageDialog(null, "Contato inexistente");
				bttnAdd.setEnabled(true);
				bttnBuscar.setEnabled(false);
			
			}
			// fechar a conexão (IMPORTANTE!)
			con.close();
		} catch (Exception e) {
			System.out.println(e);}
	} // fim do metodo buscar
	
	@SuppressWarnings("deprecation")
	private void adicionar() {
		//
		
		// condição
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome obrigatorio.");
			txtNome.requestFocus();
		}else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Senha obrigatoria.");
			txtSenha.requestFocus();
		}else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email obrigatoria.");
			txtEmail.requestFocus();
		}
		else if (txtFone.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Fone obrigatorio.");
		txtEmail.requestFocus();
		
		}else {
			//adicionar contato
			//usar dao e pst via con
			try {
				
			con = dao.conectar();
			//estrutura dao
			//adicionar mensagem
			String create = "insert into contatos(nome,email,senha,fone) values(?,?,?,?)";
			pst = con.prepareStatement(create);
			// Lista dos contatos
			pst.setString(1,txtNome.getText()); 
			pst.setString(2,txtEmail.getText());
			pst.setString(3,txtSenha.getText());
			pst.setString(4,txtFone.getText());
			
			pst.executeUpdate(); // execute update
			JOptionPane.showMessageDialog(null, "Contato adicionado com sucesso");
			// limpar os campos
			limparCampos();
			// fechar a conexão com o banco
			con.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				// TODO: handle exception
			}
		}
		
	}
		private void limparCampos() {
			txtId.setText(null);
			txtNome.setText(null);
			txtSenha.setText(null);
			txtEmail.setText(null);
			txtFone.setText(null);
			
			bttnAdd.setEnabled(false);
			bttnRemove.setEnabled(false);
			bttnAdd.setEnabled(false);
			bttnEditar.setEnabled(false);
			
			bttnBuscar.setEnabled(true);
		}//fim do método limparCampos()
	@SuppressWarnings("deprecation")
	private void refresh() {
		if (txtNome.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha os campos");
		txtNome.requestFocus(); // Oque faz?
		}else if(txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos");
			txtEmail.requestFocus(); // Oque faz?
		}else if(txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos");
			txtSenha.requestFocus(); // Oque faz?
		}else {
			String update = "update contatos set nome=?,fone?,email=? where";
			try{
				con = dao.conectar();
				pst = con.prepareStatement(update);
				
				pst.setString(1, txtId.getText());
				pst.setString(2, txtNome.getText());
				pst.setString(3, txtEmail.getText());
				pst.setString(4, txtSenha.getText());
				pst.setString(5, txtFone.getText());
				
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados contato editados com sucesso.");
				limparCampos();
				con.close();
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	private void remove() {
		con = dao.conectar();
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?","Atenção!", JOptionPane.YES_NO_CANCEL_OPTION);
		if(confirma == JOptionPane.YES_OPTION) {
			try {
			String delete = "delete from contatos where id = ?;";
			con = dao.conectar();
			pst = con.prepareStatement(delete);
			pst.setString(1, txtId.getText());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuario removidos com sucesso");

			limparCampos();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			
		}
	}
}

