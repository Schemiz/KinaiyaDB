import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

public class InventoryartworksAD {

	private JFrame inventoryartwad;
	private JTable tbl_artsad;
	private JTextField txt_artnamead;
	private JTextField txt_datecreatedad;
	private JTextField txt_dateacquiredad;
	private JTextField txt_subad;
	private JTextField txt_artistidad;
	private JTextField txt_empidad;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryartworksAD window = new InventoryartworksAD();
					window.inventoryartwad.setVisible(true);
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
	public InventoryartworksAD() {
		initialize();
		InvArtAD();
	}
	 public ArrayList<InventoryAWAD> ArtworksAd(){
	        ArrayList<InventoryAWAD> ArtworksAd = new ArrayList<>();
	        String query = "SELECT * FROM Art_Inventory";
	        try{
	            Connection con = DriverManager.getConnection(x.connectionUrl);
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(query);
	            
	            InventoryAWAD call;
	            while(rs.next()){
	                call = new InventoryAWAD(rs.getString("Art_ID"),rs.getString("Art_Name"),rs.getString("Art_Subject"),rs.getString("Date_Acquired"),rs.getString("Date_Created"),rs.getString("Orientation"),rs.getString("Emp_ID"),rs.getString("Artist_ID"));
	                ArtworksAd.add(call);
	            }
	        }catch(SQLException e){
	            JOptionPane.showMessageDialog(null,e);
	        }
	        return ArtworksAd;
	  }
	
