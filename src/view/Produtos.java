package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.DAO;
import util.Validador;
import view.produtoCRUD.AddProduto;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.Toolkit;
import javax.swing.JList;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Produtos extends JDialog {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	@SuppressWarnings("unused")
	private FileInputStream fis;
	@SuppressWarnings("unused")
	private int fisSize;

	private JLabel lblimg;
	private JTextField txtidProdutos;
	private JTextField txtValor;
	private JTextField txtLocalArmazenagem;
	private JTextField txtIdFornecedor;
	private JTextField txtEstoqueMin;
	private JTextField txtEstoque;
	private JTextField txtFornecedor;
	private JTextField txtProdutos;
	private JScrollPane scrollPane;
	private JList listFornecedor;
	private JButton btnCarregarFoto;
	private JTextField txtBarcode;
	private JLabel lblNewLabel_5;
	private JComboBox cmbmedida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produtos dialog = new Produtos();
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
	public Produtos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produtos.class.getResource("/img/ConsoleIcon.png")));
		setTitle("Produtos");
		setBounds(100, 100, 658, 398);
		getContentPane().setLayout(null);

		lblimg = new JLabel("");
		lblimg.setIcon(new ImageIcon(Produtos.class.getResource("/img/produtosIcon.png")));
		lblimg.setBounds(321, 100, 140, 140);
		getContentPane().add(lblimg);

		btnCarregarFoto = new JButton("Carregar foto");
		btnCarregarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadPhoto();
			}
		});
		btnCarregarFoto.setBounds(331, 248, 130, 23);
		getContentPane().add(btnCarregarFoto);

		txtidProdutos = new JTextField();
		txtidProdutos.setEnabled(false);
		txtidProdutos.setBounds(18, 69, 68, 20);
		getContentPane().add(txtidProdutos);
		txtidProdutos.setColumns(10);

		JLabel lblNewLabel = new JLabel("EstoqueMin");
		lblNewLabel.setBounds(85, 100, 68, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setBounds(18, 100, 57, 14);
		getContentPane().add(lblEstoque);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBounds(18, 300, 95, 35);
		getContentPane().add(btnAdicionar);

		JLabel lblNewLabel_1 = new JLabel("IdProdutos");
		lblNewLabel_1.setBounds(18, 47, 68, 14);
		getContentPane().add(lblNewLabel_1);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
			}
		});
		btnRemover.setBounds(123, 300, 95, 35);
		getContentPane().add(btnRemover);

		JButton btnPesquisar = new JButton("Pesquisar por id");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String es = JOptionPane.showInputDialog("Por favor, insira o ID do produto:");
				System.out.println(es);
				String comando = "Select * from fornecedores where idfornecedores like '" + es.toString();
				try {
					con = dao.conectar();
					pst = con.prepareStatement(comando);

				} catch (SQLException SQL) {
					// TODO: handle exception
					SQL.printStackTrace();
				}

			}
		});
		btnPesquisar.setBounds(163, 96, 126, 23);
		getContentPane().add(btnPesquisar);

		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(18, 179, 113, 20);
		getContentPane().add(txtValor);

		JLabel lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setBounds(18, 156, 86, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Local armazenagem");
		lblNewLabel_2_1.setBounds(167, 156, 113, 14);
		getContentPane().add(lblNewLabel_2_1);

		txtLocalArmazenagem = new JTextField();
		txtLocalArmazenagem.setColumns(10);
		txtLocalArmazenagem.setBounds(167, 179, 126, 20);
		getContentPane().add(txtLocalArmazenagem);

		JButton btnNewButton = new JButton("Barcode");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(543, 333, 89, 23);
		getContentPane().add(btnNewButton);

		txtIdFornecedor = new JTextField();
		txtIdFornecedor.setEnabled(false);
		txtIdFornecedor.setColumns(10);
		txtIdFornecedor.setBounds(391, 72, 57, 20);
		getContentPane().add(txtIdFornecedor);

		JLabel lblNewLabel_1_1 = new JLabel("IdFornecedor");
		lblNewLabel_1_1.setBounds(393, 47, 68, 14);
		getContentPane().add(lblNewLabel_1_1);

		txtEstoque = new JTextField();
		txtEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtEstoque.setColumns(10);
		txtEstoque.setBounds(28, 125, 31, 20);
		getContentPane().add(txtEstoque);

		txtEstoqueMin = new JTextField();
		txtEstoqueMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtEstoqueMin.setColumns(10);
		txtEstoqueMin.setBounds(95, 125, 31, 20);
		getContentPane().add(txtEstoqueMin);

		txtEstoque.setDocument(new Validador(3));
		txtEstoqueMin.setDocument(new Validador(3));

		txtFornecedor = new JTextField();
		txtFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarUsuarios();
			}
		});
		txtFornecedor.setBounds(477, 72, 126, 20);
		getContentPane().add(txtFornecedor);
		txtFornecedor.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Nome do fornecedor");
		lblNewLabel_3.setBounds(474, 47, 110, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Produto");
		lblNewLabel_4.setBounds(96, 47, 107, 14);
		getContentPane().add(lblNewLabel_4);

		txtProdutos = new JTextField();
		txtProdutos.setBounds(96, 69, 107, 20);
		getContentPane().add(txtProdutos);
		txtProdutos.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(476, 91, 127, 51);
		getContentPane().add(scrollPane);

		listFornecedor = new JList();
		listFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listarFornecedor();
			}
		});
		scrollPane.setViewportView(listFornecedor);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(221, 69, 86, 20);
		getContentPane().add(txtBarcode);
		txtBarcode.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Barcode");
		lblNewLabel_5.setBounds(220, 47, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		cmbmedida = new JComboBox();
		cmbmedida.setModel(new DefaultComboBoxModel(new String[] {"", "UN", "CX", "PC ", "Kg ", "m"}));
		cmbmedida.setBounds(18, 218, 57, 22);
		getContentPane().add(cmbmedida);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			editar();}
		});
		btnEditar.setBounds(228, 300, 101, 35);
		getContentPane().add(btnEditar);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(179, 242, 101, 35);
		getContentPane().add(btnProcurar);
	}

	private void LoadPhoto() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Selecionar arquivo");
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens (*.PNG,*.JPG,*.JPEG)", "png", "jpg", "jpeg"));
		int rs = jfc.showOpenDialog(this);
		if (rs == JFileChooser.APPROVE_OPTION) {
			try {
				fis = new FileInputStream(jfc.getSelectedFile());
				fisSize = (int) jfc.getSelectedFile().length();
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblimg.getWidth(),
						lblimg.getHeight(), Image.SCALE_SMOOTH);
				lblimg.setIcon(new ImageIcon(foto));
				lblimg.updateUI();
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
	}

	private void adicionar() {
		
		String comando = "insert into produtos(barcode,foto,estoque,estoquemin,valor,unidademedida,localarmazenagem,idfornecedor) values(?,?,?,?,?,?,?,?)";
		String numOs = "SELECT idfornecedores FROM fornecedores WHERE idfornecedores ="+txtIdFornecedor.getText();
		if (txtProdutos.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O produto deve ser preenchido");
		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O estoque deve ser preenchido");
		}else if (txtProdutos.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O nome do produto deve ser preenchido");
		}else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "As informações do estoque deve ser preenchido");
		}else if (txtEstoqueMin.getText().isEmpty()) {JOptionPane.showMessageDialog(null, "O estoque minimo deve ser declarado");
		}else if (txtValor.getText().isEmpty()) {JOptionPane.showMessageDialog(null, "O valor deve ser declrado");}
		else if (txtLocalArmazenagem.getText().isEmpty()) {JOptionPane.showMessageDialog(null, "Declarar local de armazenagem");
//		}
//		else if (lblimg.getIcon().equals("/bin/img/produtosIcon.png")) {JOptionPane.showMessageDialog(null, "Carregar imagem");
		}else {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);

				pst.setString(1, txtBarcode.getText());
				pst.setBlob(2, fis,fisSize);
				pst.setString(3, txtEstoque.getText());
				pst.setString(4, txtEstoqueMin.getText());
				pst.setString(5, txtValor.getText());
				pst.setString(6, String.valueOf(cmbmedida.getSelectedItem()));
				pst.setString(7, txtEstoqueMin.getText());
				pst.setString(8, txtIdFornecedor.getText());
				
