package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;
import view.suporte.Contato;
import view.suporte.SuporteMenu;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.ComponentOrientation;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JProgressBar;
import javax.swing.JMenuItem;
import java.awt.Panel;
import java.awt.Component;
import java.awt.ScrollPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import java.awt.Button;

public class Principal extends JFrame {

	/**
	 * 
	 */
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel dbicon;
	private JPanel PanelBarraEsquerda;

	public JLabel lblUsuario;
	public JLabel lblCargo;
	public JButton btnUsuarios;
	public JButton btnRelatorios;
	public JButton btnServicos;
	public JButton btnClientes;
	public JButton btnFornecedor;
	
	private JMenuItem btnMenuFornecedor;
	private JMenuItem btnMenuRelatorio;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/IconMonitor.png")));
		setTitle("ConsoleX Ordens de Serviço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(91, 92, 93));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		dbicon = new JLabel("");
		dbicon.setBounds(717, 502, 57, 48);
		contentPane.add(dbicon);
		dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 801, 22);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		btnMenuRelatorio = new JMenuItem("Relatorio");
		btnMenuRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		mnNewMenu.add(btnMenuRelatorio);

		btnMenuFornecedor = new JMenuItem("Fornecedor");
		btnMenuFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedor forn = new Fornecedor();
				forn.setEnabled(true);
				forn.setVisible(true);
			}
		});
		mnNewMenu.add(btnMenuFornecedor);

		PanelBarraEsquerda = new JPanel();
		PanelBarraEsquerda.setRequestFocusEnabled(false);
		PanelBarraEsquerda.setLayout(null);
		PanelBarraEsquerda.setBackground(new Color(36, 36, 36));
		PanelBarraEsquerda.setBounds(0, 0, 241, 579);

		contentPane.add(PanelBarraEsquerda);

		btnRelatorios = new JButton("Relatório");
		btnRelatorios.setForeground(new Color(255, 255, 255));
		btnRelatorios.setEnabled(false);
		btnRelatorios.setContentAreaFilled(false);
		btnRelatorios.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		btnRelatorios.setHorizontalAlignment(SwingConstants.LEFT);
		btnRelatorios.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnRelatorios.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnRelatorios.setBounds(10, 376, 221, 46);
		PanelBarraEsquerda.add(btnRelatorios);
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setToolTipText("Gerador de Relatorio de clientes e serviços");
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/img/ReportIcon.png")));

		btnUsuarios = new JButton("Usuarios");
		btnUsuarios.setForeground(new Color(255, 255, 255));
		btnUsuarios.setBackground(new Color(255, 255, 255));
		btnUsuarios.setEnabled(false);
		btnUsuarios.setContentAreaFilled(false);
		btnUsuarios.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		btnUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
		btnUsuarios.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnUsuarios.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnUsuarios.setBounds(10, 141, 221, 46);
		PanelBarraEsquerda.add(btnUsuarios);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var Usuario = new Usuarios();
				Usuario.setVisible(true);
			}
		});
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setToolTipText("Criação de usuarios");
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/UserIcon.png")));

		btnClientes = new JButton("Clientes");
		btnClientes.setForeground(new Color(255, 255, 255));
		btnClientes.setEnabled(false);
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		btnClientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnClientes.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnClientes.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnClientes.setBounds(10, 185, 221, 46);
		PanelBarraEsquerda.add(btnClientes);
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setEnabled(true);
				clientes.setVisible(true);
			}
		});
		btnClientes.setIcon(new ImageIcon(Principal.class.getResource("/img/UsersIcon2.png")));
		btnClientes.setToolTipText("Criação de clientes");

		btnServicos = new JButton("Serviços");
		btnServicos.setForeground(new Color(255, 255, 255));
		btnServicos.setEnabled(false);
		btnServicos.setContentAreaFilled(false);
		btnServicos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		btnServicos.setHorizontalAlignment(SwingConstants.LEFT);
		btnServicos.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnServicos.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnServicos.setBounds(10, 332, 221, 46);
		PanelBarraEsquerda.add(btnServicos);
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servicos servicos = new Servicos();
				servicos.setVisible(true);
			}
		});
		btnServicos.setIcon(new ImageIcon(Principal.class.getResource("/img/ToolIcon.png")));
		btnServicos.setToolTipText("Declarador de Serviços");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(57, 27, 128, 128);
		PanelBarraEsquerda.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/img/ConsoleIcon.png")));
		
		btnFornecedor = new JButton("Fornecedor");
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFornecedor.setIcon(new ImageIcon(Principal.class.getResource("/img/SupplierIcon.png")));
		btnFornecedor.setToolTipText("Criação de clientes");
		btnFornecedor.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnFornecedor.setHorizontalAlignment(SwingConstants.LEFT);
		btnFornecedor.setForeground(Color.WHITE);
		btnFornecedor.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnFornecedor.setEnabled(false);
		btnFornecedor.setContentAreaFilled(false);
		btnFornecedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		btnFornecedor.setBounds(10, 238, 221, 46);
		PanelBarraEsquerda.add(btnFornecedor);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(35, 36, 37));
		panel.setBounds(0, 0, 1132, 57);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton bttnAbout = new JButton("");
		bttnAbout.setContentAreaFilled(false);
		bttnAbout.setBorderPainted(false);
		bttnAbout.setBorder(null);
		bttnAbout.setBounds(721, 23, 32, 34);
		panel.add(bttnAbout);
		bttnAbout.setToolTipText("Sobre");
		bttnAbout.addActionListener(new ActionListener() {
			// Action about ! !
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		bttnAbout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnAbout.setIcon(new ImageIcon(Principal.class.getResource("/img/AboutIcon.png")));

		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		lblNewLabel_1.setBounds(513, 482, 67, 33);
		contentPane.add(lblNewLabel_1);

		lblUsuario = new JLabel("[]");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
		lblUsuario.setBounds(590, 482, 117, 33);
		contentPane.add(lblUsuario);

		JLabel lblNewLabel_1_1 = new JLabel("Cargo:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(524, 513, 67, 33);
		contentPane.add(lblNewLabel_1_1);

		lblCargo = new JLabel("[]");
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCargo.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
		lblCargo.setBounds(590, 513, 117, 33);
		contentPane.add(lblCargo);
		status();

		setLocationRelativeTo(null);
	}
	/**
	 * Method that check if MySQL is avaible then outputs to a new Icon
	 */
	private void status() {
		try {
			con = dao.conectar();
			if (con == null) {
				dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
			} else {
				dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dbon.png")));
			}
			con.close();
		} catch (SQLException SQLe) {
			SQLe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
