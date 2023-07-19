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

public class Produtos extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private DAO dao;
	private Connection con;
	
	private FileInputStream fis;
	private int fisSize;
	
	private JLabel labelImg;
	
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
		
		labelImg = new JLabel("");
		labelImg.setIcon(new ImageIcon(Produtos.class.getResource("/img/produtosIcon.png")));
		labelImg.setBounds(335, 152, 140, 140);
		getContentPane().add(labelImg);
		
		JButton btnNewButton = new JButton("Carregar foto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			LoadPhoto();
			}
		});
		btnNewButton.setBounds(175, 210, 126, 23);
		getContentPane().add(btnNewButton);

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
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(labelImg.getWidth(), labelImg.getHeight(), Image.SCALE_SMOOTH);
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
	}
}
