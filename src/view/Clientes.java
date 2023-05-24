package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
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

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Clientes {
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
	private JButton btnPesquisar;
	private JButton btnCep;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnAdicionar;
	private JComboBox cboUf;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes window = new Clientes();
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
	public Clientes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClientes = new JFrame();
		frmClientes.setTitle("Clientes");
		frmClientes.setBounds(100, 100, 577, 363);
		frmClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClientes.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 11, 27, 21);
		frmClientes.getContentPane().add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(30, 11, 80, 21);
		frmClientes.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 43, 41, 21);
		frmClientes.getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(61, 43, 302, 21);
		frmClientes.getContentPane().add(txtNome);
		
		JLabel lblNewLabel_1_1 = new JLabel("CEP");
		lblNewLabel_1_1.setBounds(10, 107, 41, 21);
		frmClientes.getContentPane().add(lblNewLabel_1_1);
		
		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(61, 107, 158, 21);
		frmClientes.getContentPane().add(txtCep);
		
		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(10, 75, 41, 21);
		frmClientes.getContentPane().add(lblFone);
		
		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(61, 75, 112, 21);
		frmClientes.getContentPane().add(txtFone);
		
		btnCep = new JButton("Buscar CEP");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			buscarCep();}
		});
		btnCep.setBounds(244, 106, 89, 23);
		frmClientes.getContentPane().add(btnCep);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Endereço");
		lblNewLabel_1_1_1.setBounds(10, 139, 56, 21);
		frmClientes.getContentPane().add(lblNewLabel_1_1_1);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(71, 139, 292, 21);
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
		frmClientes.getContentPane().add(txtBairro);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Complemento");
		lblNewLabel_1_1_1_2_1.setBounds(286, 171, 65, 21);
		frmClientes.getContentPane().add(lblNewLabel_1_1_1_2_1);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(357, 171, 148, 21);
		frmClientes.getContentPane().add(txtComplemento);
		
		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Cidade");
		lblNewLabel_1_1_1_2_2.setBounds(61, 224, 65, 21);
		frmClientes.getContentPane().add(lblNewLabel_1_1_1_2_2);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(132, 224, 148, 21);
		frmClientes.getContentPane().add(txtCidade);
		
		JLabel lblNewLabel_1 = new JLabel("UF");
		lblNewLabel_1.setBounds(305, 227, 27, 14);
		frmClientes.getContentPane().add(lblNewLabel_1);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			adicionar();
			}
		});
		btnAdicionar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAdicionar.setBounds(61, 279, 89, 23);
		frmClientes.getContentPane().add(btnAdicionar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnEditar.setBounds(175, 279, 89, 23);
		frmClientes.getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			remove();
			}
		});
		btnExcluir.setBounds(286, 279, 89, 23);
		frmClientes.getContentPane().add(btnExcluir);
		
		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cboUf.setBounds(333, 223, 56, 22);
		frmClientes.getContentPane().add(cboUf);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			pesquisar();
			}
		});
		btnPesquisar.setBounds(373, 42, 89, 23);
		frmClientes.getContentPane().add(btnPesquisar);
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
			JOptionPane.showMessageDialog(null, "O nome deve ser preenchido");}
//		} else if (txtId.getText().isEmpty()) {
//			
//		}
		else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O telefone deve ser preenchido");
		}else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O cep deve ser preenchido");
		}else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Endereço deve ser preenchido");
		}else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Numero deve ser preenchido");
		}else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Bairro deve ser preenchido");
		}else if (txtComplemento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Complemento deve ser preenchido");
		}else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "A Cidade deve ser preenchida");
		}else if (String.valueOf(cboUf.getSelectedItem()) == "") {
			JOptionPane.showMessageDialog(null, "O UF deve ser selecionado");
		}else {
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
			
			con.close();
			}catch (SQLException se) {
				System.out.println(se);
			
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	private void remove() {
		con = dao.conectar();
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Cliente?","Atenção!", JOptionPane.YES_NO_OPTION);
		if(confirma == JOptionPane.YES_OPTION) {
			try {
			String delete = "delete from clientes where idcli = ?;";
			con = dao.conectar();
			
			pst = con.prepareStatement(delete);
			pst.setString(1, txtId.getText());
			pst.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Cliente removidos com sucesso");

//			limparCampos();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}	
	}//fim do remove
	private void pesquisar() {
		String comando = "select * from clientes where nome = ?";
		
		try {
			con = dao.conectar();
			pst = con.prepareStatement(comando);
			pst.setString(1, txtNome.getText());
			rs = pst.executeQuery();
			
			if (rs.next()) {
				txtId.setText(rs.getString(1));
				txtNome.setText(rs.getString(2));
				txtFone.setText(rs.getString(3));
				txtCep.setText(rs.getString(4));
				txtEndereco.setText(rs.getString(5));
				txtNumero.setText(rs.getString(6));
				txtBairro.setText(rs.getString(7));
				txtComplemento.setText(rs.getString(8));
				txtCidade.setText(rs.getString(7));
				cboUf.setSelectedItem(rs.getString(9));
			}
			
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);}
		}
	}// Fim do codigo
/**
 * buscarCep
 */




