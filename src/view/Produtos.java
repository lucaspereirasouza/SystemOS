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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Blob;
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
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

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
	private JList<String> listFornecedor;
	private JButton btnCarregarFoto;
	private JTextField txtBarcode;
	private JLabel lblNewLabel_5;
	private JComboBox cmbmedida;
	private JButton btnLimparCampos;
	private JTextField txtDescricao;
	private JList listProdutos;
	private JScrollPane scrollprodutos;

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
		setBounds(100, 100, 658, 469);
		getContentPane().setLayout(null);

		JButton btnPesquisar = new JButton("Pesquisar por id");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnPesquisar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				String es = JOptionPane.showInputDialog("Por favor, insira o ID do produto:");
				System.out.println(es);
				String comando = "Select * from produtos inner join fornecedores where idproduto like '" + es.toString()
						+ "' ;";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(comando);
					rs = pst.executeQuery();

					if (rs.next()) {

						txtidProdutos.setText((rs.getString(1)));
						txtProdutos.setText((rs.getString(2)));
						txtBarcode.setText((rs.getString(3)));
						txtDescricao.setText(rs.getString(4));

						Blob blob = (Blob) rs.getBlob(5);
//						lblimg.setIcon();
//					lblimg.setIcon(null);;
						txtEstoque.setText((rs.getString(6)));
						txtEstoqueMin.setText((rs.getString(7)));
						txtValor.setText((rs.getString(8)));

						cmbmedida.setSelectedItem(rs.getString(9));// .setText((rs.getString(1)));
						txtLocalArmazenagem.setText((rs.getString(10)));
						txtIdFornecedor.setText((rs.getString(11)));
						txtFornecedor.setText(rs.getString(13));

						byte[] img = blob.getBytes(1, (int) blob.length());
						BufferedImage imagem = null;
						try {
							imagem = ImageIO.read(new ByteArrayInputStream(img));
						} catch (Exception e1) {
							System.out.println(e1);
						}

						ImageIcon icone = new ImageIcon(imagem);
						Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblimg.getWidth(),
								lblimg.getHeight(), Image.SCALE_SMOOTH));
						lblimg.setIcon(foto);
					}
				} catch (SQLException SQL) {
					// TODO: handle exception
					SQL.printStackTrace();
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
		});

		scrollprodutos = new JScrollPane();
		scrollprodutos.setVisible(false);
		scrollprodutos.setBounds(96, 89, 127, 51);
		getContentPane().add(scrollprodutos);

		listProdutos = new JList();
		listProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listarProdutos();
			}
		});
		scrollprodutos.setViewportView(listProdutos);
		btnPesquisar.setBounds(96, 89, 140, 23);
		getContentPane().add(btnPesquisar);

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
		lblNewLabel.setBounds(95, 188, 68, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setBounds(28, 188, 57, 14);
		getContentPane().add(lblEstoque);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBounds(18, 384, 95, 35);
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
		btnRemover.setBounds(123, 384, 95, 35);
		getContentPane().add(btnRemover);

		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(18, 263, 113, 20);
		getContentPane().add(txtValor);

		JLabel lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setBounds(18, 240, 86, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Local armazenagem");
		lblNewLabel_2_1.setBounds(167, 240, 113, 14);
		getContentPane().add(lblNewLabel_2_1);

		txtLocalArmazenagem = new JTextField();
		txtLocalArmazenagem.setColumns(10);
		txtLocalArmazenagem.setBounds(167, 263, 126, 20);
		getContentPane().add(txtLocalArmazenagem);

		JButton btnNewButton = new JButton("Barcode");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(543, 390, 89, 23);
		getContentPane().add(btnNewButton);

		txtIdFornecedor = new JTextField();
		txtIdFornecedor.setEnabled(false);
		txtIdFornecedor.setColumns(10);
		txtIdFornecedor.setBounds(391, 72, 57, 20);
		getContentPane().add(txtIdFornecedor);

		JLabel lblNewLabel_1_1 = new JLabel("IdFornecedor");
		lblNewLabel_1_1.setBounds(375, 47, 86, 14);
		getContentPane().add(lblNewLabel_1_1);

		txtEstoque = new JTextField();
		txtEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtEstoque.setColumns(10);
		txtEstoque.setBounds(38, 213, 31, 20);
		getContentPane().add(txtEstoque);

		txtEstoqueMin = new JTextField();
		txtEstoqueMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtEstoqueMin.setColumns(10);
		txtEstoqueMin.setBounds(105, 213, 31, 20);
		getContentPane().add(txtEstoqueMin);

		txtEstoque.setDocument(new Validador(3));
		txtEstoqueMin.setDocument(new Validador(3));

		txtFornecedor = new JTextField();
		txtFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				digitUsuarios();
			}
		});
		txtFornecedor.setBounds(477, 72, 126, 20);
		getContentPane().add(txtFornecedor);
		txtFornecedor.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Nome do fornecedor");
		lblNewLabel_3.setBounds(457, 47, 146, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Produto");
		lblNewLabel_4.setBounds(96, 47, 107, 14);
		getContentPane().add(lblNewLabel_4);

		txtProdutos = new JTextField();
		txtProdutos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				digitProdutos();
			}
		});
		txtProdutos.setBounds(96, 69, 107, 20);
		getContentPane().add(txtProdutos);
		txtProdutos.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
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
		lblNewLabel_5.setBounds(220, 47, 87, 14);
		getContentPane().add(lblNewLabel_5);

		cmbmedida = new JComboBox();
		cmbmedida.setModel(new DefaultComboBoxModel(new String[] { "", "UN", "CX", "PC ", "Kg ", "M" }));
		cmbmedida.setBounds(18, 302, 57, 22);
		getContentPane().add(cmbmedida);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(228, 384, 101, 35);
		getContentPane().add(btnEditar);

		btnLimparCampos = new JButton("LimparCampos");
		btnLimparCampos.setIcon(new ImageIcon(Produtos.class.getResource("/img/erase.png")));
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparcampos();
			}
		});
		btnLimparCampos.setBounds(391, 333, 192, 35);
		getContentPane().add(btnLimparCampos);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(18, 117, 281, 60);
		getContentPane().add(txtDescricao);
		txtDescricao.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Descricao");
		lblNewLabel_6.setBounds(18, 100, 46, 14);
		getContentPane().add(lblNewLabel_6);
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

		String comando = "insert into produtos(produto,barcode,foto,estoque,estoquemin,valor,unidademedida,localarmazenagem,idfornecedor) values(?,?,?,?,?,?,?,?,?)";
		String numOs = "SELECT idfornecedores FROM fornecedores WHERE idfornecedores =" + txtIdFornecedor.getText();
		if (txtProdutos.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O produto deve ser preenchido");
		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O estoque deve ser preenchido");
		} else if (txtProdutos.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O nome do produto deve ser preenchido");
		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "As informações do estoque deve ser preenchido");
		} else if (txtEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O estoque minimo deve ser declarado");
		} else if (txtIdFornecedor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Id do fornecedor deve ser escolhido");
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O valor deve ser declrado");
		} else if (txtLocalArmazenagem.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Declarar local de armazenagem");

			// }
//		else if (lblimg.getIcon().equals("/bin/img/produtosIcon.png")) {JOptionPane.showMessageDialog(null, "Carregar imagem");
		} else {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);
				pst.setString(1, txtProdutos.getText());
				pst.setString(2, txtBarcode.getText());
				pst.setBlob(3, fis, fisSize);
				pst.setString(4, txtEstoque.getText());
				pst.setString(5, txtEstoqueMin.getText());
				pst.setString(6, txtValor.getText());
				pst.setString(7, String.valueOf(cmbmedida.getSelectedItem()));
				pst.setString(8, txtEstoqueMin.getText());
				pst.setString(9, txtIdFornecedor.getText());

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
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
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

	private void listarProdutos() {
		int linha = listProdutos.getSelectedIndex();
		String comando = "Select * from produtos where produto like '" + txtProdutos.getText() + "%'"
				+ " order by produto limit " + (linha) + ", 1";
		if (linha >= 0) {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);
				rs = pst.executeQuery();

				if (rs.next()) {
					scrollprodutos.setVisible(false);

					txtidProdutos.setText(rs.getString(1));
					txtProdutos.setText(rs.getString(2));
					txtBarcode.setText(rs.getString(3));
					txtDescricao.setText(rs.getString(4));
					Blob blob = (Blob) rs.getBlob(5);
					txtEstoque.setText(rs.getString(6));
					txtEstoqueMin.setText(rs.getString(7));
					txtValor.setText(rs.getString(8));
					cmbmedida.setSelectedItem(rs.getString(9));
					txtLocalArmazenagem.setText(rs.getString(10));
					txtIdFornecedor.setText(rs.getString(11));
					System.out.println("saida com sucesso");

					byte[] img = blob.getBytes(1, (int) blob.length());
					BufferedImage imagem = null;
					try {
						imagem = ImageIO.read(new ByteArrayInputStream(img));
					} catch (Exception e1) {
						System.out.println(e1);
					}

					ImageIcon icone = new ImageIcon(imagem);
					Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(),
							Image.SCALE_SMOOTH));
					lblimg.setIcon(foto);
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

	private void digitUsuarios() {

		DefaultListModel<String> modelo = new DefaultListModel<>();
		listFornecedor.setModel(modelo);
		String type = "Select * from fornecedores where nome like '" + txtFornecedor.getText() + "%'"
				+ " order by nome ";
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
//					txtFornecedor.setVisible(false);
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

	private void digitProdutos() {

		DefaultListModel<String> modelo = new DefaultListModel<>();
		listProdutos.setModel(modelo);
		String type = "Select * from produtos where produto like '" + txtProdutos.getText() + "%'"
				+ " order by produto ";
		try {

			con = dao.conectar();
			pst = con.prepareStatement(type);
			rs = pst.executeQuery();
			System.out.println("Conexão");
			while (rs.next()) {
				listProdutos.setVisible(true);
				scrollprodutos.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtProdutos.getText().isEmpty()) {
					System.out.println("Condição");
//					txt.setVisible(false);
					
					scrollprodutos.setVisible(false);
				}
			}
			con.close();
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		}

	}//

	private void listareProdutos() {

		DefaultListModel<String> modelo = new DefaultListModel<>();
		listProdutos.setModel(modelo);
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
		txtBarcode.setText(null);
		txtDescricao.setText(null);
		cmbmedida.setSelectedItem("");

		lblimg.setIcon(new ImageIcon(Produtos.class.getResource("/img/produtosIcon.png")));
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
				JOptionPane.showInternalMessageDialog(null, "Removido com sucesso");
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
		}

	}// fim do remove

	public void editar() {
		if (txtProdutos.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O produto deve ser preenchido");
		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O estoque deve ser preenchido");
		} else if (txtProdutos.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O nome do produto deve ser preenchido");
		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "As informações do estoque deve ser preenchido");
		} else if (txtEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O estoque minimo deve ser declarado");
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O valor deve ser declrado");

		} else if (txtLocalArmazenagem.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Declarar local de armazenagem");
		} else if (cmbmedida.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(null, "Declarar local de armazenagem");
		}
//		}
//		else if (lblimg.getIcon().equals("/bin/img/produtosIcon.png")) {JOptionPane.showMessageDialog(null, "Carregar imagem");
		else {
			String update = "update produtos set produto=?,barcode=?,descricao=?,foto=?,estoque=?,estoquemin=?,valor=?,unidademedida=?,localarmazenagem=? where idproduto=?";
			try {

				con = dao.conectar();
				pst = con.prepareStatement(update);
				// TxtID, sempre ultimo

				pst.setString(10, txtidProdutos.getText());

				// resto
				pst.setString(1, txtProdutos.getText());
				pst.setString(2, txtBarcode.getText());
				pst.setString(3, txtDescricao.getText());
				pst.setBlob(4, fis);
				pst.setString(5, txtEstoque.getText());
				pst.setString(6, txtEstoqueMin.getText());
				pst.setString(7, txtValor.getText());
				pst.setString(8, String.valueOf(cmbmedida.getSelectedItem()));
				pst.setString(9, txtLocalArmazenagem.getText());

				pst.executeUpdate();
				JOptionPane.showInternalMessageDialog(null, "Editado com sucesso!");
				System.out.println("editado");
				limparcampos();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace(); // TODO: handle exception
			}
		}

	}
}
