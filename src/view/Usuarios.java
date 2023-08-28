package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.DAO;
import util.Validador;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import javax.swing.text.Document;

import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

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
	private JPasswordField txtSenha;
	private JButton bttnAdd;
	private JButton bttnRemove;
	private JButton bttnEditar;
	private JButton bttnBuscar;
	private JLabel lblNewLabel_4;
	private JTextField txtLogin;
	private JScrollPane scrollPane;
	private JList listaUsuarios;
	private JComboBox cbPerfil;
	private JCheckBox checkSenha;

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
		getContentPane().setBackground(SystemColor.menu);
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
			}
		});
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/UserIcon.png")));
		setTitle("Usuarios");
		setModal(true);
		setBounds(100, 100, 547, 408);
		getContentPane().setLayout(null);

		txtLogin = new JTextField();
		txtLogin.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLogin.addKeyListener(new KeyAdapter() {
//					@Override
//					public void keyTyped(KeyEvent e) {
//						
//					}
//					@Override
//					public void keyReleased(KeyEvent e) {
//						listarUsuarios();
//					}
		});
		txtLogin.setDocument(new Validador(50));
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(75, 152, 204, 43);
		getContentPane().add(scrollPane);

		listaUsuarios = new JList();
		scrollPane.setViewportView(listaUsuarios);
		listaUsuarios.setVisible(false);
		listaUsuarios.setBorder(null);
		listaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ItensUsuariosLista();
			}
		});
		listaUsuarios.setModel(new AbstractListModel() {
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		txtLogin.setColumns(10);
		txtLogin.setBounds(75, 83, 204, 20);
		getContentPane().add(txtLogin);
		txtLogin.setDocument(new Validador(18));

		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 26, 46, 28);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(20, 128, 46, 28);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(20, 195, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(76, 32, 46, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarUsuarios();
			}
		});
		txtNome.setBounds(75, 134, 204, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(15));

		bttnBuscar = new JButton("");
		bttnBuscar.setToolTipText("Buscar");
		getRootPane().setDefaultButton(bttnBuscar);
		bttnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}

		});
		bttnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnBuscar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/search.png")));
		bttnBuscar.setBounds(457, 261, 64, 64);
		getContentPane().add(bttnBuscar);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Remover");
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
		txtSenha.setBounds(76, 194, 219, 14);
		getContentPane().add(txtSenha);
		txtSenha.setDocument(new Validador(250));

		bttnAdd = new JButton("");
		bttnAdd.setToolTipText("Adicionar");
		bttnAdd.setIcon(new ImageIcon(Usuarios.class.getResource("/img/plusIcon.png")));
		bttnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		bttnAdd.setBounds(457, 39, 64, 64);
		getContentPane().add(bttnAdd);

		bttnEditar = new JButton("");
		bttnEditar.setEnabled(false);
		bttnEditar.setToolTipText("Editar");
		bttnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		bttnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/EditIcon.png")));
		bttnEditar.setBounds(457, 187, 64, 64);
		getContentPane().add(bttnEditar);

		bttnRemove = new JButton("X");
		bttnRemove.setEnabled(false);
		bttnRemove.setToolTipText("Remover");
		bttnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		bttnRemove.setFont(new Font("Tahoma", Font.PLAIN, 49));
		bttnRemove.setBounds(457, 113, 64, 64);
		getContentPane().add(bttnRemove);

		lblNewLabel_4 = new JLabel("Login");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(20, 77, 46, 28);
		getContentPane().add(lblNewLabel_4);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPerfil.setBounds(20, 237, 46, 14);
		getContentPane().add(lblPerfil);

		cbPerfil = new JComboBox();
		cbPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cbPerfil.setBounds(86, 229, 72, 22);
		getContentPane().add(cbPerfil);

		checkSenha = new JCheckBox("Alterar senha");
		checkSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//			txtSenha.setText(null);
