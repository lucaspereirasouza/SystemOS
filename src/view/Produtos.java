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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.Toolkit;

public class Produtos extends JDialog {
	private static final long serialVersionUID = 1L;

	private DAO dao;
	private Connection con;

	@SuppressWarnings("unused")
	private FileInputStream fis;
	@SuppressWarnings("unused")
	private int fisSize;

	private JLabel lblImg;
	private JTextField txtidProdutos;
	private JTextField txtValor;
	private JTextField txtLocalArmazenagem;
	private JTextField txtIdFornecedor;
	private JTextField txtestoquemin;
	private JTextField txtestoque;

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
		setBounds(100, 100, 589, 398);
		getContentPane().setLayout(null);

		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(Produtos.class.getResource("/img/produtosIcon.png")));
		lblImg.setBounds(358, 11, 140, 140);
		getContentPane().add(lblImg);

		JButton btnCarregarFoto = new JButton("Carregar foto");
		btnCarregarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadPhoto();
			}
		});
		btnCarregarFoto.setBounds(372, 176, 126, 23);
		getContentPane().add(btnCarregarFoto);

		txtidProdutos = new JTextField();
		txtidProdutos.setEnabled(false);
		txtidProdutos.setBounds(27, 58, 57, 20);
		getContentPane().add(txtidProdutos);
		txtidProdutos.setColumns(10);

		JLabel lblNewLabel = new JLabel("EstoqueMin");
		lblNewLabel.setBounds(274, 33, 68, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setBounds(207, 33, 57, 14);
		getContentPane().add(lblEstoque);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBounds(27, 333, 126, 23);
		getContentPane().add(btnAdicionar);

		JLabel lblNewLabel_1 = new JLabel("IdProdutos");
		lblNewLabel_1.setBounds(29, 33, 68, 14);
		getContentPane().add(lblNewLabel_1);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtidProdutos.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo ID deve ser preenchido.");
					txtestoque.requestFocus();
				} else {

				}
			}
		});
		btnRemover.setBounds(163, 333, 126, 23);
		getContentPane().add(btnRemover);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(207, 89, 126, 23);
		getContentPane().add(btnPesquisar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtidProdutos.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "O campo ID deve ser preenchido.");
					txtestoque.requestFocus();
				} else if (txtidProdutos.getText().isEmpty()) {

				} else {

				}
			}
		});
		btnEditar.setBounds(299, 333, 126, 23);
		getContentPane().add(btnEditar);

		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(27, 241, 113, 20);
		getContentPane().add(txtValor);

		JLabel lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setBounds(27, 218, 86, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Local armazenagem");
		lblNewLabel_2_1.setBounds(176, 218, 113, 14);
		getContentPane().add(lblNewLabel_2_1);

		txtLocalArmazenagem = new JTextField();
		txtLocalArmazenagem.setColumns(10);
		txtLocalArmazenagem.setBounds(176, 241, 126, 20);
		getContentPane().add(txtLocalArmazenagem);

		JButton btnNewButton = new JButton("Barcode");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(474, 333, 89, 23);
		getContentPane().add(btnNewButton);

		txtIdFornecedor = new JTextField();
		txtIdFornecedor.setColumns(10);
		txtIdFornecedor.setBounds(113, 58, 57, 20);
		getContentPane().add(txtIdFornecedor);

		JLabel lblNewLabel_1_1 = new JLabel("IdFornecedor");
		lblNewLabel_1_1.setBounds(115, 33, 68, 14);
		getContentPane().add(lblNewLabel_1_1);

		txtestoque = new JTextField();
		txtestoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtestoque.setColumns(10);
		txtestoque.setBounds(217, 58, 31, 20);
		getContentPane().add(txtestoque);

		txtestoquemin = new JTextField();
		txtestoquemin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtestoquemin.setColumns(10);
		txtestoquemin.setBounds(284, 58, 31, 20);
		getContentPane().add(txtestoquemin);

		txtestoque.setDocument(new Validador(3));
		txtestoquemin.setDocument(new Validador(3));
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
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblImg.getWidth(),
						lblImg.getHeight(), Image.SCALE_SMOOTH);
				lblImg.setIcon(new ImageIcon(foto));
				lblImg.updateUI();
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
	}

	private void adicionar() {
		if (txtestoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo Estoque deve ser preenchido.");
			txtestoque.requestFocus();
		} else if (txtestoquemin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo Estoque Minimo deve ser preenchido.");
			txtestoquemin.requestFocus();
		}
		else if (txtIdFornecedor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O campo Fornecedor deve ser preenchido.");
			txtestoquemin.requestFocus();
		} else {
		}
	}

	public void onlyNum(KeyEvent e) {
		char c = e.getKeyChar();
		if (Character.isLetter(c)) {
			e.consume();
		}
	}
}
