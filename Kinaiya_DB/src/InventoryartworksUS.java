import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class InventoryartworksUS {

	private JFrame inventoryartwus;
	private JTextField txt_artnameus;
	private JTextField txt_datecreatedus;
	private JTextField txt_dateacquiredus;
	private JTextField txt_subus;
	private JTextField txt_artistidus;
	private JTextField txt_empidus;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryartworksUS window = new InventoryartworksUS();
					window.inventoryartwus.setVisible(true);
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
	private JTable tbl_arts;
	public InventoryartworksUS() {
		initialize();
		InvArt();
	}
	
	 public ArrayList<InventoryAWUS> ArtworksUs(){
	        ArrayList<InventoryAWUS> ArtworksUs = new ArrayList<>();
	        String query = "SELECT * FROM Art_Inventory";
	        try{
	            Connection con = DriverManager.getConnection(x.connectionUrl);
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(query);
	            
	            InventoryAWUS call;
	            while(rs.next()){
	                call = new InventoryAWUS(rs.getString("Art_ID"),rs.getString("Art_Name"),rs.getString("Art_Subject"),rs.getString("Date_Acquired"),rs.getString("Date_Created"),rs.getString("Orientation"),rs.getString("Emp_ID"),rs.getString("Artist_ID"));
	                ArtworksUs.add(call);
	            }
	        }catch(SQLException e){
	            JOptionPane.showMessageDialog(null,e);
	        }
	        return ArtworksUs;
	  }
	
	  private void InvArt() {
			// TODO Auto-generated method stub
			Clear_Table2();
	        ArrayList<InventoryAWUS> list = ArtworksUs();
	        DefaultTableModel model = (DefaultTableModel)tbl_arts.getModel();
	        Object[] row = new Object[8];
	        for (int i = 0; i < list.size(); i++) {
	            row[0]=list.get(i).getArt_ID();
	            row[1]=list.get(i).getArt_Name();
	            row[2]=list.get(i).getArt_Subject();
	            row[3]=list.get(i).getDate_Acquired();
	            row[4]=list.get(i).getDate_Created();
	            row[5]=list.get(i).getOrientation();
	            row[6]=list.get(i).getEmp_ID();
	            row[7]=list.get(i).getArtist_ID();
	            model.addRow(row);
	        }
		}
	  
	  private void Clear_Table2() {
			// TODO Auto-generated method stub
			 DefaultTableModel dm = (DefaultTableModel)tbl_arts.getModel();
		        int rowCount = dm.getRowCount();
		        for (int i = rowCount - 1; i >= 0; i--) {
		            dm.removeRow(i);
		        }
			
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inventoryartwus = new JFrame();
		inventoryartwus.setBounds(100, 100, 1140, 624);
		inventoryartwus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inventoryartwus.getContentPane().setLayout(null);
		inventoryartwus.setVisible(true);
		
		JComboBox cb_orientationus = new JComboBox();
		cb_orientationus.setFont(new Font("Verdana", Font.PLAIN, 11));
		cb_orientationus.setBounds(40, 257, 142, 43);
		cb_orientationus.setModel(new DefaultComboBoxModel(new String[] {"Select Orientation","Landscape","Portrait"}));
		inventoryartwus.getContentPane().add(cb_orientationus);
		
		JLabel lblartistsuser1_1 = new JLabel("ARTIST");
		lblartistsuser1_1.setForeground(new Color(153, 102, 51));
		lblartistsuser1_1.setFont(new Font("Verdana", Font.BOLD, 25));
		lblartistsuser1_1.setBounds(845, 128, 116, 23);
		inventoryartwus.getContentPane().add(lblartistsuser1_1);
		
		JLabel lblartworksuser1 = new JLabel("ARTWORKS");
		lblartworksuser1.setForeground(new Color(51, 0, 0));
		lblartworksuser1.setFont(new Font("Verdana", Font.BOLD, 25));
		lblartworksuser1.setBounds(475, 120, 201, 38);
		inventoryartwus.getContentPane().add(lblartworksuser1);
		
		txt_artistidus = new JTextField();
		txt_artistidus.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_artistidus.setColumns(10);
		txt_artistidus.setBounds(40, 393, 142, 43);
		inventoryartwus.getContentPane().add(txt_artistidus);
		
		JButton btn_updateus = new JButton("");
		btn_updateus.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_updateusActionPerformed(evt);
			}
			private void btn_updateusActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)tbl_arts.getModel();
			        int SelectRowIndex = tbl_arts.getSelectedRow();
			        String hold = model.getValueAt(SelectRowIndex, 0).toString();
			        String query = "UPDATE Art_Inventory set Art_Name=?, Art_Subject=?, Date_Acquired=?, Date_Created=?, Orientation=?, Emp_ID=?, Artist_ID=? WHERE Art_ID='"+hold +"'";
			        try{
			            Connection con = DriverManager.getConnection(x.connectionUrl);
			            PreparedStatement pst = con.prepareStatement(query);
			               pst.setString(1, txt_artnameus.getText());
			               pst.setString(2, txt_subus.getText());
			               pst.setString(3, txt_dateacquiredus.getText());
			               pst.setString(4, txt_datecreatedus.getText());
			               pst.setString(5, cb_orientationus.getSelectedItem().toString());   
			               pst.setString(6, txt_empidus.getText());
			               pst.setString(7, txt_artistidus.getText());  
			               int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?", "ALERT!", JOptionPane.YES_NO_OPTION); {
								if (input == JOptionPane.YES_OPTION) {
									  pst.executeUpdate();
						               JOptionPane.showMessageDialog(null, "Added successfully.");
						               InvArt();
								}
							}
			               txt_artnameus.setText("");
			               txt_subus.setText("");
			               txt_dateacquiredus.setText("");
			               txt_datecreatedus.setText("");
			               cb_orientationus.setSelectedIndex(0);
			               txt_empidus.setText("");
			               txt_artistidus.setText("");
			               
			        }catch(HeadlessException | SQLException e){
			            JOptionPane.showMessageDialog(null,e);
			        }
				
			}
		});
		Image updbtn = new ImageIcon(this.getClass().getResource("/UPDATE.png")).getImage();
		btn_updateus.setIcon(new ImageIcon(updbtn));
		btn_updateus.setBounds(63, 512, 108, 38);
		inventoryartwus.getContentPane().add(btn_updateus);
		
		JButton btn_logoutinvus = new JButton("");
		btn_logoutinvus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryartwus.dispose();
				new LoginUser();
			}
		});
		Image logout = new ImageIcon(this.getClass().getResource("/ICON LOGOUT.png")).getImage();
		btn_logoutinvus.setIcon(new ImageIcon(logout));
		btn_logoutinvus.setBounds(10, 11, 104, 36);
		inventoryartwus.getContentPane().add(btn_logoutinvus);
		
		JButton btn_addus = new JButton("");
		btn_addus.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_addusActionPerformed(evt);
			}
			private void btn_addusActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				String query = "INSERT INTO Art_Inventory (Art_Name,Art_Subject,Date_Acquired, Date_Created, Orientation,Emp_ID,Artist_ID) VALUES (?,?,?,?,?,?,?)";
		        try{
		            Connection con = DriverManager.getConnection(x.connectionUrl);
		            PreparedStatement pst = con.prepareStatement(query);
		               pst.setString(1, txt_artnameus.getText());
		               pst.setString(2, txt_subus.getText());
		               pst.setString(3, txt_dateacquiredus.getText());
		               pst.setString(4, txt_datecreatedus.getText());
		               pst.setString(5, cb_orientationus.getSelectedItem().toString());   
		               pst.setString(6, txt_empidus.getText());
		               pst.setString(7, txt_artistidus.getText());
		               int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "ALERT!", JOptionPane.YES_NO_OPTION); {
							if (input == JOptionPane.YES_OPTION) {
								  pst.executeUpdate();
					               JOptionPane.showMessageDialog(null, "Added successfully.");
					               InvArt();
							}
						}
		               
		        }catch(HeadlessException | SQLException e){
		            JOptionPane.showMessageDialog(null,e);
		        }
				
			}
		});
		Image addbtn = new ImageIcon(this.getClass().getResource("/ADD.png")).getImage();
		btn_addus.setIcon(new ImageIcon(addbtn));
		btn_addus.setBounds(63, 463, 108, 38);
		inventoryartwus.getContentPane().add(btn_addus);
		
		JButton btn_deleteus = new JButton("");
		btn_deleteus.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_deleteusActionPerformed(evt);
			}
			private void btn_deleteusActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)tbl_arts.getModel();
			        int SelectRowIndex = tbl_arts.getSelectedRow();
			        String hold = model.getValueAt(SelectRowIndex, 0).toString();
			        String query = "DELETE FROM Art_Inventory WHERE Art_ID ='"+hold +"'";
			        try{
			            Connection con = DriverManager.getConnection(x.connectionUrl);
			            PreparedStatement pst = con.prepareStatement(query);
			            int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "ALERT!", JOptionPane.YES_NO_OPTION); {
							if (input == JOptionPane.YES_OPTION) {
								  pst.executeUpdate();
					               JOptionPane.showMessageDialog(null, "Deleted successfully.");
					               InvArt();
							}
						}
			            txt_datecreatedus.setText("");
						txt_dateacquiredus.setText("");
						txt_subus.setText("");
						txt_artistidus.setText("");
						txt_empidus.setText("");
						cb_orientationus.setSelectedIndex(0);
			        }catch(HeadlessException | SQLException e){
			            JOptionPane.showMessageDialog(null,e);
			        }
			}
		});
		Image delbtn = new ImageIcon(this.getClass().getResource("/DELETE.png")).getImage();
		btn_deleteus.setIcon(new ImageIcon(delbtn));
		btn_deleteus.setBounds(206, 463, 108, 38);
		inventoryartwus.getContentPane().add(btn_deleteus);
		
		JButton btn_clearus = new JButton("");
		btn_clearus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_artnameus.setText("");
				txt_datecreatedus.setText("");
				txt_dateacquiredus.setText("");
				txt_subus.setText("");
				txt_artistidus.setText("");
				txt_empidus.setText("");
				cb_orientationus.setSelectedIndex(0);
				
			}
		});
		Image clrbtn = new ImageIcon(this.getClass().getResource("/CLEAR.png")).getImage();
		btn_clearus.setIcon(new ImageIcon(clrbtn));
		btn_clearus.setBounds(206, 512, 108, 38);
		inventoryartwus.getContentPane().add(btn_clearus);
		
		txt_artnameus = new JTextField();
		txt_artnameus.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_artnameus.setBounds(40, 191, 294, 43);
		inventoryartwus.getContentPane().add(txt_artnameus);
		txt_artnameus.setColumns(10);
		
		txt_datecreatedus = new JTextField();
		txt_datecreatedus.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_datecreatedus.setBounds(40, 325, 142, 43);
		inventoryartwus.getContentPane().add(txt_datecreatedus);
		txt_datecreatedus.setColumns(10);
		
		txt_dateacquiredus = new JTextField();
		txt_dateacquiredus.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_dateacquiredus.setBounds(192, 325, 142, 43);
		inventoryartwus.getContentPane().add(txt_dateacquiredus);
		txt_dateacquiredus.setColumns(10);
		
		txt_subus = new JTextField();
		txt_subus.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_subus.setBounds(192, 257, 142, 43);
		inventoryartwus.getContentPane().add(txt_subus);
		txt_subus.setColumns(10);
		
		JLabel lblart = new JLabel("ART NAME:");
		lblart.setForeground(new Color(51, 0, 0));
		lblart.setFont(new Font("Verdana", Font.BOLD, 15));
		lblart.setBounds(40, 170, 131, 22);
		inventoryartwus.getContentPane().add(lblart);
		
		JLabel lbldateacq = new JLabel("DATE ACQUIRED:");
		lbldateacq.setFont(new Font("Verdana", Font.BOLD, 15));
		lbldateacq.setForeground(new Color(51, 0, 0));
		lbldateacq.setBounds(191, 302, 149, 23);
		inventoryartwus.getContentPane().add(lbldateacq);
		
		JLabel lblsub = new JLabel("SUBJECT:");
		lblsub.setFont(new Font("Verdana", Font.BOLD, 15));
		lblsub.setForeground(new Color(51, 0, 0));
		lblsub.setBounds(191, 236, 82, 23);
		inventoryartwus.getContentPane().add(lblsub);
		
		JLabel lbldatecreated = new JLabel("DATE CREATED:");
		lbldatecreated.setFont(new Font("Verdana", Font.BOLD, 15));
		lbldatecreated.setForeground(new Color(51, 0, 0));
		lbldatecreated.setBounds(40, 302, 142, 23);
		inventoryartwus.getContentPane().add(lbldatecreated);
		
		JLabel lblorientation = new JLabel("ORIENTATION:");
		lblorientation.setFont(new Font("Verdana", Font.BOLD, 15));
		lblorientation.setForeground(new Color(51, 0, 0));
		lblorientation.setBounds(40, 235, 131, 25);
		inventoryartwus.getContentPane().add(lblorientation);
		
		JLabel lblartistid = new JLabel("ARTIST ID:");
		lblartistid.setFont(new Font("Verdana", Font.BOLD, 15));
		lblartistid.setForeground(new Color(51, 0, 0));
		lblartistid.setBounds(40, 375, 142, 14);
		inventoryartwus.getContentPane().add(lblartistid);
		
		JButton btnartworksuser1 = new JButton("");
		Image artworkicon = new ImageIcon(this.getClass().getResource("/ARTWORK SMALL ICON.png")).getImage();
		btnartworksuser1.setIcon(new ImageIcon(artworkicon));
		btnartworksuser1.setBounds(421, 116, 44, 42);
		inventoryartwus.getContentPane().add(btnartworksuser1);
		
		JButton btnartistuser1 = new JButton("");
		btnartistuser1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryartwus.dispose();
				new InventoryartistUS();
			}
		});
		Image artisticon = new ImageIcon(this.getClass().getResource("/ARTIST SMALL ICON.png")).getImage();
		btnartistuser1.setIcon(new ImageIcon(artisticon));
		btnartistuser1.setBounds(791, 116, 44, 42);
		inventoryartwus.getContentPane().add(btnartistuser1);
		
		txt_empidus = new JTextField();
		txt_empidus.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_empidus.setColumns(10);
		txt_empidus.setBounds(192, 393, 142, 43);
		inventoryartwus.getContentPane().add(txt_empidus);
		
		JLabel lblemployeeid = new JLabel("ADDED BY:");
		lblemployeeid.setForeground(new Color(51, 0, 0));
		lblemployeeid.setFont(new Font("Verdana", Font.BOLD, 15));
		lblemployeeid.setBounds(192, 375, 183, 14);
		
		inventoryartwus.getContentPane().add(lblemployeeid);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(350, 169, 734, 381);
		inventoryartwus.getContentPane().add(scrollPane);
		
		tbl_arts = new JTable();
		tbl_arts.setFont(new Font("Verdana", Font.PLAIN, 11));
		tbl_arts.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Art ID","Art Name","Subject","Date Acquired","Date Created","Orientation","Employee ID","Artist ID"
	            }
	        ));
		
		tbl_arts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbl_artsMouseClicked(evt);
			}

			private void tbl_artsMouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)tbl_arts.getModel();
				 int SelectRowIndex = tbl_arts.getSelectedRow();
				 txt_artnameus.setText(model.getValueAt(SelectRowIndex, 1).toString());
				 txt_subus.setText(model.getValueAt(SelectRowIndex, 2).toString());
				 txt_dateacquiredus.setText(model.getValueAt(SelectRowIndex, 3).toString());
				 txt_datecreatedus.setText(model.getValueAt(SelectRowIndex, 4).toString());
				 cb_orientationus.setSelectedItem(model.getValueAt(SelectRowIndex, 5).toString());
				 txt_empidus.setText(model.getValueAt(SelectRowIndex, 6).toString());
				 txt_artistidus.setText(model.getValueAt(SelectRowIndex, 7).toString());
			}
		});
		scrollPane.setViewportView(tbl_arts);

		JLabel lblbg = new JLabel("");
		Image lgbg = new ImageIcon(this.getClass().getResource("/INVENTORY.png")).getImage();
		lblbg.setIcon(new ImageIcon(lgbg));
		lblbg.setIcon(new ImageIcon(lgbg));
		lblbg.setBounds(0, 0, 1140, 585);
		inventoryartwus.getContentPane().add(lblbg);
	}
}