	  private void InvArtAD() {
			// TODO Auto-generated method stub
			Clear_Table5();
	        ArrayList<InventoryAWAD> list = ArtworksAd();
	        DefaultTableModel model = (DefaultTableModel)tbl_artsad.getModel();
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
	  
	  private void Clear_Table5() {
			// TODO Auto-generated method stub
			 DefaultTableModel dm = (DefaultTableModel)tbl_artsad.getModel();
		        int rowCount = dm.getRowCount();
		        for (int i = rowCount - 1; i >= 0; i--) {
		            dm.removeRow(i);
		        }
			
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inventoryartwad = new JFrame();
		inventoryartwad.setBounds(100, 100, 1140, 624);
		inventoryartwad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inventoryartwad.getContentPane().setLayout(null);
		inventoryartwad.setVisible(true);
		
		JComboBox cb_orientationad = new JComboBox();
		cb_orientationad.setFont(new Font("Verdana", Font.PLAIN, 11));
		cb_orientationad.setBounds(40, 257, 142, 42);
		cb_orientationad.setModel(new DefaultComboBoxModel(new String[] {"Select Orientation","Landscape","Portrait"}));
		inventoryartwad.getContentPane().add(cb_orientationad);
		
		JButton btnartworksadmin1 = new JButton("");
		btnartworksadmin1.setIcon(new ImageIcon("D:\\Program Files\\Eclipse Codes\\Kinaiya_DB\\IMG\\ARTWORK SMALL ICON.png"));
		btnartworksadmin1.setBounds(421, 116, 44, 42);
		inventoryartwad.getContentPane().add(btnartworksadmin1);
		
		JButton btnartistadmin1 = new JButton("");
		btnartistadmin1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryartwad.dispose();
				new InventoryartistAD();
			}
		});
		Image artworkicon = new ImageIcon(this.getClass().getResource("/ARTWORK SMALL ICON.png")).getImage();
		btnartistadmin1.setIcon(new ImageIcon(artworkicon));
		btnartistadmin1.setBounds(791, 116, 44, 42);
		inventoryartwad.getContentPane().add(btnartistadmin1);
		
		JButton btn_updatead = new JButton("");
		btn_updatead.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_updateadActionPerformed(evt);
			}
			private void btn_updateadActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)tbl_artsad.getModel();
			        int SelectRowIndex = tbl_artsad.getSelectedRow();
			        String hold = model.getValueAt(SelectRowIndex, 0).toString();
			        String query = "UPDATE Art_Inventory set Art_Name=?, Art_Subject=?, Date_Acquired=?, Date_Created=?, Orientation=?, Emp_ID=?, Artist_ID=? WHERE Art_ID='"+hold +"'";
			        try{
			            Connection con = DriverManager.getConnection(x.connectionUrl);
			            PreparedStatement pst = con.prepareStatement(query);
			               pst.setString(1, txt_artnamead.getText());
			               pst.setString(2, txt_subad.getText());
			               pst.setString(3, txt_dateacquiredad.getText());
			               pst.setString(4, txt_datecreatedad.getText());
			               pst.setString(5, cb_orientationad.getSelectedItem().toString());   
			               pst.setString(6, txt_empidad.getText());
			               pst.setString(7, txt_artistidad.getText());  
			               int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?", "ALERT!", JOptionPane.YES_NO_OPTION); {
								if (input == JOptionPane.YES_OPTION) {
									  pst.executeUpdate();
						               JOptionPane.showMessageDialog(null, "Added successfully.");
						               InvArtAD();
								}
							}
			               txt_artnamead.setText("");
			               txt_subad.setText("");
			               txt_dateacquiredad.setText("");
			               txt_datecreatedad.setText("");
			               cb_orientationad.setSelectedIndex(0);
			               txt_empidad.setText("");
			               txt_artistidad.setText("");
			               
			        }catch(HeadlessException | SQLException e){
			            JOptionPane.showMessageDialog(null,e);
			        }
				
			}
		});
		Image updbtn = new ImageIcon(this.getClass().getResource("/UPDATE.png")).getImage();
		btn_updatead.setIcon(new ImageIcon(updbtn));
		btn_updatead.setBounds(63, 512, 108, 38);
		inventoryartwad.getContentPane().add(btn_updatead);
		
		JButton btn_backinvad = new JButton("");
		btn_backinvad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryartwad.dispose();
				new DashboardAD();
			}
		});
		Image backbtn = new ImageIcon(this.getClass().getResource("/Back.png")).getImage();
		btn_backinvad.setIcon(new ImageIcon(backbtn));
		btn_backinvad.setBounds(10, 11, 50, 43);
		inventoryartwad.getContentPane().add(btn_backinvad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(344, 169, 741, 381);
		inventoryartwad.getContentPane().add(scrollPane);
		
		tbl_artsad = new JTable();
		tbl_artsad.setFont(new Font("Verdana", Font.PLAIN, 11));
		tbl_artsad.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Art ID","Art Name","Subject","Date Acquired","Date Created","Orientation","Employee ID","Artist ID"
	            }
	        ));
		
		tbl_artsad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbl_artsadMouseClicked(evt);
			}

			private void tbl_artsadMouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)tbl_artsad.getModel();
				 int SelectRowIndex = tbl_artsad.getSelectedRow();
				 txt_artnamead.setText(model.getValueAt(SelectRowIndex, 1).toString());
				 txt_subad.setText(model.getValueAt(SelectRowIndex, 2).toString());
				 txt_dateacquiredad.setText(model.getValueAt(SelectRowIndex, 3).toString());
				 txt_datecreatedad.setText(model.getValueAt(SelectRowIndex, 4).toString());
				 cb_orientationad.setSelectedItem(model.getValueAt(SelectRowIndex, 5).toString());
				 txt_empidad.setText(model.getValueAt(SelectRowIndex, 6).toString());
				 txt_artistidad.setText(model.getValueAt(SelectRowIndex, 7).toString());
			}
		});
		scrollPane.setViewportView(tbl_artsad);
		tbl_artsad.setFont(new Font("Verdana", Font.PLAIN, 11));
		
		JButton btn_addad = new JButton("");
		btn_addad.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_addadActionPerformed(evt);
			}
			private void btn_addadActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				String query = "INSERT INTO Art_Inventory (Art_Name,Art_Subject,Date_Acquired, Date_Created, Orientation,Emp_ID,Artist_ID) VALUES (?,?,?,?,?,?,?)";
		        try{
		            Connection con = DriverManager.getConnection(x.connectionUrl);
		            PreparedStatement pst = con.prepareStatement(query);
		               pst.setString(1, txt_artnamead.getText());
		               pst.setString(2, txt_subad.getText());
		               pst.setString(3, txt_dateacquiredad.getText());
		               pst.setString(4, txt_datecreatedad.getText());
		               pst.setString(5, cb_orientationad.getSelectedItem().toString());   
		               pst.setString(6, txt_empidad.getText());
		               pst.setString(7, txt_artistidad.getText());
		               int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "ALERT!", JOptionPane.YES_NO_OPTION); {
							if (input == JOptionPane.YES_OPTION) {
								  pst.executeUpdate();
					               JOptionPane.showMessageDialog(null, "Added successfully.");
					               InvArtAD();
							}
						}
		               
		        }catch(HeadlessException | SQLException e){
		            JOptionPane.showMessageDialog(null,e);
		        }
				
			}
		});
		Image addbtn = new ImageIcon(this.getClass().getResource("/ADD.png")).getImage();
		btn_addad.setIcon(new ImageIcon(addbtn));
		btn_addad.setBounds(63, 463, 108, 38);
		inventoryartwad.getContentPane().add(btn_addad);
		
		JButton btn_deletead = new JButton("");
		btn_deletead.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_deleteadActionPerformed(evt);
			}
			private void btn_deleteadActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)tbl_artsad.getModel();
			        int SelectRowIndex = tbl_artsad.getSelectedRow();
			        String hold = model.getValueAt(SelectRowIndex, 0).toString();
			        String query = "DELETE FROM Art_Inventory WHERE Art_ID ='"+hold +"'";
			        try{
			            Connection con = DriverManager.getConnection(x.connectionUrl);
			            PreparedStatement pst = con.prepareStatement(query);
			            int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "ALERT!", JOptionPane.YES_NO_OPTION); {
							if (input == JOptionPane.YES_OPTION) {
								  pst.executeUpdate();
					               JOptionPane.showMessageDialog(null, "Deleted successfully.");
					               InvArtAD();
							}
						}
			            txt_datecreatedad.setText("");
						txt_dateacquiredad.setText("");
						txt_subad.setText("");
						txt_artistidad.setText("");
						txt_empidad.setText("");
						cb_orientationad.setSelectedIndex(0);
			        }catch(HeadlessException | SQLException e){
			            JOptionPane.showMessageDialog(null,e);
			        }
			}
		});
		Image delbtn = new ImageIcon(this.getClass().getResource("/DELETE.png")).getImage();
		btn_deletead.setIcon(new ImageIcon(delbtn));
		btn_deletead.setBounds(206, 463, 108, 38);
		inventoryartwad.getContentPane().add(btn_deletead);
		
		JButton btn_clearad = new JButton("");
		btn_clearad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_artnamead.setText("");
				txt_datecreatedad.setText("");
				txt_dateacquiredad.setText("");
				txt_subad.setText("");
				txt_artistidad.setText("");
				txt_empidad.setText("");
				cb_orientationad.setSelectedIndex(0);
			}
		});
		Image clrbtn = new ImageIcon(this.getClass().getResource("/Clear.png")).getImage();
		btn_clearad.setIcon(new ImageIcon(clrbtn));
		btn_clearad.setBounds(206, 512, 108, 38);
		inventoryartwad.getContentPane().add(btn_clearad);
		
		txt_artnamead = new JTextField();
		txt_artnamead.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_artnamead.setBounds(40, 191, 294, 43);
		inventoryartwad.getContentPane().add(txt_artnamead);
		txt_artnamead.setColumns(10);
		
		txt_datecreatedad = new JTextField();
		txt_datecreatedad.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_datecreatedad.setBounds(40, 325, 142, 43);
		inventoryartwad.getContentPane().add(txt_datecreatedad);
		txt_datecreatedad.setColumns(10);
		
		txt_dateacquiredad = new JTextField();
		txt_dateacquiredad.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_dateacquiredad.setBounds(192, 325, 142, 43);
		inventoryartwad.getContentPane().add(txt_dateacquiredad);
		txt_dateacquiredad.setColumns(10);
		
		txt_subad = new JTextField();
		txt_subad.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_subad.setBounds(192, 257, 142, 43);
		inventoryartwad.getContentPane().add(txt_subad);
		txt_subad.setColumns(10);
		
		txt_artistidad = new JTextField();
		txt_artistidad.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_artistidad.setColumns(10);
		txt_artistidad.setBounds(40, 393, 142, 43);
		inventoryartwad.getContentPane().add(txt_artistidad);
		
		JLabel lblartad = new JLabel("ART NAME:");
		lblartad.setForeground(new Color(51, 0, 0));
		lblartad.setFont(new Font("Verdana", Font.BOLD, 15));
		lblartad.setBounds(40, 168, 131, 22);
		inventoryartwad.getContentPane().add(lblartad);
		
		JLabel lbldateacqad = new JLabel("DATE ACQUIRED:");
		lbldateacqad.setFont(new Font("Verdana", Font.BOLD, 15));
		lbldateacqad.setForeground(new Color(51, 0, 0));
		lbldateacqad.setBounds(191, 302, 160, 23);
		inventoryartwad.getContentPane().add(lbldateacqad);
		
		JLabel lblsubad = new JLabel("SUBJECT");
		lblsubad.setFont(new Font("Verdana", Font.BOLD, 15));
		lblsubad.setForeground(new Color(51, 0, 0));
		lblsubad.setBounds(191, 236, 82, 23);
		inventoryartwad.getContentPane().add(lblsubad);
		
		JLabel lbldatecreatedad = new JLabel("DATE CREATED:");
		lbldatecreatedad.setFont(new Font("Verdana", Font.BOLD, 15));
		lbldatecreatedad.setForeground(new Color(51, 0, 0));
		lbldatecreatedad.setBounds(40, 302, 160, 23);
		inventoryartwad.getContentPane().add(lbldatecreatedad);
		
		JLabel lblorientationad = new JLabel("ORIENTATION:");
		lblorientationad.setFont(new Font("Verdana", Font.BOLD, 15));
		lblorientationad.setForeground(new Color(51, 0, 0));
		lblorientationad.setBounds(40, 235, 160, 25);
		inventoryartwad.getContentPane().add(lblorientationad);
		
		JLabel lblartistidad = new JLabel("ARTIST ID:");
		lblartistidad.setFont(new Font("Verdana", Font.BOLD, 15));
		lblartistidad.setForeground(new Color(51, 0, 0));
		lblartistidad.setBounds(40, 375, 142, 14);
		inventoryartwad.getContentPane().add(lblartistidad);
		
		JLabel lblartworkadmin1 = new JLabel("ARTWORKS");
		lblartworkadmin1.setForeground(new Color(51, 0, 0));
		lblartworkadmin1.setFont(new Font("Verdana", Font.BOLD, 25));
		lblartworkadmin1.setBounds(475, 120, 201, 38);
		inventoryartwad.getContentPane().add(lblartworkadmin1);
		
		JLabel lblartistsadmin1 = new JLabel("ARTIST");
		lblartistsadmin1.setForeground(new Color(153, 102, 51));
		lblartistsadmin1.setFont(new Font("Verdana", Font.BOLD, 25));
		lblartistsadmin1.setBounds(845, 128, 116, 23);
		inventoryartwad.getContentPane().add(lblartistsadmin1);
		
		txt_empidad = new JTextField();
		txt_empidad.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_empidad.setColumns(10);
		txt_empidad.setBounds(192, 393, 142, 43);
		inventoryartwad.getContentPane().add(txt_empidad);
		
		JLabel lblempidad = new JLabel("EMPLOYEE ID:");
		lblempidad.setForeground(new Color(51, 0, 0));
		lblempidad.setFont(new Font("Verdana", Font.BOLD, 15));
		lblempidad.setBounds(192, 375, 142, 14);
		inventoryartwad.getContentPane().add(lblempidad);
		
		JLabel lblbg = new JLabel("");
		Image lgbg = new ImageIcon(this.getClass().getResource("/INVENTORY.png")).getImage();
		lblbg.setIcon(new ImageIcon(lgbg));
		lblbg.setBounds(0, 0, 1140, 585);
		inventoryartwad.getContentPane().add(lblbg);
	}

}
