import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashboardAD {

	private JFrame dashboardad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardAD window = new DashboardAD();
					window.dashboardad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DashboardAD() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dashboardad = new JFrame();
		dashboardad.setBounds(100, 100, 1140, 624);
		dashboardad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dashboardad.getContentPane().setLayout(null);
		dashboardad.setVisible(true);
		
		JButton btn_manage = new JButton("");
		btn_manage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboardad.dispose();
				new ManageAD();
			}
		});
		Image mngbtn = new ImageIcon(this.getClass().getResource("/ICON MANAGE.png")).getImage();
		btn_manage.setIcon(new ImageIcon(mngbtn));
		btn_manage.setBounds(569, 289, 141, 130);
		dashboardad.getContentPane().add(btn_manage);
		
		JButton btn_logoutad = new JButton("");
		btn_logoutad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboardad.dispose();
				new LoginAdmin();
			}
		});
		Image logoutbtn = new ImageIcon(this.getClass().getResource("/ICON LOGOUT.png")).getImage();
		btn_logoutad.setIcon(new ImageIcon(logoutbtn));
		btn_logoutad.setBounds(495, 483, 104, 36);
		dashboardad.getContentPane().add(btn_logoutad);
		
		
		JButton btn_inventoryad = new JButton("");
		btn_inventoryad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboardad.dispose();
				new InventoryartworksAD();
			}
		});
		Image invenbtn = new ImageIcon(this.getClass().getResource("/ICON INVENTORY.png")).getImage();
		btn_inventoryad.setIcon(new ImageIcon(invenbtn));
		btn_inventoryad.setBounds(389, 289, 141, 130);
		dashboardad.getContentPane().add(btn_inventoryad);
		
		JLabel lblbg = new JLabel("");
		Image lgbg = new ImageIcon(this.getClass().getResource("/DASHBOARD.png")).getImage();
		lblbg.setIcon(new ImageIcon(lgbg));
		lblbg.setBounds(0, 0, 1140, 585);
		dashboardad.getContentPane().add(lblbg);
	}

}
