package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import model.DAOclientes;
import util.Validador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;

public class Servicos extends JDialog {

	private static final long serialVersionUID = 1L;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pst;
	DAO dao = new DAO();

	private JTextField txtOS;
	private JTextField txtDate;
	private JTextField txtEquipamento;
	private JTextField txtDefeito;
	private JTextField txtValor;
	private JTextField txtID;
	private JButton btnEditar;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnApagar;
	private JTextField txtCliente;
	private JScrollPane scrollPane;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servicos dialog = new Servicos();
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
	public Servicos() {
		setTitle("Serviços");
		setBounds(100, 100, 602, 292);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("OS");
		lblNewLabel.setBounds(28, 24, 52, 20);
		getContentPane().add(lblNewLabel);

		txtOS = new JTextField();
		txtOS.setEnabled(false);
		txtOS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});

		txtOS.setBounds(90, 24, 107, 20);
		getContentPane().add(txtOS);
		txtOS.setColumns(10);
		txtOS.setDocument(new Validador(5));

		JLabel lblDatwe = new JLabel("Date");
		lblDatwe.setBounds(28, 55, 52, 20);
		getContentPane().add(lblDatwe);

		txtDate = new JTextField();
		txtDate.setEnabled(false);
		txtDate.setColumns(10);
		txtDate.setBounds(102, 55, 195, 20);
		getContentPane().add(txtDate);

		JLabel lblEquipamentio = new JLabel("Equipamento");
		lblEquipamentio.setBounds(28, 86, 97, 20);
		getContentPane().add(lblEquipamentio);

		txtEquipamento = new JTextField();
		txtEquipamento.setColumns(10);
		txtEquipamento.setBounds(135, 86, 162, 20);
		txtEquipamento.setDocument(new Validador(200));
		getContentPane().add(txtEquipamento);

		JLabel lblNewLabel_1_1 = new JLabel("Defeito");
		lblNewLabel_1_1.setBounds(28, 123, 52, 20);
		getContentPane().add(lblNewLabel_1_1);

		txtDefeito = new JTextField();
		txtDefeito.setColumns(10);
		txtDefeito.setBounds(90, 123, 207, 20);
		txtDefeito.setDocument(new Validador(200));
		getContentPane().add(txtDefeito);

		JLabel lblNewLabel_1_2 = new JLabel("valor");
		lblNewLabel_1_2.setBounds(28, 154, 52, 20);
		getContentPane().add(lblNewLabel_1_2);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtValor.setColumns(10);
		txtValor.setBounds(112, 154, 125, 20);
		txtOS.setDocument(new Validador(10));
		getContentPane().add(txtValor);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(Servicos.class.getResource("/img/cliAdd.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBounds(10, 208, 138, 41);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(Servicos.class.getResource("/img/cliEdit.png")));
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(158, 208, 125, 41);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(Servicos.class.getResource("/img/cliRemove.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			excluir();
			}
		});
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnExcluir.setBounds(293, 208, 125, 41);
		getContentPane().add(btnExcluir);

		JButton btnBuscar = new JButton("Pesquisar");
		btnBuscar.setIcon(new ImageIcon(Servicos.class.getResource("/img/search.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(207, 11, 162, 40);
		getContentPane().add(btnBuscar);

		btnApagar = new JButton("");
		btnApagar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparcampos();
			}
		});
		btnApagar.setIcon(new ImageIcon(Servicos.class.getResource("/img/erase.png")));
		btnApagar.setBounds(512, 154, 64, 64);
		getContentPane().add(btnApagar);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(335, 55, 207, 94);
		getContentPane().add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(10, 40, 187, 43);
		panel.add(scrollPane);

		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			cliqueCLI();}
		});
		scrollPane.setViewportView(list);

		txtID = new JTextField();
		txtID.setBounds(33, 61, 65, 14);
		panel.add(txtID);
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtID.setColumns(10);
		txtID.setDocument(new Validador(5));

		txtCliente = new JTextField();
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscarCLI();
			}
		});
		txtCliente.setColumns(10);
		txtCliente.setBounds(10, 22, 187, 20);
		panel.add(txtCliente);

		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(10, 61, 46, 14);
		panel.add(lblNewLabel_1);

	}

	public void limparcampos() {
		// Nulifica todos os campos
		txtOS.setText(null);
		txtID.setText(null);
		txtDate.setText(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtValor.setText(null);
	}

	public void adicionar() {
		if (txtID.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo ID não pode estar vazio.");
			txtID.requestFocus();
		} else if (txtID.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo ID não pode estar vazio.");
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo txtDefeito não pode estar vazio.");
			txtDefeito.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo txtDefeito não pode estar vazio.");
			txtDefeito.requestFocus();
		} else {
			try {
				con = dao.conectar();
				String comando = "insert into servicos (id,equipamento,defeito,valor) value (?,?,?,?)";

				pst = con.prepareStatement(comando);
				pst.setString(1, txtID.getText());
				pst.setString(2, txtEquipamento.getText());
				pst.setString(3, txtDefeito.getText());
				pst.setString(4, txtValor.getText());

				pst.executeUpdate();
				con.close();

				limparcampos();
				JOptionPane.showInternalMessageDialog(null, "Adicionado com sucesso");
				btnEditar.setEnabled(true);
			} catch (SQLException se) {
				se.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}//

	public void excluir() {
		con = dao.conectar();
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				String delete = "delete from servicos where id = ?;";
				
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "servico removidos com sucesso");

				limparcampos();
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

		}
	}

	public void buscar() {
		// Captura do numero da OS (sem usar a caixa de texto)
		String numOS = JOptionPane.showInputDialog("Numero da os:");
		try {
			String comando = "select * from servicos where os = ? ";

			con = dao.conectar();
			pst = con.prepareStatement(comando);
			pst.setString(1, numOS);
			rs = pst.executeQuery();
			if (rs.next()) {
				txtOS.setText(rs.getString(1));
//				txtOS.setText(numOS);
				txtDate.setText(rs.getString(2));
				txtEquipamento.setText(rs.getString(3));
				txtDefeito.setText(rs.getString(4));
				txtValor.setText(rs.getString(5));
				txtID.setText(rs.getString(6));

//			OS
//			dataOS
//			equipame
//			defeito
//			valor
//			id

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editar() {
		String comando = "update servicos set dataOS=?,equipamento=?,defeito=?,valor=? where os=?";
		if (txtID.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo ID não pode estar vazio.");
			txtID.requestFocus();
		} else if (txtID.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo ID não pode estar vazio.");
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo txtDefeito não pode estar vazio.");
			txtDefeito.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo txtDefeito não pode estar vazio.");
			txtDefeito.requestFocus();
		} else {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);

				pst.setString(1, txtDate.getText());
				pst.setString(2, txtEquipamento.getText());
				pst.setString(3, txtDefeito.getText());
				pst.setString(4, txtValor.getText());
				pst.setString(5, txtOS.getText());

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados editados com sucesso.");
				limparcampos();

				con.close();
			} catch (SQLException se) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, se);
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	public void buscarCLI() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		list.setModel(modelo);
		String type = "Select * from clientes where nome like '" + txtCliente.getText() + "%'" + " order by nome ";
		try {

			con = dao.conectar();
			pst = con.prepareStatement(type);
			rs = pst.executeQuery();
			System.out.println("Conexão");
			while (rs.next()) {
				list.setVisible(true);
				scrollPane.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtCliente.getText().isEmpty()) {
//					System.out.println("Condição");
					list.setVisible(false);
					scrollPane.setVisible(false);
				}
			}
			con.close();
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void onlyNum(KeyEvent e) {
		char c = e.getKeyChar();
		if (Character.isLetter(c)) {
			e.consume();

		}
	}

	public void cliqueCLI() {
		int linha = list.getSelectedIndex();
		String comando = "Select * from clientes where nome like '" + txtCliente.getText() + "%'" + " order by nome limit "
				+ (linha) + ", 1";
		if (linha >= 0) {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);
				rs = pst.executeQuery();

				if (rs.next()) {
					scrollPane.setVisible(false);
					txtCliente.setText(rs.getString(2));
					txtID.setText(rs.getString(1));
					
				}
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		} else {
			scrollPane.setVisible(false);
		}
	}

}
