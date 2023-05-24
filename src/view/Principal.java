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
	private JLabel lblDate;

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
		setBounds(100, 100, 1212, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton bttnUsers = new JButton("");
		bttnUsers.addActionListener(new ActionListener() {
			// Action performer ! !
			public void actionPerformed(ActionEvent e) {
				// Abrir a tela de Agenda
				Usuarios Usuario = new Usuarios();
				Usuario.setVisible(true);
			}
		});

		JButton bttnTool = new JButton("");
		bttnTool.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnTool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuporteMenu Suporte = new SuporteMenu();
				Suporte.setVisible(true);
			}
		});
		bttnTool.setIcon(new ImageIcon(Principal.class.getResource("/img/ToolIcon.png")));
		bttnTool.setToolTipText("Suporte");
		bttnTool.setBounds(432, 11, 64, 64);
		contentPane.add(bttnTool);

		JButton bttnUsers_1 = new JButton("");
		bttnUsers_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnUsers_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ClienteMain cliente = new ClienteMain();
			cliente.setVisible(true);
			}
		});
		bttnUsers_1.setIcon(new ImageIcon(Principal.class.getResource("/img/UsersIcon2.png")));
		bttnUsers_1.setToolTipText("Clientes");
		bttnUsers_1.setBounds(506, 11, 73, 64);
		contentPane.add(bttnUsers_1);

		JButton BttnOS = new JButton("");
		BttnOS.setEnabled(false);
		BttnOS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BttnOS.setToolTipText("Relatório");
		BttnOS.setIcon(new ImageIcon(Principal.class.getResource("/img/ReportIcon.png")));
		BttnOS.setBounds(589, 11, 64, 64);
		contentPane.add(BttnOS);

		dbicon = new JLabel("");
		dbicon.setBounds(754, 284, 57, 48);
		contentPane.add(dbicon);
		dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
		bttnUsers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnUsers.setToolTipText("Agenda");
		bttnUsers.setIcon(new ImageIcon(Principal.class.getResource("/img/UserIcon.png")));
		bttnUsers.setBounds(663, 11, 64, 64);
		contentPane.add(bttnUsers);

		JButton bttnAbout = new JButton("");
		bttnAbout.addActionListener(new ActionListener() {
			// Action about ! !
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		bttnAbout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnAbout.setIcon(new ImageIcon(Principal.class.getResource("/img/AboutIcon.png")));
		bttnAbout.setBounds(737, 11, 64, 64);
		contentPane.add(bttnAbout);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 32, 873, 27);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 294, 883, 38);
		contentPane.add(panel_1);
		
				lblDate = new JLabel("New label");
				panel_1.add(lblDate);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/img/ConsoleIcon.png")));
		lblNewLabel.setBounds(673, 115, 128, 128);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Barrel");
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(0, 0, 139, 415);
		contentPane.add(lblNewLabel_1);
		
		JButton bttnHome = new JButton("");
		bttnHome.setEnabled(false);
		bttnHome.setIcon(new ImageIcon(Principal.class.getResource("/img/houseIcon.png")));
		bttnHome.setBounds(277, 73, 64, 64);
		contentPane.add(bttnHome);
		status();
		setardata();
	}

	/*
	 * 
	 * Teste
	 */
	private void setardata() {
		Date data = new Date();
		DateFormat formatado = DateFormat.getDateInstance(DateFormat.FULL);
		lblDate.setText(formatado.format(data));
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
