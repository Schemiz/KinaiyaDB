import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.AbstractButton;
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
import javax.swing.JScrollPane;

public class InventoryartistAD {

	private JFrame inventoryartad;
	private JTextField txt_artistad;
	private JTable tableartistad;
	private JTextField txt_artistagead;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryartistAD window = new InventoryartistAD();
					window.inventoryartad.setVisible(true);
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
	public InventoryartistAD() {
		initialize();
		List_Artistad();
	}
	 public ArrayList<ArtistAD> ListArtistad(){
	        ArrayList<ArtistAD> ListArtistad = new ArrayList<>();
	        String query = "SELECT * FROM Artists";
	        try{
	            Connection con = DriverManager.getConnection(x.connectionUrl);
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(query);
	            
	            ArtistAD call;
	            while(rs.next()){
	                call = new ArtistAD(rs.getString("Artist_ID"),rs.getString("Artist_Name"),rs.getString("Artist_Age"));
	                ListArtistad.add(call);
	            }
	        }catch(SQLException e){
	            JOptionPane.showMessageDialog(null,e);
	        }
	        return ListArtistad;
	  }
	
	
	private void List_Artistad() {
		// TODO Auto-generated method stub
		Clear_Table4();
      ArrayList<ArtistAD> list = ListArtistad();
      DefaultTableModel model = (DefaultTableModel)tableartistad.getModel();
      Object[] row = new Object[3];
	  for (int i = 0; i < list.size(); i++) {
	      row[0]=list.get(i).getArtist_ID();
	      row[1]=list.get(i).getArtist_Name();
	      row[2]=list.get(i).getArtist_Age();
	      model.addRow(row);
	  }
		}



