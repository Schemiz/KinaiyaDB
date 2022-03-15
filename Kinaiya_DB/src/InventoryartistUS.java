import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.HeadlessException;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;


public class InventoryartistUS {

	private JFrame inventoryartus;
	private JTextField txt_artistageus;
	private JTextField txt_artistnameus;
	private JTable tableartistsus;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryartistUS window = new InventoryartistUS();
					window.inventoryartus.setVisible(true);
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
	public InventoryartistUS() {
		initialize();
		List_Artistus();
	}
	 public ArrayList<ArtistUS> ListArtistus(){
	        ArrayList<ArtistUS> ListArtistus = new ArrayList<>();
	        String query = "SELECT * FROM Artists";
	        try{
	            Connection con = DriverManager.getConnection(x.connectionUrl);
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(query);
	            
	            ArtistUS call;
	            while(rs.next()){
	                call = new ArtistUS(rs.getString("Artist_ID"),rs.getString("Artist_Name"),rs.getString("Artist_Age"));
	                ListArtistus.add(call);
	            }
	        }catch(SQLException e){
	            JOptionPane.showMessageDialog(null,e);
	        }
	        return ListArtistus;
	  }
	
	
	private void List_Artistus() {
		// TODO Auto-generated method stub
		Clear_Table3();
     ArrayList<ArtistUS> list = ListArtistus();
     DefaultTableModel model = (DefaultTableModel)tableartistsus.getModel();
     Object[] row = new Object[3];
     for (int i = 0; i < list.size(); i++) {
         row[0]=list.get(i).getArtist_ID();
         row[1]=list.get(i).getArtist_Name();
         row[2]=list.get(i).getArtist_Age();
         model.addRow(row);
     }
	}



