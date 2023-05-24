package view.OS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;
import util.Validador;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;

public class ClienteMain extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtCPF;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtNascimento;

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private JTextArea txtAnotacao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClienteMain dialog = new ClienteMain();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClienteMain() {
		setBounds(100, 100, 618, 463);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Gravar dados");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			adicionar();
			}
		});
		btnNewButton.setBounds(21, 319, 150, 23);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(21, 38, 221, 16);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblNewLabel);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpf.setBounds(296, 99, 110, 16);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblCpf);
		
		txtNome = new JTextField();
		txtNome.setBounds(21, 65, 221, 23);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndereo.setBounds(266, 38, 321, 16);
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblEndereo);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(266, 65, 307, 23);
		txtEndereco.setColumns(10);
		getContentPane().add(txtEndereco);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(288, 126, 118, 23);
		txtCPF.setColumns(10);
		getContentPane().add(txtCPF);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefone.setBounds(21, 160, 194, 16);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(21, 187, 194, 23);
		txtTelefone.setColumns(10);
		getContentPane().add(txtTelefone);
		
		JButton btnCancelarEVoltar = new JButton("Cancelar");
		btnCancelarEVoltar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelarEVoltar.setBounds(437, 389, 150, 23);
		getContentPane().add(btnCancelarEVoltar);
		
		txtAnotacao = new JTextArea();
		txtAnotacao.setLineWrap(true);
		txtAnotacao.setBackground(Color.WHITE);
		txtAnotacao.setBounds(225, 215, 307, 157);
		getContentPane().add(txtAnotacao);
		
		JLabel lblAnotaes = new JLabel("Anotações");
		lblAnotaes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnotaes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAnotaes.setBounds(225, 188, 272, 16);
		getContentPane().add(lblAnotaes);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnImprimir.setEnabled(false);
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnImprimir.setBounds(225, 389, 150, 23);
		getContentPane().add(btnImprimir);
		
		JLabel oiergjgn = new JLabel("");
		oiergjgn.setHorizontalAlignment(SwingConstants.CENTER);
		oiergjgn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		oiergjgn.setBounds(21, 99, 180, 16);
		getContentPane().add(oiergjgn);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(21, 126, 250, 23);
		getContentPane().add(txtEmail);
		
		JLabel lblEndereo_1_1 = new JLabel("Nascimento");
		lblEndereo_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndereo_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo_1_1.setBounds(422, 99, 151, 16);
		getContentPane().add(lblEndereo_1_1);
		
		txtNascimento = new JTextField();
		txtNascimento.setColumns(10);
		txtNascimento.setBounds(422, 126, 150, 23);
		getContentPane().add(txtNascimento);
		
		txtNome.setDocument(new Validador(30));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(21, 99, 180, 16);
		getContentPane().add(lblEmail);
		
		txtEndereco.setDocument(new Validador(40));
		txtCPF.setDocument(new Validador(11));
		txtTelefone.setDocument(new Validador(18));
		txtEmail.setDocument(new Validador(30));
		
		//Formatar para dd/mm/aaaa e enviar em formato ddmmaaaa
		txtNascimento.setDocument(new Validador(10));
		
		JButton btnEditarDados = new JButton("Editar dados");
		btnEditarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			editar();}
		});
		btnEditarDados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditarDados.setBounds(21, 353, 150, 23);
		getContentPane().add(btnEditarDados);
		
		JButton btnNewButton_1_1 = new JButton("Remover dados");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			remover();}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1.setBounds(21, 387, 150, 23);
		getContentPane().add(btnNewButton_1_1);
		
		JButton btnPesquisarDados = new JButton("Pesquisar dados");
		btnPesquisarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			pesquisar();
			}
		});
		btnPesquisarDados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPesquisarDados.setBounds(21, 285, 150, 23);
		getContentPane().add(btnPesquisarDados);
		
	
	}
	private void limparCampos() {
		txtNome.setText(null);
		txtEndereco.setText(null);
		txtCPF.setText(null);
		txtTelefone.setText(null);
		txtNascimento.setText(null);
		txtAnotacao.setText(null);
		txtEmail.setText(null);
	}
	
	private void adicionar() {
		//
//		String capturaSenha = new String(txtSenha.getPassword());
		
		// condição
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome obrigatorio.");
			txtNome.requestFocus();
		}else if(txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Endereço obrigatorio.");
			txtNome.requestFocus();
		}else if(txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email obrigatorio.");
			txtNome.requestFocus();
		}else if(txtCPF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "CPF obrigatorio.");
			txtNome.requestFocus();
		}else if(txtNascimento.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Data de nascimento obrigatorio.");
				txtNome.requestFocus();
		}else if(txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Telefonius obrigatorius.");
			txtNome.requestFocus();	
		}else {

			try {
				
			con = dao.conectar();
			String create = "insert into clientes(nome,endereco,cpf,email,nascimento,telefone,anotacoes) values(?,?,?,?,?,?,?)";
			
			pst = con.prepareStatement(create);
			
			pst.setString(1,txtNome.getText());
			pst.setString(2,txtEndereco.getText());
			pst.setString(3,txtCPF.getText());
			pst.setString(4,txtEmail.getText());
			pst.setString(5,txtNascimento.getText());
			pst.setString(6,txtTelefone.getText());
			pst.setString(7,txtAnotacao.getText());
			
			
			pst.executeUpdate(); // execute update
			JOptionPane.showMessageDialog(null, "Cliente gravado com sucesso");
			// limpar os campos
			limparCampos();
			// fechar a conexão com o banco
			con.close();
			}catch(SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "Já existe uma conta com esse login, tente com outro nome.");
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				// TODO: handle exception
			}
		}
	}
	
	
	private void remover() {
		con = dao.conectar();
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão dos dados desse cliente?","Atenção!", JOptionPane.YES_NO_OPTION);
		if(confirma == JOptionPane.YES_OPTION) {
			try {
				
			String delete = "delete from clientes where nome = ?;";
			
			con = dao.conectar();
			pst = con.prepareStatement(delete);
			pst.setString(1, txtNome.getText());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dados do cliente removidos com sucesso");

			limparCampos();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			
		}
	}
	private void pesquisar() {
		//dica - testar o evento primeiro
		//System.out.println("teste do botão buscar");
		
		// Criar uma variável com a query (instrução do banco)
		String read = "select * from clientes where nome = ?";
		String Value = "      ";
		//tratamento de exceções
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtNome.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtNome.setText(rs.getString(2)); 
				txtEndereco.setText(rs.getString(3));
				txtCPF.setText(rs.getString(4));
				txtEmail.setText(rs.getString(6));
				txtNascimento.setText(rs.getString(7));
				txtTelefone.setText(rs.getString(5));
				txtAnotacao.setText(rs.getString(8));
				

			} else {
				JOptionPane.showMessageDialog(null, "Cliente inexistente");
				
//				bttnBuscar.setEnabled(false);
			
			}
			// fechar a conexão (IMPORTANTE!)
			con.close();
		} catch (Exception e) {
			System.out.println(e);}
	} // fim do metodo buscar
	private void editar() {
		
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome obrigatorio.");
			txtNome.requestFocus();
		}else if(txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Endereço obrigatorio.");
			txtNome.requestFocus();
		}else if(txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email obrigatorio.");
			txtNome.requestFocus();
		}else if(txtCPF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "CPF obrigatorio.");
			txtNome.requestFocus();
		}else if(txtNascimento.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Data de nascimento obrigatorio.");
				txtNome.requestFocus();
		}else if(txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Telefonius obrigatorius.");
			txtNome.requestFocus();	
		}else {
			String update = "update clientes set nome=?,endereco=?,cpf=?,email=?,nascimento=?,telefone=?,anotacoes=?";
			try{
				con = dao.conectar();
				pst = con.prepareStatement(update);
				
				pst.setString(1,txtNome.getText());
				pst.setString(2,txtEndereco.getText());
				pst.setString(3,txtCPF.getText());
				pst.setString(4,txtEmail.getText());
				pst.setString(5,txtNascimento.getText());
				pst.setString(6,txtTelefone.getText());
				pst.setString(7,txtAnotacao.getText());
				
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
}

