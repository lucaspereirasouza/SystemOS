package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.DAO;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Produtos extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private DAO dao;
	private Connection con;
	
	private FileInputStream fis;
	private int fisSize;
	
	private JLabel lblImg;
	private JTextField txtidProdutos;
	private JTextField txtEstoque;
	private JTextField txtEstoqueMin;
	
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
		setBounds(100, 100, 589, 450);
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
		btnCarregarFoto.setBounds(222, 80, 126, 23);
		getContentPane().add(btnCarregarFoto);
		
		txtidProdutos = new JTextField();
		txtidProdutos.setBounds(27, 32, 86, 20);
		getContentPane().add(txtidProdutos);
		txtidProdutos.setColumns(10);
		
		txtEstoque = new JTextField();
		txtEstoque.setBounds(27, 122, 86, 20);
		getContentPane().add(txtEstoque);
		txtEstoque.setColumns(10);
		
		txtEstoqueMin = new JTextField();
		txtEstoqueMin.setBounds(27, 176, 86, 20);
		getContentPane().add(txtEstoqueMin);
		txtEstoqueMin.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("EstoqueMin");
		lblNewLabel.setBounds(27, 153, 86, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setBounds(27, 100, 86, 14);
		getContentPane().add(lblEstoque);

	}
	
	private void LoadPhoto() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Selecionar arquivo");
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens (*.PNG,*.JPG,*.JPEG)","png","jpg","jpeg"));
		int rs = jfc.showOpenDialog(this);
		if(rs==JFileChooser.APPROVE_OPTION) {
			try {
				fis = new FileInputStream(jfc.getSelectedFile());
				fisSize = (int) jfc.getSelectedFile().length();
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
				lblImg.setIcon(new ImageIcon(foto));
				lblImg.updateUI();
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
	}
}
