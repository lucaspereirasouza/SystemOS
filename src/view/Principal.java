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
		setBounds(100, 100, 781, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnUsuarios = new JButton("");
		btnUsuarios.addActionListener(new ActionListener() {
			// Action performer ! !
			public void actionPerformed(ActionEvent e) {
				// Abrir a tela de Agenda
				Usuarios Usuario = new Usuarios();
				Usuario.setVisible(true);
			}
		});

		JButton btnServicos = new JButton("");
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servicos servicos = new Servicos();
				servicos.setVisible(true);
			}
		});
		
		JButton bttnUsers_1_1_1 = new JButton("");
		bttnUsers_1_1_1.setToolTipText("Clientes");
		bttnUsers_1_1_1.setBounds(179, 216, 73, 64);
		contentPane.add(bttnUsers_1_1_1);
		
		JButton bttnUsers_1_1 = new JButton("");
		bttnUsers_1_1.setToolTipText("Clientes");
		bttnUsers_1_1.setBounds(78, 216, 73, 64);
		contentPane.add(bttnUsers_1_1);
		
		JButton bttnHome = new JButton("");
		bttnHome.setBounds(10, 21, 81, 57);
		contentPane.add(bttnHome);
		bttnHome.setEnabled(false);
		bttnHome.setIcon(new ImageIcon(Principal.class.getResource("/img/houseIcon.png")));
		btnServicos.setIcon(new ImageIcon(Principal.class.getResource("/img/ToolIcon.png")));
		btnServicos.setToolTipText("Serviços");
		btnServicos.setBounds(449, 11, 64, 64);
		contentPane.add(btnServicos);

		JButton bttnUsers_1 = new JButton("");
		bttnUsers_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnUsers_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Clientes clientes = new Clientes();
			clientes.setEnabled(true);
			clientes.setVisible(true);
			}
		});
		bttnUsers_1.setIcon(new ImageIcon(Principal.class.getResource("/img/UsersIcon2.png")));
		bttnUsers_1.setToolTipText("Clientes");
		bttnUsers_1.setBounds(366, 14, 73, 64);
		contentPane.add(bttnUsers_1);

		JButton btnRelatorios = new JButton("");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Relatorios relatorios = new Relatorios();
			relatorios.setVisible(true);
			
			}
		});
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setToolTipText("Relatório");
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/img/ReportIcon.png")));
		btnRelatorios.setBounds(521, 11, 64, 64);
		contentPane.add(btnRelatorios);

		dbicon = new JLabel("");
		dbicon.setBounds(704, 329, 57, 48);
		contentPane.add(dbicon);
		dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setToolTipText("Usuarios");
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/UserIcon.png")));
		btnUsuarios.setBounds(595, 11, 64, 64);
		contentPane.add(btnUsuarios);

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
		bttnAbout.setBounds(669, 11, 64, 64);
		contentPane.add(bttnAbout);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 32, 1112, 27);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 341, 1204, 38);
		contentPane.add(panel_1);
		
				lblDate = new JLabel("New label");
				panel_1.add(lblDate);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/img/ConsoleIcon.png")));
		lblNewLabel.setBounds(605, 110, 128, 128);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(0, -36, 189, 697);
		contentPane.add(lblNewLabel_1);
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
