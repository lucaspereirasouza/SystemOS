package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import util.Validador;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Toolkit;

public class OldCLientes extends JFrame {
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private JFrame frmClientes;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtCep;
	private JTextField txtFone;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtComplemento;
	private JTextField txtCidade;
	private JButton btnCep;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnAdicionar;
	private JComboBox cboUf;
	private JScrollPane scrollPane;
	private JList listClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OldCLientes window = new OldCLientes();
					window.frmClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OldCLientes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClientes = new JFrame();
		frmClientes.setIconImage(Toolkit.getDefaultToolkit().getImage(OldCLientes.class.getResource("/img/ConsoleIcon.png")));
		frmClientes.getContentPane().setBackground(SystemColor.activeCaptionBorder);
		frmClientes.setTitle("Clientes");
		frmClientes.setBounds(100, 100, 577, 420);
		frmClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClientes.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(305, 304, 302, 66);
		frmClientes.getContentPane().add(scrollPane);

		listClientes = new JList();
		listClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ItensClientesLista();
			}
		});
		scrollPane.setViewportView(listClientes);

		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(61, 107, 158, 21);
		txtCep.setDocument(new Validador(10));
		frmClientes.getContentPane().add(txtCep);

		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 11, 27, 21);
		frmClientes.getContentPane().add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(30, 11, 80, 21);
		frmClientes.getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 43, 41, 21);
		frmClientes.getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarClientes();
			}
		});
		txtNome.setColumns(10);
		txtNome.setBounds(61, 43, 302, 21);
		txtNome.setDocument(new Validador(10));

		frmClientes.getContentPane().add(txtNome);

		JLabel lblNewLabel_1_1 = new JLabel("CEP");
		lblNewLabel_1_1.setBounds(10, 107, 41, 21);
		frmClientes.getContentPane().add(lblNewLabel_1_1);

		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(10, 75, 41, 21);
		frmClientes.getContentPane().add(lblFone);

		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(61, 75, 112, 21);
		txtFone.setDocument(new Validador(15));
		frmClientes.getContentPane().add(txtFone);

		btnCep = new JButton("Buscar CEP");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(244, 106, 145, 23);
		frmClientes.getContentPane().add(btnCep);

		JLabel lblNewLabel_1_1_1 = new JLabel("Endereço");
		lblNewLabel_1_1_1.setBounds(10, 139, 56, 21);
		frmClientes.getContentPane().add(lblNewLabel_1_1_1);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(71, 139, 292, 21);
		txtEndereco.setDocument(new Validador(50));
		frmClientes.getContentPane().add(txtEndereco);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Numero");
		lblNewLabel_1_1_1_1.setBounds(373, 139, 56, 21);
		frmClientes.getContentPane().add(lblNewLabel_1_1_1_1);

		txtNumero = new JTextField();
		txtNumero.setBounds(419, 139, 86, 20);
		frmClientes.getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Bairro");
		lblNewLabel_1_1_1_2.setBounds(10, 172, 65, 21);
		frmClientes.getContentPane().add(lblNewLabel_1_1_1_2);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(81, 172, 148, 21);
		txtBairro.setDocument(new Validador(30));
		frmClientes.getContentPane().add(txtBairro);

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Complemento");
		lblNewLabel_1_1_1_2_1.setBounds(286, 171, 65, 21);
		frmClientes.getContentPane().add(lblNewLabel_1_1_1_2_1);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(357, 171, 148, 21);
		txtComplemento.setDocument(new Validador(20));
		frmClientes.getContentPane().add(txtComplemento);

		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Cidade");
		lblNewLabel_1_1_1_2_2.setBounds(61, 224, 65, 21);
		frmClientes.getContentPane().add(lblNewLabel_1_1_1_2_2);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(132, 224, 148, 21);
		txtCidade.setDocument(new Validador(30));
		frmClientes.getContentPane().add(txtCidade);

		JLabel lblNewLabel_1 = new JLabel("UF");
		lblNewLabel_1.setBounds(305, 227, 27, 14);
		frmClientes.getContentPane().add(lblNewLabel_1);

		btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(OldCLientes.class.getResource("/img/cliAdd.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAdicionar.setBounds(30, 256, 64, 64);
		frmClientes.getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(OldCLientes.class.getResource("/img/cliEdit.png")));
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnEditar.setBounds(132, 256, 64, 64);
		frmClientes.getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setIcon(new ImageIcon(OldCLientes.class.getResource("/img/cliRemove.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		btnExcluir.setBounds(235, 256, 64, 64);
		frmClientes.getContentPane().add(btnExcluir);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(333, 223, 56, 22);
		frmClientes.getContentPane().add(cboUf);
	}

	private void limparcampos() {
		txtId.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtCep.setText(null);
		;
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtBairro.setText(null);
		txtComplemento.setText(null);
		txtCidade.setText(null);

		cboUf.setSelectedItem("");

		scrollPane.setVisible(false);
	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtEndereco.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						System.out.println("Ok");
					} else {
						JOptionPane.showMessageDialog(null, "CEP nÃ£o encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void adicionar() {
		String comando = "insert into clientes(nome,fone,cep,endereco,numero,complemento,bairro,cidade,uf) values(?,?,?,?,?,?,?,?,?)";

		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O nome deve ser preenchido");
		}
//		} else if (txtId.getText().isEmpty()) {
//		listClientes
//		}
		else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O telefone deve ser preenchido");
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O cep deve ser preenchido");
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Endereço deve ser preenchido");
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Numero deve ser preenchido");
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Bairro deve ser preenchido");
//		} else if (txtComplemento.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "O Complemento deve ser preenchido");
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "A Cidade deve ser preenchida");
		} else if (String.valueOf(cboUf.getSelectedItem()) == "") {
			JOptionPane.showMessageDialog(null, "O UF deve ser selecionado");
		} else {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);

				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtComplemento.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, String.valueOf(cboUf.getSelectedItem()));

				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
				limparcampos();
				con.close();
			} catch (SQLException se) {
				System.out.println(se);

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void remove() {
		con = dao.conectar();
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				String delete = "delete from clientes where idcli = ?;";
				con = dao.conectar();

				pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Cliente removidos com sucesso");
				limparcampos();
				btnAdicionar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);

//			limparCampos();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
	}// fim do remove

