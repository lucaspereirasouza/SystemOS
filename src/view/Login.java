package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Principal principal = new Principal();

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JLabel dbicon;
	private JLabel lblEsqueciMinhaSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void logar() {
		// Criar uam variavel objeto para caputrar a senha

		String capturaSenha = new String(txtSenha.getPassword());

		String read = "select * from usuarios where login = ? and senha = md5(?)";

		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "PREENCHA O LOGIN");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "PREENCHA A SENHA");
			txtSenha.requestFocus();
		} else {
			try {
				// Logica principal
				con = dao.conectar();
				pst = con.prepareStatement(read);

				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				rs = pst.executeQuery();
				if (rs.next()) {
					// Capturar o perfil do usuario
					String perfil = rs.getString(5);
					// Condição Admin

					if (perfil.equals("admin")) {
						// Principal Window visibility
						principal.setVisible(true);
						// Setting Usuario and Cargo fields from data
						principal.lblUsuario.setText(rs.getString(2));
						principal.lblCargo.setText(rs.getString(5));

						principal.btnUsuarios.setEnabled(true);
						principal.btnClientes.setEnabled(true);
						principal.btnRelatorios.setEnabled(true);
						principal.btnServicos.setEnabled(true);

						// Fechar banco
						con.close();
						// Fechar janela
						this.dispose();
					} else if (perfil.equals("user")) {
						principal.setVisible(true);
						principal.lblUsuario.setText(rs.getString(2));
						principal.lblCargo.setText(rs.getString(5));
						con.close();
						// Remoção total da janela
						// Memória solta para o Sistema
						this.dispose();
					} else {

					}
				} else {
					JOptionPane.showInternalMessageDialog(null, "Login ou senha invalida");
				}
			}catch(java.lang.NullPointerException Nulle) {
				JOptionPane.showMessageDialog(null, "Falha no banco de dados, Por favor, reinicie o aplicativo.");
			}catch(SQLException SQLe) {SQLe.printStackTrace();}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/ConsoleIcon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		dbicon = new JLabel("");
		dbicon.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		dbicon.setBounds(594, 202, 57, 48);
		contentPane.add(dbicon);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(186, 67, 293, 33);
		contentPane.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(186, 111, 293, 23);
		contentPane.add(txtLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha.setBounds(262, 159, 130, 28);
		contentPane.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(262, 198, 130, 14);
		contentPane.add(txtSenha);

		JButton bttnAcessar = new JButton("Acessar");
		getContentPane().add(bttnAcessar);
		getRootPane().setDefaultButton(bttnAcessar);
		bttnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}

		});

		bttnAcessar.setBounds(470, 202, 114, 33);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(36, 107, 128));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 223, 674, 27);
		contentPane.add(lblNewLabel);

		lblEsqueciMinhaSenha = new JLabel("Esqueci minha senha");
		lblEsqueciMinhaSenha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEsqueciMinhaSenha.setForeground(Color.BLACK);
		lblEsqueciMinhaSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String name = JOptionPane.showInputDialog("Por favor, insira o nome de usuario com administrador:");
				String pass = JOptionPane.showInputDialog("Insira a senha:");

				try {
					String comando = "SELECT * FROM usuarios where nome=? and senha=md5(?)";
					con = dao.conectar();
					pst = con.prepareStatement(comando);
					pst.setString(1, name);
					pst.setString(2, pass);

					rs = pst.executeQuery();

					if (rs.next()) {

						String perfil = rs.getString(5);
						if (perfil.equals("admin")) {
								String nam = txtLogin.getText();
								
								var esquecisenha = new EsqueciMinhaSenha();
								esquecisenha.setVisible(true);
								esquecisenha.txtNome.setText(nam);
								
								
							
//							var esqueci = new EsqueciMinhaSenha();
//							esqueci.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Login não autorizado");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Erro no login");
					}

				} catch (SQLException SQLe) {
					// TODO: handle exception
					SQLe.printStackTrace();
				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}

				

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblEsqueciMinhaSenha.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblEsqueciMinhaSenha.setForeground(Color.BLACK);
			}
		});
		lblEsqueciMinhaSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsqueciMinhaSenha.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEsqueciMinhaSenha.setBounds(139, 197, 114, 17);
		contentPane.add(lblEsqueciMinhaSenha);
		status();
		setLocationRelativeTo(null);
	}

	private void status() {
		try {
			con = dao.conectar();
			if (con == null) {
			
				dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
			} else {
				
				dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dbon.png")));
			}
			con.close();
		} catch (Exception e) {

		}
	}//
}