	private void Clear_Table3() {
		// TODO Auto-generated method stub
		 DefaultTableModel dm = (DefaultTableModel)tableartistsus.getModel();
	        int rowCount = dm.getRowCount();
	        for (int i = rowCount - 1; i >= 0; i--) {
	            dm.removeRow(i);
	        }
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inventoryartus = new JFrame();
		inventoryartus.setBounds(100, 100, 1140, 624);
		inventoryartus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inventoryartus.getContentPane().setLayout(null);
		inventoryartus.setVisible(true);
		
		JButton btnartistuser2 = new JButton("");
		Image artisticon = new ImageIcon(this.getClass().getResource("/ARTIST SMALL ICON.png")).getImage();
		btnartistuser2.setIcon(new ImageIcon(artisticon));
		btnartistuser2.setBounds(791, 116, 44, 42);
		inventoryartus.getContentPane().add(btnartistuser2);
		
		JButton btnarworksuser2 = new JButton("");
		btnarworksuser2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryartus.dispose();
				new InventoryartworksUS();
			}
		});
		Image artworkicon = new ImageIcon(this.getClass().getResource("/ARTWORK SMALL ICON.png")).getImage();
		btnarworksuser2.setIcon(new ImageIcon(artworkicon));
		btnarworksuser2.setBounds(421, 116, 44, 42);
		inventoryartus.getContentPane().add(btnarworksuser2);
		
		txt_artistageus = new JTextField();
		txt_artistageus.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_artistageus.setBounds(35, 305, 226, 38);
		inventoryartus.getContentPane().add(txt_artistageus);
		txt_artistageus.setColumns(10);
		
		JLabel lblartistage = new JLabel("ARTIST AGE:");
		lblartistage.setFont(new Font("Verdana", Font.BOLD, 20));
		lblartistage.setForeground(new Color(51, 0, 0));
		lblartistage.setBounds(35, 271, 178, 23);
		inventoryartus.getContentPane().add(lblartistage);
		
		JLabel lblartistid = new JLabel("ARTIST NAME:");
		lblartistid.setFont(new Font("Verdana", Font.BOLD, 20));
		lblartistid.setForeground(new Color(51, 0, 0));
		lblartistid.setBounds(35, 188, 178, 23);
		inventoryartus.getContentPane().add(lblartistid);
		
		
		txt_artistnameus = new JTextField();
		txt_artistnameus.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_artistnameus.setBounds(35, 222, 226, 38);
		inventoryartus.getContentPane().add(txt_artistnameus);
		txt_artistnameus.setColumns(10);
		
		JButton btn_addartistus = new JButton("");
		btn_addartistus.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_addartistusActionPerformed(evt);
			}

			private void btn_addartistusActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				String query = "INSERT INTO Artists ( Artist_Name, Artist_Age) VALUES (?,?)";
		        try{
		            Connection con = DriverManager.getConnection(x.connectionUrl);
		            PreparedStatement pst = con.prepareStatement(query);
		               pst.setString(1, txt_artistnameus.getText());
		               pst.setString(2, txt_artistageus.getText());		      
		               int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "ALERT!", JOptionPane.YES_NO_OPTION); {
							if (input == JOptionPane.YES_OPTION) {
								  pst.executeUpdate();
					               JOptionPane.showMessageDialog(null, "Added successfully.");
					               List_Artistus();
							}
						}
		               
		        }catch(HeadlessException | SQLException e){
		            JOptionPane.showMessageDialog(null,e);
		        }
				
			}
		});
		Image addbtn = new ImageIcon(this.getClass().getResource("/ADD.png")).getImage();
		btn_addartistus.setIcon(new ImageIcon(addbtn));
		btn_addartistus.setBounds(35, 402, 108, 38);
		inventoryartus.getContentPane().add(btn_addartistus);
		
		JButton btn_deleteartistus = new JButton("");
		btn_deleteartistus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_deleteartistus.setBounds(153, 451, 108, 38);
		Image delbtn = new ImageIcon(this.getClass().getResource("/DELETE.png")).getImage();
		btn_deleteartistus.setIcon(new ImageIcon(delbtn));
		inventoryartus.getContentPane().add(btn_deleteartistus);
		
		JButton btn_clearartistad = new JButton("");
		btn_clearartistad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_artistnameus.setText("");
				txt_artistageus.setText("");
			}
		});
		btn_deleteartistus.setIcon(new ImageIcon("D:\\Program Files\\Eclipse Codes\\Kinaiya_Database\\Images-BG\\DELETE.png"));
		btn_deleteartistus.setBounds(153, 451, 108, 38);
		inventoryartus.getContentPane().add(btn_deleteartistus);
		
		JButton btn_clearartistus = new JButton("");
		btn_clearartistus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_artistageus.setText("");
				txt_artistnameus.setText("");
			}
		});
		Image clrbtn = new ImageIcon(this.getClass().getResource("/CLEAR.png")).getImage();
		btn_clearartistus.setIcon(new ImageIcon(clrbtn));
		btn_clearartistus.setBounds(35, 451, 108, 38);
		inventoryartus.getContentPane().add(btn_clearartistus);
		
		JButton btn_updateartistus = new JButton("");
		btn_updateartistus.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_updateartistusActionPerformed(evt);
			}
			private void btn_updateartistusActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)tableartistsus.getModel();
			        int SelectRowIndex = tableartistsus.getSelectedRow();
			        String hold = model.getValueAt(SelectRowIndex, 0).toString();
			        String query = "UPDATE Artists set Artist_Name=?, Artist_Age=? WHERE Artist_ID='"+hold +"'";
			        try{
			            Connection con = DriverManager.getConnection(x.connectionUrl);
			            PreparedStatement pst = con.prepareStatement(query);
			               pst.setString(1, txt_artistnameus.getText());
			               pst.setString(2, txt_artistageus.getText());		           
			               int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?", "ALERT!", JOptionPane.YES_NO_OPTION); {
								if (input == JOptionPane.YES_OPTION) {
									  pst.executeUpdate();
						               JOptionPane.showMessageDialog(null, "Added successfully.");
						               List_Artistus();
								}
							}
			               	
			                txt_artistnameus.setText("");
			                txt_artistageus.setText("");				
			        }catch(HeadlessException | SQLException e){
			            JOptionPane.showMessageDialog(null,e);
			        }
				
			}
		});
		Image updbtn = new ImageIcon(this.getClass().getResource("/UPDATE.png")).getImage();
		btn_updateartistus.setIcon(new ImageIcon(updbtn));
		btn_updateartistus.setBounds(153, 402, 108, 38);
		inventoryartus.getContentPane().add(btn_updateartistus);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(277, 169, 809, 382);
		inventoryartus.getContentPane().add(scrollPane);
		
		tableartistsus = new JTable();
		tableartistsus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tableartistsus.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Artist ID", "Artist Name", "Age"
	            }
	        ));
		
		tableartistsus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableartistsusMouseClicked(evt);
			}

			private void tableartistsusMouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)tableartistsus.getModel();
				 int SelectRowIndex = tableartistsus.getSelectedRow();
				txt_artistnameus.setText(model.getValueAt(SelectRowIndex, 1).toString());
			    txt_artistageus.setText(model.getValueAt(SelectRowIndex, 2).toString());
			}
		});
		scrollPane.setViewportView(tableartistsus);
		
		JLabel lblartworksuser2 = new JLabel("ARTWORKS");
		lblartworksuser2.setForeground(new Color(153, 102, 51));
		lblartworksuser2.setFont(new Font("Verdana", Font.BOLD, 25));
		lblartworksuser2.setBounds(475, 120, 201, 38);
		inventoryartus.getContentPane().add(lblartworksuser2);
		
		JLabel lblartistsuser2 = new JLabel("ARTIST");
		lblartistsuser2.setForeground(new Color(51, 0, 0));
		lblartistsuser2.setFont(new Font("Verdana", Font.BOLD, 25));
		lblartistsuser2.setBounds(845, 128, 116, 23);
		inventoryartus.getContentPane().add(lblartistsuser2);
		
		JButton btn_logoutinvus2 = new JButton("");
		btn_logoutinvus2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryartus.dispose();
				new LoginUser();
			}
		});
		Image logoutbtn = new ImageIcon(this.getClass().getResource("/ICON LOGOUT.png")).getImage();
		btn_logoutinvus2.setIcon(new ImageIcon(logoutbtn));
		btn_logoutinvus2.setBounds(10, 11, 104, 36);
		inventoryartus.getContentPane().add(btn_logoutinvus2);
		
		JLabel lblbg = new JLabel("");
		Image lgbg = new ImageIcon(this.getClass().getResource("/INVENTORY.png")).getImage();
		lblbg.setIcon(new ImageIcon(lgbg));
		lblbg.setBounds(0, 0, 1140, 585);
		inventoryartus.getContentPane().add(lblbg);
	}
}
