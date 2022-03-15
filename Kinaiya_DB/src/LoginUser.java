import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginUser {

	private JFrame loginuser;
	private JTextField txt_usernameuser;
	private JPasswordField txt_passuser;
	public ArrayList <String> employee = new ArrayList<String>();
	public ArrayList <String> admin = new ArrayList<String>();
	public ArrayList <String> password = new ArrayList <String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUser window = new LoginUser();
					window.loginuser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	SQL_connection x = new SQL_connection();
	public LoginUser() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
			// Admin Account
			admin.add("admin");
			password.add("123");
			admin.add("admin");
		
		
		loginuser = new JFrame();
		loginuser.setBounds(100, 100, 1140, 624);
		loginuser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginuser.getContentPane().setLayout(null);
		loginuser.setVisible(true);

		JButton btn_backuser = new JButton("");
		btn_backuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginuser.dispose();
				new LoginAs();
			}
		});
		Image backuser = new ImageIcon(this.getClass().getResource("/Back.png")).getImage();
		btn_backuser.setIcon(new ImageIcon(backuser));
		btn_backuser.setBounds(10, 11, 50, 43);
		loginuser.getContentPane().add(btn_backuser);
		
		
		txt_passuser = new JPasswordField();
		txt_passuser.setFont(new Font("DK Lemon Yellow Sun", Font.PLAIN, 20));
		txt_passuser.setBounds(411, 405, 258, 49);
		loginuser.getContentPane().add(txt_passuser);
		
		txt_usernameuser = new JTextField();
		txt_usernameuser.setFont(new Font("Verdana", Font.PLAIN, 20));
		txt_usernameuser.setBounds(411, 325, 258, 49);
		loginuser.getContentPane().add(txt_usernameuser);
		txt_usernameuser.setColumns(10);
		
		JButton btn_loginuser = new JButton("");
		btn_loginuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (admin.contains(txt_usernameuser.getText()) && password.contains(txt_passuser.getText())) {
					JOptionPane.showMessageDialog(null, "Login Successfully!");
					new InventoryartworksUS();	
					loginuser.dispose();
		
			
				}
				else if (txt_usernameuser.getText().isBlank() && txt_passuser.getText().isBlank()) {
					JOptionPane.showMessageDialog( 
		                    null, "Please Input Credentials!",
					null, JOptionPane.ERROR_MESSAGE);
				}
				
				else {
					JOptionPane.showMessageDialog( 
		                    null, "Login Unsuccessful! Wrong Credentials.",
					null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
				//loginuser.dispose();
				//new InventoryartworksUS();
			//}
		//});
		Image lgbtn = new ImageIcon(this.getClass().getResource("/Login.png")).getImage();
		btn_loginuser.setIcon(new ImageIcon(lgbtn));
		btn_loginuser.setBounds(446, 483, 194, 49);
		loginuser.getContentPane().add(btn_loginuser);
		
		
		JLabel lblusename = new JLabel("USERNAME:");
		lblusename.setBackground(Color.WHITE);
		lblusename.setForeground(new Color(51, 0, 0));
		lblusename.setFont(new Font("Verdana", Font.BOLD, 16));
		lblusename.setBounds(410, 289, 150, 37);
		loginuser.getContentPane().add(lblusename);
		
		JLabel lblpassword = new JLabel("PASSWORD:");
		lblpassword.setBackground(new Color(51, 0, 0));
		lblpassword.setForeground(new Color(51, 0, 0));
		lblpassword.setFont(new Font("Verdana", Font.BOLD, 16));
		lblpassword.setBounds(411, 365, 150, 49);
		loginuser.getContentPane().add(lblpassword);
		
		JLabel lblbg = new JLabel("");
		Image lgbg = new ImageIcon(this.getClass().getResource("/BG1.png")).getImage();
		lblbg.setIcon(new ImageIcon(lgbg));
		lblbg.setBounds(0, 0, 1140, 585);
		loginuser.getContentPane().add(lblbg);
	}
}