	private void Clear_Table4() {
		// TODO Auto-generated method stub
		 DefaultTableModel dm = (DefaultTableModel)tableartistad.getModel();
	        int rowCount = dm.getRowCount();
	        for (int i = rowCount - 1; i >= 0; i--) {
	            dm.removeRow(i);
	        }
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inventoryartad = new JFrame();
		inventoryartad.setBounds(100, 100, 1140, 624);
		inventoryartad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inventoryartad.getContentPane().setLayout(null);
		inventoryartad.setVisible(true);
		
		JButton btnarworksadmin2 = new JButton("");
		btnarworksadmin2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryartad.dispose();
				new InventoryartworksAD();
			}
		});
		Image artworkicon = new ImageIcon(this.getClass().getResource("/ARTWORK SMALL ICON.png")).getImage();
		btnarworksadmin2.setIcon(new ImageIcon(artworkicon));
		btnarworksadmin2.setBounds(421, 116, 44, 42);
		inventoryartad.getContentPane().add(btnarworksadmin2);
		
		JButton btnartistadmin2 = new JButton("");
		Image artisticon = new ImageIcon(this.getClass().getResource("/ARTIST SMALL ICON.png")).getImage();
		btnartistadmin2.setIcon(new ImageIcon(artisticon));
		btnartistadmin2.setBounds(791, 116, 44, 42);
		inventoryartad.getContentPane().add(btnartistadmin2);
		
		txt_artistad = new JTextField();
		txt_artistad.setBounds(35, 217, 226, 38);
		txt_artistad.setFont(new Font("Verdana", Font.PLAIN, 11));
		inventoryartad.getContentPane().add(txt_artistad);
		txt_artistad.setColumns(10);
		
		JLabel lblartist = new JLabel("ARTIST NAME:");
		lblartist.setBounds(35, 183, 201, 23);
		lblartist.setFont(new Font("Verdana", Font.BOLD, 20));
		lblartist.setForeground(new Color(51, 0, 0));
		inventoryartad.getContentPane().add(lblartist);
		
		JButton btn_addartistad = new JButton("");
		btn_addartistad.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_addartistadActionPerformed(evt);
			}

			private void btn_addartistadActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				String query = "INSERT INTO Artists ( Artist_Name, Artist_Age) VALUES (?,?)";
		        try{
		            Connection con = DriverManager.getConnection(x.connectionUrl);
		            PreparedStatement pst = con.prepareStatement(query);
		               pst.setString(1, txt_artistad.getText());
		               pst.setString(2, txt_artistagead.getText());		      
		               int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "ALERT!", JOptionPane.YES_NO_OPTION); {
							if (input == JOptionPane.YES_OPTION) {
								  pst.executeUpdate();
					               JOptionPane.showMessageDialog(null, "Added successfully.");
					               List_Artistad();
							}
						}
		               
		        }catch(HeadlessException | SQLException e){
		            JOptionPane.showMessageDialog(null,e);
		        }
				
			}
		});
		btn_addartistad.setBounds(35, 402, 108, 38);
		Image addbtn = new ImageIcon(this.getClass().getResource("/ADD.png")).getImage();
		btn_addartistad.setIcon(new ImageIcon(addbtn));
		inventoryartad.getContentPane().add(btn_addartistad);
		
		JButton btn_deleteartistad = new JButton("");
		btn_deleteartistad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_deleteartistad.setBounds(153, 451, 108, 38);
		Image delbtn = new ImageIcon(this.getClass().getResource("/DELETE.png")).getImage();
		btn_deleteartistad.setIcon(new ImageIcon(delbtn));
		inventoryartad.getContentPane().add(btn_deleteartistad);
		
		JButton btn_clearartistad = new JButton("");
		btn_clearartistad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_artistad.setText("");
				txt_artistagead.setText("");
			}
		});
		btn_clearartistad.setBounds(35, 451, 108, 38);
		Image clrbtn = new ImageIcon(this.getClass().getResource("/CLEAR.png")).getImage();
		btn_clearartistad.setIcon(new ImageIcon(clrbtn));
		inventoryartad.getContentPane().add(btn_clearartistad);
		
		JButton btn_updateartistad = new JButton("");
		btn_updateartistad.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_updateartistadActionPerformed(evt);
			}
			private void btn_updateartistadActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)tableartistad.getModel();
			        int SelectRowIndex = tableartistad.getSelectedRow();
			        String hold = model.getValueAt(SelectRowIndex, 0).toString();
			        String query = "UPDATE Artists set Artist_Name=?, Artist_Age=? WHERE Artist_ID='"+hold +"'";
			        try{
			            Connection con = DriverManager.getConnection(x.connectionUrl);
			            PreparedStatement pst = con.prepareStatement(query);
			               pst.setString(1, txt_artistad.getText());
			               pst.setString(2, txt_artistagead.getText());		           
			               int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?", "ALERT!", JOptionPane.YES_NO_OPTION); {
								if (input == JOptionPane.YES_OPTION) {
									  pst.executeUpdate();
						               JOptionPane.showMessageDialog(null, "Added successfully.");
						               List_Artistad();
								}
							}
			               	
			                txt_artistad.setText("");
			                txt_artistagead.setText("");				
			        }catch(HeadlessException | SQLException e){
			            JOptionPane.showMessageDialog(null,e);
			        }
				
			}
		});
		btn_updateartistad.setBounds(153, 402, 108, 38);
		Image updbtn = new ImageIcon(this.getClass().getResource("/UPDATE.png")).getImage();
		btn_updateartistad.setIcon(new ImageIcon(updbtn));
		inventoryartad.getContentPane().add(btn_updateartistad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(277, 169, 809, 382);
		inventoryartad.getContentPane().add(scrollPane);
		
		tableartistad = new JTable();
		tableartistad = new JTable();
		tableartistad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tableartistad.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Artist ID", "Artist Name", "Age"
	            }
	        ));
		
		tableartistad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableartistadsMouseClicked(evt);
			}

			private void tableartistadsMouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				 DefaultTableModel model = (DefaultTableModel)tableartistad.getModel();
				 int SelectRowIndex = tableartistad.getSelectedRow();
				txt_artistad.setText(model.getValueAt(SelectRowIndex, 1).toString());
			    txt_artistagead.setText(model.getValueAt(SelectRowIndex, 2).toString());
			}
		});
		
		scrollPane.setViewportView(tableartistad);
		
		JLabel lblartworksuser2 = new JLabel("ARTWORKS");
		lblartworksuser2.setBounds(475, 120, 201, 38);
		lblartworksuser2.setForeground(new Color(153, 102, 51));
		lblartworksuser2.setFont(new Font("Verdana", Font.BOLD, 25));
		inventoryartad.getContentPane().add(lblartworksuser2);
		
		JLabel lblartistsuser2 = new JLabel("ARTIST");
		lblartistsuser2.setBounds(845, 128, 116, 23);
		lblartistsuser2.setForeground(new Color(51, 0, 0));
		lblartistsuser2.setFont(new Font("Verdana", Font.BOLD, 25));
		inventoryartad.getContentPane().add(lblartistsuser2);
		
		JButton btn_backartistad = new JButton("");
		btn_backartistad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryartad.dispose();
				new DashboardAD();
			}
		});
		btn_backartistad.setBounds(10, 11, 50, 43);
		Image backbtn = new ImageIcon(this.getClass().getResource("/Back.png")).getImage();
		btn_backartistad.setIcon(new ImageIcon(backbtn));
		inventoryartad.getContentPane().add(btn_backartistad);
		
		txt_artistagead = new JTextField();
		txt_artistagead.setFont(new Font("Verdana", Font.PLAIN, 11));
		txt_artistagead.setColumns(10);
		txt_artistagead.setBounds(35, 300, 226, 38);
		inventoryartad.getContentPane().add(txt_artistagead);
		
		JLabel lblartistage = new JLabel("ARTIST AGE:");
		lblartistage.setForeground(new Color(51, 0, 0));
		lblartistage.setFont(new Font("Verdana", Font.BOLD, 20));
		lblartistage.setBounds(35, 266, 201, 23);
		inventoryartad.getContentPane().add(lblartistage);
		
		JLabel lblbg = new JLabel("");
		lblbg.setBounds(0, 0, 1140, 585);
		Image lgbg = new ImageIcon(this.getClass().getResource("/INVENTORY.png")).getImage();
		lblbg.setIcon(new ImageIcon(lgbg));
		inventoryartad.getContentPane().add(lblbg);
	}
}
