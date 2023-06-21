package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;
import view.OS.ClienteMain;
import view.suporte.Contato;
import view.suporte.SuporteMenu;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private JButton bttnHome;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/IconMonitor.png")));
		setResizable(false);
		setTitle("ConsoleX Ordens de Serviço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 423);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		bttnHome = new JButton("");
		bttnHome.setBorder(null);
		bttnHome.setBorderPainted(false);
		bttnHome.setBounds(251, 33, 73, 57);
		contentPane.add(bttnHome);
		bttnHome.setToolTipText("Abrir Menu");
		bttnHome.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));

		bttnHome.addActionListener(new ActionListener() {
			public Boolean ValueTest = true;

			public void actionPerformed(ActionEvent e) {
				if (ValueTest == true) {
					bttnHome.setLocation(251, 33);
					ValueTest = false;
					System.out.println("Valor agora é FALSE");
					PanelBarraEsquerda.setVisible(true);
					System.out.println("Barra visivel");
				} else {
					bttnHome.setLocation(10, 33);
					ValueTest = true;
					System.out.println("Valor agora é TRUE");
					PanelBarraEsquerda.setVisible(false);
					System.out.println("Barra Invisivel");
				}
			}
		});
		bttnHome.setIcon(new ImageIcon(Principal.class.getResource("/img/MenuIcon.png")));

		dbicon = new JLabel("");
		dbicon.setBounds(704, 329, 57, 48);
		contentPane.add(dbicon);
		dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 761, 22);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Relatorio");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Fornecedor");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedor forn= new Fornecedor();
				forn.setEnabled(true);
				forn.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Produtos");
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_3);

		PanelBarraEsquerda = new JPanel();
		PanelBarraEsquerda.setRequestFocusEnabled(false);
		PanelBarraEsquerda.setLayout(null);
		PanelBarraEsquerda.setBackground(new Color(36, 107, 128));
		PanelBarraEsquerda.setBounds(0, 0, 241, 384);
		contentPane.add(PanelBarraEsquerda);

		JButton btnRelatorios = new JButton("Relatório");
		btnRelatorios.setContentAreaFilled(false);
		btnRelatorios.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		btnRelatorios.setHorizontalAlignment(SwingConstants.LEFT);
		btnRelatorios.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnRelatorios.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnRelatorios.setBounds(10, 315, 221, 46);
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

		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.setContentAreaFilled(false);
		btnUsuarios.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		btnUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
		btnUsuarios.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnUsuarios.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnUsuarios.setBounds(10, 183, 221, 46);
		PanelBarraEsquerda.add(btnUsuarios);
		btnUsuarios.addActionListener(new ActionListener() {
			// Action performer ! !
			public void actionPerformed(ActionEvent e) {
				// Abrir a tela de Agenda
				Usuarios Usuario = new Usuarios();
				Usuario.setVisible(true);
			}
		});
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setToolTipText("Criação de usuarios");
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/UserIcon.png")));

		JButton bttnUsers_1 = new JButton("Clientes");
		bttnUsers_1.setContentAreaFilled(false);
		bttnUsers_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		bttnUsers_1.setHorizontalAlignment(SwingConstants.LEFT);
		bttnUsers_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		bttnUsers_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		bttnUsers_1.setBounds(10, 227, 221, 46);
		PanelBarraEsquerda.add(bttnUsers_1);
		bttnUsers_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnUsers_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setEnabled(true);
				clientes.setVisible(true);
			}
		});
		bttnUsers_1.setIcon(new ImageIcon(Principal.class.getResource("/img/UsersIcon2.png")));
		bttnUsers_1.setToolTipText("Criação de clientes");

		JButton btnServicos = new JButton("Serviços");
		btnServicos.setContentAreaFilled(false);
		btnServicos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		btnServicos.setHorizontalAlignment(SwingConstants.LEFT);
		btnServicos.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnServicos.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnServicos.setBounds(10, 271, 221, 46);
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

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 26, 128, 128);
		PanelBarraEsquerda.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/img/ConsoleIcon.png")));

		JButton bttnUsers_1_1 = new JButton("");
		bttnUsers_1_1.setBounds(682, 254, 73, 64);
		contentPane.add(bttnUsers_1_1);
		bttnUsers_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Apertado");
			}
		});
		bttnUsers_1_1.setToolTipText("Clientes");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(36, 107, 128));
		panel.setBounds(0, 0, 1132, 57);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton bttnAbout = new JButton("");
		bttnAbout.setBorderPainted(false);
		bttnAbout.setBorder(null);
		bttnAbout.setBounds(721, 21, 32, 36);
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

		JLabel lblNewLabel_1 = new JLabel("[Insira cargo aqui]");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		lblNewLabel_1.setBounds(543, 348, 151, 14);
		contentPane.add(lblNewLabel_1);
		status();
		setardata();
		comeco();
	}

	/*
	 * 
	 * Teste
	 */
	private void setardata() {
		Date data = new Date();
		DateFormat formatado = DateFormat.getDateInstance(DateFormat.FULL);
	}

	private void comeco() {
		bttnHome.setLocation(10, 33);
		System.out.println("Valor agora é TRUE");
		PanelBarraEsquerda.setVisible(false);
		System.out.println("Barra Invisivel");
	}

	private void status() {
		try {
			// abrir a conexão

			con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de conexão");
				dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
			} else {
				// System.out.println("Banco conectado");
				dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dbon.png")));
			}
			// NUNCA esquecer de fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do método status()
}
