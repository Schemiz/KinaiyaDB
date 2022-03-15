import java.awt.EventQueue;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;


public class ManageAD {

	private JFrame managead;
	private JTextField txt_empus;
	private JTextField txt_emppass;
	private JTextField txt_empname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageAD window = new ManageAD();
					window.managead.setVisible(true);
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
	private JTable Userstable;
	public ManageAD() {
		initialize();
		Registered_Users();
	}
		
	  public ArrayList<Users> RegUsers(){
	        ArrayList<Users> RegUsers = new ArrayList<>();
	        String query = "SELECT * FROM Registered_Users";
	        try{
	            Connection con = DriverManager.getConnection(x.connectionUrl);
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(query);
	            
	            Users call;
	            while(rs.next()){
	                call = new Users(rs.getString("Emp_ID"),rs.getString("Emp_Name"),rs.getString("Emp_Username"),rs.getString("Emp_Password"),rs.getString("Emp_Position"));
	                RegUsers.add(call);
	            }
	        }catch(SQLException e){
	            JOptionPane.showMessageDialog(null,e);
	        }
	        return RegUsers;
	  }
	
	  private void Registered_Users() {
			// TODO Auto-generated method stub
			Clear_Table1();
	        ArrayList<Users> list = RegUsers();
	        DefaultTableModel model = (DefaultTableModel)Userstable.getModel();
	        Object[] row = new Object[5];
	        for (int i = 0; i < list.size(); i++) {
	            row[0]=list.get(i).getEmp_ID();
	            row[1]=list.get(i).getEmp_Name();
	            row[2]=list.get(i).getEmp_Username();
	            row[3]=list.get(i).getEmp_Password();
	            row[4]=list.get(i).getEmp_Position();
	            model.addRow(row);
	        }
		}
	  
	  private void Clear_Table1() {
			// TODO Auto-generated method stub
			 DefaultTableModel dm = (DefaultTableModel)Userstable.getModel();
		        int rowCount = dm.getRowCount();
		        for (int i = rowCount - 1; i >= 0; i--) {
		            dm.removeRow(i);
		        }
			
		}
		
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		managead = new JFrame();
		managead.setBounds(100, 100, 1140, 624);
		managead.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managead.getContentPane().setLayout(null);
		managead.setVisible(true);
		