//				pst.setString(1, txtNome.getText());
//				pst.setString(2, txtFone.getText());
//				pst.setString(3, txtCep.getText());
//				pst.setString(4, txtEndereco.getText());
//				pst.setString(5, txtNumero.getText());
//				pst.setString(6, txtComplemento.getText());
//				pst.setString(7, txtBairro.getText());
//				pst.setString(8, txtCidade.getText());
//				pst.setString(9, String.valueOf(cboUf.getSelectedItem()));
//				pst.setString(10, txtCPF.getText());

				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
				limparcampos();
				con.close();
				System.out.println(lblimg.getIcon());
			}catch(SQLException se) {
				se.printStackTrace();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public void onlyNum(KeyEvent e) {
		char c = e.getKeyChar();
		if (Character.isLetter(c)) {
			e.consume();
		}
	}

	private void listarFornecedor() {
		int linha = listFornecedor.getSelectedIndex();
		String comando = "Select * from fornecedores where nome like '" + txtFornecedor.getText() + "%'"
				+ " order by nome limit " + (linha) + ", 1";
		if (linha >= 0) {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);
				rs = pst.executeQuery();

				if (rs.next()) {
					scrollPane.setVisible(false);

					txtIdFornecedor.setText(rs.getString(1));
					txtFornecedor.setText(rs.getString(2));

					System.out.println("saida com sucesso");
//
//					txtId.setText(rs.getString(1));
//					txtNome.setText(rs.getString(2));
//					txtFone.setText(rs.getString(3));
//					txtCep.setText(rs.getString(4));
//					txtEndereco.setText(rs.getString(5));
//					txtNumero.setText(rs.getString(6));
//					txtComplemento.setText(rs.getString(7));
//					txtBairro.setText(rs.getString(8));
//					txtCidade.setText(rs.getString(9));
//					cboUf.setSelectedItem(rs.getString(10));
//					txtCPF.setText(rs.getString(11));
//
//					btnEditar.setEnabled(true);
//					btnExcluir.setEnabled(true);
//					btnAdicionar.setEnabled(false);
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			scrollPane.setVisible(false);
		}
	}

	private void listarUsuarios() {

		DefaultListModel<String> modelo = new DefaultListModel<>();
		listFornecedor.setModel(modelo);
		String type = "Select * from usuarios where nome like '" + txtFornecedor.getText() + "%'" + " order by nome ";
		try {

			con = dao.conectar();
			pst = con.prepareStatement(type);
			rs = pst.executeQuery();
			System.out.println("Conexão");
			while (rs.next()) {
				listFornecedor.setVisible(true);
				scrollPane.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtFornecedor.getText().isEmpty()) {
					System.out.println("Condição");
					txtFornecedor.setVisible(false);
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
	private void limparcampos() {
		txtEstoque.setText(null);
		txtEstoqueMin.setText(null);
		txtFornecedor.setText(null);
		txtIdFornecedor.setText(null);
		txtidProdutos.setText(null);
		txtLocalArmazenagem.setText(null);
		txtProdutos.setText(null);
		txtValor.setText(null);
		
	}
	private void remover() {
		String comando = "delete from produtos where idproduto = ?";
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste produto?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				con = dao.conectar();

				pst = con.prepareStatement(comando);
				pst.setString(1, txtidProdutos.getText());
				pst.executeUpdate();

				con.close();
				JOptionPane.showInternalConfirmDialog(null, "Cliente removidos com sucesso");
				limparcampos();

//			limparCampos();
			} catch (java.sql.SQLIntegrityConstraintViolationException se) {
				//
				JOptionPane.showInternalMessageDialog(null, "Não pode excluir o cliente (Tem registro OS)");
				System.out.println(se);
			} catch (Exception e) {
				//
				System.out.println(e);
			}
		
	}// fim do remove
	public void editar() {
		if (txtProdutos.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O produto deve ser preenchido");
		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O estoque deve ser preenchido");
		}else if (txtProdutos.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O nome do produto deve ser preenchido");
		}else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "As informações do estoque deve ser preenchido");
		}else if (txtEstoqueMin.getText().isEmpty()) {JOptionPane.showMessageDialog(null, "O estoque minimo deve ser declarado");
		}else if (txtValor.getText().isEmpty()) {JOptionPane.showMessageDialog(null, "O valor deve ser declrado");}
		else if (txtLocalArmazenagem.getText().isEmpty()) {JOptionPane.showMessageDialog(null, "Declarar local de armazenagem");
//		}
//		else if (lblimg.getIcon().equals("/bin/img/produtosIcon.png")) {JOptionPane.showMessageDialog(null, "Carregar imagem");
		}else {
			String update = "update produtos set nome=?,barcode=?,descricao=?,foto=?,estoque=?,estoquemin=?,valor=?,medida=?,armazenagem=?,id=? where idproduto=?";
		}
	
	}
}
