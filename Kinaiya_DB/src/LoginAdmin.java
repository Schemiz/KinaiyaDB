import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;


public class LoginAdmin {

	private JFrame loginadmin;
	private JTextField txt_usernameadmin;
	private JPasswordField txt_passadmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAdmin window = new LoginAdmin();
					window.loginadmin.setVisible(true);
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
	public LoginAdmin() {
		initialize();
	}
	public ArrayList<REGIST_USERS> REGUSERS(){
        ArrayList<REGIST_USERS> REGUSERS = new ArrayList<>();
        String query = "SELECT * FROM Registered_Users";
        try{
            Connection con = DriverManager.getConnection(x.connectionUrl);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            REGIST_USERS call;
            while(rs.next()){
                call = new REGIST_USERS(rs.getString("Emp_Username"),rs.getString("Emp_Password"));
                REGUSERS.add(call);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return REGUSERS;
  }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginadmin = new JFrame();
		loginadmin.setBounds(100, 100, 1140, 624);
		loginadmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginadmin.getContentPane().setLayout(null);
		loginadmin.setVisible(true);
		
		txt_passadmin = new JPasswordField();
		txt_passadmin.setFont(new Font("DK Lemon Yellow Sun", Font.PLAIN, 20));
		txt_passadmin.setBounds(411, 405, 258, 49);
		loginadmin.getContentPane().add(txt_passadmin);
		
		txt_usernameadmin = new JTextField();
		txt_usernameadmin.setFont(new Font("Verdana", Font.PLAIN, 20));
		txt_usernameadmin.setBounds(411, 325, 258, 49);
		loginadmin.getContentPane().add(txt_usernameadmin);
		txt_usernameadmin.setColumns(10);
		
		JButton btn_loginadmin = new JButton("");
		btn_loginadmin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				 try{
					    String query = "SELECT * FROM Registered_Users WHERE Emp_Username=? and Emp_Password=?";
					 	Connection con = DriverManager.getConnection(x.connectionUrl);		  
					 	PreparedStatement pst = con.prepareStatement(query);		            
			            pst.setString(1, txt_usernameadmin.getText());
			            pst.setString(2, txt_passadmin.getText());
			            ResultSet rs = pst.executeQuery();	
			            if(rs.next()){
			                JOptionPane.showMessageDialog(null, "Login Successfully!");
			                new InventoryartworksUS();	
			                loginadmin.dispose();
							             
			            }
			        	else if (txt_usernameadmin.getText().isBlank() && txt_passadmin.getText().isBlank()) {
							JOptionPane.showMessageDialog( 
				                    null, "Please Input Credentials!",
							null, JOptionPane.ERROR_MESSAGE);
			        	}
			            
			            else{
			                JOptionPane.showMessageDialog(null, "Login Unsuccessful! Wrong Credenrtials!");
			                txt_usernameadmin.setText("");
			                txt_passadmin.setText("");
			            }
			            con.close();
			        }
			        catch(Exception a){
			            JOptionPane.showMessageDialog(null, a);
			        }
			     
			    }
			
				//loginadmin.dispose();
				//new DashboardAD();
			}
		);
		Image lgadm = new ImageIcon(this.getClass().getResource("/Login.png")).getImage();
		btn_loginadmin.setIcon(new ImageIcon(lgadm));
		btn_loginadmin.setBounds(446, 483, 194, 49);
		loginadmin.getContentPane().add(btn_loginadmin);
		
		
		JButton btn_backadmin = new JButton("");
		btn_backadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginadmin.dispose();
				new LoginAs();
			}
		});
		Image backbtn = new ImageIcon(this.getClass().getResource("/Back.png")).getImage();
		btn_backadmin.setIcon(new ImageIcon(backbtn));
		btn_backadmin.setBounds(10, 11, 50, 43);
		loginadmin.getContentPane().add(btn_backadmin);
		
		JLabel lblusename = new JLabel("USERNAME:");
		lblusename.setForeground(new Color(51, 0, 0));
		lblusename.setFont(new Font("Verdana", Font.BOLD, 16));
		lblusename.setBounds(410, 289, 150, 37);
		loginadmin.getContentPane().add(lblusename);
		
		JLabel lblpassword = new JLabel("PASSWORD:");
		lblpassword.setForeground(new Color(51, 0, 0));
		lblpassword.setFont(new Font("Verdana", Font.BOLD, 16));
		lblpassword.setBounds(411, 365, 150, 49);
		loginadmin.getContentPane().add(lblpassword);
		
		JLabel lblbg = new JLabel("");
		Image lgbg = new ImageIcon(this.getClass().getResource("/BG1.png")).getImage();
		lblbg.setIcon(new ImageIcon(lgbg));
		lblbg.setBounds(0, 0, 1140, 585);
		loginadmin.getContentPane().add(lblbg);
	}

}