//	private void pesquisar() {
//		String comando = "select * from clientes where nome = ?";
//
//		try {
//			con = dao.conectar();
//			pst = con.prepareStatement(comando);
//			pst.setString(1, txtNome.getText());
//			rs = pst.executeQuery();
//
//			if (rs.next()) {
//				txtId.setText(rs.getString(1));
//				txtNome.setText(rs.getString(2));
//				txtFone.setText(rs.getString(3));
//				txtCep.setText(rs.getString(4));
//				txtEndereco.setText(rs.getString(5));
//				txtNumero.setText(rs.getString(6));
//				txtBairro.setText(rs.getString(7));
//				txtComplemento.setText(rs.getString(8));
//				txtCidade.setText(rs.getString(7));
//				cboUf.setSelectedItem(rs.getString(9));
//			}
//
//		} catch (SQLException se) {
//			System.out.println(se);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	private void listarClientes() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listClientes.setModel(modelo);
		String type = "Select * from clientes where nome like '" + txtNome.getText() + "%'" + " order by nome ";
		try {

			con = dao.conectar();
			pst = con.prepareStatement(type);
			rs = pst.executeQuery();
			System.out.println("Conexão");
			while (rs.next()) {
				listClientes.setVisible(true);
				scrollPane.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtNome.getText().isEmpty()) {
//					System.out.println("Condição");
					listClientes.setVisible(false);
					scrollPane.setVisible(false);
				}
			}
			con.close();
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void ItensClientesLista() {
		int linha = listClientes.getSelectedIndex();
		String comando = "Select * from clientes where nome like '" + txtNome.getText() + "%'" + " order by nome limit "
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
					txtFone.setText(rs.getString(3));
					txtCep.setText(rs.getString(4));
					txtEndereco.setText(rs.getString(5));
					txtNumero.setText(rs.getString(6));
					txtComplemento.setText(rs.getString(7));
					txtBairro.setText(rs.getString(8));
					txtCidade.setText(rs.getString(9));
					cboUf.setSelectedItem(rs.getString(10));

					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnAdicionar.setEnabled(false);
				}
			} catch (SQLException se) {

			} catch (Exception e) {

			}
		} else {
			scrollPane.setVisible(false);
		}

		System.out.println(linha);
	}

	public void editar() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome do cliente obrigatorio.");
			txtNome.requestFocus();
		} else {
			String update = "update clientes set nome=?,fone=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=? where idcli=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);

				pst.setString(10, txtId.getText());
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, String.valueOf(cboUf.getSelectedItem()));

//				txtId.setText(rs.getString(1));
//				txtNome.setText(rs.getString(2));
//				txtFone.setText(rs.getString(3));
//				txtCep.setText(rs.getString(4));
//				txtEndereco.setText(rs.getString(5));
//				txtNumero.setText(rs.getString(6));
//				txtComplemento.setText(rs.getString(7));
//				txtBairro.setText(rs.getString(8));
//				txtCidade.setText(rs.getString(4));
//				cboUf.setSelectedItem(rs.getString(9));

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados contato editados com sucesso.");
				limparcampos();
				btnAdicionar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
				con.close();
			} catch (SQLException se) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, se);
			} catch (Exception e1) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e1);
			}
		}
	}

}// Fim do codigo
/**
 * buscarCep
 */