//			txtSenha.setBackground(Color.YELLOW);
				@SuppressWarnings("deprecation")
				String pass = txtSenha.getText();
				if (checkSenha.isSelected()) {
					System.out.println("selecionado");
					txtSenha.setBackground(Color.YELLOW);
					txtSenha.setText(null);
					txtSenha.requestFocus();

				} else {
					System.out.println("Não selecionado");
				
					txtSenha.setBackground(Color.WHITE);
				}
			}
		});
		checkSenha.setBounds(90, 320, 135, 23);
		getContentPane().add(checkSenha);
		setLocationRelativeTo(null);
	}

	private void search() {
		// dica - testar o evento primeiro
		// System.out.println("teste do botão buscar");

		// Criar uma variável com a query (instrução do banco)
		String read = "select * from usuarios where login = ?";
		String Value = "      ";
		// tratamento de exceções
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtLogin.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtId.setText(rs.getString(1)); // 1º ID
				txtNome.setText(rs.getString(2)); // 2° NOME
				txtLogin.setText(rs.getString(3)); // 3° Login
				txtSenha.setText(rs.getString(4));
				cbPerfil.setSelectedItem(rs.getString(5));

				bttnEditar.setEnabled(true);
				bttnAdd.setEnabled(true);
				bttnBuscar.setEnabled(true);
				bttnRemove.setEnabled(true);
			} else {
				String msg = "Usuario inexistente, gostaria de procurar por id?";
				int conf = JOptionPane.showConfirmDialog(null, msg);
//				
				bttnAdd.setEnabled(true);

//				bttnBuscar.setEnabled(false);

			}
			// fechar a conexão (IMPORTANTE!)
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	} // fim do metodo buscar

	@SuppressWarnings("deprecation")
	private void adicionar() {
		//
		String capturaSenha = new String(txtSenha.getPassword());
		// condição
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome obrigatorio.");
			txtNome.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Senha obrigatoria.");
			txtSenha.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login obrigatorio.");
			txtLogin.requestFocus();
		} else if (capturaSenha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Senha obrigatoria.");
			txtSenha.requestFocus();
		} else if (cbPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Perfil obrigatorio.");
			cbPerfil.requestFocus();
		} else {
			// adicionar contato
			// usar dao e pst via con
			try {

				con = dao.conectar();
				// estrutura dao
				// adicionar mensagem
				String create = "insert into usuarios(nome,login,senha,perfil) values(?,?,md5(?),?)";

				pst = con.prepareStatement(create);
				// Lista dos usuarios
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, (String) cbPerfil.getSelectedItem());

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Contato adicionado com sucesso");

				limparCampos();
				bttnEditar.setEnabled(true);

				con.close(); // "Releases this Connection object's database and JDBC resources immediately
								// instead of waiting for them to be automatically released"
			} catch (SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "Já existe uma conta com esse login, tente com outro nome.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

	}

	private void limparCampos() {
		// Remoção de todos os textos
		txtId.setText(null);
		txtNome.setText(null);
		txtSenha.setText(null);
		txtLogin.setText(null);
		// Validação dos botões
		bttnAdd.setEnabled(false);
		bttnRemove.setEnabled(false);
		bttnAdd.setEnabled(false);
		bttnEditar.setEnabled(false);

		bttnBuscar.setEnabled(true);
		//
		scrollPane.setVisible(false);
		listaUsuarios.setVisible(false);
		//
		cbPerfil.setSelectedItem("");
		checkSenha.setSelected(false);
		txtSenha.setBackground(Color.WHITE);
	}//

	@SuppressWarnings("deprecation")
	private void refresh() {
		String update = "update usuarios set nome=?,senha=md5(?),login=?,perfil=? where id=?";
		String updatemd5 = "update usuarios set nome=?,senha=?,login=?,perfil=? where id=?";

		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login obrigatorio.");
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Senha obrigatoria.");
			txtSenha.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login obrigatorio.");
			txtLogin.requestFocus();
		} else if (cbPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Perfil obrigatorio.");
			cbPerfil.requestFocus();

		} else {

			try {
				con = dao.conectar();
				if (checkSenha.isSelected())
					pst = con.prepareStatement(update);
//				if (txtSenha.getText().length()>=30) {
//					pst = con.prepareStatement(updatemd5);
//				}else {
				else {
					pst = con.prepareStatement(updatemd5);
				}
//				}
				pst.setString(1, txtNome.getText());

//				if(txtSenha.getText().length()>=30) {
//				pst.setString(2, rs.getString(4));
//				System.out.println("mudado");
//				}else{
				pst.setString(2, txtSenha.getText());

				pst.setString(3, txtLogin.getText());

				pst.setString(4, (String) cbPerfil.getSelectedItem());
				pst.setString(5, txtId.getText());

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados contato editados com sucesso.");
				limparCampos();

				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}//

	private void remove() {
		con = dao.conectar();
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				String delete = "delete from usuarios where id = ?;";
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Usuario removidos com sucesso");

				limparCampos();
				bttnAdd.setEnabled(true);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

		}
	}//

	private void listarUsuarios() {

		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaUsuarios.setModel(modelo);
		String type = "Select * from usuarios where nome like '" + txtNome.getText() + "%'" + " order by nome ";
		try {

			con = dao.conectar();
			pst = con.prepareStatement(type);
			rs = pst.executeQuery();
			System.out.println("Conexão");
			while (rs.next()) {
				listaUsuarios.setVisible(true);
				scrollPane.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtNome.getText().isEmpty()) {
//					System.out.println("Condição");
					listaUsuarios.setVisible(false);
					scrollPane.setVisible(false);
				}
			}
			con.close();
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		}

	}//

	public void ItensUsuariosLista() {
		int linha = listaUsuarios.getSelectedIndex();
		String comando = "Select * from usuarios where nome like '" + txtNome.getText() + "%'" + " order by nome limit "
				+ (linha) + ", 1";
		if (linha >= 0) {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);
				rs = pst.executeQuery();

				if (rs.next()) {
					scrollPane.setVisible(false);

					txtId.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtLogin.setText(rs.getString(3));
					txtSenha.setText(rs.getString(4));
					cbPerfil.setSelectedItem(rs.getString(5));
					bttnAdd.setEnabled(false);
					bttnRemove.setEnabled(true);
					bttnEditar.setEnabled(true);
				}
			} catch (SQLException se) {

			} catch (Exception e) {

			}
		} else {
			scrollPane.setVisible(false);
		}

		System.out.println(linha);
	}
}