		JButton btn_backmanage = new JButton("");
		btn_backmanage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				managead.dispose();
				new DashboardAD();
			}
		});
		Image backbtn = new ImageIcon(this.getClass().getResource("/Back.png")).getImage();
		btn_backmanage.setIcon(new ImageIcon(backbtn));
		btn_backmanage.setBounds(10, 11, 50, 43);
		managead.getContentPane().add(btn_backmanage);
		
		JComboBox cb_position = new JComboBox();
		cb_position.setFont(new Font("Verdana", Font.PLAIN, 11));
		cb_position.setBounds(45, 333, 264, 31);
		cb_position.setModel(new DefaultComboBoxModel(new String[] {"Select Position","Admin","User"}));
		managead.getContentPane().add(cb_position);
		
		txt_empus = new JTextField();
		txt_empus.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_empus.setBounds(45, 202, 264, 31);
		managead.getContentPane().add(txt_empus);
		txt_empus.setColumns(10);
		
		txt_emppass = new JTextField();
		txt_emppass.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_emppass.setBounds(45, 267, 264, 31);
		managead.getContentPane().add(txt_emppass);
		txt_emppass.setColumns(10);
		
		JLabel lblemployeename = new JLabel("EMPLOYEE NAME:");
		lblemployeename.setForeground(new Color(51, 0, 0));
		lblemployeename.setFont(new Font("Verdana", Font.BOLD, 16));
		lblemployeename.setBounds(45, 114, 167, 20);
		managead.getContentPane().add(lblemployeename);
		
		JLabel lblusername = new JLabel("USERNAME:");
		lblusername.setFont(new Font("Verdana", Font.BOLD, 16));
		lblusername.setBounds(45, 181, 114, 14);
		managead.getContentPane().add(lblusername);
		
		JLabel lblpassword = new JLabel("PASSWORD:");
		lblpassword.setFont(new Font("Verdana", Font.BOLD, 16));
		lblpassword.setBounds(45, 244, 148, 16);
		managead.getContentPane().add(lblpassword);
		
		JLabel lbladduser = new JLabel("ADD USERS");
		lbladduser.setForeground(new Color(51, 0, 0));
		lbladduser.setFont(new Font("Verdana", Font.BOLD, 30));
		lbladduser.setBounds(78, 58, 198, 61);
		managead.getContentPane().add(lbladduser);
		
		JLabel lblexistinguser = new JLabel("LIST OF REGISTERED USERS");
		lblexistinguser.setFont(new Font("Verdana", Font.BOLD, 30));
		lblexistinguser.setForeground(new Color(51, 0, 0));
		lblexistinguser.setBounds(487, 25, 501, 69);
		managead.getContentPane().add(lblexistinguser);
		
		
		JButton btn_adduser = new JButton("");
		btn_adduser.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAddActionPerformed(evt);
			}
			private void btnAddActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				String query = "INSERT INTO Registered_Users (Emp_Name, Emp_Username, Emp_Password, Emp_Position) VALUES (?,?,?,?)";
		        try{
		            Connection con = DriverManager.getConnection(x.connectionUrl);
		            PreparedStatement pst = con.prepareStatement(query);
		               pst.setString(1, txt_empname.getText());
		               pst.setString(2, txt_empus.getText());
		               pst.setString(3, txt_emppass.getText());
		               pst.setString(4, cb_position.getSelectedItem().toString());   
		               int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "ALERT!", JOptionPane.YES_NO_OPTION); {
							if (input == JOptionPane.YES_OPTION) {
								  pst.executeUpdate();
					               JOptionPane.showMessageDialog(null, "Added successfully.");
					               Registered_Users();
							}
						}
		               
		        }catch(HeadlessException | SQLException e){
		            JOptionPane.showMessageDialog(null,e);
		        }
				
			}
		});
		btn_adduser.setBounds(51, 442, 108, 38);
		Image adduser = new ImageIcon(this.getClass().getResource("/ADD.png")).getImage();
		btn_adduser.setIcon(new ImageIcon(adduser));
		managead.getContentPane().add(btn_adduser);
		
		JButton btn_deleteuser = new JButton("");
		btn_deleteuser.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_deleteuserActionPerformed(evt);
			}
			private void btn_deleteuserActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)Userstable.getModel();
			        int SelectRowIndex = Userstable.getSelectedRow();
			        String hold = model.getValueAt(SelectRowIndex, 0).toString();
			        String query = "DELETE FROM Registered_Users WHERE Emp_ID ='"+hold +"'";
			        try{
			            Connection con = DriverManager.getConnection(x.connectionUrl);
			            PreparedStatement pst = con.prepareStatement(query);
			            int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "ALERT!", JOptionPane.YES_NO_OPTION); {
							if (input == JOptionPane.YES_OPTION) {
								  pst.executeUpdate();
					               JOptionPane.showMessageDialog(null, "Deleted successfully.");
					               Registered_Users();
							}
						}
			            txt_emppass.setText("");
						txt_empus.setText("");
						txt_empname.setText("");
			            cb_position.setSelectedIndex(0);
			        }catch(HeadlessException | SQLException e){
			            JOptionPane.showMessageDialog(null,e);
			        }
			}
		});
		btn_deleteuser.setBounds(185, 505, 108, 38);
		Image deluser = new ImageIcon(this.getClass().getResource("/DELETE.png")).getImage();
		btn_deleteuser.setIcon(new ImageIcon(deluser));
		managead.getContentPane().add(btn_deleteuser);
		
		JButton btn_clearuser = new JButton("");
		btn_clearuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_emppass.setText("");
				txt_empus.setText("");
				txt_empname.setText("");
				cb_position.setSelectedIndex(0);
			}
		});
		btn_clearuser.setBounds(51, 505, 108, 38);
		Image clruser = new ImageIcon(this.getClass().getResource("/CLEAR.png")).getImage();
		btn_clearuser.setIcon(new ImageIcon(clruser));
		managead.getContentPane().add(btn_clearuser);
		
		JButton btn_updateuser = new JButton("");
		btn_updateuser.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				 btn_updateuserActionPerformed(evt);
			}
			private void btn_updateuserActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)Userstable.getModel();
			        int SelectRowIndex = Userstable.getSelectedRow();
			        String hold = model.getValueAt(SelectRowIndex, 0).toString();
			        String query = "UPDATE Registered_Users set Emp_Name=?, Emp_Username=?,Emp_Password=?,Emp_Position=? WHERE Emp_ID='"+hold +"'";
			        try{
			            Connection con = DriverManager.getConnection(x.connectionUrl);
			            PreparedStatement pst = con.prepareStatement(query);
			               pst.setString(1, txt_empname.getText());
			               pst.setString(2, txt_empus.getText());
			               pst.setString(3, txt_emppass.getText());
			               pst.setString(4, cb_position.getSelectedItem().toString());  
			               int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?", "ALERT!", JOptionPane.YES_NO_OPTION); {
								if (input == JOptionPane.YES_OPTION) {
									  pst.executeUpdate();
						               JOptionPane.showMessageDialog(null, "Added successfully.");
						               Registered_Users();
								}
							}
			               	
							txt_empname.setText("");
							txt_empus.setText("");
							txt_emppass.setText("");
							cb_position.setSelectedIndex(0);
			               
			        }catch(HeadlessException | SQLException e){
			            JOptionPane.showMessageDialog(null,e);
			        }
				
			}
		});
		btn_updateuser.setBounds(185, 442, 108, 38);
		Image upduser = new ImageIcon(this.getClass().getResource("/UPDATE.png")).getImage();
		btn_updateuser.setIcon(new ImageIcon(upduser));
		managead.getContentPane().add(btn_updateuser);
		
		txt_empname = new JTextField();
		txt_empname.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_empname.setColumns(10);
		txt_empname.setBounds(45, 139, 264, 31);
		managead.getContentPane().add(txt_empname);
		
		JLabel lblposition = new JLabel("POSITION:");
		lblposition.setFont(new Font("Verdana", Font.BOLD, 16));
		lblposition.setBounds(45, 309, 148, 16);
		managead.getContentPane().add(lblposition);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(361, 89, 723, 454);
		managead.getContentPane().add(scrollPane);
		
		Userstable = new JTable();
		Userstable.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Employee ID", "Employee Name", "Username","Password","Position"
	            }
	        ));
		
		Userstable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				UserstableMouseClicked(evt);
			}

			private void UserstableMouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)Userstable.getModel();
				 int SelectRowIndex = Userstable.getSelectedRow();
				txt_empname.setText(model.getValueAt(SelectRowIndex, 1).toString());
			    txt_empus.setText(model.getValueAt(SelectRowIndex, 2).toString());
			    txt_emppass.setText(model.getValueAt(SelectRowIndex, 3).toString());
			    cb_position.setSelectedItem(model.getValueAt(SelectRowIndex, 4).toString());
			}
		});
		scrollPane.setViewportView(Userstable);
		
		JLabel lblbg = new JLabel("");
		Image lgbg = new ImageIcon(this.getClass().getResource("/BG.png")).getImage();
		lblbg.setIcon(new ImageIcon(lgbg));
		lblbg.setBounds(0, 0, 1140, 585);
		managead.getContentPane().add(lblbg);
			
		
	}
}